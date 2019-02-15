
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Enrolment extends DomainEntity {

	// Attributes
	private Date	momentRegistered;
	private Date	momentDropOut;


	// Getters and Setters
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Past
	public Date getMomentRegistered() {
		return this.momentRegistered;
	}

	public void setMomentRegistered(final Date momentRegistered) {
		this.momentRegistered = momentRegistered;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Past
	public Date getMomentDropOut() {
		return this.momentDropOut;
	}

	public void setMomentDropOut(final Date momentDropOut) {
		this.momentDropOut = momentDropOut;
	}

}
