package fact.it.cinemaservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "cinema")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cinema {
    private String cinemaId;
    private String name;
    private String address;
    private List<String> movies;
}
