
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BoxRepository;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class BoxService {

	// Managed repository
	@Autowired
	private BoxRepository	boxRepository;


	// Supporting services

	// Simple CRUD methods
	public Box create() {
		Box result;

		result = new Box();

		return result;
	}

	public Collection<Box> findAll() {
		Collection<Box> result;

		result = this.boxRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Box findOne(final int boxId) {
		Assert.isTrue(boxId != 0);

		Box result;

		result = this.boxRepository.findOne(boxId);
		Assert.notNull(result);

		return result;
	}

	public Box save(final Box box) {
		Assert.notNull(box);

		Box result;

		result = this.boxRepository.save(box);

		return result;
	}

	public void delete(final Box box) {
		Assert.notNull(box);
		Assert.isTrue(box.getId() != 0);
		Assert.isTrue(this.boxRepository.exists(box.getId()));

		this.boxRepository.delete(box);
	}

	// Other business methods
	public Box createForSystemBox() {
		Box result;

		result = new Box();
		final Collection<Message> messages = new HashSet<>();

		result.setMessages(messages);
		result.setIsSystemBox(true);

		return result;
	}

	public Box saveForSystemBox(final Box box) {
		Assert.notNull(box);

		Box result;

		result = this.boxRepository.save(box);

		return result;
	}

	// Reconstruct methods

}
