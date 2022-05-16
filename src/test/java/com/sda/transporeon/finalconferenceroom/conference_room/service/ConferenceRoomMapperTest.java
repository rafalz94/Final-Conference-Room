package com.sda.transporeon.finalconferenceroom.conference_room.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.Availability;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomResponse;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConferenceRoomMapperTest {

    private final ConferenceRoomMapper conferenceRoomMapper = new ConferenceRoomMapper();

    @Test
    void when_fromEntityToResponse_is_used_then_response_object_should_be_returned() {
        //given
        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setConferenceRoomId(1L);
        conferenceRoom.setConferenceRoomName("room1");
        conferenceRoom.setLevel(10);
        conferenceRoom.setAvailability(Availability.YES);
        conferenceRoom.setStandingPlaces(30);
        conferenceRoom.setSittingPlaces(10);
        Organization organization = new Organization();
        organization.setOrganizationId(1L);
        organization.setOrganizationName("organization1");
        conferenceRoom.setOrganization(organization);
        //when
        ConferenceRoomResponse conferenceRoomResponse = conferenceRoomMapper.fromEntityToResponse(conferenceRoom);
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
    void when_fromRequestToEntity_is_used_then_entity_object_should_be_returned() {
        //given
        ConferenceRoomRequest conferenceRoomRequest=new ConferenceRoomRequest();
        conferenceRoomRequest.setConferenceRoomName("room1");
        conferenceRoomRequest.setLevel(10);
        conferenceRoomRequest.setStandingPlaces(30);
        conferenceRoomRequest.setSittingPlaces(10);
        conferenceRoomRequest.setOrganizationName("organization1");
        //when
        ConferenceRoom conferenceRoom=conferenceRoomMapper.fromRequestToEntity(conferenceRoomRequest);
        //then
        assertAll(
                () -> assertEquals(conferenceRoomRequest.getConferenceRoomName(),conferenceRoom.getConferenceRoomName()),
                () -> assertEquals(conferenceRoomRequest.getSittingPlaces(),conferenceRoom.getSittingPlaces()),
                () -> assertEquals(conferenceRoomRequest.getStandingPlaces(),conferenceRoom.getStandingPlaces()),
                () -> assertEquals(conferenceRoomRequest.getLevel(),conferenceRoom.getLevel()),
                () -> assertEquals(conferenceRoomRequest.getOrganizationName(),conferenceRoom.getOrganization().getOrganizationName())
        );
    }

}