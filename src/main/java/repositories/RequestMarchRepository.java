
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.RequestMarch;

@Repository
public interface RequestMarchRepository extends JpaRepository<RequestMarch, Integer> {

}
