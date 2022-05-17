package com.sda.transporeon.finalconferenceroom.reservation.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.repository.ConferenceRoomRepository;
import com.sda.transporeon.finalconferenceroom.reservation.model.Reservation;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationRequest;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationResponse;
import com.sda.transporeon.finalconferenceroom.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
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

    public ReservationResponse getReservationById(Long reservationId) {
        Reservation reservation = getIfFound(reservationId);
        return reservationMapper.mapFromEntityToResponse(reservation);
    }

    public ReservationResponse addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.mapFromRequestToEntity(reservationRequest);
        checkIfUniqueReservation(reservationRequest.getConferenceRoomName(),
                reservationRequest.getReservationEndDate(), reservationRequest.getReservationStartDate());
        checkIfUniqueIdentifier(reservationRequest.getReservationIdentifier());
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(reservation.getConferenceRoom().getConferenceRoomName()).orElseThrow(() -> {
            throw new ConferenceRoomNotFoundException(reservation.getConferenceRoom().getConferenceRoomName());
        });
        reservation.setConferenceRoom(conferenceRoom);
        return reservationMapper.mapFromEntityToResponse(reservationRepository.save(reservation));
    }

    public void deleteReservationByIdentifier(Long reservationId) {
        Reservation reservation = getIfFound(reservationId);
        reservationRepository.delete(reservation);
    }

    public ReservationResponse updateReservation(String reservationIdentifier, ReservationRequest reservationRequest) {
        Reservation reservation = getIfFound(reservationRequest.getReservationId());
        reservation.setReservationIdentifier(reservationRequest.getReservationIdentifier());
        checkIfUniqueIdentifierForUpdate(reservationRequest.getReservationIdentifier(), reservationIdentifier);
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(reservationRequest.getConferenceRoomName()).orElseThrow(() -> {
            throw new ConferenceRoomNotFoundException(reservationRequest.getConferenceRoomName());
        });
        reservation.setConferenceRoom(conferenceRoom);
        reservation.setReservationStartDate(reservationRequest.getReservationStartDate());
        reservation.setReservationEndDate(reservationRequest.getReservationEndDate());
        checkIfUniqueReservationForUpdate(reservationIdentifier, reservationRequest.getConferenceRoomName(), reservationRequest.getReservationEndDate(),
                reservationRequest.getReservationStartDate());
        return reservationMapper.mapFromEntityToResponse(reservationRepository.save(reservation));
    }

    public void checkIfUniqueIdentifier(String reservationIdentifier) {
        reservationRepository.findByReservationIdentifier(reservationIdentifier).ifPresent(reservation -> {
            throw new IllegalArgumentException();
        });
    }

    public void checkIfUniqueIdentifierForUpdate(String reservationIdentifier1, String reservationIdentifier2) {
        reservationRepository.findByReservationIdentifierAndReservationIdentifierNot(reservationIdentifier1, reservationIdentifier2).ifPresent(reservation -> {
            throw new IllegalArgumentException();
        });
    }

    public Reservation getIfFound(Long reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
    }

    public void checkIfUniqueReservationForUpdate(String reservationIdentifier, String conferenceRoomName, LocalDateTime endDate, LocalDateTime startDate) {
        reservationRepository.findByReservationIdentifierNotAndConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(
                reservationIdentifier, conferenceRoomName, endDate,
                startDate).ifPresent(res -> {
            throw new IllegalArgumentException();
        });
    }

    public void checkIfUniqueReservation(String conferenceRoomName, LocalDateTime endDate, LocalDateTime startDate) {
        reservationRepository.findByConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(
                conferenceRoomName, endDate,
                startDate).ifPresent(res -> {
            throw new IllegalArgumentException();
        });
    }


}
