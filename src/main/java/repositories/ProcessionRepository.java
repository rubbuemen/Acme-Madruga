
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Procession;

@Repository
public interface ProcessionRepository extends JpaRepository<Procession, Integer> {

	@Query("select p from Brotherhood b join b.processions p where b.id = ?1 and p.isFinalMode = 1")
	Collection<Procession> findProcessionsByBrotherhoodId(int brotherhoodId);
}
