
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProcessionRepository;
import domain.Procession;

@Service
@Transactional
public class ProcessionService {

	// Managed repository
	@Autowired
	private ProcessionRepository	processionRepository;


	// Supporting services

	// Simple CRUD methods
	public Procession create() {
		Procession result;

		result = new Procession();

		return result;
	}

	public Collection<Procession> findAll() {
		Collection<Procession> result;

		result = this.processionRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Procession findOne(final int processionId) {
		Assert.isTrue(processionId != 0);

		Procession result;

		result = this.processionRepository.findOne(processionId);
		Assert.notNull(result);

		return result;
	}

	public Procession save(final Procession procession) {
		Assert.notNull(procession);

		Procession result;

		result = this.processionRepository.save(procession);

		return result;
	}

	public void delete(final Procession procession) {
		Assert.notNull(procession);
		Assert.isTrue(procession.getId() != 0);
		Assert.isTrue(this.processionRepository.exists(procession.getId()));

		this.processionRepository.delete(procession);
	}

	// Other business methods

	// Reconstruct methods

}
