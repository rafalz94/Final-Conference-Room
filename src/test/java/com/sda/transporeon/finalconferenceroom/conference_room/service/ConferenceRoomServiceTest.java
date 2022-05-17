package com.sda.transporeon.finalconferenceroom.conference_room.service;

import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomAlreadyExistsException;
import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomNotFoundException;
import com.sda.transporeon.finalconferenceroom.conference_room.model.Availability;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomResponse;
import com.sda.transporeon.finalconferenceroom.conference_room.repository.ConferenceRoomRepository;
import com.sda.transporeon.finalconferenceroom.organization.exception.OrganizationNotFoundException;
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
        conferenceRooms = new ArrayList<>(List.of(room1, room2, room3));
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

        ConferenceRoom roomFromDb = new ConferenceRoom(1L, "room1", 10,
                Availability.YES, 50, 15,
                new Organization(1L,"organization1",null), null);
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
    void ifAddConferenceRoomIsUsedAndRoomAlreadyExistThenExceptionShouldBeThrown() {
        //given
        Mockito.when(conferenceRoomRepository.findByConferenceRoomName("room1")).thenReturn(Optional.of(new ConferenceRoom()));
        //when
        //then
        assertThrows(ConferenceRoomAlreadyExistsException.class, () -> {
            conferenceRoomService.addConferenceRoom(new ConferenceRoomRequest(1L,
                    "organization1", "room1", 10, 50, 10));
        });
    }

    @Test
    void ifAddConferenceRoomIsUsedAndOrganizationDoesNotExistThenExceptionShouldBeThrown() {
        //given
        Mockito.when(organizationRepository.findByOrganizationName("organization1")).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(OrganizationNotFoundException.class, () -> {
            conferenceRoomService.addConferenceRoom(new ConferenceRoomRequest(1L,
                    "organization1", "room1", 10, 50, 10));
        });
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
    void ifGetConferenceRoomByIdIsUsedThenResponseObjectShouldBeReturned() {
        //given
        ConferenceRoom roomFromDb = new ConferenceRoom(1L, "room1", 10,
                Availability.YES, 50, 15,
                new Organization(1L,"organization1",null), null);

        Mockito.when(conferenceRoomRepository.findById(1L)).thenReturn(Optional.of(roomFromDb));
        //when
        ConferenceRoomResponse returnedRoom = conferenceRoomService.getConferenceRoomById(1L);
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
    void ifGetConferenceRoomByIdIsUsedAndRoomDoesNotExistThenExceptionShouldBeThrown() {
        //given
        Mockito.when(conferenceRoomRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(ConferenceRoomNotFoundException.class, () -> {
            conferenceRoomService.getConferenceRoomById(1L);
        });
    }

    @Test
    void ifDeleteConferenceRoomIsUsedThenRoomShouldBeDeleted() {
        //given
        ConferenceRoom roomToDelete = new ConferenceRoom(1L, "room1", 10,
                Availability.YES, 50, 15,
                new Organization(1L,"organization1",null), null);
        Mockito.when(conferenceRoomRepository.findById(1L)).thenReturn(Optional.of(roomToDelete));
        //when
        conferenceRoomService.deleteConferenceRoom(1L);
        //then
        Mockito.verify(conferenceRoomRepository).delete(roomToDelete);
    }

    @Test
    void ifDeleteConferenceRoomIsUsedAndRoomDoesNotExistThenExceptionShouldBeThrown() {
        //given
        Mockito.when(conferenceRoomRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(ConferenceRoomNotFoundException.class, () -> {
            conferenceRoomService.deleteConferenceRoom(1L);
        });
    }

    @Test
    void ifGetAllByOrganizationNameIsUsedThenListOfAllRoomsShouldBeReturned() {
        //given
        ConferenceRoom room1 = new ConferenceRoom(1L, "room1", 10,
                Availability.YES, 50, 15, new Organization(1L,"organization1",null), null);

        ConferenceRoom room3 = new ConferenceRoom(2L, "room2", 10,
                Availability.YES, 50, 15, new Organization(1L,"organization1",null), null);

        Mockito.when(conferenceRoomRepository.findByOrganization_OrganizationName("organization1")).thenReturn(List.of(room1, room3));
        //when
        List<ConferenceRoomResponse> returnedRooms = conferenceRoomService.getAllByOrganizationName("organization1");
        //then
        assertEquals(2, returnedRooms.size());

    }

    @Test
    void ifUpdateConferenceRoomIsUsedThenUpdatedRoomShouldBeReturned() {
        //given
        ConferenceRoom roomFromDb = new ConferenceRoom(1L, "room1", 10,
                Availability.YES, 50, 15, new Organization(), null);

        ConferenceRoom updatedRoomFromDb = new ConferenceRoom(1L, "updatedName", 10,
                Availability.YES, 40, 10, new Organization(), null);

        Mockito.when(conferenceRoomRepository.findById(1L)).thenReturn(Optional.of(roomFromDb));
        Mockito.when(conferenceRoomRepository.save(updatedRoomFromDb)).thenReturn(updatedRoomFromDb);
        //when
        ConferenceRoomResponse returnedRoom = conferenceRoomService.updateConferenceRoom(new ConferenceRoomRequest(1L,
                "organization1", "updatedName", 10, 40, 10));
        //then
        assertAll(
                () -> assertEquals(updatedRoomFromDb.getConferenceRoomName(), returnedRoom.getConferenceRoomName()),
                ()->assertEquals(updatedRoomFromDb.getSittingPlaces(),returnedRoom.getSittingPlaces()),
                ()->assertEquals(updatedRoomFromDb.getStandingPlaces(),returnedRoom.getStandingPlaces())
        );
    }

    @Test
    void ifUpdateConferenceRoomIsUsedAndRoomDoesNotExistThenExceptionShouldBeThrown() {
        //given
        Mockito.when(conferenceRoomRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(OrganizationNotFoundException.class, () -> {
            conferenceRoomService.addConferenceRoom(new ConferenceRoomRequest(1L,
                    "organization1", "room1", 10, 50, 10));
        });
    }

    @Test
    void ifUpdateConferenceRoomIsUsedAndRoomNameIsNotUniqueThenExceptionShouldBeThrown() {
        //given
        Mockito.when(conferenceRoomRepository.findById(1L)).thenReturn(Optional.of(new ConferenceRoom()));
        Mockito.when(conferenceRoomRepository.findByConferenceRoomName("room1")).thenReturn(Optional.of(new ConferenceRoom()));
        //when
        //then
        assertThrows(ConferenceRoomAlreadyExistsException.class, () -> {
            conferenceRoomService.updateConferenceRoom(new ConferenceRoomRequest(1L,
                    "organization1", "room1", 10, 50, 10));
        });
    }


}