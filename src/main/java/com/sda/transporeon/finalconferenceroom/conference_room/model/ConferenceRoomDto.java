package com.sda.transporeon.finalconferenceroom.conference_room.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConferenceRoomDto {

    private String conferenceRoomName;
    private Short level;
    private Boolean availability;
    private Short sittingPlaces;
    private Short standingPlaces;
    private String organizationName;
}
