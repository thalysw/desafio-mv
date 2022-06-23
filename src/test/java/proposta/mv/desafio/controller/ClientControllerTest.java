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
import proposta.mv.desafio.models.CityModel;
import proposta.mv.desafio.models.ClientModel;
import proposta.mv.desafio.models.StateModel;
import proposta.mv.desafio.repository.CityRepository;
import proposta.mv.desafio.repository.ClientRepository;
import proposta.mv.desafio.repository.StateRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ClientControllerTest {

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

    List<CityModel> cityModelList = new ArrayList<>();
    List<ClientModel> clientModelList = new ArrayList<>();
    List<ClientModel> clientModelList2 = new ArrayList<>();

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
    public void getAllClientsTest() throws Exception {
        mockMvc.perform(get("/client")).andExpect(status().isOk());
    }

    @Test
    public void getAllClientsTestNotFoundException() throws Exception {
        cleanUp();
        mockMvc.perform(get("/client")).andExpect(status().isNotFound());
    }

    @Test
    public void getClientByIdTest() throws Exception {
        mockMvc.perform(get("/client/getClientId/"+cl1.getIdClient())).andExpect(status().isOk());
    }

    @Test
    public void getClientByIdTestNotFoundException() throws Exception {
        mockMvc.perform(get("/client/getClientId/999")).andExpect(status().isNotFound());
    }

    @Test
    public void getClientByNameTest() throws Exception {
        mockMvc.perform(get("/client/getClientName?clientName="+cl1.getNameComplete())).andExpect(status().isOk());
    }

    @Test
    public void getClientByNameTestNotFoundException() throws Exception {
        mockMvc.perform(get("/client/getClientName?clientName=NAOTEM")).andExpect(status().isNotFound());
    }

//    @Test
//    public void postClientTestCityNotFoundException() throws Exception {
//        cleanUp();
//        String json = objectMapper.writeValueAsString(cl1);
//
//        mockMvc.perform(post("/client")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(json)).andExpect(status().isNotFound());
//    }

//    @Test
//    public void postClientTest() throws Exception {
//        cl3.setNameComplete("TESTE_PASSANDO");
//        String json = objectMapper.writeValueAsString(cl3);
//
//        mockMvc.perform(post("/client")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(json)).andExpect(status().isCreated());
//    }

//    @Test
//    public void updateClientTestNotFoundException() throws Exception {
//        cleanUp();
//        String json = objectMapper.writeValueAsString(cl3);
//
//        mockMvc.perform(put("/client/updateClient/1")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(json)).andExpect(status().isNotFound());
//    }

//    @Test
//    public void updateClientTest() throws Exception {
//        String json = objectMapper.writeValueAsString(cl3);
//
//        mockMvc.perform(put("/client/updateClient/"+cl3.getIdClient())
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(json)).andExpect(status().isOk());
//    }

    @Test
    public void deleteCityNotFoundException() throws Exception {
        cleanUp();
        mockMvc.perform(delete("/client/deleteClient/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCity() throws Exception {
        mockMvc.perform(delete("/client/deleteClient/"+cl3.getIdClient()))
                .andExpect(status().isOk());
    }
}
