package proposta.mv.desafio.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import proposta.mv.desafio.dto.CityDTO;
import proposta.mv.desafio.dto.StateDTO;
import proposta.mv.desafio.exceptions.business.CityExistException;
import proposta.mv.desafio.exceptions.business.CityNotExistException;
import proposta.mv.desafio.exceptions.business.StateNotExistException;
import proposta.mv.desafio.models.CityModel;
import proposta.mv.desafio.models.ClientModel;
import proposta.mv.desafio.models.StateModel;
import proposta.mv.desafio.repository.CityRepository;
import proposta.mv.desafio.repository.StateRepository;
import proposta.mv.desafio.service.impl.CityServiceImpl;
import proposta.mv.desafio.serviceMapper.CityMapper;
import proposta.mv.desafio.serviceMapper.StateMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityServiceTest {

    @InjectMocks
    private CityServiceImpl cityService;

    @Mock
    CityRepository cityRepository;

    @Mock
    StateRepository stateRepository;

    List<CityModel> cityModelList = new ArrayList<>();
    List<ClientModel> clientModelList = new ArrayList<>();
    List<ClientModel> clientModelList2 = new ArrayList<>();
    List<StateModel> stateList = new ArrayList<>();

    StateModel state1 = new StateModel();
    StateModel state2 = new StateModel();

    CityModel city1 = new CityModel();
    CityModel city2 = new CityModel();
    CityModel city3 = new CityModel();

    ClientModel client1 = new ClientModel();
    ClientModel client2 = new ClientModel();
    ClientModel client3 = new ClientModel();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        state1 = new StateModel(1l, "São Paulo", "SP", cityModelList);
        state2 = new StateModel(2l, "Rio de Janeiro", "RJ", cityModelList);

        city1 = new CityModel(1l, "Campinas", "SP", state1, clientModelList);
        city2 = new CityModel(2l, "Rio de Janeiro", "RJ", state2, clientModelList);
        city3 = new CityModel(3l, "São Paulo", "SP", state2, clientModelList2);

        client1 = new ClientModel(1l, "Thalys Wel", "M", LocalDate.now(), "25", city1);
        client2 = new ClientModel(2l, "Melissa", "F", LocalDate.now(), "25", city2);
        client3 = new ClientModel(3l, "Vezaro Silva", "M", LocalDate.now(), "25", city3);

        cityModelList.add(city1);
        cityModelList.add(city2);
        cityModelList.add(city3);

        clientModelList.add(client1);
        clientModelList.add(client2);
        clientModelList2.add(client3);

        stateList.add(state1);
        stateList.add(state2);

    }

    @Test
    @DisplayName("TEST - CityService - findAll cities")
    public void findAllTestCityNotExistException() {
        List<CityModel> cityModels = new ArrayList<>();

        Mockito.when(cityRepository.findAll()).thenReturn(cityModels);

        Assert.assertThrows(CityNotExistException.class, () -> {
            cityService.findAll();
        });
    }

    @Test
    @DisplayName("TEST - CityService - findAll cities")
    public void findAllTest() {
        Mockito.when(cityRepository.findAll()).thenReturn(cityModelList);

        List<CityDTO> cityDTOList = cityService.findAll();

        Assert.assertEquals(cityDTOList.get(0).getIdCity(), cityModelList.get(0).getIdCity());
        Assert.assertEquals(cityDTOList.get(0).getNameCity(), cityModelList.get(0).getNameCity());
        Assert.assertEquals(cityDTOList.get(0).getAbbreviation(), cityModelList.get(0).getAbbreviation());
    }

    @Test
    @DisplayName("TEST - CityService - findById cities")
    public void findByIdTestCityNotExistException() {
        Assert.assertThrows(CityNotExistException.class, () -> {
            cityService.findById(999L);
        });
    }

    @Test
    @DisplayName("TEST - CityService - findById cities")
    public void findByIdTest() {
        Mockito.when(cityRepository.findById(1l)).thenReturn(Optional.of(city1));

        CityDTO cityDTO = cityService.findById(1l);

        Assert.assertEquals(city1.getIdCity(), cityDTO.getIdCity());
        Assert.assertEquals(city1.getNameCity(), cityDTO.getNameCity());
        Assert.assertEquals(city1.getAbbreviation(), cityDTO.getAbbreviation());
    }

    @Test
    @DisplayName("TEST - CityService - saveCity cities")
    public void saveCityTest() {
        Mockito.when(cityRepository.save(city1)).thenReturn(city1);
        Mockito.when(stateRepository.findAll()).thenReturn(stateList);

        CityDTO cityDTO = CityMapper.makeCityDto(city1);
        StateDTO stateDTO = StateMapper.makeStateDto(state1);
        cityDTO.setState(stateDTO);

        CityDTO cityDTOReturn = cityService.saveCity(cityDTO);

        Assert.assertEquals(cityDTOReturn.getIdCity(), cityDTO.getIdCity());
    }

    @Test
    @DisplayName("TEST - CityService - saveCity cities")
    public void saveCityTestStateNotExistException() {
        Mockito.when(cityRepository.save(city1)).thenReturn(city1);

        CityDTO cityDTO = CityMapper.makeCityDto(city1);

        Assert.assertThrows(StateNotExistException.class, () -> {
            cityService.saveCity(cityDTO);
        });
    }

    @Test
    @DisplayName("TEST - CityService - saveCity cities")
    public void saveCityTestCityExistException() {
        Mockito.when(cityRepository.save(city1)).thenReturn(city1);
        Mockito.when(stateRepository.findAll()).thenReturn(stateList);
        Mockito.when(cityRepository.existsByNameCity(city1.getNameCity())).thenReturn(true);

        CityDTO cityDTO = CityMapper.makeCityDto(city1);

        Assert.assertThrows(CityExistException.class, () -> {
            cityService.saveCity(cityDTO);
        });
    }

    @Test
    @DisplayName("TEST - CityService - updateCity cities")
    public void updateCityTestCityNotExistException() {
        CityDTO cityDTO = CityMapper.makeCityDto(city1);
        Assert.assertThrows(CityNotExistException.class, () -> {
            cityService.updateCity(999l, cityDTO);
        });
    }

    @Test
    @DisplayName("TEST - CityService - updateCity cities")
    public void updateCityTest() {
        Mockito.when(cityRepository.save(city1)).thenReturn(city1);
        Mockito.when(cityRepository.findById(1l)).thenReturn(Optional.of(city1));

        CityDTO cityDTO = CityMapper.makeCityDto(city1);

        CityDTO cityDTOReturn = cityService.updateCity(1L, cityDTO);

        Assert.assertEquals(cityDTOReturn.getIdCity(), cityDTO.getIdCity());
    }

    @Test
    @DisplayName("TEST - CityService - deleteCity cities")
    public void deleteCity() {
        Mockito.when(cityRepository.findById(1l)).thenReturn(Optional.of(city1));
        cityService.deleteCity(1L);
        Mockito.verify(cityRepository, Mockito.times(1)).delete(city1);
    }

    @Test
    @DisplayName("TEST - CityService - deleteCity cities")
    public void deleteCityCityNotExistException() {
        Assert.assertThrows(CityNotExistException.class, () -> {
            cityService.deleteCity(999l);
        });
    }

}