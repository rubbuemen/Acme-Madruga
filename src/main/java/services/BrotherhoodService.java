
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.BrotherhoodRepository;
import security.Authority;
import security.UserAccount;
import domain.Box;
import domain.Brotherhood;
import domain.Enrolment;
import domain.Float;
import domain.Procession;
import forms.BrotherhoodForm;

@Service
@Transactional
public class BrotherhoodService {

	// Managed repository
	@Autowired
	private BrotherhoodRepository	brotherhoodRepository;

	// Supporting services
	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private ActorService			actorService;


	// Simple CRUD methods
	// R8.1, R9.1
	public Brotherhood create() {
		Brotherhood result;

		result = new Brotherhood();
		final Collection<Float> floats = new HashSet<>();
		final Collection<Procession> processions = new HashSet<>();
		final Collection<Enrolment> enrolments = new HashSet<>();
		final Collection<Box> boxes = new HashSet<>();
		final UserAccount userAccount = this.userAccountService.create();
		final Authority auth = new Authority();

		auth.setAuthority(Authority.BROTHERHOOD);
		userAccount.addAuthority(auth);
		result.setFloats(floats);
		result.setProcessions(processions);
		result.setEnrolments(enrolments);
		result.setBoxes(boxes);
		result.setUserAccount(userAccount);
		result.setIsSpammer(false);

		return result;
	}
	public Collection<Brotherhood> findAll() {
		Collection<Brotherhood> result;

		result = this.brotherhoodRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Brotherhood findOne(final int brotherhoodId) {
		Assert.isTrue(brotherhoodId != 0);

		Brotherhood result;

		result = this.brotherhoodRepository.findOne(brotherhoodId);
		Assert.notNull(result);

		return result;
	}

	public Brotherhood save(final Brotherhood brotherhood) {
		Assert.notNull(brotherhood);

		Brotherhood result;

		result = (Brotherhood) this.actorService.save(brotherhood);
		result = this.brotherhoodRepository.save(result);

		return result;
	}

	public void delete(final Brotherhood brotherhood) {
		Assert.notNull(brotherhood);
		Assert.isTrue(brotherhood.getId() != 0);
		Assert.isTrue(this.brotherhoodRepository.exists(brotherhood.getId()));

		this.brotherhoodRepository.delete(brotherhood);
	}


	// Other business methods

	// Reconstruct methods
	@Autowired
	private Validator	validator;


	public BrotherhoodForm reconstruct(final BrotherhoodForm brotherhoodForm, final BindingResult binding) {

		BrotherhoodForm result;
		final Brotherhood brotherhoodF = brotherhoodForm.getBrotherhood();

		if (brotherhoodF.getId() == 0) {
			UserAccount userAccount;

			final Collection<Float> floats = new HashSet<>();
			final Collection<Procession> processions = new HashSet<>();
			final Collection<Enrolment> enrolments = new HashSet<>();
			final Collection<Box> boxes = new HashSet<>();
			userAccount = brotherhoodF.getUserAccount();
			final Authority auth = new Authority();
			auth.setAuthority(Authority.BROTHERHOOD);
			userAccount.addAuthority(auth);

			brotherhoodF.setUserAccount(userAccount);
			brotherhoodF.setIsSpammer(false);
			brotherhoodF.setFloats(floats);
			brotherhoodF.setProcessions(processions);
			brotherhoodF.setEnrolments(enrolments);
			brotherhoodF.setBoxes(boxes);

		} else {
			final Brotherhood brotherhood = this.brotherhoodRepository.findOne(brotherhoodF.getId());
			brotherhoodF.setId(brotherhood.getId());
			brotherhoodF.setVersion(brotherhood.getVersion());
			brotherhoodF.setUserAccount(brotherhood.getUserAccount());
			brotherhoodF.setIsSpammer(brotherhood.getIsSpammer());
			brotherhoodF.setFloats(brotherhood.getFloats());
			brotherhoodF.setProcessions(brotherhood.getProcessions());
			brotherhoodF.setEnrolments(brotherhood.getEnrolments());
			brotherhoodF.setBoxes(brotherhood.getBoxes());
		}

		brotherhoodForm.setBrotherhood(brotherhoodF);
		result = brotherhoodForm;

		this.validator.validate(result, binding);

		return result;

	}

}
