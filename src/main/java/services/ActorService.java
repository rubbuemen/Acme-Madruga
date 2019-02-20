
package services;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Box;

@Service
@Transactional
public class ActorService {

	// Managed repository
	@Autowired
	private ActorRepository				actorRepository;

	// Supporting services
	@Autowired
	private UserAccountService			userAccountService;

	@Autowired
	private BoxService					boxService;

	@Autowired
	private SystemConfigurationService	systemConfigurationService;


	// Simple CRUD methods
	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = this.actorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Actor findOne(final int actorId) {
		Assert.isTrue(actorId != 0);

		Actor result;

		result = this.actorRepository.findOne(actorId);
		Assert.notNull(result);

		return result;
	}

	// R9.1, R9.2
	public Actor save(final Actor actor) {
		Assert.notNull(actor);

		Actor result;

		Assert.isTrue(actor.getUserAccount().getStatusAccount());

		if (actor.getId() == 0) {
			final UserAccount userAccount = this.userAccountService.save(actor.getUserAccount());
			Box inBox = this.boxService.createForSystemBox();
			inBox.setName("In Box");
			inBox.setIsSystemBox(true);
			inBox = this.boxService.saveForSystemBox(inBox);
			Box outBox = this.boxService.createForSystemBox();
			outBox.setName("Out Box");
			outBox.setIsSystemBox(true);
			outBox = this.boxService.saveForSystemBox(outBox);
			Box trashBox = this.boxService.createForSystemBox();
			trashBox.setName("Trash Box");
			trashBox.setIsSystemBox(true);
			trashBox = this.boxService.saveForSystemBox(trashBox);
			Box spamBox = this.boxService.createForSystemBox();
			spamBox.setName("Spam Box");
			spamBox.setIsSystemBox(true);
			spamBox = this.boxService.saveForSystemBox(spamBox);
			final Box notificationBox = this.boxService.createForSystemBox();
			spamBox.setName("Notification Box");
			spamBox.setIsSystemBox(true);
			spamBox = this.boxService.saveForSystemBox(spamBox);
			final Collection<Box> systemBoxes = new HashSet<>();
			Collections.addAll(systemBoxes, inBox, outBox, trashBox, spamBox, notificationBox);
			actor.setUserAccount(userAccount);
			actor.setBoxes(systemBoxes);
		} else {
			final Actor actorLogged = this.findActorLogged();
			Assert.notNull(actorLogged);
			Assert.isTrue(actor.equals(actorLogged));
		}

		if (actor.getPhoneNumber() != null) {
			String phoneNumber = actor.getPhoneNumber();
			final String phoneCountryCode = this.systemConfigurationService.getConfiguration().getPhoneCountryCode();
			if (!actor.getPhoneNumber().startsWith("+"))
				phoneNumber = phoneCountryCode + " " + phoneNumber;
			actor.setPhoneNumber(phoneNumber);
		}

		result = this.actorRepository.save(actor);

		return result;
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(this.actorRepository.exists(actor.getId()));

		this.actorRepository.delete(actor);
	}

	// Other business methods

	public Actor findActorLogged() {
		Actor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.actorRepository.findActorByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public Actor getSystemActor() {
		Actor result;

		result = this.actorRepository.getSystemActor();
		Assert.notNull(result);

		return result;
	}

	public void checkUserLoginBrotherhood(final Actor actor) {
		final Authority auth = new Authority();
		auth.setAuthority(Authority.BROTHERHOOD);
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		Assert.isTrue(authorities.contains(auth));
	}

	public void checkUserLoginMember(final Actor actor) {
		final Authority auth = new Authority();
		auth.setAuthority(Authority.MEMBER);
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		Assert.isTrue(authorities.contains(auth));
	}

	public void checkUserLoginAdministrator(final Actor actor) {
		final Authority auth = new Authority();
		auth.setAuthority(Authority.ADMIN);
		final Collection<Authority> authorities = actor.getUserAccount().getAuthorities();
		Assert.isTrue(authorities.contains(auth));
	}

	// Other business methods

	// Reconstruct methods

}