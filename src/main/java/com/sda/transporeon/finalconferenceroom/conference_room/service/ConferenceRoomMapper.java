package com.sda.transporeon.finalconferenceroom.conference_room.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomResponse;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import org.springframework.stereotype.Component;

@Component
public class ConferenceRoomMapper {

    public ConferenceRoom mapFromRequestToEntity(final ConferenceRoomRequest request) {
        final ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setConferenceRoomName(request.getConferenceRoomName());
        conferenceRoom.setLevel(request.getLevel());
        conferenceRoom.setSittingPlaces(request.getSittingPlaces());
        conferenceRoom.setStandingPlaces(request.getStandingPlaces());
        conferenceRoom.setAvailability(stringToBoolean(request.getAvailability()));
        final Organization organization = new Organization();
        organization.setOrganizationName(request.getOrganizationName());
        conferenceRoom.setOrganization(organization);

        return conferenceRoom;
    }

    public ConferenceRoomResponse mapFromEntityToResponse(final ConferenceRoom conferenceRoom) {
        final ConferenceRoomResponse response = new ConferenceRoomResponse();
        response.setConferenceRoomId(conferenceRoom.getConferenceRoomId());
        response.setConferenceRoomName(conferenceRoom.getConferenceRoomName());
        response.setLevel(conferenceRoom.getLevel());
        response.setAvailability(booleanToString(conferenceRoom.getAvailability()));
        response.setSittingPlaces(conferenceRoom.getSittingPlaces());
        response.setStandingPlaces(conferenceRoom.getStandingPlaces());
        response.setOrganizationName(conferenceRoom.getOrganization().getOrganizationName());

        return response;
    }

    public String booleanToString(Boolean availability) {
        return availability ? "YES" : "NO";
    }

    public static Boolean stringToBoolean(String availability) {
        return availability.equals("YES");
    }
}
