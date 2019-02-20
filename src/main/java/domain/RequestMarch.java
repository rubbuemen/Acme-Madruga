
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
public class RequestMarch extends DomainEntity {

	// Attributes
	private String	status;
	private String	rejectReason;


	// Getters and Setters
	@NotBlank
	@Pattern(regexp = "^PENDING|APPROVED|REJECTED$")
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getRejectReason() {
		return this.rejectReason;
	}

	public void setRejectReason(final String rejectReason) {
		this.rejectReason = rejectReason;
	}


	// Relationships
	private PositionProcession	positionProcession;


	@Valid
	@OneToOne(optional = true)
	public PositionProcession getPositionProcession() {
		return this.positionProcession;
	}

	public void setPositionProcession(final PositionProcession positionProcession) {
		this.positionProcession = positionProcession;
	}

}
