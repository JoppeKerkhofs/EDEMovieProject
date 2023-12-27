package fact.it.movieservice.controller;

import org.springframework.web.bind.annotation.*;

import fact.it.movieservice.model.Movie;
import fact.it.movieservice.repository.MovieRepository;

import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieRepository movieRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie(@RequestBody Movie movie) {
        movieRepository.save(movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable String id) {
        movieRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMovie(@PathVariable String id, @RequestBody Movie movie) {
        Movie newMovie = Movie.builder()
            .movieId(id)
            .title(movie.getTitle())
            .description(movie.getDescription())
            .releaseDate(movie.getReleaseDate())
            .actors(movie.getActors())
            .build();
        movieRepository.save(newMovie);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovieById(@PathVariable String id) {
        return movieRepository.findById(id).orElseThrow();
    }
}
