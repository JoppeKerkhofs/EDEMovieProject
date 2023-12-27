package fact.it.cinemaservice.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import fact.it.cinemaservice.model.Cinema;

public interface CinemaRepository extends MongoRepository<Cinema, String> {
    List<Cinema> findByName(String name);
}
