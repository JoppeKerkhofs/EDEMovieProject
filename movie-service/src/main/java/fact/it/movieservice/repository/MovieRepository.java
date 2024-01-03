package fact.it.movieservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import fact.it.movieservice.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByTitle(String title);

    void deleteById(String movieId);

    Movie findByMovieId(String movieId);
}
