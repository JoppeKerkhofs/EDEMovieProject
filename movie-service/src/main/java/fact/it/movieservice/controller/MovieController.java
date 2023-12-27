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
