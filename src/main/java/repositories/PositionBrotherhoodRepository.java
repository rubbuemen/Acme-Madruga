
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PositionBrotherhood;

@Repository
public interface PositionBrotherhoodRepository extends JpaRepository<PositionBrotherhood, Integer> {

}
