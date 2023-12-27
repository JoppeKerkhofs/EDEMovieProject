package fact.it.cinemaservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CinemaResponse {
    private String cinemaId;
    private String name;
    private String address;
    private List<String> movies;
}
