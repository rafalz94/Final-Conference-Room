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

    @GetMapping("/{reservationIdentifier}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable("reservationIdentifier") String reservationIdentifier) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationByIdentifier(reservationIdentifier));
    }

    @PostMapping("/add")
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.addReservation(reservationRequest));
    }

    @DeleteMapping("/delete/{reservationIdentifier}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("reservationIdentifier") String reservationIdentifier) {
        reservationService.deleteReservationByIdentifier(reservationIdentifier);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{reservationIdentifier}")
    public ResponseEntity<ReservationResponse> updateReservation(@PathVariable("reservationIdentifier") String reservationIdentifier, @RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.updateReservation(reservationIdentifier, reservationRequest));
    }
}
