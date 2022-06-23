package proposta.mv.desafio.serviceMapper;

import org.springframework.beans.BeanUtils;
import proposta.mv.desafio.dto.StateDTO;
import proposta.mv.desafio.models.StateModel;

import java.util.ArrayList;
import java.util.List;

public class StateMapper {

    // Converte a lista de Model para DTO
    public static List<StateDTO> makeListStateDTO (List<StateModel> stateModel) {
        List<StateDTO> stateDTO = new ArrayList<>();

        stateModel.forEach(s -> {
            StateDTO state = new StateDTO();
            BeanUtils.copyProperties(s ,state);
            stateDTO.add(state);
        });

        return stateDTO;
    }

    // Converte a lista de Dto para Model
    public static List<StateModel> makeListStateModel (List<StateDTO> stateDTO) {
        List<StateModel> stateModel = new ArrayList<>();

        stateDTO.forEach(s -> {
            StateModel state = new StateModel();
            BeanUtils.copyProperties(s ,state);
            stateModel.add(state);
        });

        return stateModel;
    }
    // Conversão de Model para Dto
    public static StateDTO makeStateDto(StateModel stateModel) {
        StateDTO state = new StateDTO();
        BeanUtils.copyProperties(stateModel, state);

        return state;
    }
    // Conversão de Dto para Model
    public static StateModel makeStateModel (StateDTO stateDTO) {
        StateModel state = new StateModel();
        BeanUtils.copyProperties(stateDTO, state);

        return state;
    }
}
