
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AreaRepository;
import domain.Area;

@Service
@Transactional
public class AreaService {

	// Managed repository
	@Autowired
	private AreaRepository	areaRepository;


	// Supporting services

	// Simple CRUD methods
	public Area create() {
		Area result;

		result = new Area();

		return result;
	}

	public Collection<Area> findAll() {
		Collection<Area> result;

		result = this.areaRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Area findOne(final int areaId) {
		Assert.isTrue(areaId != 0);

		Area result;

		result = this.areaRepository.findOne(areaId);
		Assert.notNull(result);

		return result;
	}

	public Area save(final Area area) {
		Assert.notNull(area);

		Area result;

		result = this.areaRepository.save(area);

		return result;
	}

	public void delete(final Area area) {
		Assert.notNull(area);
		Assert.isTrue(area.getId() != 0);
		Assert.isTrue(this.areaRepository.exists(area.getId()));

		this.areaRepository.delete(area);
	}

	// Other business methods

	// Reconstruct methods

}
