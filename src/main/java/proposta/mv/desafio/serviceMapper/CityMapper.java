package proposta.mv.desafio.serviceMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import proposta.mv.desafio.dto.CityDTO;
import proposta.mv.desafio.dto.StateDTO;
import proposta.mv.desafio.models.CityModel;
import proposta.mv.desafio.models.StateModel;

import java.util.ArrayList;
import java.util.List;

public class CityMapper {

    public static CityDTO makeCityDTOList (CityModel cityModel, List<StateModel> stateModel) {
        StateDTO stateDTO = new StateDTO();
        BeanUtils.copyProperties(cityModel.getState(), stateDTO);
        stateDTO.setCities(makeListCityDTOWithState(stateModel));
        CityDTO cityDTOReturn = new CityDTO();
        BeanUtils.copyProperties(cityModel, cityDTOReturn);
        cityDTOReturn.setState(stateDTO);
        return cityDTOReturn;
    }

    public static List<CityDTO> makeListCityDTOWithState (List<StateModel> stateModel){
        List<CityDTO> listCityDTO = new ArrayList<>();

        stateModel.forEach(s -> {
            s.getCities().forEach(sCity -> {
                CityDTO stateCityDTO = new CityDTO();
                BeanUtils.copyProperties(sCity, stateCityDTO);
                listCityDTO.add(stateCityDTO);
            });
        });

        return listCityDTO;
    }

    //Conversão de Dto para Model
    public static CityDTO makeCityDto (CityModel cityModel) {
        CityDTO city = new CityDTO();
        BeanUtils.copyProperties(cityModel, city);
        StateDTO stateDTO = new StateDTO();
        BeanUtils.copyProperties(cityModel.getState(), stateDTO);
        city.setState(stateDTO);

        return city;
    }

    //Conversão de Dto para Model
    public static CityModel makeCityModel (CityDTO cityDto) {
        CityModel city = new CityModel();
        BeanUtils.copyProperties(cityDto, city);

        return city;
    }

    // Converte a lista de Model para DTO
    public static List<CityDTO> makeListCityDTO (List<CityModel> cityModel) {
        List<CityDTO> city = new ArrayList<>();

        cityModel.forEach(s -> {
            CityDTO cityDTO = new CityDTO();
            BeanUtils.copyProperties(s, cityDTO);
            StateDTO stateDto = new StateDTO();
            BeanUtils.copyProperties(s.getState(), stateDto);
            cityDTO.setState(stateDto);
            city.add(cityDTO);
        });

        return city;
    }

    // Converte a lista de DTO para Model
    public static List<CityModel> makeListCityModel (List<CityDTO> cityDTO) {
        List<CityModel> city = new ArrayList<>();

        cityDTO.forEach(s -> {
            CityModel cityDTOConverter = new CityModel();
            BeanUtils.copyProperties(s, cityDTOConverter);
            city.add(cityDTOConverter);
        });

        return city;
    }
}
