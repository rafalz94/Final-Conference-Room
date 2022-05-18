package com.sda.transporeon.finalconferenceroom.reservation.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.repository.ConferenceRoomRepository;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationNotFoundException;
import com.sda.transporeon.finalconferenceroom.reservation.model.Reservation;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationResponse;
import com.sda.transporeon.finalconferenceroom.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
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

    @Test
    void ifGetReservationByIdIsUsedThenReservationShouldBeReturned() {
        //given
        Reservation reservationFromDb = new Reservation(1L, "reservation1", LocalDateTime.MIN, LocalDateTime.MAX,
                new ConferenceRoom(1L, "room1", 10, true, 50, 10,
                        new Organization(1L, "organization1", null), null));
        Mockito.when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservationFromDb));
        //when
        ReservationResponse returnedReservation = reservationService.getReservationById(1L);
        //then
        assertAll(
                () -> assertEquals(reservationFromDb.getReservationId(), returnedReservation.getReservationId()),
                () -> assertEquals(reservationFromDb.getReservationIdentifier(), returnedReservation.getReservationIdentifier()),
                () -> assertEquals(reservationFromDb.getReservationStartDate(), returnedReservation.getReservationStartDate()),
                () -> assertEquals(reservationFromDb.getReservationEndDate(), returnedReservation.getReservationEndDate()),
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


}