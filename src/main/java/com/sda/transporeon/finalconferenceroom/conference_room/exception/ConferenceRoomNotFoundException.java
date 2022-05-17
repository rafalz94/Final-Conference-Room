package com.sda.transporeon.finalconferenceroom.conference_room.exception;

import java.util.NoSuchElementException;

public class ConferenceRoomNotFoundException extends NoSuchElementException {

    public ConferenceRoomNotFoundException(String conferenceRoom) {
        super(String.format("Conference room %s not found.", conferenceRoom));
    }
}
