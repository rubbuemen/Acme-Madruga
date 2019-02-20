
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FloatRepository;
import domain.Float;

@Service
@Transactional
public class FloatService {

	// Managed repository
	@Autowired
	private FloatRepository	floatRepository;


	// Supporting services

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

	public Float save(final Float floatE) {
		Assert.notNull(floatE);

		Float result;

		result = this.floatRepository.save(floatE);

		return result;
	}

	public void delete(final Float floatE) {
		Assert.notNull(floatE);
		Assert.isTrue(floatE.getId() != 0);
		Assert.isTrue(this.floatRepository.exists(floatE.getId()));

		this.floatRepository.delete(floatE);
	}

	// Other business methods

	// Reconstruct methods

}
