package fact.it.movieservice;

import java.util.List;
import java.util.Optional;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
	public void testGetMovieById() {
		Movie movie = Movie.builder()
				.movieId("1")
				.title("The Lion King")
				.description(
						"The Lion King is a 2019 American musical drama film directed and produced by Jon Favreau, written by Jeff Nathanson, and produced by Walt Disney Pictures.")
				.releaseDate("19 July 2019")
				.actors(List.of(1L, 2L, 3L))
				.ratingId(0)
				.build();

		when(movieRepository.findById("1")).thenReturn(Optional.of(movie));

		MovieResponse movieResponse = movieService.getMovieById("1");

		assertThat(movieResponse.getTitle()).isEqualTo("The Lion King");
	}

	@Test
	public void testAddMovie() {
		Movie movie = Movie.builder()
				.movieId("1")
				.title("The Lion King")
				.description("The Lion King is a 2019 American musical drama film directed and produced by Jon Favreau, written by Jeff Nathanson, and produced by Walt Disney Pictures.")
				.releaseDate("19 July 2019")
				.actors(List.of(1L, 2L, 3L))
				.ratingId(0)
				.build();

		// Mocking save method
		when(movieRepository.save(any(Movie.class))).thenReturn(movie);

		// Call addMovie
		movieService.addMovie(movie);

		// Verify save method was called
		verify(movieRepository, times(1)).save(eq(movie));

		// Get the new movie
		MovieResponse movieResponse = movieService.getMovieById("1");

		// Assertion
		assertThat(movieResponse.getTitle()).isEqualTo("The Lion King");
	}

	@Test
	public void testUpdateMovie() {
		Movie movie = Movie.builder()
				.movieId("1")
				.title("The Lion King")
				.description("The Lion King is a 2019 American musical drama film directed and produced by Jon Favreau, written by Jeff Nathanson, and produced by Walt Disney Pictures.")
				.releaseDate("19 July 2019")
				.actors(List.of(1L, 2L, 3L))
				.ratingId(0)
				.build();

		// Mocking findById method
		when(movieRepository.findById("1")).thenReturn(Optional.of(movie));

		// Mocking save method
		when(movieRepository.save(any(Movie.class))).thenReturn(movie);

		// Call updateMovie
		movieService.updateMovie("1", movie);

		// Verify save method was called
		verify(movieRepository, times(1)).save(eq(movie));

		// Get the updated movie
		MovieResponse movieResponse = movieService.getMovieById("1");

		// Assertion
		assertThat(movieResponse.getTitle()).isEqualTo("The Lion King");
	}

	@Test
	public void testDeleteMovie() {
		Movie movie = Movie.builder()
				.movieId("1")
				.title("The Lion King")
				.description("The Lion King is a 2019 American musical drama film directed and produced by Jon Favreau, written by Jeff Nathanson, and produced by Walt Disney Pictures.")
				.releaseDate("19 July 2019")
				.actors(List.of(1L, 2L, 3L))
				.ratingId(0)
				.build();

		// Mocking findById method
		when(movieRepository.findById("1")).thenReturn(Optional.of(movie));

		// Call deleteMovie
		movieService.deleteMovie("1");

		// Verify deleteById method was called
		verify(movieRepository, times(1)).deleteById(eq("1"));
	}
}
