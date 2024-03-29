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
                .movieId("1")
                .title("The Lion King")
                .description("The Lion King is a 2019 American musical drama film directed and produced by Jon Favreau, written by Jeff Nathanson, and produced by Walt Disney Pictures.")
                .releaseDate("19 July 2019")
                .actors(List.of(1L, 2L, 3L))
                .ratingId(0)
                .build();
            movieRepository.save(movie);

            movie = Movie.builder()
                .movieId("2")
                .title("The Joker")
                .description("Joker is a 2019 American psychological thriller film directed and produced by Todd Phillips, who co-wrote the screenplay with Scott Silver.")
                .releaseDate("4 October 2019")
                .actors(List.of(4L, 5L))
                .ratingId(1)
                .build();
            movieRepository.save(movie);

            movie = Movie.builder()
                .movieId("3")
                .title("Avengers: Endgame")
                .description("Avengers: Endgame is a 2019 American superhero film based on the Marvel Comics superhero team the Avengers.")
                .releaseDate("26 April 2019")
                .actors(List.of(6L, 7L, 8L, 9L, 10L))
                .ratingId(2)
                .build();
            movieRepository.save(movie);
        }
    }

    public List<MovieResponse> getAllMovies() {
        return movieRepository.findAll().stream().map(this::mapToMovieResponse).toList();
    }

    public List<MovieResponse> getMoviesByTitle(String title) {
        return movieRepository.findByTitle(title).stream().map(this::mapToMovieResponse).toList();
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
