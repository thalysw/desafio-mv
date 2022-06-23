package proposta.mv.desafio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CityDTO {

    private Long idCity;
    private String nameCity;
    private String abbreviation;
    private StateDTO state;
    @JsonIgnore
    private List<ClientDTO> clients;
}
