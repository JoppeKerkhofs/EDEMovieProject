package fact.it.actorservice.controller;

import org.springframework.web.bind.annotation.*;

import fact.it.actorservice.model.Actor;
import fact.it.actorservice.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Actor> findAll() {
        return actorService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Actor findById(@PathVariable Long id) {
        return actorService.findById(id);
    }
}
