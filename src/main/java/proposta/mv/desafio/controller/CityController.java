package proposta.mv.desafio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proposta.mv.desafio.dto.CityDTO;
import proposta.mv.desafio.service.CityService;

import java.util.List;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/city")
public class CityController {

    Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    CityService cityService;

    @GetMapping
    @ApiOperation(value = "List of all cities")
    public ResponseEntity<List<CityDTO>> getAllCities() {
        log.info("CONTROLLER - Using getAllCities method");
        List<CityDTO> cities = cityService.findAll();
        log.info("Cities: {}", cities);
        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }

    @GetMapping("/cityId/{id}")
    @ApiOperation(value = "Details of a city")
    public ResponseEntity<CityDTO> getCity(@PathVariable(value = "id") Long id) {
        log.info("CONTROLLER - Using getCity method");
        CityDTO city = cityService.findById(id);
        log.info("City: {}", city);
        return ResponseEntity.status(HttpStatus.OK).body(city);
    }

    @GetMapping("/cityName")
    @ApiOperation(value = "Details of a city by name")
    public ResponseEntity<CityDTO> getCityByName(@RequestParam(value = "cityName") String nameCity) {
        log.info("CONTROLLER - Using getCityByName method");
        CityDTO city = cityService.findByNameCity(nameCity);
        log.info("City: {}", city);
        return ResponseEntity.status(HttpStatus.OK).body(city);
    }

    @PostMapping()
    @ApiOperation(value = "Adding a city")
    public ResponseEntity<CityDTO> postCity(@RequestBody CityDTO cityDTO) {
        log.info("CONTROLLER - Using postCity method");
        CityDTO city = cityService.saveCity(cityDTO);
        log.info("City saved: {}", city);
        return ResponseEntity.status(HttpStatus.CREATED).body(city);
    }

    @PutMapping("/updateCity/{id}")
    @ApiOperation(value = "Updating a city")
    public ResponseEntity<CityDTO> updateCity(@PathVariable(value = "id") Long id, @RequestBody CityDTO cityDTO) {
        log.info("CONTROLLER - Using updateCity method");
        CityDTO city = cityService.updateCity(id, cityDTO);
        log.info("City updated: {}", city);
        return ResponseEntity.status(HttpStatus.OK).body(city);
    }

    @DeleteMapping("/deleteCity/{id}")
    @ApiOperation(value = "Removing a city")
    public ResponseEntity<Object> deleteCity(@PathVariable(value = "id") Long id) {
        log.info("CONTROLLER - Using deleteCity method");
        cityService.deleteCity(id);
        return ResponseEntity.status(HttpStatus.OK).body("City delete success.");
    }

}
