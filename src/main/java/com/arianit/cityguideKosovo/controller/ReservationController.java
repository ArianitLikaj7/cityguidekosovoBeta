package com.arianit.cityguideKosovo.controller;

import com.arianit.cityguideKosovo.dto.ReservationDto;
import com.arianit.cityguideKosovo.entity.Reservation;
import com.arianit.cityguideKosovo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping("/createReservation/{gastronomeId}")
    public ResponseEntity<ReservationDto> saveReservation(
            @PathVariable Long gastronomeId,
            @RequestBody Reservation reservation) {
        ReservationDto savedReservation = reservationService.saveReservation(gastronomeId, reservation);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    @GetMapping("/getReservationById/{gastronomeId}/{reservationId}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long gastronomeId,
                                                             @PathVariable Long reservationId){
        return new ResponseEntity<>(reservationService.getReservationById(gastronomeId,reservationId),HttpStatus.OK);
    }

    @GetMapping("/getReservationsByGastronomeId/{gastronomeId}")
    public ResponseEntity<List<ReservationDto>> getReservationByGastronomeId(@PathVariable Long gastronomeId) {
        List<ReservationDto> reservation = reservationService.getReservationsByGastronomeId(gastronomeId);
        return new ResponseEntity<>(reservation,HttpStatus.OK);
    }


    @PutMapping("/updateReservation/{gastronomeId}/{reservationId}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long gastronomeId,
                                                            @PathVariable Long reservationId,
                                                            @RequestBody Reservation reservation){
        return new ResponseEntity<>(reservationService.updateReservation(gastronomeId,reservationId,reservation),HttpStatus.OK);
    }

    @DeleteMapping("/deleteReservation/{gastronomeId}/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long gastronomeId,
                                                  @PathVariable Long reservationId) {
        reservationService.deleteReservation(gastronomeId,reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}