package com.sda.transporeon.finalconferenceroom.reservation.exception;

public class ReservationAlreadyExistsException extends IllegalArgumentException {

    public ReservationAlreadyExistsException() {
        super("Reservation already exists.");
    }
}
