
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.EnrolmentRepository;
import domain.Actor;
import domain.Brotherhood;
import domain.Enrolment;
import domain.Member;
import domain.PositionBrotherhood;
import domain.Procession;
import domain.RequestMarch;

@Service
@Transactional
public class EnrolmentService {

	// Managed repository
	@Autowired
	private EnrolmentRepository			enrolmentRepository;

	// Supporting services
	@Autowired
	private ActorService				actorService;

	@Autowired
	private PositionBrotherhoodService	positionBrotherhoodService;

	@Autowired
	private BrotherhoodService			brotherhoodService;

	@Autowired
	private RequestMarchService			requestMarchService;

	@Autowired
	private MemberService				memberService;

	@Autowired
	private ProcessionService			processionService;


	// Simple CRUD methods
	public Enrolment create() {
		Enrolment result;

		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginMember(actorLogged);

		final Member memberLogged = (Member) actorLogged;

		result = new Enrolment();

		result.setMember(memberLogged);

		return result;
	}

	public Collection<Enrolment> findAll() {
		Collection<Enrolment> result;

		result = this.enrolmentRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Enrolment findOne(final int enrolmentId) {
		Assert.isTrue(enrolmentId != 0);

		Enrolment result;

		result = this.enrolmentRepository.findOne(enrolmentId);
		Assert.notNull(result);

		return result;
	}

	// R10.3 (save for brotherhood)
	public Enrolment save(final Enrolment enrolment) {
		Assert.notNull(enrolment);

		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);

		Enrolment result;

		if (actorLogged instanceof Brotherhood) {
			this.actorService.checkUserLoginBrotherhood(actorLogged);

			final Brotherhood brotherhoodLogged = (Brotherhood) actorLogged;

			Assert.notNull(brotherhoodLogged.getArea(), "You can not enroll members until you selected an area");

			final Date momentRegistered = new Date(System.currentTimeMillis() - 1);
			enrolment.setMomentRegistered(momentRegistered);

			// When a member is enrolled, a position must be selected
			final PositionBrotherhood positionBrotherhood = this.positionBrotherhoodService.save(enrolment.getPositionBrotherhood());
			Assert.notNull(positionBrotherhood);
			enrolment.setPositionBrotherhood(positionBrotherhood);
		}

		result = this.enrolmentRepository.save(enrolment);

		return result;
	}

	public Enrolment save(final Enrolment enrolment, final Brotherhood brotherhood) {
		Assert.notNull(enrolment);
		Assert.notNull(brotherhood);

		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginMember(actorLogged);

		Enrolment result;

		enrolment.setBrotherhood(brotherhood);

		result = this.enrolmentRepository.save(enrolment);

		return result;
	}

	public void delete(final Enrolment enrolment) {
		// En principio nunca se borrará un enrolment
		Assert.notNull(enrolment);
		Assert.isTrue(enrolment.getId() != 0);
		Assert.isTrue(this.enrolmentRepository.exists(enrolment.getId()));

		this.enrolmentRepository.delete(enrolment);
	}

	// Other business methods
	// R10.3
	public Collection<Enrolment> findEnrolmentsPendingByBrotherhoodLogged() {
		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginBrotherhood(actorLogged);

		Collection<Enrolment> result;

		final Brotherhood brotherhoodLogged = (Brotherhood) actorLogged;

		result = this.enrolmentRepository.findEnrolmentsPendingByBrotherhoodId(brotherhoodLogged.getId());
		Assert.notNull(result);

		return result;
	}

	// R10.3
	public void removeMemberOfBrotherhood(final int memberId) {
		Enrolment result;

		final Actor brotherhoodLogged = this.actorService.findActorLogged();

		result = this.findEnrolmentMemberBrotherhoodLogged(memberId);

		final Collection<RequestMarch> requestsMarchMemberBrotherhoodLogged = this.requestMarchService.findRequestsMarchMemberId(memberId, brotherhoodLogged.getId());

		final Member memberLogged = (Member) this.actorService.findOne(memberId);
		final Collection<RequestMarch> requestsMember = memberLogged.getRequestsMarch();

		requestsMember.removeAll(requestsMarchMemberBrotherhoodLogged);
		memberLogged.setRequestsMarch(requestsMarchMemberBrotherhoodLogged);
		this.memberService.saveAuxiliar(memberLogged);
		for (final RequestMarch rm : requestsMarchMemberBrotherhoodLogged) {
			final Procession pro = this.processionService.findProcessionByRequestMarchId(rm.getId());
			final Collection<RequestMarch> requests = pro.getRequestsMarch();
			requests.remove(rm);
			pro.setRequestsMarch(requests);
			this.processionService.saveForRequestMarch(pro);
			this.requestMarchService.deleteAuxiliar(rm);
		}

		final Date momentDropOut = new Date(System.currentTimeMillis() - 1);
		result.setMomentDropOut(momentDropOut);

		this.enrolmentRepository.save(result);
	}

	// R11.2
	public void dropOutOfBrotherhood(final int brotherhoodId) {
		Enrolment result;

		final Member memberLogged = (Member) this.actorService.findActorLogged();

		result = this.findEnrolmentBrotherhoodMemberLogged(brotherhoodId);

		final Collection<RequestMarch> requestsMarchMemberBrotherhoodLogged = this.requestMarchService.findRequestsMarchMemberId(memberLogged.getId(), brotherhoodId);

		final Collection<RequestMarch> requestsMember = memberLogged.getRequestsMarch();

		requestsMember.removeAll(requestsMarchMemberBrotherhoodLogged);
		memberLogged.setRequestsMarch(requestsMarchMemberBrotherhoodLogged);
		this.memberService.saveAuxiliar(memberLogged);
		for (final RequestMarch rm : requestsMarchMemberBrotherhoodLogged) {
			final Procession pro = this.processionService.findProcessionByRequestMarchId(rm.getId());
			final Collection<RequestMarch> requests = pro.getRequestsMarch();
			requests.remove(rm);
			pro.setRequestsMarch(requests);
			this.processionService.saveForRequestMarch(pro);
			this.requestMarchService.deleteAuxiliar(rm);
		}

		final Date momentDropOut = new Date(System.currentTimeMillis() - 1);
		result.setMomentDropOut(momentDropOut);

		this.enrolmentRepository.save(result);
	}

	public Enrolment findEnrolmentMemberBrotherhoodLogged(final int memberId) {
		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginBrotherhood(actorLogged);

		Enrolment result;

		final Brotherhood brotherhoodLogged = (Brotherhood) actorLogged;

		result = this.enrolmentRepository.findEnrolmentOfBrotherhoodByMemberId(brotherhoodLogged.getId(), memberId);
		Assert.notNull(result);

		return result;
	}

	public Enrolment findEnrolmentBrotherhoodMemberLogged(final int brotherhoodId) {
		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginMember(actorLogged);

		Enrolment result;

		final Member memberLogged = (Member) actorLogged;

		result = this.enrolmentRepository.findEnrolmentOfBrotherhoodByMemberId(brotherhoodId, memberLogged.getId());
		Assert.notNull(result);

		return result;
	}

	public Enrolment findEnrolmentPenddingBrotherhoodLogged(final int enrolmentId) {
		Assert.isTrue(enrolmentId != 0);

		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginBrotherhood(actorLogged);

		final Brotherhood brotherhoodOwner = this.brotherhoodService.findBrotherhoodByEnrolmentId(enrolmentId);
		Assert.isTrue(brotherhoodOwner.equals(actorLogged), "The logged actor is not the owner of this entity");

		Enrolment result;

		result = this.enrolmentRepository.findOne(enrolmentId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Enrolment> findEnrolmentsByBrotherhoodId(final int brotherhoodId) {
		Assert.isTrue(brotherhoodId != 0);

		final Actor actorLogged = this.actorService.findActorLogged();
		Assert.notNull(actorLogged);
		this.actorService.checkUserLoginMember(actorLogged);

		Collection<Enrolment> result;

		final Collection<Member> memberOwner = this.memberService.findMembersByBrotherhoodIdAll(brotherhoodId);
		Assert.isTrue(memberOwner.contains(actorLogged), "The logged actor is not the owner of this entity");

		result = this.enrolmentRepository.findEnrolmentsOfBrotherhoodByMemberId(brotherhoodId, actorLogged.getId());

		return result;
	}


	// Reconstruct methods
	@Autowired
	private Validator	validator;


	public Enrolment reconstruct(final Enrolment enrolment, final BindingResult binding) {
		Enrolment result;

		if (enrolment.getId() == 0)
			//Esto hay que verlo
			result = enrolment;
		else {
			result = this.enrolmentRepository.findOne(enrolment.getId());
			result.setPositionBrotherhood(enrolment.getPositionBrotherhood());
		}

		this.validator.validate(result, binding);

		return result;
	}

}
