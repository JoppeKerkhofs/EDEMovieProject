package fact.it.actorservice;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import fact.it.actorservice.model.Actor;
import fact.it.actorservice.repository.ActorRepository;
import fact.it.actorservice.service.ActorService;

// import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ActorServiceApplicationTests {

	@InjectMocks
	private ActorService actorService;

	@Mock
	private ActorRepository actorRepository;

	@Test
	public void testGetAllActors() {
		Actor actor1 = new Actor();
		actor1.setActorId(1L);
		actor1.setName("Donald Glover");
		actor1.setBirthDate(java.sql.Date.valueOf("1983-09-25"));
		actor1.setCountry("USA");
		actor1.setActive(true);

		Actor actor2 = new Actor();
		actor2.setActorId(2L);
		actor2.setName("Beyoncé Knowles");
		actor2.setBirthDate(java.sql.Date.valueOf("1981-09-04"));
		actor2.setCountry("USA");
		actor2.setActive(true);

		List<Actor> actors = List.of(actor1, actor2);
		
		when(actorRepository.findAll()).thenReturn(actors);

		List<Actor> actorResponses = actorService.getAllActors();

		assertThat(actorResponses.size()).isEqualTo(2);
	}

	@Test
	public void testGetActorById() {
		Actor actor = new Actor();
		actor.setActorId(1L);
		actor.setName("Donald Glover");
		actor.setBirthDate(java.sql.Date.valueOf("1983-09-25"));
		actor.setCountry("USA");
		actor.setActive(true);

		when(actorRepository.findById(1L)).thenReturn(java.util.Optional.of(actor));

		Actor actorResponse = actorService.findById(1L);

		assertThat(actorResponse.getName()).isEqualTo("Donald Glover");
	}

	@Test
	public void testAddActor() {
		Actor actor = new Actor();
		actor.setActorId(1L);
		actor.setName("Donald Glover");
		actor.setBirthDate(java.sql.Date.valueOf("1983-09-25"));
		actor.setCountry("USA");
		actor.setActive(true);

		when(actorRepository.save(actor)).thenReturn(actor);

		Actor actorResponse = actorService.addActor(actor);

		assertThat(actorResponse.getName()).isEqualTo("Donald Glover");
	}

	@Test
	public void testUpdateActor() {
		Actor actor = new Actor();
		actor.setActorId(1L);
		actor.setName("Donald Glover");
		actor.setBirthDate(java.sql.Date.valueOf("1983-09-25"));
		actor.setCountry("USA");
		actor.setActive(true);

		when(actorRepository.save(actor)).thenReturn(actor);

		Actor actorResponse = actorService.updateActor(actor);

		assertThat(actorResponse.getName()).isEqualTo("Donald Glover");
	}

	@Test
	public void testDeleteActor() {
		actorService.deleteActor(1L);
	}
}
