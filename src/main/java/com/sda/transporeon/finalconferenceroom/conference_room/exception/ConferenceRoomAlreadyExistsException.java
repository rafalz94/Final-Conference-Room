package com.sda.transporeon.finalconferenceroom.conference_room.exception;

public class ConferenceRoomAlreadyExistsException extends IllegalArgumentException {
    public ConferenceRoomAlreadyExistsException(String conferenceRoom) {
        super(String.format("Conference room %s already exists.", conferenceRoom));
    }
}
