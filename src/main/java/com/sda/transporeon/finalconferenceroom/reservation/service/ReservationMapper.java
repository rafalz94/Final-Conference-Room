package com.sda.transporeon.finalconferenceroom.reservation.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.reservation.model.Reservation;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationRequest;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ReservationMapper {

    public Reservation mapFromRequestToEntity(final ReservationRequest request) {
        final Reservation reservation = new Reservation();
        reservation.setReservationId(reservation.getReservationId());
        reservation.setReservationIdentifier(request.getReservationIdentifier());
        reservation.setReservationStartDate(request.getReservationStartDate());
        reservation.setReservationEndDate(request.getReservationEndDate());
        final ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setConferenceRoomName(request.getConferenceRoomName());
        reservation.setConferenceRoom(conferenceRoom);

        return reservation;
    }

    public ReservationResponse mapFromEntityToResponse(final Reservation reservation) {
        final ReservationResponse response = new ReservationResponse();
        response.setReservationId(reservation.getReservationId());
        response.setReservationIdentifier(reservation.getReservationIdentifier());
        response.setReservationStartDate(dateParser(reservation.getReservationStartDate()));
        response.setReservationEndDate(dateParser(reservation.getReservationEndDate()));
        response.setConferenceRoomName(reservation.getConferenceRoom().getConferenceRoomName());
        response.setOrganizationName(reservation.getConferenceRoom().getOrganization().getOrganizationName());

        return response;
    }

    public static String dateParser(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return localDateTime.format(dateTimeFormatter);
    }
}
