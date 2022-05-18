package com.sda.transporeon.finalconferenceroom.reservation.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.reservation.model.Reservation;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationRequest;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationMapperTest {
    private final ReservationMapper reservationMapper = new ReservationMapper();

    @Test
    void ifMapFromRequestToEntityIsUsedThenEntityObjectShouldBeReturned() {
        //given
        ReservationRequest reservationRequest = new ReservationRequest(1L, "reservation1", LocalDateTime.MIN, LocalDateTime.MAX,
                "room1");
        //when
        Reservation reservation = reservationMapper.mapFromRequestToEntity(reservationRequest);
        //then
        assertAll(
                () -> assertEquals(reservationRequest.getReservationIdentifier(), reservation.getReservationIdentifier()),
                () -> assertEquals(reservationRequest.getConferenceRoomName(), reservation.getConferenceRoom().getConferenceRoomName()),
                () -> assertEquals(reservationRequest.getReservationStartDate(), reservation.getReservationStartDate()),
                () -> assertEquals(reservationRequest.getReservationEndDate(), reservation.getReservationEndDate())
        );
    }

    @Test
    void ifMapFromEntityToResponseIsUsedThenResponseObjectShouldBeReturned() {
        //given
        Reservation reservation = new Reservation(1L, "reservation1", LocalDateTime.MIN, LocalDateTime.MAX,
                new ConferenceRoom(1L, "room1", 10, true, 50, 10,
                        new Organization(1L, "organization1", null), null));
        String startDate = ReservationMapper.dateParser(reservation.getReservationStartDate());
        String endDate = ReservationMapper.dateParser(reservation.getReservationEndDate());
        //when
        ReservationResponse reservationResponse = reservationMapper.mapFromEntityToResponse(reservation);

        //then
        assertAll(
                () -> assertEquals(reservation.getReservationIdentifier(), reservationResponse.getReservationIdentifier()),
                () -> assertEquals(reservation.getConferenceRoom().getConferenceRoomName(), reservationResponse.getConferenceRoomName()),
                () -> assertEquals(startDate, reservationResponse.getReservationStartDate()),
                () -> assertEquals(endDate, reservationResponse.getReservationEndDate())
        );
    }
}