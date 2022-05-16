package com.sda.transporeon.finalconferenceroom.reservation.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.reservation.model.Reservation;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationRequest;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationResponse;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public static Reservation mapFromRequestToEntity(final ReservationRequest request){
        final Reservation reservation = new Reservation();
        reservation.setReservationIdentifier(request.getReservationIdentifier());
        reservation.setReservationStartDate(request.getReservationStartDate());
        reservation.setReservationEndDate(request.getReservationEndDate());
        final ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setConferenceRoomName(request.getConferenceRoomName());
        reservation.setConferenceRoom(conferenceRoom);

        return reservation;
    }

    public static ReservationResponse mapFromEntityToResponse(final Reservation reservation){
        final ReservationResponse response = new ReservationResponse();
        response.setReservationIdentifier(reservation.getReservationIdentifier());
        response.setReservationStartDate(reservation.getReservationStartDate());
        response.setReservationEndDate(reservation.getReservationEndDate());
        response.setConferenceRoomName(reservation.getConferenceRoom().getConferenceRoomName());

        return response;
    }

}
