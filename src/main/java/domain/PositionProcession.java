
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class PositionProcession extends DomainEntity {

	// Attributes
	private Integer	positionRow;
	private Integer	positionColumn;


	// Getters and Setters
	@NotNull
	@Min(0)
	public Integer getPositionRow() {
		return this.positionRow;
	}

	public void setPositionRow(final Integer positionRow) {
		this.positionRow = positionRow;
	}

	@NotNull
	@Min(0)
	public Integer getPositionColumn() {
		return this.positionColumn;
	}

	public void setPositionColumn(final Integer positionColumn) {
		this.positionColumn = positionColumn;
	}

}
