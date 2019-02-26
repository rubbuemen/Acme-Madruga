
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Procession;

@Repository
public interface ProcessionRepository extends JpaRepository<Procession, Integer> {

	@Query("select p from Brotherhood b join b.processions p where b.id = ?1 and p.isFinalMode = 1")
	Collection<Procession> findProcessionsFinalModeByBrotherhoodId(int brotherhoodId);

	@Query("select p from Procession p where ?1 member of p.floats")
	Collection<Procession> findProcessionsByFloatId(int floatId);

	@Query("select p from Procession p join p.requestsMarch rm where rm.id = ?1")
	Procession findProcessionByRequestMarchId(int requestMarchId);

}
