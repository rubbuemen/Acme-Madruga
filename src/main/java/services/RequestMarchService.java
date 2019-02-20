
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestMarchRepository;
import domain.RequestMarch;

@Service
@Transactional
public class RequestMarchService {

	// Managed repository
	@Autowired
	private RequestMarchRepository	requestMarchRepository;


	// Supporting services

	// Simple CRUD methods
	public RequestMarch create() {
		RequestMarch result;

		result = new RequestMarch();

		return result;
	}

	public Collection<RequestMarch> findAll() {
		Collection<RequestMarch> result;

		result = this.requestMarchRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public RequestMarch findOne(final int requestMarchId) {
		Assert.isTrue(requestMarchId != 0);

		RequestMarch result;

		result = this.requestMarchRepository.findOne(requestMarchId);
		Assert.notNull(result);

		return result;
	}

	public RequestMarch save(final RequestMarch requestMarch) {
		Assert.notNull(requestMarch);

		RequestMarch result;

		result = this.requestMarchRepository.save(requestMarch);

		return result;
	}

	public void delete(final RequestMarch requestMarch) {
		Assert.notNull(requestMarch);
		Assert.isTrue(requestMarch.getId() != 0);
		Assert.isTrue(this.requestMarchRepository.exists(requestMarch.getId()));

		this.requestMarchRepository.delete(requestMarch);
	}

	// Other business methods

	// Reconstruct methods

}
