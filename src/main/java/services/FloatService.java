
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FloatRepository;
import domain.Actor;
import domain.Brotherhood;
import domain.Float;

@Service
@Transactional
public class FloatService {

	// Managed repository
	@Autowired
	private FloatRepository		floatRepository;

	// Supporting services
	@Autowired
	private ActorService		actorService;

	@Autowired
	private BrotherhoodService	brotherhoodService;


	// Simple CRUD methods
	public Float create() {
		Float result;

		result = new Float();

		return result;
	}

	public Collection<Float> findAll() {
		Collection<Float> result;

		result = this.floatRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Float findOne(final int floatId) {
		Assert.isTrue(floatId != 0);

		Float result;

		result = this.floatRepository.findOne(floatId);
		Assert.notNull(result);

		return result;
	}

	public Float save(final Float floatE, final Brotherhood brotherhood) {
		Assert.notNull(floatE);

		Float result;

		result = this.floatRepository.save(floatE);

		final Collection<Float> floatsBrotherhoodLogged = brotherhood.getFloats();
		floatsBrotherhoodLogged.add(result);
		brotherhood.setFloats(floatsBrotherhoodLogged);
		this.brotherhoodService.save(brotherhood);

		return result;
	}

	public void delete(final Float floatE) {
		Assert.notNull(floatE);
		Assert.isTrue(floatE.getId() != 0);
		Assert.isTrue(this.floatRepository.exists(floatE.getId()));

		this.floatRepository.delete(floatE);
	}

	// Other business methods
	// R8.2
	public Collection<Float> findFloatsByBrotherhoodId(final int brotherhoodId) {
		Assert.isTrue(brotherhoodId != 0);

		Collection<Float> result;

		result = this.floatRepository.findFloatsByBrotherhoodId(brotherhoodId);
		Assert.notNull(result);

		return result;
	}

	// R10.1
	public Collection<Float> findFloatsByBrotherhoodLogged() {
		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginBrotherhood(actorLogged);

		Collection<Float> result;

		final Brotherhood brotherhoodLogged = (Brotherhood) actorLogged;

		result = brotherhoodLogged.getFloats();
		Assert.notNull(result);

		return result;
	}

	// Reconstruct methods

}
