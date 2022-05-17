package com.sda.transporeon.finalconferenceroom.reservation.exception;

import java.util.NoSuchElementException;

public class ReservationNotFoundException extends NoSuchElementException {

    public ReservationNotFoundException() {
        super("Reservation not found.");
    }
}
