
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
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
	private Date	momentOrganise;
	private Boolean	isFinalMode;
	private Integer	maxRows;
	private Integer	maxColumns;


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
	public Date getMomentOrganise() {
		return this.momentOrganise;
	}

	public void setMomentOrganise(final Date momentOrganise) {
		this.momentOrganise = momentOrganise;
	}

	@NotNull
	public Boolean getIsFinalMode() {
		return this.isFinalMode;
	}

	public void setIsFinalMode(final Boolean isFinalMode) {
		this.isFinalMode = isFinalMode;
	}

	@NotNull
	@Min(1)
	public Integer getMaxRows() {
		return this.maxRows;
	}

	public void setMaxRows(final Integer maxRows) {
		this.maxRows = maxRows;
	}

	@NotNull
	@Min(1)
	public Integer getMaxColumns() {
		return this.maxColumns;
	}

	public void setMaxColumns(final Integer maxColumns) {
		this.maxColumns = maxColumns;
	}


	// Relationships
	private Collection<Float>			floats;
	private Collection<RequestMarch>	requestsMarch;


	@NotEmpty
	@Valid
	@EachNotNull
	@ManyToMany(fetch = FetchType.EAGER)
	public Collection<Float> getFloats() {
		return this.floats;
	}

	public void setFloats(final Collection<Float> floats) {
		this.floats = floats;
	}

	@Valid
	@EachNotNull
	@OneToMany
	public Collection<RequestMarch> getRequestsMarch() {
		return this.requestsMarch;
	}

	public void setRequestsMarch(final Collection<RequestMarch> requestsMarch) {
		this.requestsMarch = requestsMarch;
	}

}
