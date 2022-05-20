package com.sda.transporeon.finalconferenceroom.reservation.exception;

public class ReservationDateNotValidException extends IllegalArgumentException{
    public ReservationDateNotValidException() {
        super("Reservation date incorrect.");
    }
}
