package proposta.mv.desafio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import proposta.mv.desafio.models.CityModel;
import proposta.mv.desafio.models.ClientModel;
import proposta.mv.desafio.models.StateModel;
import proposta.mv.desafio.repository.CityRepository;
import proposta.mv.desafio.repository.ClientRepository;
import proposta.mv.desafio.repository.StateRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ClientRepository clientRepository;

    CityModel c1 = new CityModel();
    CityModel c2 = new CityModel();
    CityModel c3 = new CityModel();

    @BeforeEach
    public void setUp () {
        List<CityModel> cityModelList = new ArrayList<>();
        List<ClientModel> clientModelList = new ArrayList<>();
        List<ClientModel> clientModelList2 = new ArrayList<>();


        StateModel state1 = new StateModel(null, "São Paulo", "SP", cityModelList);
        StateModel state2 = new StateModel(null, "Rio de Janeiro", "RJ", cityModelList);

        StateModel s1 = stateRepository.save(state1);
        StateModel s2 = stateRepository.save(state2);

        CityModel city1 = new CityModel(null, "Campinas", "SP", s1, clientModelList);
        CityModel city2 = new CityModel(null, "Rio de Janeiro", "RJ", s1, clientModelList);
        CityModel city3 = new CityModel(null, "São Paulo", "SP", s2, clientModelList2);

        c1 = cityRepository.save(city1);
        c2 = cityRepository.save(city2);
        c3 = cityRepository.save(city3);

        ClientModel client1 = new ClientModel(null, "Thalys Wel", "M", LocalDate.now(), "25", c1);
        ClientModel client2 = new ClientModel(null, "Melissa", "F", LocalDate.now(), "25", c2);
        ClientModel client3 = new ClientModel(null, "Vezaro Silva", "M", LocalDate.now(), "25", c3);

        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);

    }

    @AfterEach
    public void cleanUp() {
        clientRepository.deleteAll();
        cityRepository.deleteAll();
        stateRepository.deleteAll();
    }

    @Test
    public void getAllCitiesTest() throws Exception {
        mockMvc.perform(get("/city")).andExpect(status().isOk());
    }

    @Test
    public void getAllCitiesTestNotFoundException() throws Exception {
        cleanUp();
        mockMvc.perform(get("/city")).andExpect(status().isNotFound());
    }

    @Test
    public void getCityByIdTest() throws Exception {
        mockMvc.perform(get("/city/cityId/"+c1.getIdCity())).andExpect(status().isOk());
    }

    @Test
    public void getCityByIdTestNotFoundException() throws Exception {
        mockMvc.perform(get("/city/cityId/999")).andExpect(status().isNotFound());
    }

    @Test
    public void getCityByNameTest() throws Exception {
        mockMvc.perform(get("/city/cityName?cityName="+c1.getNameCity())).andExpect(status().isOk());
    }

    @Test
    public void getCityByNameTestNotFoundException() throws Exception {
        mockMvc.perform(get("/city/cityName?cityName=NAOTEM")).andExpect(status().isNotFound());
    }

    @Test
    public void postCityTestCityAlreadyTakenException() throws Exception {
        String json = objectMapper.writeValueAsString(c3);

        mockMvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(status().isConflict());
    }

    @Test
    public void postCityTestStateNotFoundException() throws Exception {
        cleanUp();
        String json = objectMapper.writeValueAsString(c3);

        mockMvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(status().isNotFound());
    }

    @Test
    public void postCityTest() throws Exception {
        c3.setNameCity("TESTE_PASSANDO");
        String json = objectMapper.writeValueAsString(c3);

        mockMvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(status().isCreated());
    }

    @Test
    public void updateCityTestNotFoundException() throws Exception {
        cleanUp();
        String json = objectMapper.writeValueAsString(c3);

        mockMvc.perform(put("/city/updateCity/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(status().isNotFound());
    }

    @Test
    public void updateCityTest() throws Exception {
        String json = objectMapper.writeValueAsString(c3);

        mockMvc.perform(put("/city/updateCity/"+c3.getIdCity())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(status().isOk());
    }

    @Test
    public void deleteCityNotFoundException() throws Exception {
        cleanUp();
        mockMvc.perform(delete("/city/deleteCity/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCity() throws Exception {
        clientRepository.deleteAll();
        mockMvc.perform(delete("/city/deleteCity/"+c3.getIdCity()))
                .andExpect(status().isOk());
    }
}
