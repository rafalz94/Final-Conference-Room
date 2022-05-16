package com.sda.transporeon.finalconferenceroom.reservation.exception;

import java.util.NoSuchElementException;

public class ReservationNotFoundException extends NoSuchElementException {

    public ReservationNotFoundException(String reservation) {
        super(String.format("Reservation %s not found.", reservation));
    }
}
