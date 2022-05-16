package com.sda.transporeon.finalconferenceroom.conference_room.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConferenceRoomResponse {

    private Integer conferenceRoomId;
    private String conferenceRoomName;
    private Integer level;
    private Availability availability;
    private Integer sittingPlaces;
    private Integer standingPlaces;
    private String organizationName;
}
