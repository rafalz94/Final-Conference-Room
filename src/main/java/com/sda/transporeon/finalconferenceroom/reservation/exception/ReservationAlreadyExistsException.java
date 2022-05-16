package com.sda.transporeon.finalconferenceroom.reservation.exception;

public class ReservationAlreadyExistsException extends IllegalArgumentException {

    public ReservationAlreadyExistsException(String reservation) {
        super(String.format("Reservation %s already exists.", reservation));
    }
}
