package fact.it.cinemaservice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;

import fact.it.cinemaservice.dto.CinemaResponse;
import fact.it.cinemaservice.service.CinemaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cinemas")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CinemaResponse> getAllCinemas() {
        return cinemaService.getAllCinemas();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CinemaResponse getCinemaById(@PathVariable String id) {
        return cinemaService.getCinemaById(id);
    }
}
