package com.sda.transporeon.finalconferenceroom.reservation.rest;

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

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationById(id));
    }

    @PostMapping()
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationResponse reservationResponse) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.addReservation(reservationResponse));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Integer id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReservationResponse> updateReservation(@PathVariable("id") Integer id, @RequestBody ReservationResponse reservationResponse) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.updateReservationById(id, reservationResponse));
    }
}
