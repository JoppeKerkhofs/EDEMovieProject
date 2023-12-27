package fact.it.ratingservice.repository;

import fact.it.ratingservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRating(int rating);
}
