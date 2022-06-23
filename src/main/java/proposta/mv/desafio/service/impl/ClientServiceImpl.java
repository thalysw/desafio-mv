package proposta.mv.desafio.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proposta.mv.desafio.controller.ClientController;
import proposta.mv.desafio.dto.CityDTO;
import proposta.mv.desafio.dto.ClientDTO;
import proposta.mv.desafio.dto.StateDTO;
import proposta.mv.desafio.exceptions.business.CityNotExistException;
import proposta.mv.desafio.exceptions.business.ClientNotExistException;
import proposta.mv.desafio.models.CityModel;
import proposta.mv.desafio.models.ClientModel;
import proposta.mv.desafio.models.StateModel;
import proposta.mv.desafio.repository.CityRepository;
import proposta.mv.desafio.repository.ClientRepository;
import proposta.mv.desafio.service.CityService;
import proposta.mv.desafio.service.ClientService;
import proposta.mv.desafio.serviceMapper.CityMapper;
import proposta.mv.desafio.serviceMapper.ClientMapper;
import proposta.mv.desafio.serviceMapper.StateMapper;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CityRepository cityRepository;

    @Override
    public List<ClientDTO> findAll() {
        log.info("SERVICE - Using findAll method!");
        List<ClientModel> clientModel = clientRepository.findAll();
        if(clientModel.isEmpty()){
            throw new ClientNotExistException("Client not exist in database!");
        }
        log.info("Client found successfully!");

        log.info("SERVICE - End of method findAll!");
        return ClientMapper.makeListClientDTO(clientModel);

    }

    @Override
    public ClientDTO findById(Long id) {
        log.info("SERVICE - Using findById method!");
        ClientModel clientModel = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotExistException("Client not found!"));
        log.info("SERVICE - End of method findById!");
        return ClientMapper.makeClientDto(clientModel);
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        log.info("SERVICE - Using saveClient method!");
        ClientModel clientModel = ClientMapper.makeClientModel(clientDTO);

        CityModel city = cityRepository.findById(clientDTO.getCity().getIdCity())
                .orElseThrow(() -> new CityNotExistException("City not exist in database!"));
        log.info("City found successfully!");

        clientModel.setCity(city);

        ClientModel clientSaved = clientRepository.save(clientModel);

        log.info("SERVICE - End of method saveClient!");
        return ClientMapper.makeClientDtoSave(clientSaved);
    };

    @Override
    public List<ClientDTO> findByNameComplete(String clientName) {
        log.info("SERVICE - Using findByNameComplete method!");
        List<ClientModel> clientModel = clientRepository.findByNameComplete(clientName);
        if(clientModel.isEmpty()){
            throw new ClientNotExistException("Client not found!");
        }
        log.info("SERVICE - End of method findByNameComplete!");
        return ClientMapper.makeListClientDTO(clientModel);
    }

    @Override
    public ClientDTO updateClient(Long id, String name) {
        log.info("SERVICE - Using updateClient method!");
        ClientModel clientModel = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotExistException("Client not found!"));
        clientModel.setNameComplete(name);

        clientRepository.save(clientModel);

        log.info("SERVICE - End of method update!");
        return ClientMapper.makeClientDto(clientModel);
    }

    @Override
    public void deleteClient(Long id) {
        log.info("SERVICE - Using deleteClient method!");
        ClientModel clientModel = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotExistException("Client not found!"));
        clientRepository.delete(clientModel);
        log.info("SERVICE - End of method delete!");
    }
}
