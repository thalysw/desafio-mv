package proposta.mv.desafio.controller;

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
import proposta.mv.desafio.dto.StateDTO;
import proposta.mv.desafio.models.CityModel;
import proposta.mv.desafio.models.ClientModel;
import proposta.mv.desafio.models.StateModel;
import proposta.mv.desafio.repository.CityRepository;
import proposta.mv.desafio.repository.ClientRepository;
import proposta.mv.desafio.repository.StateRepository;
import proposta.mv.desafio.serviceMapper.StateMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StateControllerTest {

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

    StateModel s1 = new StateModel();
    StateModel s2 = new StateModel();
    StateModel s3 = new StateModel();

    ClientModel cl1 = new ClientModel();
    ClientModel cl2 = new ClientModel();
    ClientModel cl3 = new ClientModel();

    @BeforeEach
    public void setUp () {
        List<CityModel> cityModelList = new ArrayList<>();
        List<ClientModel> clientModelList = new ArrayList<>();
        List<ClientModel> clientModelList2 = new ArrayList<>();


        StateModel state1 = new StateModel(null, "São Paulo", "SP", cityModelList);
        StateModel state2 = new StateModel(null, "Rio de Janeiro", "RJ", cityModelList);
        StateModel state3 = new StateModel(null, "Paraiba", "PB", cityModelList);

        s1 = stateRepository.save(state1);
        s2 = stateRepository.save(state2);
        s3 = stateRepository.save(state3);

        CityModel city1 = new CityModel(null, "Campinas", "SP", s1, clientModelList);
        CityModel city2 = new CityModel(null, "Rio de Janeiro", "RJ", s1, clientModelList);
        CityModel city3 = new CityModel(null, "Sao Paulo", "SP", s2, clientModelList2);

        c1 = cityRepository.save(city1);
        c2 = cityRepository.save(city2);
        c3 = cityRepository.save(city3);

        ClientModel client1 = new ClientModel(null, "Thalys Wel", "M", LocalDate.now(), "25", c1);
        ClientModel client2 = new ClientModel(null, "Melissa", "F", LocalDate.now(), "25", c2);
        ClientModel client3 = new ClientModel(null, "Vezaro Silva", "M", LocalDate.now(), "25", c3);

        cl1 = clientRepository.save(client1);
        cl2 = clientRepository.save(client2);
        cl3 = clientRepository.save(client3);

    }

    @AfterEach
    public void cleanUp() {
        clientRepository.deleteAll();
        cityRepository.deleteAll();
        stateRepository.deleteAll();
    }

    @Test
    public void getAllStatesTest() throws Exception {
        mockMvc.perform(get("/state")).andExpect(status().isOk());
    }

    @Test
    public void getAllStatesTestNotFoundException() throws Exception {
        cleanUp();
        mockMvc.perform(get("/state")).andExpect(status().isNotFound());
    }

    @Test
    public void getStateByIdTest() throws Exception {
        mockMvc.perform(get("/state/getState/"+s1.getIdState()))
                .andExpect(status().isOk());
    }

    @Test
    public void getStateByIdTestNotFoundException() throws Exception {
        mockMvc.perform(get("/state/getStateId/999")).andExpect(status().isNotFound());
    }

//    @Test
//    public void getCitiesByStateNameTest() throws Exception {
//        mockMvc.perform(get("/getState/city?stateName="+s3.getNameState())).andExpect(status().isOk());
//    }

    @Test
    public void getCitiesByStateNameTestNotFoundException() throws Exception {
        mockMvc.perform(get("/getState/city?stateName=NAOTEM")).andExpect(status().isNotFound());
    }

    @Test
    public void postStateTestStateAlreadyTakenException() throws Exception {
        String json = objectMapper.writeValueAsString(s1);

        mockMvc.perform(post("/state")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(status().isConflict());
    }

    @Test
    public void postStateTest() throws Exception {
        s1.setNameState("TESTE_PASSANDO");
        String json = objectMapper.writeValueAsString(s1);

        mockMvc.perform(post("/city")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(status().isCreated());
    }

    @Test
    public void updateStateTestNotFoundException() throws Exception {
        cleanUp();
        StateDTO stateDTO = StateMapper.makeStateDto(s1);
        String json = objectMapper.writeValueAsString(stateDTO);

        mockMvc.perform(put("/updateState/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(status().isNotFound());
    }

    @Test
    public void updateStateTest() throws Exception {
        String json = objectMapper.writeValueAsString(s1);

        mockMvc.perform(put("/state/updateState/"+s1.getIdState())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json)).andExpect(status().isOk());
    }

    @Test
    public void deleteStateNotFoundException() throws Exception {
        cleanUp();
        mockMvc.perform(delete("/state/deleteState/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteState() throws Exception {
        clientRepository.deleteAll();
        cityRepository.deleteAll();
        mockMvc.perform(delete("/state/deleteState/"+s1.getIdState()))
                .andExpect(status().isOk());
    }
}
