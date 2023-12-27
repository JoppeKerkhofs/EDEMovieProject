package fact.it.ratingservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fact.it.ratingservice.model.Rating;
import fact.it.ratingservice.repository.RatingRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    
    @PostConstruct
    public void loadData() {
        ratingRepository.save(new Rating(1L, 1));
        ratingRepository.save(new Rating(2L, 2));
        ratingRepository.save(new Rating(3L, 3));
        ratingRepository.save(new Rating(4L, 4));
        ratingRepository.save(new Rating(5L, 5));
    }

    @Transactional
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Transactional
    public Rating findById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }
}
