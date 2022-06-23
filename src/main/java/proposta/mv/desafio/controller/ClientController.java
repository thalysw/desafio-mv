package proposta.mv.desafio.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proposta.mv.desafio.dto.ClientDTO;
import proposta.mv.desafio.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    ClientService clientService;

    @GetMapping
    @ApiOperation(value = "List of all clients")
    public ResponseEntity<List<ClientDTO>> getAllClients(){
        log.info("CONTROLLER - Using getAllClients method");
        List<ClientDTO> clients = clientService.findAll();
        log.info("Clients: {}", clients);
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @GetMapping("/getClientId/{id}")
    @ApiOperation(value = "Details of a client")
    public ResponseEntity<ClientDTO> getClient (@PathVariable(value = "id") Long id) {
        log.info("CONTROLLER - Using getClient method");
        ClientDTO client = clientService.findById(id);
        log.info("Client: {}", client);
        return ResponseEntity.status(HttpStatus.OK).body(client);

    }

    @GetMapping("/getClientName")
    @ApiOperation(value = "Details of a client by name")
    public ResponseEntity<List<ClientDTO>> getClientByNameComplete(@RequestParam(value = "clientName") String clientName) {
        log.info("CONTROLLER - Using getClientByNameComplete method");
        List<ClientDTO> client = clientService.findByNameComplete(clientName);
        log.info("Client: {}", client);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @PostMapping()
    @ApiOperation(value = "Adding a client")
    public ResponseEntity<ClientDTO> postClient(@RequestBody ClientDTO clientDTO) {
        log.info("CONTROLLER - Using postClient method");
        ClientDTO savedClient = clientService.saveClient(clientDTO);
        log.info("Client saved: {}", savedClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/updateClient/{id}")
    @ApiOperation(value = "Updating a client")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable(value = "id") Long id, @RequestParam (value = "name") String nameClient) {
        log.info("CONTROLLER - Using updateClient method");
        ClientDTO client = clientService.updateClient(id, nameClient);
        log.info("Client updated: {}", client);
        return ResponseEntity.status(HttpStatus.OK).body(client);
    }

    @DeleteMapping("/deleteClient/{id}")
    @ApiOperation(value = "Removing a client")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") Long id) {
        log.info("CONTROLLER - Using deleteClient method");
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client delete success.");
    }
}
