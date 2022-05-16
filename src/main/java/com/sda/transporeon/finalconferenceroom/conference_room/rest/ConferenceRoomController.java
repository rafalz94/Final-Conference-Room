package com.sda.transporeon.finalconferenceroom.conference_room.rest;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomResponse;
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
    public ResponseEntity<List<ConferenceRoomResponse>> getAllConferenceRooms() {
        return ResponseEntity.status(HttpStatus.OK).body(conferenceRoomService.getAllConferenceRooms());
    }

    @GetMapping("/{conferenceRoomName}")
    public ResponseEntity<ConferenceRoomResponse> getConferenceRoomById(@PathVariable("conferenceRoomName") String conferenceRoomName) {
        return ResponseEntity.status(HttpStatus.OK).body(conferenceRoomService.getConferenceRoomByName(conferenceRoomName));
    }

    @GetMapping("/organization/{organizationName}")
    public ResponseEntity<List<ConferenceRoomResponse>> getAllByOrganizationName(@PathVariable("organizationName") String organizationName) {
        return ResponseEntity.status(HttpStatus.OK).body(conferenceRoomService.getAllByOrganizationName(organizationName));
    }

    @PostMapping("/add")
    public ResponseEntity<ConferenceRoomResponse> addConferenceRoom(@RequestBody ConferenceRoomRequest conferenceRoomRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.addConferenceRoom(conferenceRoomRequest));
    }

    @DeleteMapping("/delete/{conferenceRoomName}")
    public ResponseEntity<Void> deleteConferenceRoom(@PathVariable("conferenceRoomName") String conferenceRoomName) {
        conferenceRoomService.deleteConferenceRoom(conferenceRoomName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{conferenceRoomName}")
    public ResponseEntity<ConferenceRoomResponse> updateConferenceRoom(@PathVariable("conferenceRoomName") String conferenceRoomName, @RequestBody ConferenceRoomRequest conferenceRoomRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conferenceRoomService.updateConferenceRoomById(conferenceRoomName, conferenceRoomRequest));
    }
}
