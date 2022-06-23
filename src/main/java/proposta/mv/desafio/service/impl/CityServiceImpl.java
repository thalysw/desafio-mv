package proposta.mv.desafio.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proposta.mv.desafio.controller.ClientController;
import proposta.mv.desafio.dto.CityDTO;
import proposta.mv.desafio.dto.StateDTO;
import proposta.mv.desafio.exceptions.business.CityExistException;
import proposta.mv.desafio.exceptions.business.CityNotExistException;
import proposta.mv.desafio.exceptions.business.StateNotExistException;
import proposta.mv.desafio.models.CityModel;
import proposta.mv.desafio.models.StateModel;
import proposta.mv.desafio.repository.CityRepository;
import proposta.mv.desafio.repository.StateRepository;
import proposta.mv.desafio.service.CityService;
import proposta.mv.desafio.service.StateService;
import proposta.mv.desafio.serviceMapper.CityMapper;
import proposta.mv.desafio.serviceMapper.StateMapper;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    CityRepository cityRepository;

    @Autowired
    StateRepository stateRepository;

    @Override
    public List<CityDTO> findAll() {
        log.info("SERVICE - Using findAll method");
        List<CityModel> cityModels = cityRepository.findAll();
        if(cityModels.isEmpty()){
            throw new CityNotExistException("Cities not found!");
        }

        log.info("SERVICE - End of method findAll!");
        return CityMapper.makeListCityDTO(cityModels);
    }

    @Override
    public CityDTO findById(Long id) {
        log.info("SERVICE - Using findById method");
        CityModel cityModel = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotExistException("City not found!"));

        log.info("SERVICE - End of method findById!");
        return CityMapper.makeCityDto(cityModel);
    }

    @Override
    public CityDTO saveCity(CityDTO cityDTO) {
        log.info("SERVICE - Using saveCity method");
        List<StateModel> state = stateRepository.findAll();

        if (state.isEmpty()) {
            throw new StateNotExistException("State not found!");
        }
        log.info("State found successfully!");

        if(existsByNameCity(cityDTO.getNameCity())){
            throw new CityExistException("City is Already Taken!");
        }
        CityDTO cityDTOReturn = makeCityDtoReturn(state, cityDTO);

        log.info("SERVICE - End of method saveCity!");
        return cityDTOReturn;
    }

    @Override
    public boolean existsByNameCity(String nameCity) {
        log.info("SERVICE - Using existsByNameCity method");
        return cityRepository.existsByNameCity(nameCity);
    }

    @Override
    public CityDTO findByNameCity(String nameCity) {
        log.info("SERVICE - Using findByNameCity method");
        CityModel cityModel = cityRepository.findByNameCity(nameCity)
                .orElseThrow(() -> new CityNotExistException("City not exists in database!"));

        log.info("SERVICE - End of method findByNameCity!");
        return CityMapper.makeCityDto(cityModel);
    }

    @Override
    public CityDTO updateCity(Long id, CityDTO cityDTO) {
        log.info("SERVICE - Using updateCity method");
        CityModel cityModel = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotExistException("City not found!"));
        cityModel.setNameCity(cityDTO.getNameCity());
        cityModel.setAbbreviation(cityDTO.getAbbreviation());
        cityRepository.save(cityModel);

        log.info("SERVICE - End of method updateCity!");
        return CityMapper.makeCityDto(cityModel);
    }

    @Override
    public void deleteCity(Long id) {
        log.info("SERVICE - Using delete method");
        CityModel cityModel = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotExistException("City not found!"));

        log.info("SERVICE - End of method delete!");
        cityRepository.delete(cityModel);
    }


    public CityDTO makeCityDtoReturn (List<StateModel> state, CityDTO cityDTO){
        CityModel cityModel = new CityModel();

        state.forEach(s -> {
            if(s.getAbbreviation().equals(cityDTO.getAbbreviation())){
                BeanUtils.copyProperties(cityDTO, cityModel);
                cityModel.setState(s);
                cityRepository.save(cityModel);
            }
        });

        if (cityModel.getIdCity() == null) {
            throw new CityExistException("City not created!");
        }

        return CityMapper.makeCityDTOList(cityModel, state);
    }


}
