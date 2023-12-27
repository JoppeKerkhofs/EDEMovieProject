package fact.it.ratingservice.controller;

import org.springframework.web.bind.annotation.RestController;

import fact.it.ratingservice.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Object findAll() {
        return ratingService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object findById(@PathVariable Long id) {
        return ratingService.findById(id);
    }
}
