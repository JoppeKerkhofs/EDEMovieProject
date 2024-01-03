package fact.it.ratingservice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fact.it.ratingservice.model.Rating;
import fact.it.ratingservice.repository.RatingRepository;
import fact.it.ratingservice.service.RatingService;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingServiceApplicationTests {

	@InjectMocks
	private RatingService ratingService;

	@Mock
	private RatingRepository ratingRepository;

	@Test
	public void testGetAllRatings() {
		Rating rating1 = new Rating();
		rating1.setRatingId(1L);
		rating1.setRating(1);

		Rating rating2 = new Rating();
		rating2.setRatingId(2L);
		rating2.setRating(2);

		List<Rating> ratings = List.of(rating1, rating2);
		
		when(ratingRepository.findAll()).thenReturn(ratings);

		List<Rating> ratingResponses = ratingService.getAllRatings();

		assertThat(ratingResponses.size()).isEqualTo(2);
	}

	@Test
	public void testGetRatingById() {
		Rating rating = new Rating();
		rating.setRatingId(1L);
		rating.setRating(1);

		when(ratingRepository.findById(1L)).thenReturn(java.util.Optional.of(rating));

		Rating ratingResponse = ratingService.getRatingById(1L);

		assertThat(ratingResponse.getRatingId()).isEqualTo(1L);
		assertThat(ratingResponse.getRating()).isEqualTo(1);
	}
}
