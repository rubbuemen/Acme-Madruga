
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {

	// Getters and Setters
	@Override
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9 ]*[<]?\\w+[@][a-zA-Z0-9.]*[>]?$")
	public String getEmail() {
		return super.getEmail();
	}

	@Override
	public void setEmail(final String email) {
		super.setEmail(email);
	}
}
