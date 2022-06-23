package proposta.mv.desafio.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proposta.mv.desafio.dto.CityDTO;
import proposta.mv.desafio.dto.StateDTO;
import proposta.mv.desafio.service.StateService;

import java.util.List;

@RestController
@RequestMapping("/state")
public class StateController {

    Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    StateService stateService;

    @GetMapping
    @ApiOperation(value = "List of all states")
    public ResponseEntity<List<StateDTO>> getAllStates() {
        List<StateDTO> states = stateService.findAll();
        log.info("States: {}", states);
        return ResponseEntity.status(HttpStatus.OK).body(states);
    }

    @GetMapping("/getState/{id}")
    @ApiOperation(value = "Details of a state")
    public ResponseEntity<StateDTO> getState(@PathVariable(value = "id") Long id) {
        StateDTO state = stateService.findById(id);
        log.info("State: {}", state);
        return ResponseEntity.status(HttpStatus.OK).body(state);
    }

    @GetMapping("/getState/city")
    @ApiOperation(value = "Details of a cities by state")
    public ResponseEntity<List<CityDTO>> getCitiesByStateName(@RequestParam (value = "stateName") String stateName) {
        List<CityDTO> cityDTOS = stateService.citiesByStateName(stateName);
        log.info("Cities: {}", cityDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(cityDTOS);
    }

    @PostMapping()
    @ApiOperation(value = "Adding a state")
    public ResponseEntity<StateDTO> postState(@RequestBody StateDTO stateDTO) {
        StateDTO state = stateService.saveState(stateDTO);
        log.info("State saved: {}", state);
        return ResponseEntity.status(HttpStatus.CREATED).body(state);
    }

    @PutMapping("/updateState/{id}")
    @ApiOperation(value = "Updating a state")
    public ResponseEntity<StateDTO> updateState (@PathVariable(value = "id") Long id, @RequestBody StateDTO stateDTO) {
        StateDTO state = stateService.updateState(id, stateDTO);
        log.info("State updated: {}", state);
        return ResponseEntity.status(HttpStatus.OK).body(state);
    }

    @DeleteMapping("/deleteState/{id}")
    @ApiOperation(value = "Removing a state")
    public ResponseEntity<Object> deleteState(@PathVariable(value = "id") Long id) {
        stateService.deleteState(id);
        return ResponseEntity.status(HttpStatus.OK).body("State delete success.");
    }

}
