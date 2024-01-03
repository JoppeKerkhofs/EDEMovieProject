package fact.it.movieservice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import fact.it.movieservice.model.Movie;
import fact.it.movieservice.repository.MovieRepository;
import fact.it.movieservice.service.MovieService;
import fact.it.movieservice.dto.*;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovieServiceApplicationTests {

	@InjectMocks
	private MovieService movieService;

	@Mock
	private MovieRepository movieRepository;

	@Test
	public void testGetAllMovies() {
		Movie movie1 = Movie.builder()
			.movieId("1")
			.title("The Lion King")
			.description("The Lion King is a 2019 American musical drama film directed and produced by Jon Favreau, written by Jeff Nathanson, and produced by Walt Disney Pictures.")
			.releaseDate("19 July 2019")
			.actors(List.of(1L, 2L, 3L))
			.ratingId(0)
			.build();
		Movie movie2 = Movie.builder()
			.movieId("2")
			.title("The Joker")
			.description("Joker is a 2019 American psychological thriller film directed and produced by Todd Phillips, who co-wrote the screenplay with Scott Silver.")
			.releaseDate("4 October 2019")
			.actors(List.of(4L, 5L))
			.ratingId(1)
			.build();

		List<Movie> movies = List.of(movie1, movie2);

		when(movieRepository.findAll()).thenReturn(movies);

		List<MovieResponse> movieResponses = movieService.getAllMovies();

		assertThat(movieResponses.size()).isEqualTo(2);
		assertThat(movieResponses.get(0).getTitle()).isEqualTo("The Lion King");
		assertThat(movieResponses.get(1).getTitle()).isEqualTo("The Joker");
	}

	@Test
	public void testGetMovieByTitle() {
		Movie movie = Movie.builder()
				.movieId("1")
				.title("The Lion King")
				.description(
						"The Lion King is a 2019 American musical drama film directed and produced by Jon Favreau, written by Jeff Nathanson, and produced by Walt Disney Pictures.")
				.releaseDate("19 July 2019")
				.actors(List.of(1L, 2L, 3L))
				.ratingId(0)
				.build();

		when(movieRepository.findByTitle("The Lion King")).thenReturn(List.of(movie));

		List<MovieResponse> movieResponses = movieService.getMoviesByTitle("The Lion King");

		assertThat(movieResponses.size()).isEqualTo(1);
		assertThat(movieResponses.get(0).getTitle()).isEqualTo("The Lion King");
	}
}
