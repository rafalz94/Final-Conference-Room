package com.sda.transporeon.finalconferenceroom.reservation.rest;

import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationRequest;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationResponse;
import com.sda.transporeon.finalconferenceroom.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping()
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservations());
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable("reservationId") Long reservationId) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationById(reservationId));
    }

    @PostMapping("/add")
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.addReservation(reservationRequest));
    }

    @DeleteMapping("/delete/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("reservationId") Long reservationId) {
        reservationService.deleteReservationById(reservationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //    @PutMapping("/update/{reservationIdentifier}")
//    public ResponseEntity<ReservationResponse> updateReservation(@PathVariable("reservationIdentifier") String reservationIdentifier, @RequestBody ReservationRequest reservationRequest) {
//        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.updateReservation(reservationIdentifier, reservationRequest));
//    }
    @PutMapping("/update")
    public ResponseEntity<ReservationResponse> updateReservation(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.updateReservation(reservationRequest));
    }
}
