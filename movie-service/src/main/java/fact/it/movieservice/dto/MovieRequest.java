package fact.it.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {
    private String movieId;
    private String title;
    private String description;
    private String releaseDate;
    private String actors;
    private int ratingId;
}
