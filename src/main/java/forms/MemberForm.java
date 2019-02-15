
package forms;

import javax.validation.Valid;

import domain.Member;

public class MemberForm {

	// Attributes
	@Valid
	private Member	member;
	private String	passwordCheck;
	private Boolean	termsConditions;


	// Constructors
	public MemberForm() {
		super();
	}

	public MemberForm(final Member member) {
		this.member = member;
		this.passwordCheck = "";
		this.termsConditions = false;
	}

	// Getters and Setters
	public Member getMember() {
		return this.member;
	}

	public void setMember(final Member member) {
		this.member = member;
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
