package com.sda.transporeon.finalconferenceroom.conference_room.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceRoomService {

    //TODO
    public List<ConferenceRoomResponse> getAllConferenceRooms() {
        return List.of();
    }

    //TODO
    public ConferenceRoomResponse getConferenceRoomByName(String conferenceRoomName) {
        return null;
    }

    //TODO
    public ConferenceRoomResponse addConferenceRoom(ConferenceRoomRequest conferenceRoomRequest) {
        return null;
    }

    //TODO
    public void deleteConferenceRoom(String conferenceRoomName) {
    }

    //TODO
    public ConferenceRoomResponse updateConferenceRoomById(String conferenceRoomName, ConferenceRoomRequest conferenceRoomRequest) {
        return null;
    }
}
