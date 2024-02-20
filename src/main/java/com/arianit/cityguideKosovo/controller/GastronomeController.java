package com.arianit.cityguideKosovo.controller;
import com.arianit.cityguideKosovo.dto.GastronomeDto;
import com.arianit.cityguideKosovo.entity.Gastronome;
import com.arianit.cityguideKosovo.service.GastronomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/gastronomes")
public class GastronomeController {

    private final GastronomeService gastronomeService;

    @Autowired
    public GastronomeController(GastronomeService gastronomeService) {
        this.gastronomeService = gastronomeService;
    }

    @PostMapping( "/createGastronome/{cityId}")
    public ResponseEntity<GastronomeDto> createGastronomeForLocation(
            @PathVariable Long cityId,
            @RequestBody Gastronome gastronome) {
        GastronomeDto createdGastronome = gastronomeService.createGastronome(cityId,gastronome);
        return new ResponseEntity<>(createdGastronome, HttpStatus.CREATED);
    }

    @GetMapping("/getGastronomeById/{cityId}/{gastronomeId}")
    public ResponseEntity<GastronomeDto> getGastronomeById(@PathVariable Long cityId,
                                                           @PathVariable Long gastronomeId) {
        GastronomeDto gastronome = gastronomeService.getGastronomeById(cityId,gastronomeId);
        return new ResponseEntity<>(gastronome, HttpStatus.OK);
    }

    @GetMapping("/getGastronomesByLocationId/{cityId}")
    public ResponseEntity<List<GastronomeDto>> getGastronomesByLocationId(@PathVariable long cityId) {
        List<GastronomeDto> gastronomes = gastronomeService.getGastronomesByLocationId(cityId);
        return new ResponseEntity<>(gastronomes, HttpStatus.OK);
    }

    @PutMapping("/updateGastronome/{cityId}/{gastronomeId}")
    public ResponseEntity<GastronomeDto> updateGastronome(@PathVariable Long cityId,
                                                          @PathVariable Long gastronomeId,
                                                          @RequestBody Gastronome gastronome){
        return new ResponseEntity<>(gastronomeService.updateGastronome(cityId,gastronomeId,gastronome),HttpStatus.OK);
    }

    @DeleteMapping("/deleteGastronome/{cityId}/{gastronomeId}")
    public ResponseEntity<String> deleteGastronomeById(@PathVariable long cityId,
                                                       @PathVariable long gastronomeId) {
        gastronomeService.deleteGastronomeById(cityId,gastronomeId);
        return new ResponseEntity<>("Gastronome deleted successfully", HttpStatus.NO_CONTENT);
    }
}