
package services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SystemConfigurationRepository;
import domain.Procession;
import domain.RequestMarch;
import domain.SystemConfiguration;

@Service
@Transactional
public class SystemConfigurationService {

	// Managed repository
	@Autowired
	private SystemConfigurationRepository	systemConfigurationRepository;

	// Supporting services
	@Autowired
	private ProcessionService				processionService;


	// Simple CRUD methods
	public SystemConfiguration create() {
		SystemConfiguration result;

		result = new SystemConfiguration();

		return result;
	}

	public Collection<SystemConfiguration> findAll() {
		Collection<SystemConfiguration> result;

		result = this.systemConfigurationRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public SystemConfiguration findOne(final int systemConfigurationId) {
		Assert.isTrue(systemConfigurationId != 0);

		SystemConfiguration result;

		result = this.systemConfigurationRepository.findOne(systemConfigurationId);
		Assert.notNull(result);

		return result;
	}

	public SystemConfiguration save(final SystemConfiguration systemConfiguration) {
		Assert.notNull(systemConfiguration);

		SystemConfiguration result;

		result = this.systemConfigurationRepository.save(systemConfiguration);

		return result;
	}

	public void delete(final SystemConfiguration systemConfiguration) {
		Assert.notNull(systemConfiguration);
		Assert.isTrue(systemConfiguration.getId() != 0);
		Assert.isTrue(this.systemConfigurationRepository.exists(systemConfiguration.getId()));

		this.systemConfigurationRepository.delete(systemConfiguration);
	}

	// Other business methods
	public SystemConfiguration getConfiguration() {
		SystemConfiguration result;

		result = this.systemConfigurationRepository.getConfiguration();
		Assert.notNull(result);

		return result;
	}

	// R10.6: The system must suggest a good position automatically
	public Map<Integer, Integer> suggestedRowColumn(final int requestMarchId) {
		final Map<Integer, Integer> result = new HashMap<>();

		final Procession procession = this.processionService.findProcessionByRequestMarchId(requestMarchId);
		final Collection<RequestMarch> requestsMarchProcession = procession.getRequestsMarch();

		Integer suggestedRow = 0;
		Integer suggestedColumn = 0;

		final boolean[][] position = new boolean[procession.getMaxRows()][procession.getMaxColumns()];

		for (final RequestMarch rm : requestsMarchProcession)
			if (rm.getStatus().equals("APPROVED") && rm.getPositionRow() != null && rm.getPositionColumn() != null) {
				final Integer r = rm.getPositionRow() - 1;
				final Integer c = rm.getPositionColumn() - 1;
				position[r][c] = true;
			}

		breakloop: for (int r = 0; r < position.length; r++)
			for (int c = 0; c < position[r].length; c++)
				if (position[r][c] == false) {
					suggestedRow = r;
					suggestedColumn = c;
					break breakloop;
				}

		result.put(suggestedRow + 1, suggestedColumn + 1);

		return result;
	}

	// Reconstruct methods

}
