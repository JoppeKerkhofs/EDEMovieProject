package fact.it.actorservice.service;

import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import fact.it.actorservice.model.Actor;
import fact.it.actorservice.repository.ActorRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActorService {
    private final ActorRepository actorRepository;

    @PostConstruct
    public void loadData() {
        if (actorRepository.count() == 0) {
            actorRepository.save(new Actor(1L,"Tom Hanks", Date.valueOf("1956-07-09"), "USA", true));
            actorRepository.save(new Actor(2L, "Brad Pitt", Date.valueOf("1963-12-18"), "USA", true));
            actorRepository.save(new Actor(3L, "Morgan Freeman", Date.valueOf("1937-06-01"), "USA", true));
            actorRepository.save(new Actor(4L, "Jennifer Aniston", Date.valueOf("1969-02-11"), "USA", true));
            actorRepository.save(new Actor(5L, "Julia Roberts", Date.valueOf("1967-10-28"), "USA", true));
            actorRepository.save(new Actor(6L, "George Clooney", Date.valueOf("1961-05-06"), "USA", true));
            actorRepository.save(new Actor(7L, "Scarlett Johansson", Date.valueOf("1984-11-22"), "USA", true));
            actorRepository.save(new Actor(8L, "Robert Downey Jr.", Date.valueOf("1965-04-04"), "USA", true));
            actorRepository.save(new Actor(9L, "Will Smith", Date.valueOf("1968-09-25"), "USA", true));
            actorRepository.save(new Actor(10L, "Johnny Depp", Date.valueOf("1963-06-09"), "USA", true));
            actorRepository.save(new Actor(11L, "Leonardo DiCaprio", Date.valueOf("1974-11-11"), "USA", true));
            actorRepository.save(new Actor(12L, "Dwayne Johnson", Date.valueOf("1972-05-02"), "USA", true));
            actorRepository.save(new Actor(13L, "Matt Damon", Date.valueOf("1970-10-08"), "USA", true));
            actorRepository.save(new Actor(14L, "Natalie Portman", Date.valueOf("1981-06-09"), "USA", true));
            actorRepository.save(new Actor(15L, "Emma Watson", Date.valueOf("1990-04-15"), "USA", true));
            actorRepository.save(new Actor(16L, "Anne Hathaway", Date.valueOf("1982-11-12"), "USA", true));
            actorRepository.save(new Actor(17L, "Meryl Streep", Date.valueOf("1949-06-22"), "USA", true));
            actorRepository.save(new Actor(18L, "Angelina Jolie", Date.valueOf("1975-06-04"), "USA", true));
            actorRepository.save(new Actor(19L, "Emma Stone", Date.valueOf("1988-11-06"), "USA", true));
            actorRepository.save(new Actor(20L, "Charlize Theron", Date.valueOf("1975-08-07"), "USA", true));
        }
    }

    @Transactional
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Transactional
    public Actor findById(Long id) {
        return actorRepository.findById(id).orElse(null);
    }
}
