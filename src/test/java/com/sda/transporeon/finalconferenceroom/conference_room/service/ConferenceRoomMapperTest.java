package com.sda.transporeon.finalconferenceroom.conference_room.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomResponse;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConferenceRoomMapperTest {

    private final ConferenceRoomMapper conferenceRoomMapper = new ConferenceRoomMapper();

    @Test
    void ifFromEntityToResponseIsUsedThenResponseObjectShouldBeReturned() {
        //given
        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setConferenceRoomId(1L);
        conferenceRoom.setConferenceRoomName("room1");
        conferenceRoom.setLevel(10);
        conferenceRoom.setAvailability(true);
        conferenceRoom.setStandingPlaces(30);
        conferenceRoom.setSittingPlaces(10);
        Organization organization = new Organization();
        organization.setOrganizationId(1L);
        organization.setOrganizationName("organization1");
        conferenceRoom.setOrganization(organization);
        //when
        ConferenceRoomResponse conferenceRoomResponse = conferenceRoomMapper.mapFromEntityToResponse(conferenceRoom);
        //then
        assertAll(
                () -> assertEquals(conferenceRoom.getConferenceRoomName(), conferenceRoomResponse.getConferenceRoomName()),
                () -> assertEquals(conferenceRoom.getLevel(), conferenceRoomResponse.getLevel()),
                () -> assertEquals(conferenceRoom.getStandingPlaces(), conferenceRoomResponse.getStandingPlaces()),
                () -> assertEquals(conferenceRoom.getSittingPlaces(), conferenceRoomResponse.getSittingPlaces()),
                () -> assertEquals(conferenceRoom.getOrganization().getOrganizationName(), conferenceRoomResponse.getOrganizationName()),
                () -> assertEquals(conferenceRoom.getAvailability(), conferenceRoomResponse.getAvailability())
        );

    }

    @Test
    void ifFromRequestToEntityIsUsedThenEntityObjectShouldBeReturned() {
        //given
        ConferenceRoomRequest conferenceRoomRequest = new ConferenceRoomRequest();
        conferenceRoomRequest.setConferenceRoomName("room1");
        conferenceRoomRequest.setLevel(10);
        conferenceRoomRequest.setStandingPlaces(30);
        conferenceRoomRequest.setSittingPlaces(10);
        conferenceRoomRequest.setOrganizationName("organization1");
        //when
        ConferenceRoom conferenceRoom = conferenceRoomMapper.mapFromRequestToEntity(conferenceRoomRequest);
        //then
        assertAll(
                () -> assertEquals(conferenceRoomRequest.getConferenceRoomName(), conferenceRoom.getConferenceRoomName()),
                () -> assertEquals(conferenceRoomRequest.getSittingPlaces(), conferenceRoom.getSittingPlaces()),
                () -> assertEquals(conferenceRoomRequest.getStandingPlaces(), conferenceRoom.getStandingPlaces()),
                () -> assertEquals(conferenceRoomRequest.getLevel(), conferenceRoom.getLevel()),
                () -> assertEquals(conferenceRoomRequest.getOrganizationName(), conferenceRoom.getOrganization().getOrganizationName())
        );
    }

}