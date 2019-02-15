
package forms;

import javax.validation.Valid;

import domain.Administrator;

public class AdministratorForm {

	// Attributes
	@Valid
	private Administrator	administrator;
	private String			passwordCheck;
	private Boolean			termsConditions;


	// Constructors
	public AdministratorForm() {
		super();
	}

	public AdministratorForm(final Administrator administrator) {
		this.administrator = administrator;
		this.passwordCheck = "";
		this.termsConditions = false;
	}

	// Getters and Setters
	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(final Administrator administrator) {
		this.administrator = administrator;
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
