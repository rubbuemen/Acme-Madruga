
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;
import domain.Procession;

@Service
@Transactional
public class FinderService {

	// Managed repository
	@Autowired
	private FinderRepository	finderRepository;


	// Supporting services

	// Simple CRUD methods
	public Finder create() {
		Finder result;

		result = new Finder();

		final Collection<Procession> processions = new HashSet<>();

		result.setProcessions(processions);

		return result;
	}

	public Collection<Finder> findAll() {
		Collection<Finder> result;

		result = this.finderRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Finder findOne(final int finderId) {
		Assert.isTrue(finderId != 0);

		Finder result;

		result = this.finderRepository.findOne(finderId);
		Assert.notNull(result);

		return result;
	}

	public Finder save(final Finder finder) {
		Assert.notNull(finder);

		Finder result;

		if (finder.getId() != 0) {
			final Date searchMoment = new Date(System.currentTimeMillis() - 1);
			finder.setSearchMoment(searchMoment);
		}

		result = this.finderRepository.save(finder);

		return result;
	}

	public void delete(final Finder finder) {
		Assert.notNull(finder);
		Assert.isTrue(finder.getId() != 0);
		Assert.isTrue(this.finderRepository.exists(finder.getId()));

		this.finderRepository.delete(finder);
	}

	// Other business methods

	// Reconstruct methods

}
