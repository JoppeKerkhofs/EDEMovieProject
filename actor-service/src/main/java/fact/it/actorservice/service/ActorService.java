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
            // add the following actors: 1L "Donald Glover", 2L "Beyoncé Knowles", 3L "James Earl Jones", 4L Joaquin Phoenix, 5L Robert De Niro, 6L Robert Downey Jr., 7L Chris Evans, 8L Mark Ruffalo 9L, Chris Hemsworth, 10L Scarlett Johansson
            actorRepository.save(new Actor(1L, "Donald Glover", Date.valueOf("1983-09-25"), "USA", true));
            actorRepository.save(new Actor(2L, "Beyoncé Knowles", Date.valueOf("1981-09-04"), "USA", true));
            actorRepository.save(new Actor(3L, "James Earl Jones", Date.valueOf("1931-01-17"), "USA", true));
            actorRepository.save(new Actor(4L, "Joaquin Phoenix", Date.valueOf("1974-10-28"), "USA", true));
            actorRepository.save(new Actor(5L, "Robert De Niro", Date.valueOf("1943-08-17"), "USA", true));
            actorRepository.save(new Actor(6L, "Robert Downey Jr.", Date.valueOf("1965-04-04"), "USA", true));
            actorRepository.save(new Actor(7L, "Chris Evans", Date.valueOf("1981-06-13"), "USA", true));
            actorRepository.save(new Actor(8L, "Mark Ruffalo", Date.valueOf("1967-11-22"), "USA", true));
            actorRepository.save(new Actor(9L, "Chris Hemsworth", Date.valueOf("1983-08-11"), "Australia", true));
            actorRepository.save(new Actor(10L, "Scarlett Johansson", Date.valueOf("1984-11-22"), "USA", true));
        }
    }

    @Transactional
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @Transactional
    public Actor findById(Long id) {
        return actorRepository.findById(id).orElse(null);
    }

    // a function to add an actor
    @Transactional
    public Actor addActor(Actor actor) {
        return actorRepository.save(actor);
    }

    // a function to update an actor
    @Transactional
    public Actor updateActor(Long id, Actor actor) {
        Actor actorToUpdate = actorRepository.findById(id).orElse(null);
        if (actorToUpdate != null) {
            actorToUpdate.setName(actor.getName());
            actorToUpdate.setBirthDate(actor.getBirthDate());
            actorToUpdate.setCountry(actor.getCountry());
            actorToUpdate.setActive(actor.isActive());
        }
        // save the updated actor
        actorRepository.save(actorToUpdate);
        return actorToUpdate;
    }

    // a function to delete an actor
    @Transactional
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }
}
