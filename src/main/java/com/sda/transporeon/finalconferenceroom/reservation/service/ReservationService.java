package com.sda.transporeon.finalconferenceroom.reservation.service;

import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    //TODO
    public List<ReservationResponse> getAllReservations() {
        return List.of(new ReservationResponse(), new ReservationResponse());
    }

    //TODO
    public ReservationResponse getReservationById(Integer id) {
        return null;
    }

    //TODO
    public ReservationResponse addReservation(ReservationResponse reservationResponse) {
        return null;
    }

    //TODO
    public void deleteReservationById(Integer id) {
    }

    //TODO
    public ReservationResponse updateReservationById(Integer id, ReservationResponse reservationResponse) {
        return null;
    }
}
