
package forms;

import javax.validation.Valid;

import domain.Brotherhood;

public class BrotherhoodForm {

	// Attributes
	@Valid
	private Brotherhood	brotherhood;
	private String		passwordCheck;
	private Boolean		termsConditions;


	// Constructors
	public BrotherhoodForm() {
		super();
	}

	public BrotherhoodForm(final Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
		this.passwordCheck = "";
		this.termsConditions = false;
	}

	// Getters and Setters
	public Brotherhood getBrotherhood() {
		return this.brotherhood;
	}

	public void setBrotherhood(final Brotherhood brotherhood) {
		this.brotherhood = brotherhood;
	}

	public String getPasswordCheck() {
		return this.passwordCheck;
	}

	public void setPasswordCheck(final String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public Boolean getTermsConditions() {
		return this.termsConditions;
	}

	public void setTermsConditions(final Boolean termsConditions) {
		this.termsConditions = termsConditions;
	}
}
