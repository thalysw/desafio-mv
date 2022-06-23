package proposta.mv.desafio.service;

import proposta.mv.desafio.dto.CityDTO;
import proposta.mv.desafio.dto.StateDTO;

import java.util.List;
import java.util.Optional;

public interface StateService {
    List<StateDTO> findAll();
    StateDTO findById(Long id);
    boolean existsByNameState(String nameState);
    void deleteState(Long id);
    StateDTO saveState(StateDTO stateDTO);
    StateDTO updateState(Long id, StateDTO stateDTO);
    List<CityDTO> citiesByStateName(String stateName);
}
