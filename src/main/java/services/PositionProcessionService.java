
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PositionProcessionRepository;
import domain.PositionProcession;

@Service
@Transactional
public class PositionProcessionService {

	// Managed repository
	@Autowired
	private PositionProcessionRepository	positionProcessionRepository;


	// Supporting services

	// Simple CRUD methods
	public PositionProcession create() {
		PositionProcession result;

		result = new PositionProcession();

		return result;
	}

	public Collection<PositionProcession> findAll() {
		Collection<PositionProcession> result;

		result = this.positionProcessionRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public PositionProcession findOne(final int positionProcessionId) {
		Assert.isTrue(positionProcessionId != 0);

		PositionProcession result;

		result = this.positionProcessionRepository.findOne(positionProcessionId);
		Assert.notNull(result);

		return result;
	}

	public PositionProcession save(final PositionProcession positionProcession) {
		Assert.notNull(positionProcession);

		PositionProcession result;

		result = this.positionProcessionRepository.save(positionProcession);

		return result;
	}

	public void delete(final PositionProcession positionProcession) {
		Assert.notNull(positionProcession);
		Assert.isTrue(positionProcession.getId() != 0);
		Assert.isTrue(this.positionProcessionRepository.exists(positionProcession.getId()));

		this.positionProcessionRepository.delete(positionProcession);
	}

	// Other business methods

	// Reconstruct methods

}
