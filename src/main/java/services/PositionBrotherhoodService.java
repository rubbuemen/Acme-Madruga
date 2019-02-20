
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PositionBrotherhoodRepository;
import domain.PositionBrotherhood;

@Service
@Transactional
public class PositionBrotherhoodService {

	// Managed repository
	@Autowired
	private PositionBrotherhoodRepository	positionBrotherhoodRepository;


	// Supporting services

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

	public PositionBrotherhood save(final PositionBrotherhood positionBrotherhood) {
		Assert.notNull(positionBrotherhood);

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

	// Reconstruct methods

}
