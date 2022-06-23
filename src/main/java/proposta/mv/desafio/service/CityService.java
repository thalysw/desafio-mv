package proposta.mv.desafio.service;

import proposta.mv.desafio.dto.CityDTO;
import proposta.mv.desafio.models.CityModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityService {
    List<CityDTO> findAll();
    CityDTO findById(Long id);
    CityDTO saveCity(CityDTO cityDTO);
    boolean existsByNameCity(String nameCity);
    CityDTO findByNameCity(String nameCity);
    CityDTO updateCity(Long id, CityDTO cityDTO);
    void deleteCity(Long id);
}
