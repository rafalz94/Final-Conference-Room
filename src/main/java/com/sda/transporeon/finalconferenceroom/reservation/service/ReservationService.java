package com.sda.transporeon.finalconferenceroom.reservation.service;

import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    //TODO
    public List<ReservationDto> getAllReservations() {
        return List.of(new ReservationDto(), new ReservationDto());
    }

    //TODO
    public ReservationDto getReservationById(Integer id) {
        return null;
    }

    //TODO
    public ReservationDto addReservation(ReservationDto reservationDto) {
        return null;
    }

    //TODO
    public void deleteReservationById(Integer id) {
    }

    //TODO
    public ReservationDto updateReservationById(Integer id, ReservationDto reservationDto) {
        return null;
    }
}
