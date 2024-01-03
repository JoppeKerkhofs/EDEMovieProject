package fact.it.cinemaservice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fact.it.cinemaservice.dto.CinemaResponse;
import fact.it.cinemaservice.model.Cinema;
import fact.it.cinemaservice.repository.CinemaRepository;
import fact.it.cinemaservice.service.CinemaService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CinemaServiceApplicationTests {

	@InjectMocks
	private CinemaService cinemaService;

	@Mock
	private CinemaRepository cinemaRepository;

	@Test
	public void testGetAllCinemas() {
		Cinema cinema1 = new Cinema();
		cinema1.setCinemaId("1");
		cinema1.setName("Kinepolis");
		cinema1.setAddress("Groenendaallaan 394, 2030 Antwerpen");
		cinema1.setMovies(List.of("The Lion King", "The Incredibles 2"));

		Cinema cinema2 = new Cinema();
		cinema2.setCinemaId("2");
		cinema2.setName("UGC");
		cinema2.setAddress("Van Ertbornstraat 17, 2018 Antwerpen");
		cinema2.setMovies(List.of("The Lion King", "The Incredibles 2"));

		List<Cinema> cinemas = List.of(cinema1, cinema2);
		
		when(cinemaRepository.findAll()).thenReturn(cinemas);

		List<CinemaResponse> cinemaResponses = cinemaService.getAllCinemas();

		assertThat(cinemaResponses.size()).isEqualTo(2);
	}
}
