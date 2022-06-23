package proposta.mv.desafio.serviceMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import proposta.mv.desafio.dto.CityDTO;
import proposta.mv.desafio.dto.ClientDTO;
import proposta.mv.desafio.dto.StateDTO;
import proposta.mv.desafio.models.ClientModel;

import java.util.ArrayList;
import java.util.List;

public class ClientMapper {

    //Conversão de Model para DTO
    public static ClientDTO makeClientDto (ClientModel clientModel) {
        ClientDTO client = new ClientDTO();
        BeanUtils.copyProperties(clientModel, client);
        CityDTO cityDTO = CityMapper.makeCityDto(clientModel.getCity());
        client.setCity(cityDTO);

        return client;
    }

    //Conversão de Model para DTO
    public static ClientDTO makeClientDtoSave (ClientModel clientModel) {
        ClientDTO clientDto = new ClientDTO();
        BeanUtils.copyProperties(clientModel, clientDto);

        CityDTO cityDTO = CityMapper.makeCityDto(clientModel.getCity());
        StateDTO stateDTO = StateMapper.makeStateDto(clientModel.getCity().getState());

        cityDTO.setState(stateDTO);
        clientDto.setCity(cityDTO);

        return clientDto;
    }

    //Conversão de DTO para Model
    public static ClientModel makeClientModel (ClientDTO clientDTO) {
        ClientModel client = new ClientModel();
        BeanUtils.copyProperties(clientDTO, client);

        return client;
    }

    // Converte a lista de Model para DTO
    public static List<ClientDTO> makeListClientDTO (List<ClientModel> stateModel) {
        List<ClientDTO> client = new ArrayList<>();

        stateModel.forEach(s -> {
            ClientDTO clientDto = new ClientDTO();
            BeanUtils.copyProperties(s, clientDto);
            CityDTO cityDTO = CityMapper.makeCityDto(s.getCity());
            clientDto.setCity(cityDTO);
            client.add(clientDto);
        });

        return client;
    }

    // Converte a lista de DTO para Model
    public static List<ClientModel> makeListClientModel (List<ClientDTO> clientDTO) {
        List<ClientModel> client = new ArrayList<>();

        clientDTO.forEach(s -> {
            ClientModel clientModel = new ClientModel();
            BeanUtils.copyProperties(s, clientModel);
            client.add(clientModel);
        });

        return client;
    }



}
