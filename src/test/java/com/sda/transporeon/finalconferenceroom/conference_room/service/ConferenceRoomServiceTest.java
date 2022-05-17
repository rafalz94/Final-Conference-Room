package com.sda.transporeon.finalconferenceroom.conference_room.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.Availability;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomResponse;
import com.sda.transporeon.finalconferenceroom.conference_room.repository.ConferenceRoomRepository;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.repository.OrganizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ConferenceRoomServiceTest {


    @MockBean
    private ConferenceRoomRepository conferenceRoomRepository;
    @MockBean
    private OrganizationRepository organizationRepository;

    @Autowired
    private ConferenceRoomService conferenceRoomService;

    @TestConfiguration
    static class ConferenceRoomServiceConfiguration {
        @Bean
        ConferenceRoomService conferenceRoomService(ConferenceRoomRepository conferenceRoomRepository, OrganizationRepository organizationRepository) {
            return new ConferenceRoomService(conferenceRoomRepository, new ConferenceRoomMapper(), organizationRepository);
        }
    }

    private List<ConferenceRoom> conferenceRooms;

    @BeforeEach
    void initList() {
        ConferenceRoom room1 = new ConferenceRoom();
        room1.setConferenceRoomName("room1");
        Organization organization1 = new Organization();
        organization1.setOrganizationName("organization1");
        room1.setLevel(10);
        room1.setSittingPlaces(30);
        room1.setStandingPlaces(10);
        room1.setOrganization(organization1);

        ConferenceRoom room2 = new ConferenceRoom();
        room2.setConferenceRoomName("room2");
        room2.setLevel(10);
        room2.setSittingPlaces(30);
        room2.setStandingPlaces(10);
        room2.setOrganization(organization1);

        ConferenceRoom room3 = new ConferenceRoom();
        room3.setConferenceRoomName("room3");
        Organization organization2 = new Organization();
        organization1.setOrganizationName("organization2");
        room3.setLevel(10);
        room3.setSittingPlaces(30);
        room3.setStandingPlaces(10);
        room3.setOrganization(organization2);
        conferenceRooms = new ArrayList<>(List.of(room1, room3));
    }

    @Test
    void ifAddConferenceRoomIsUsedThenAddedRoomShouldBeReturned() {
        //given
        ConferenceRoomRequest roomToAdd = new ConferenceRoomRequest();
        roomToAdd.setConferenceRoomName("room1");
        roomToAdd.setLevel(10);
        roomToAdd.setSittingPlaces(30);
        roomToAdd.setStandingPlaces(10);
        roomToAdd.setOrganizationName("organization1");

        ConferenceRoom addedRoom = new ConferenceRoom();
        addedRoom.setConferenceRoomName("room1");
        Organization organization1 = new Organization();
        organization1.setOrganizationName("organization1");
        addedRoom.setOrganization(organization1);
        addedRoom.setLevel(10);
        addedRoom.setSittingPlaces(30);
        addedRoom.setStandingPlaces(10);

        ConferenceRoom roomFromDb = new ConferenceRoom();
        roomFromDb.setConferenceRoomId(1L);
        roomFromDb.setConferenceRoomName("room1");
        Organization organization2 = new Organization();
        organization2.setOrganizationName("organization1");
        roomFromDb.setOrganization(organization2);
        roomFromDb.setLevel(10);
        roomFromDb.setSittingPlaces(30);
        roomFromDb.setStandingPlaces(10);
        roomFromDb.setConferenceRoomId(1L);

        Mockito.when(conferenceRoomRepository.save(addedRoom)).thenReturn(roomFromDb);
        Mockito.when(organizationRepository.findByOrganizationName("organization1")).thenReturn(Optional.of(organization2));
        //when
        ConferenceRoomResponse returnedRoom = conferenceRoomService.addConferenceRoom(roomToAdd);
        //then
        assertAll(
                () -> assertEquals(roomToAdd.getConferenceRoomName(), returnedRoom.getConferenceRoomName()),
                () -> assertEquals(roomToAdd.getLevel(), returnedRoom.getLevel()),
                () -> assertEquals(roomToAdd.getStandingPlaces(), returnedRoom.getStandingPlaces()),
                () -> assertEquals(roomToAdd.getSittingPlaces(), returnedRoom.getSittingPlaces())
        );
    }

    @Test
    void ifGetAllConferenceRoomsIsUsedThenListOfAllRoomsShouldBeReturned() {
        //given
        Mockito.when(conferenceRoomRepository.findAll()).thenReturn(conferenceRooms);
        //when
        List<ConferenceRoomResponse> rooms = conferenceRoomService.getAllConferenceRooms();
        //then
        assertEquals(3, rooms.size());
    }

    @Test
    void ifGetConferenceRoomByNameIsUsedThenResponseObjectShouldBeReturned() {
        //given
        ConferenceRoom roomFromDb = new ConferenceRoom();
        roomFromDb.setConferenceRoomName("room1");
        roomFromDb.setAvailability(Availability.YES);
        Organization organization1 = new Organization();
        organization1.setOrganizationName("organization1");
        roomFromDb.setOrganization(organization1);
        roomFromDb.setLevel(10);
        roomFromDb.setSittingPlaces(30);
        roomFromDb.setStandingPlaces(10);

        Mockito.when(conferenceRoomRepository.findByConferenceRoomName("room1")).thenReturn(Optional.of(roomFromDb));
        //when
        ConferenceRoomResponse returnedRoom = conferenceRoomService.getConferenceRoomByName("room1");
        //then
        assertAll(
                () -> assertEquals(roomFromDb.getConferenceRoomId(), returnedRoom.getConferenceRoomId()),
                () -> assertEquals(roomFromDb.getConferenceRoomName(), returnedRoom.getConferenceRoomName()),
                () -> assertEquals(roomFromDb.getLevel(), returnedRoom.getLevel()),
                () -> assertEquals(roomFromDb.getAvailability(), returnedRoom.getAvailability()),
                () -> assertEquals(roomFromDb.getStandingPlaces(), returnedRoom.getStandingPlaces()),
                () -> assertEquals(roomFromDb.getSittingPlaces(), returnedRoom.getSittingPlaces())
        );
    }

    @Test
    void ifDeleteConferenceRoomIsUsedThenRoomShouldBeDeleted() {
        //given
        ConferenceRoom roomToDelete = new ConferenceRoom();
        roomToDelete.setConferenceRoomName("room1");
        roomToDelete.setAvailability(Availability.YES);
        Organization organization1 = new Organization();
        organization1.setOrganizationName("organization1");
        roomToDelete.setOrganization(organization1);
        roomToDelete.setLevel(10);
        roomToDelete.setSittingPlaces(30);
        roomToDelete.setStandingPlaces(10);
        Mockito.when(conferenceRoomRepository.findByConferenceRoomName("room1")).thenReturn(Optional.of(roomToDelete));
        //when
        conferenceRoomService.deleteConferenceRoom("room1");
        //then
        Mockito.verify(conferenceRoomRepository).delete(roomToDelete);
    }

    //TODO
    @Test
    void ifGetAllByOrganizationNameIsUsedThenListOfAllRoomsShouldBeReturned() {
        //given
        ConferenceRoom room1 = new ConferenceRoom();
        room1.setConferenceRoomName("room1");
        Organization organization1 = new Organization();
        organization1.setOrganizationName("organization1");
        room1.setLevel(10);
        room1.setSittingPlaces(30);
        room1.setStandingPlaces(10);
        room1.setOrganization(organization1);

        ConferenceRoom room3 = new ConferenceRoom();
        room3.setConferenceRoomName("room3");
        Organization organization2 = new Organization();
        organization1.setOrganizationName("organization1");
        room3.setLevel(10);
        room3.setSittingPlaces(30);
        room3.setStandingPlaces(10);
        room3.setOrganization(organization2);
        List<ConferenceRoom> filteredRooms = conferenceRooms.stream().filter(conferenceRoom -> conferenceRoom.getOrganization().getOrganizationName().equals("organization1")).collect(Collectors.toList());

        Mockito.when(conferenceRoomRepository.findByOrganization_OrganizationName("organization1")).thenReturn(filteredRooms);
        //when
        List<ConferenceRoomResponse> returnedRooms = conferenceRoomService.getAllByOrganizationName("organization1");
        //then
        assertEquals(2, returnedRooms.size());

    }

    //TODO
    @Test
    void ifUpdateConferenceRoomIsUsedThenUpdatedRoomShouldBeReturned() {
        //given
        //when
        //then
    }


}