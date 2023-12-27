package fact.it.actorservice.repository;

import fact.it.actorservice.model.Actor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Transactional
public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByNameContaining(String name);
}
