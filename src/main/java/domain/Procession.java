
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.springframework.format.annotation.DateTimeFormat;

import cz.jirutka.validator.collection.constraints.EachNotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Procession extends DomainEntity {

	// Attributes
	private String	ticker;
	private String	title;
	private String	description;
	private Date	momentOrganize;
	private Boolean	isFinalMode;


	// Getters and Setters
	@NotBlank
	@Pattern(regexp = "^\\d{2}[0-1]\\d[0-3]\\d[-][A-Z]{5}$")
	@Column(unique = true)
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Future
	public Date getMomentOrganize() {
		return this.momentOrganize;
	}

	public void setMomentOrganize(final Date momentOrganize) {
		this.momentOrganize = momentOrganize;
	}

	@NotNull
	public Boolean getIsFinalMode() {
		return this.isFinalMode;
	}

	public void setIsFinalMode(final Boolean isFinalMode) {
		this.isFinalMode = isFinalMode;
	}


	// Relationships
	private Collection<PositionProcession>	positionsProcession;


	@Valid
	@EachNotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<PositionProcession> getPositionsProcession() {
		return this.positionsProcession;
	}

	public void setPositionsProcession(final Collection<PositionProcession> positionsProcession) {
		this.positionsProcession = positionsProcession;
	}

}
