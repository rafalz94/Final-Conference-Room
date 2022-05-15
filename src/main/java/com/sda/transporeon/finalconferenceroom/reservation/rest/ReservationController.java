package com.sda.transporeon.finalconferenceroom.reservation.rest;

import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationDto;
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
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationById(id));
    }

    @PostMapping()
    public ResponseEntity<ReservationDto> addReservation(@RequestBody ReservationDto reservationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.addReservation(reservationDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Integer id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable("id") Integer id, @RequestBody ReservationDto reservationDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.updateReservationById(id, reservationDto));
    }
}
