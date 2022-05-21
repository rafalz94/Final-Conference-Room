package com.sda.transporeon.finalconferenceroom.reservation.service;

import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomNotFoundException;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.repository.ConferenceRoomRepository;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationAlreadyExistsException;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationDateNotValidException;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationNotFoundException;
import com.sda.transporeon.finalconferenceroom.reservation.model.Reservation;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationRequest;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationResponse;
import com.sda.transporeon.finalconferenceroom.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ReservationServiceTest {

    @MockBean
    private ConferenceRoomRepository conferenceRoomRepository;
    @MockBean
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationService reservationService;

    @TestConfiguration
    static class ReservationServiceConfiguration {
        @Bean
        ReservationService ReservationService(ReservationRepository reservationRepository, ConferenceRoomRepository conferenceRoomRepository) {
            return new ReservationService(reservationRepository, conferenceRoomRepository, new ReservationMapper());
        }
    }

    private List<Reservation> reservations;

    @BeforeEach
    void initList() {
        Reservation reservation1 = new Reservation(1L, "reservation1", LocalDateTime.MIN, LocalDateTime.MAX,
                new ConferenceRoom(1L, "room1", 10, true, 50, 15,
                        new Organization(1L, "organization1", null), null));
        Reservation reservation2 = new Reservation(2L, "reservation2", LocalDateTime.MIN, LocalDateTime.MAX,
                new ConferenceRoom(2L, "room2", 10, true, 40, 10,
                        new Organization(2L, "organization2", null), null));
        Reservation reservation3 = new Reservation(3L, "reservation3", LocalDateTime.MIN, LocalDateTime.MAX,
                new ConferenceRoom(3L, "room3", 10, true, 35, 10,
                        new Organization(3L, "organization3", null), null));

        reservations = List.of(reservation1, reservation2, reservation3);
    }

    @Test
    void ifGetReservationByIdIsUsedThenReservationShouldBeReturned() {
        //given
        Reservation reservationFromDb = new Reservation(1L, "reservation1", LocalDateTime.MIN, LocalDateTime.MAX,
                new ConferenceRoom(1L, "room1", 10, true, 50, 10,
                        new Organization(1L, "organization1", null), null));
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservationFromDb));
        String startDate = ReservationMapper.dateParser(reservationFromDb.getReservationStartDate());
        String endDate = ReservationMapper.dateParser(reservationFromDb.getReservationEndDate());
        //when
        ReservationResponse returnedReservation = reservationService.getReservationById(1L);
        //then
        assertAll(
                () -> assertEquals(reservationFromDb.getReservationId(), returnedReservation.getReservationId()),
                () -> assertEquals(reservationFromDb.getReservationIdentifier(), returnedReservation.getReservationIdentifier()),
                () -> assertEquals(startDate, returnedReservation.getReservationStartDate()),
                () -> assertEquals(endDate, returnedReservation.getReservationEndDate()),
                () -> assertEquals(reservationFromDb.getConferenceRoom().getConferenceRoomName(), returnedReservation.getConferenceRoomName())
        );
    }

    @Test
    void ifGetReservationByIdIsUsedAndReservationDoesNotExistThenExceptionShouldBeThrown() {
        //given
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.getReservationById(1L);
        });
    }

    @Test
    void ifDeleteReservationByIdIsUsedThenReservationShouldBeDeleted() {
        //given
        Reservation reservationToDelete = new Reservation();
        reservationToDelete.setReservationId(1L);
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservationToDelete));
        //when
        reservationService.deleteReservationById(1L);
        //then
        Mockito.verify(reservationRepository).delete(reservationToDelete);
    }

    @Test
    void ifDeleteReservationByIdIsUsedAndReservationDoesNotExistThenExceptionShouldBeThrown() {
        //given
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.deleteReservationById(1L);
        });
    }

    @Test
    void ifAddReservationIsUsedThenAddedReservationShouldBeReturned() {
        //given
        LocalDateTime startDate = LocalDateTime.now().plusHours(1);
        LocalDateTime endDate = LocalDateTime.now().plusHours(2);

        ConferenceRoom conferenceRoom = new ConferenceRoom(1L, "room1", 10, true,
                50, 10, new Organization(1L, "organization1", null), null);
        Reservation addedReservation = new Reservation();
        addedReservation.setReservationIdentifier("reservation1");
        addedReservation.setReservationStartDate(startDate);
        addedReservation.setReservationEndDate(endDate);
        addedReservation.setConferenceRoom(conferenceRoom);

        Reservation reservationFromDb = new Reservation(1L, "reservation1",
                startDate, endDate, conferenceRoom);
        Mockito.when(conferenceRoomRepository.findByConferenceRoomNameAndAvailabilityEquals("room1", true)).thenReturn(Optional.of(conferenceRoom));
        Mockito.when(reservationRepository.save(addedReservation)).thenReturn(reservationFromDb);

        ReservationRequest reservationToAdd = new ReservationRequest();
        reservationToAdd.setReservationIdentifier("reservation1");
        reservationToAdd.setReservationStartDate(startDate);
        reservationToAdd.setReservationEndDate(endDate);
        reservationToAdd.setConferenceRoomName("room1");

        //when
        ReservationResponse returnedReservation = reservationService.addReservation(reservationToAdd);
        //then
        assertAll(
                () -> assertEquals(reservationFromDb.getReservationId(), returnedReservation.getReservationId()),
                () -> assertEquals(reservationFromDb.getReservationIdentifier(), returnedReservation.getReservationIdentifier()),
                () -> assertEquals(reservationFromDb.getConferenceRoom().getConferenceRoomName(), returnedReservation.getConferenceRoomName())
        );
    }

    @Test
    void ifAddReservationIsUsedAndConferenceRoomDoesNotExistThenExceptionShouldBeThrown() {
        //given
        LocalDateTime startDate = LocalDateTime.now().plusHours(1);
        LocalDateTime endDate = LocalDateTime.now().plusHours(2);
        //when
        Mockito.when(conferenceRoomRepository.findByConferenceRoomNameAndAvailabilityEquals("room1", true)).thenReturn(Optional.empty());
        //then
        assertThrows(ConferenceRoomNotFoundException.class, () -> {
            reservationService.addReservation(new ReservationRequest(1L, "reservation1", startDate, endDate, "room1"));
        });
    }

    @Test
    void ifAddReservationIsUsedAndReservationAlreadyExistThenExceptionShouldBeThrown() {
        //given
        LocalDateTime startDate = LocalDateTime.now().plusHours(1);
        LocalDateTime endDate = LocalDateTime.now().plusHours(2);
        Mockito.when(reservationRepository.findByConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual("room1", endDate, startDate))
                .thenReturn(Optional.of(new Reservation()));
        //when
        //then
        assertThrows(ReservationAlreadyExistsException.class, () -> {
            reservationService.addReservation(new ReservationRequest(1L, "reservation1", startDate, endDate, "room1"));
        });
    }

    @Test
    void ifAddReservationIsUsedAndReservationIdentifierIsNotUniqueThenExceptionShouldBeThrown() {
        //given
        LocalDateTime startDate = LocalDateTime.now().plusHours(1);
        LocalDateTime endDate = LocalDateTime.now().plusHours(2);
        Mockito.when(reservationRepository.findByReservationIdentifier("reservation1")).thenReturn(Optional.of(new Reservation()));
        //when
        //then
        assertThrows(ReservationAlreadyExistsException.class, () -> {
            reservationService.addReservation(new ReservationRequest(1L, "reservation1", startDate, endDate, "room1"));
        });
    }

    @Test
    void ifAddReservationIsUsedAndEndDateIsBeforeStartDateThenExceptionShouldBeThrown() {
        //given
        LocalDateTime startDate = LocalDateTime.now().plusHours(2);
        LocalDateTime endDate = LocalDateTime.now().plusHours(1);
        //when
        //then
        assertThrows(ReservationDateNotValidException.class, () -> {
            reservationService.addReservation(new ReservationRequest(1L, "reservation1", startDate, endDate, "room1"));
        });
    }

    @Test
    void ifAddReservationIsUsedAndStartDateIsBeforeCurrentDateThenExceptionShouldBeThrown() {
        //given
        LocalDateTime startDate = LocalDateTime.now().minusHours(1);
        LocalDateTime endDate = LocalDateTime.now().plusHours(2);
        //when
        //then
        assertThrows(ReservationDateNotValidException.class, () -> {
            reservationService.addReservation(new ReservationRequest(1L, "reservation1", startDate, endDate, "room1"));
        });
    }

    @Test
    void ifGetAllReservationsIsUsedThenListOfReservationsShouldBeReturned() {
        //given
        Mockito.when(reservationRepository.findAll()).thenReturn(reservations);
        //when
        List<ReservationResponse> returnedReservations = reservationService.getAllReservations();
        //then
        assertAll(
                () -> assertFalse(returnedReservations.isEmpty()),
                () -> assertEquals(3, returnedReservations.size())
        );
    }

    @Test
    void ifUpdateReservationIsUsedThenUpdatedReservationShouldBeReturned() {
        //given
        LocalDateTime startDate = LocalDateTime.now().plusHours(1);
        LocalDateTime endDate = LocalDateTime.now().plusHours(2);

        LocalDateTime updatedStartDate = LocalDateTime.now().plusHours(5);
        LocalDateTime updatedEndDate = LocalDateTime.now().plusHours(6);

        ConferenceRoom conferenceRoom = new ConferenceRoom(1L, "room1", 10, true,
                50, 10, new Organization(1L, "organization1", null), null);

        Reservation reservationFromDb = new Reservation(1L, "reservation1",
                startDate, endDate, conferenceRoom);

        Reservation updatedReservationFromDb = new Reservation(1L, "reservation1",
                updatedStartDate, updatedEndDate, conferenceRoom);

        ReservationRequest updatedReservation = new ReservationRequest(1L, "reservation1",
                updatedStartDate, updatedEndDate, "room1");

        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservationFromDb));
        Mockito.when(conferenceRoomRepository.findByConferenceRoomNameAndAvailabilityEquals("room1", true)).thenReturn(Optional.of(conferenceRoom));
        Mockito.when(reservationRepository.save(updatedReservationFromDb)).thenReturn(updatedReservationFromDb);

        //when
        ReservationResponse returnedReservation = reservationService.updateReservation(updatedReservation);
        //then
        assertAll(
                () -> assertEquals(updatedReservation.getReservationIdentifier(), returnedReservation.getReservationIdentifier()),
                () -> assertEquals(ReservationMapper.dateParser(updatedReservation.getReservationStartDate()), returnedReservation.getReservationStartDate()),
                () -> assertEquals(ReservationMapper.dateParser(updatedReservation.getReservationEndDate()), returnedReservation.getReservationEndDate())
        );
    }

    @Test
    void ifUpdateReservationIsUsedAndRoomDoesNotExistThenExceptionShouldBeThrown() {
        //given
        LocalDateTime startDate = LocalDateTime.now().plusHours(1);
        LocalDateTime endDate = LocalDateTime.now().plusHours(2);

        ConferenceRoom conferenceRoom = new ConferenceRoom(1L, "room1", 10, true,
                50, 10, new Organization(1L, "organization1", null), null);
        Reservation reservationFromDb = new Reservation(1L, "reservation1",
                startDate, endDate, conferenceRoom);

        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservationFromDb));
        Mockito.when(conferenceRoomRepository.findByConferenceRoomNameAndAvailabilityEquals("room2", true)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(ConferenceRoomNotFoundException.class, () -> {
            reservationService.updateReservation(new ReservationRequest(1L, "updatedReservation", startDate, endDate, "room2"));
        });
    }

    @Test
    void ifUpdateReservationIsUsedAndReservationDoesNotExistThenExceptionShouldBeThrown() {
        //given
        LocalDateTime startDate = LocalDateTime.now().plusHours(1);
        LocalDateTime endDate = LocalDateTime.now().plusHours(2);
        //when
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.empty());
        //then
        assertThrows(ReservationNotFoundException.class, () -> {
            reservationService.updateReservation(new ReservationRequest(1L, "updatedReservation", startDate, endDate, "room2"));
        });
    }

    @Test
    void ifUpdateReservationIsUsedAndDateIsNotValidThenExceptionShouldBeThrown() {
        //given
        LocalDateTime startDate = LocalDateTime.now().plusHours(1);
        LocalDateTime endDate = LocalDateTime.now().plusHours(2);

        ConferenceRoom conferenceRoom = new ConferenceRoom(1L, "room1", 10, true,
                50, 10, new Organization(1L, "organization1", null), null);
        Reservation reservationFromDb = new Reservation(1L, "reservation1",
                startDate, endDate, conferenceRoom);
        //when
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservationFromDb));
        Mockito.when(conferenceRoomRepository.findByConferenceRoomNameAndAvailabilityEquals("room1", true)).thenReturn(Optional.of(conferenceRoom));
        //then
        assertThrows(ReservationDateNotValidException.class, () -> {
            reservationService.updateReservation(new ReservationRequest(1L, "updatedReservation", startDate.minusHours(5), endDate, "room1"));
        });
    }

    @Test
    void ifUpdateReservationIsUsedAndDateIsNotAvailableThenExceptionShouldBeThrown() {
        //given
        LocalDateTime startDate = LocalDateTime.now().plusHours(1);
        LocalDateTime endDate = LocalDateTime.now().plusHours(2);

        LocalDateTime updatedStartDate = LocalDateTime.now().plusHours(5);
        LocalDateTime updatedEndDate = LocalDateTime.now().plusHours(6);

        ConferenceRoom conferenceRoom = new ConferenceRoom(1L, "room1", 10, true,
                50, 10, new Organization(1L, "organization1", null), null);

        Reservation reservationFromDb = new Reservation(1L, "reservation1",
                startDate, endDate, conferenceRoom);

        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservationFromDb));
        Mockito.when(conferenceRoomRepository.findByConferenceRoomNameAndAvailabilityEquals("room1", true))
                .thenReturn(Optional.of(conferenceRoom));
        Mockito.when(reservationRepository.findByReservationIdNotAndConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(
                1L, "room1", updatedEndDate, updatedStartDate)).thenReturn(Optional.of(new Reservation()));
        //when
        //then
        assertThrows(ReservationAlreadyExistsException.class, () -> {
            reservationService.updateReservation(new ReservationRequest(1L, "reservation1", updatedStartDate, updatedEndDate, "room1"));
        });
    }


}