package com.sda.transporeon.finalconferenceroom.reservation.service;

import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomNotFoundException;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.repository.ConferenceRoomRepository;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationAlreadyExistsException;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationNotFoundException;
import com.sda.transporeon.finalconferenceroom.reservation.model.Reservation;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationRequest;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationResponse;
import com.sda.transporeon.finalconferenceroom.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ConferenceRoomRepository conferenceRoomRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationResponse> getAllReservations() {
        return reservationRepository.findAll().stream().map(reservationMapper::mapFromEntityToResponse).collect(Collectors.toList());
    }

    public ReservationResponse getReservationByIdentifier(String reservationIdentifier) {
        Reservation reservation = reservationRepository.findByReservationIdentifier(reservationIdentifier).orElseThrow(() -> {
            throw new ReservationNotFoundException(reservationIdentifier);
        });
        return reservationMapper.mapFromEntityToResponse(reservation);
    }

    public ReservationResponse addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.mapFromRequestToEntity(reservationRequest);
        reservationRepository.findByConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual
                (reservationRequest.getConferenceRoomName(),
                        reservationRequest.getReservationEndDate(), reservationRequest.getReservationStartDate()).ifPresent(res -> {
            throw new ReservationAlreadyExistsException();
        });
        reservationRepository.findByReservationIdentifier(reservationRequest.getReservationIdentifier()).ifPresent(res -> {
            throw new ReservationAlreadyExistsException();
        });
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(reservation.getConferenceRoom().getConferenceRoomName()).orElseThrow(() -> {
            throw new ConferenceRoomNotFoundException(reservation.getConferenceRoom().getConferenceRoomName());
        });
        reservation.setConferenceRoom(conferenceRoom);
        return reservationMapper.mapFromEntityToResponse(reservationRepository.save(reservation));
    }

    public void deleteReservationByIdentifier(String reservationIdentifier) {
        Reservation reservation = reservationRepository.findByReservationIdentifier(reservationIdentifier).orElseThrow(() -> {
            throw new ReservationNotFoundException(reservationIdentifier);
        });
        reservationRepository.delete(reservation);
    }

    public ReservationResponse updateReservation(String reservationIdentifier, ReservationRequest reservationRequest) {
        Reservation reservation = reservationRepository.findByReservationIdentifier(reservationIdentifier).orElseThrow(() -> {
            throw new ReservationNotFoundException(reservationRequest.getReservationIdentifier());
        });
        reservation.setReservationIdentifier(reservationRequest.getReservationIdentifier());
        reservationRepository.findByReservationIdentifier(reservationRequest.getReservationIdentifier()).ifPresent(res -> {
            throw new ReservationAlreadyExistsException();
        });
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(reservationRequest.getConferenceRoomName()).orElseThrow(() -> {
            throw new ConferenceRoomNotFoundException(reservationRequest.getConferenceRoomName());
        });
        reservation.setConferenceRoom(conferenceRoom);
        reservation.setReservationStartDate(reservationRequest.getReservationStartDate());
        reservation.setReservationEndDate(reservationRequest.getReservationEndDate());
        reservationRepository.
                findByReservationIdentifierNotAndConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(
                        reservationRequest.getReservationIdentifier(), reservationRequest.getConferenceRoomName(), reservationRequest.getReservationEndDate(),
                        reservationRequest.getReservationStartDate()).ifPresent(res -> {
                    throw new ReservationAlreadyExistsException();
                });
        return reservationMapper.mapFromEntityToResponse(reservationRepository.save(reservation));
    }
}
