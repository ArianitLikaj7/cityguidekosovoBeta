package com.arianit.cityguideKosovo.controller;

import com.arianit.cityguideKosovo.dto.CityDto;
import com.arianit.cityguideKosovo.entity.City;
import com.arianit.cityguideKosovo.entity.TypeOfGastronome;
import com.arianit.cityguideKosovo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cities")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/createCity")
    public ResponseEntity<CityDto> saveCity(@RequestBody City city) {
        CityDto savedCity = cityService.saveCity(city);
        return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
    }



    // Get Gastronomes based on cityId and types of gastronome
    @GetMapping("/{cityId}/gastronomes")
    public ResponseEntity<CityDto> getCityWithGastronomesByType(@PathVariable long cityId,
                                                                @RequestParam TypeOfGastronome type) {
        CityDto city = cityService.getCityWithGastronomesByType(cityId, type);
        if (city != null) {
            return new ResponseEntity<>(city, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getCityById/{cityId}")
    public ResponseEntity<CityDto> getCityById(@PathVariable long cityId) {
        CityDto city = cityService.getCityById(cityId);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
    @GetMapping("/getCities")
    public ResponseEntity<List<CityDto>> getAllCities() {
        List<CityDto> cities = cityService.getAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PutMapping("/updateCity/{cityId}")
    public ResponseEntity<CityDto> updateCity(@PathVariable Long cityId,
                                              @RequestBody City city){
        return new ResponseEntity<>(cityService.updateCity(cityId,city),HttpStatus.OK);

    }
    @DeleteMapping("/deleteCity/{cityId}")
    public ResponseEntity<String> deleteCity(@PathVariable long cityId) {
        cityService.deleteCity(cityId);
        return new ResponseEntity<>("City deleted successfully", HttpStatus.NO_CONTENT);
    }
}
