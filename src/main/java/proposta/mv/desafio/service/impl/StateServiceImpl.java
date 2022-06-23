package proposta.mv.desafio.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proposta.mv.desafio.controller.ClientController;
import proposta.mv.desafio.dto.CityDTO;
import proposta.mv.desafio.dto.StateDTO;
import proposta.mv.desafio.exceptions.business.StateExistException;
import proposta.mv.desafio.exceptions.business.StateNotExistException;
import proposta.mv.desafio.models.CityModel;
import proposta.mv.desafio.models.StateModel;
import proposta.mv.desafio.repository.CityRepository;
import proposta.mv.desafio.repository.StateRepository;
import proposta.mv.desafio.service.StateService;
import proposta.mv.desafio.serviceMapper.CityMapper;
import proposta.mv.desafio.serviceMapper.StateMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {

    Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<StateDTO> findAll() {
        log.info("SERVICE - Using findAll method!");
        List<StateModel> stateModel = stateRepository.findAll();
        if(stateModel.isEmpty()){
            throw new StateNotExistException("State not exist in database!");
        }
        log.info("SERVICE - End of method findAll!");
        return StateMapper.makeListStateDTO(stateModel);
    }

    @Override
    public StateDTO findById(Long id) {
        log.info("SERVICE - Using findById method!");
        StateModel stateModel = stateRepository.findById(id)
                .orElseThrow(() -> new StateNotExistException("City not found!"));
        StateDTO state = new StateDTO();
        BeanUtils.copyProperties(stateModel, state);

        log.info("SERVICE - End of method findById!");
        return state;
    }

    @Override
    public boolean existsByNameState(String nameState) {
        log.info("SERVICE - Using existsByNameState method!");
        return stateRepository.existsByNameState(nameState);
    }

    @Override
    public void deleteState(Long id) {
        log.info("SERVICE - Using deleteState method!");
        StateModel stateModel = stateRepository.findById(id)
                .orElseThrow(() -> new StateNotExistException("State not found!"));
        stateRepository.delete(stateModel);
        log.info("SERVICE - End of method delete!");
    }

    @Override
    public StateDTO saveState(StateDTO stateDTO) {
        log.info("SERVICE - Using saveState method!");
        if(existsByNameState(stateDTO.getNameState())){
            throw new StateExistException("State is Already Taken!");
        }
        StateModel stateModel = StateMapper.makeStateModel(stateDTO);
        stateRepository.save(stateModel);

        if(stateModel.getIdState() == null){
            throw new StateExistException("State not created!");
        }
        log.info("SERVICE - End of method saveState!");
        return StateMapper.makeStateDto(stateModel);
    }

    @Override
    public StateDTO updateState(Long id, StateDTO stateDTO) {
        log.info("SERVICE - Using updateState method!");
        StateModel stateModel = stateRepository.findById(id)
                .orElseThrow(() -> new StateNotExistException("State not found!"));
        stateModel.setNameState(stateDTO.getNameState());
        stateModel.setAbbreviation(stateDTO.getAbbreviation());
        stateRepository.save(stateModel);

        StateDTO state = new StateDTO();
        BeanUtils.copyProperties(stateModel, state);
        log.info("SERVICE - End of method updateState!");
        return state;
    }

    @Override
    public List<CityDTO> citiesByStateName(String stateName) {
        List<CityModel> cityModels = cityRepository.findByNameState(stateName);

        return CityMapper.makeListCityDTO(cityModels);
    }


}
