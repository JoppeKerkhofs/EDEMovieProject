package fact.it.movieservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fact.it.movieservice.dto.MovieResponse;
import fact.it.movieservice.model.Movie;
import fact.it.movieservice.repository.MovieRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    @PostConstruct
    public void loadData() {
        if (movieRepository.count() <= 0) {
            Movie movie = Movie.builder()
                .title("Movie 1")
                .description("Description 1")
                .releaseDate("2021-01-01")
                .actors(List.of(1L, 2L, 3L))
                .build();
            movieRepository.save(movie);

            movie = Movie.builder()
                .title("Movie 2")
                .description("Description 2")
                .releaseDate("2021-01-02")
                .actors(List.of(1L, 2L))
                .build();
            movieRepository.save(movie);

            movie = Movie.builder()
                .title("Movie 3")
                .description("Description 3")
                .releaseDate("2021-01-03")
                .actors(List.of(1L))
                .build();
            movieRepository.save(movie);
        }
    }

    public void addMovie(Movie movie) {
        Movie newMovie = Movie.builder()
            .title(movie.getTitle())
            .description(movie.getDescription())
            .releaseDate(movie.getReleaseDate())
            .actors(movie.getActors())
            .build();
        movieRepository.save(newMovie);
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream().map(this::mapToMovieResponse).toList();
    }

    public MovieResponse getMovieById(String movieId) {
        return movieRepository.findById(movieId).map(this::mapToMovieResponse).orElse(null);
    }

    private MovieResponse mapToMovieResponse(Movie movie) {
        return MovieResponse.builder()
            .movieId(movie.getMovieId())
            .title(movie.getTitle())
            .description(movie.getDescription())
            .releaseDate(movie.getReleaseDate())
            .actors(movie.getActors().toString())
            .build();
    }
}
