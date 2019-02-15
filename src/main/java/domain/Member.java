
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import cz.jirutka.validator.collection.constraints.EachNotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Member extends Actor {

	// Relationships
	private Collection<Enrolment>		enrolments;
	private Collection<RequestMarch>	requetsMarch;
	private Finder						finder;


	@EachNotNull
	@Valid
	@OneToMany(mappedBy = "member")
	public Collection<Enrolment> getEnrolments() {
		return this.enrolments;
	}

	public void setEnrolments(final Collection<Enrolment> enrolments) {
		this.enrolments = enrolments;
	}

	@EachNotNull
	@Valid
	@OneToMany
	public Collection<RequestMarch> getRequetsMarch() {
		return this.requetsMarch;
	}

	public void setRequetsMarch(final Collection<RequestMarch> requetsMarch) {
		this.requetsMarch = requetsMarch;
	}

	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Finder getFinder() {
		return this.finder;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

}
