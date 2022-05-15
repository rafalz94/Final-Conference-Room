package com.sda.transporeon.finalconferenceroom.conference_room.rest;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomDto;
import com.sda.transporeon.finalconferenceroom.conference_room.service.ConferenceRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/conference-room")
public class ConferenceRoomController {

    private final ConferenceRoomService conferenceRoomService;

    @Autowired
    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping()
    public ResponseEntity<List<ConferenceRoomDto>> getAllConferenceRooms() {
        return ResponseEntity.status(HttpStatus.OK).body(conferenceRoomService.getAllConferenceRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceRoomDto> getConferenceRoomById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(conferenceRoomService.getConferenceRoomById(id));
    }

    @PostMapping
    public ResponseEntity<ConferenceRoomDto> addConferenceRoom(@RequestBody ConferenceRoomDto conferenceRoomDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.addConferenceRoom(conferenceRoomDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConferenceRoom(@PathVariable("id") Integer id) {
        conferenceRoomService.deleteConferenceRoomById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ConferenceRoomDto> updateConferenceRoom(@PathVariable("id") Integer id, @RequestBody ConferenceRoomDto conferenceRoomDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.updateConferenceRoomById(id, conferenceRoomDto));
    }
}
