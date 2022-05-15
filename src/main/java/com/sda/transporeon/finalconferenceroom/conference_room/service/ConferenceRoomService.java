package com.sda.transporeon.finalconferenceroom.conference_room.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceRoomService {

    //TODO
    public List<ConferenceRoomDto> getAllConferenceRooms() {
        return List.of(new ConferenceRoomDto(), new ConferenceRoomDto());
    }

    //TODO
    public ConferenceRoomDto getConferenceRoomById(Integer id) {
        return null;
    }

    //TODO
    public ConferenceRoomDto addConferenceRoom(ConferenceRoomDto conferenceRoomDto) {
        return null;
    }

    //TODO
    public void deleteConferenceRoomById(Integer id) {
    }

    //TODO
    public ConferenceRoomDto updateConferenceRoomById(Integer id, ConferenceRoomDto conferenceRoomDto) {
        return null;
    }
}
