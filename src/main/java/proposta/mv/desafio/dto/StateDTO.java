package proposta.mv.desafio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class StateDTO {

    private Long idState;
    private String nameState;
    private String abbreviation;
    @JsonIgnore
    private List<CityDTO> cities;
}
