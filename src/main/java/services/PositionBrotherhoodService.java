
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PositionBrotherhoodRepository;
import domain.Actor;
import domain.Brotherhood;
import domain.PositionBrotherhood;

@Service
@Transactional
public class PositionBrotherhoodService {

	// Managed repository
	@Autowired
	private PositionBrotherhoodRepository	positionBrotherhoodRepository;

	// Supporting services
	@Autowired
	ActorService							actorService;


	// Simple CRUD methods
	public PositionBrotherhood create() {
		PositionBrotherhood result;

		result = new PositionBrotherhood();

		return result;
	}

	public Collection<PositionBrotherhood> findAll() {
		Collection<PositionBrotherhood> result;

		result = this.positionBrotherhoodRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public PositionBrotherhood findOne(final int positionBrotherhoodId) {
		Assert.isTrue(positionBrotherhoodId != 0);

		PositionBrotherhood result;

		result = this.positionBrotherhoodRepository.findOne(positionBrotherhoodId);
		Assert.notNull(result);

		return result;
	}

	// R10.3: When a member is enrolled, a position must be selected
	public PositionBrotherhood save(final PositionBrotherhood positionBrotherhood) {
		Assert.notNull(positionBrotherhood);

		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginBrotherhood(actorLogged);

		PositionBrotherhood result;

		result = this.positionBrotherhoodRepository.save(positionBrotherhood);

		return result;
	}

	public void delete(final PositionBrotherhood positionBrotherhood) {
		Assert.notNull(positionBrotherhood);
		Assert.isTrue(positionBrotherhood.getId() != 0);
		Assert.isTrue(this.positionBrotherhoodRepository.exists(positionBrotherhood.getId()));

		this.positionBrotherhoodRepository.delete(positionBrotherhood);
	}

	// Other business methods
	public PositionBrotherhood findPositionBrotherhoodByMemberIdBrotherhoodLogged(final int memberId) {
		Assert.isTrue(memberId != 0);

		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginBrotherhood(actorLogged);

		PositionBrotherhood result;

		final Brotherhood brotherhoodLogged = (Brotherhood) actorLogged;

		result = this.positionBrotherhoodRepository.findPositionBrotherhoodByMemberIdBrotherhoodId(memberId, brotherhoodLogged.getId());
		Assert.notNull(result);

		return result;
	}

	// Reconstruct methods

}
