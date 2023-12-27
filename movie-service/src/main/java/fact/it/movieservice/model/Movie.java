package fact.it.movieservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    private String movieId;
    private String title;
    private String description;
    private String releaseDate;
    private List<Long> actors;
}
