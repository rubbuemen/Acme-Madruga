
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PositionProcession;

@Repository
public interface PositionProcessionRepository extends JpaRepository<PositionProcession, Integer> {

}
