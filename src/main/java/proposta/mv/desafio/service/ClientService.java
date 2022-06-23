package proposta.mv.desafio.service;

import proposta.mv.desafio.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> findAll();
    ClientDTO findById(Long id);
    ClientDTO saveClient(ClientDTO clientDTO);
    List<ClientDTO> findByNameComplete(String clientName);
    ClientDTO updateClient(Long id, String clientName);
    void deleteClient(Long id);
}
