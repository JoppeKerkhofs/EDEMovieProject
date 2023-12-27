package fact.it.cinemaservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fact.it.cinemaservice.dto.CinemaResponse;
import fact.it.cinemaservice.model.Cinema;
import fact.it.cinemaservice.repository.CinemaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CinemaService {
    private final CinemaRepository cinemaRepository;

    @PostConstruct
    public void loadData() {
        if (cinemaRepository.count() <= 0) {
            Cinema cinema1 = Cinema.builder().name("Kinepolis Antwerpen").address("Groenendaallaan 394, 2030 Antwerpen")
                    .movies(List.of("The Lion King", "The Joker", "Avengers: Endgame")).build();
            Cinema cinema2 = Cinema.builder().name("Kinepolis Brussel").address("Boulevard du Centenaire 20, 1020 Brussel")
                    .movies(List.of("The Lion King", "The Joker", "Avengers: Endgame")).build();
            Cinema cinema3 = Cinema.builder().name("Kinepolis Gent").address("Ter Platen 12, 9000 Gent")
                    .movies(List.of("The Lion King", "The Joker", "Avengers: Endgame")).build();
            Cinema cinema4 = Cinema.builder().name("Kinepolis Hasselt").address("Eikenenweg 3, 3500 Hasselt")
                    .movies(List.of("The Lion King", "The Joker", "Avengers: Endgame")).build();

            cinemaRepository.save(cinema1);
            cinemaRepository.save(cinema2);
            cinemaRepository.save(cinema3);
            cinemaRepository.save(cinema4);
        }
    }

    public List<CinemaResponse> getAllCinemas() {
        return cinemaRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public CinemaResponse getCinemaById(String id) {
        return mapToResponse(cinemaRepository.findById(id).orElse(null));
    }

    private CinemaResponse mapToResponse(Cinema cinema) {
        return CinemaResponse.builder().cinemaId(cinema.getCinemaId()).name(cinema.getName())
                .address(cinema.getAddress()).movies(cinema.getMovies()).build();
    }
}
