package fact.it.actorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActorRespone {
    private Long actorId;
    private String name;
    private String birthDate;
    private String country;
    private boolean active;
}
