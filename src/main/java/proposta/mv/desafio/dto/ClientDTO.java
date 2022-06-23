package proposta.mv.desafio.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ClientDTO {

    private Long idClient;
    private String nameComplete;
    private String gender;
    private LocalDate birthDate;
    private String age;
    private CityDTO city;
}
