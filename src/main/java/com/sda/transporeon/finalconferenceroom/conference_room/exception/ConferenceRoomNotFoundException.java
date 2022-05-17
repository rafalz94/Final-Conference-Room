package com.sda.transporeon.finalconferenceroom.conference_room.exception;

import java.util.NoSuchElementException;

public class ConferenceRoomNotFoundException extends NoSuchElementException {

    public ConferenceRoomNotFoundException() {
        super("Conference room not found.");
    }
}
