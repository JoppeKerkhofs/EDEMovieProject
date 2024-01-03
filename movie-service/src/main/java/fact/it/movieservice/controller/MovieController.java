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

    @DeleteMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovie(@PathVariable String movieId) {
        movieRepository.deleteById(movieId);
    }

    @PutMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMovie(@PathVariable String movieId, @RequestBody Movie movie) {
        Movie newMovie = Movie.builder()
            .movieId(movieId)
            .title(movie.getTitle())
            .description(movie.getDescription())
            .releaseDate(movie.getReleaseDate())
            .actors(movie.getActors())
            .ratingId(movie.getRatingId())
            .build();
        movieRepository.save(newMovie);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Movie getMovieById(@PathVariable String movieId) {
        return movieRepository.findById(movieId).orElseThrow();
    }
}
