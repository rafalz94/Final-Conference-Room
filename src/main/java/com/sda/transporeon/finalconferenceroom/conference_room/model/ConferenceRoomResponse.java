package com.sda.transporeon.finalconferenceroom.conference_room.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConferenceRoomResponse {

    private Long conferenceRoomId;
    private String conferenceRoomName;
    private Integer level;
    private Boolean availability;
    private Integer sittingPlaces;
    private Integer standingPlaces;
    private String organizationName;
}
