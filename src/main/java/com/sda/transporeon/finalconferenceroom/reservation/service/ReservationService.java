package com.sda.transporeon.finalconferenceroom.reservation.service;

import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomNotFoundException;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.repository.ConferenceRoomRepository;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationAlreadyExistsException;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationDateNotValidException;
import com.sda.transporeon.finalconferenceroom.reservation.exception.ReservationNotFoundException;
import com.sda.transporeon.finalconferenceroom.reservation.model.Reservation;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationRequest;
import com.sda.transporeon.finalconferenceroom.reservation.model.ReservationResponse;
import com.sda.transporeon.finalconferenceroom.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public ReservationResponse getReservationById(Long reservationId) {
        Reservation reservation = getIfFound(reservationId);
        return reservationMapper.mapFromEntityToResponse(reservation);
    }

    public ReservationResponse addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.mapFromRequestToEntity(reservationRequest);
        checkIfUniqueReservation(reservationRequest.getConferenceRoomName(),
                reservationRequest.getReservationEndDate(), reservationRequest.getReservationStartDate());
        checkIfUniqueIdentifier(reservationRequest.getReservationIdentifier());
        checkIfDateIsValid(reservationRequest.getReservationStartDate(),reservationRequest.getReservationEndDate());
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomNameAndAvailabilityEquals(reservation.getConferenceRoom().getConferenceRoomName(), true)
                .orElseThrow(() -> {
                    throw new ConferenceRoomNotFoundException();
                });
        reservation.setConferenceRoom(conferenceRoom);

        return reservationMapper.mapFromEntityToResponse(reservationRepository.save(reservation));
    }

    public void deleteReservationById(Long reservationId) {
        Reservation reservation = getIfFound(reservationId);
        reservationRepository.delete(reservation);
    }

    public ReservationResponse updateReservation(ReservationRequest reservationRequest) {
        Reservation reservation = getIfFound(reservationRequest.getReservationId());
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(reservationRequest.getConferenceRoomName()).orElseThrow(() -> {
            throw new ConferenceRoomNotFoundException();
        });
        checkIfDateIsValid(reservationRequest.getReservationStartDate(),reservationRequest.getReservationEndDate());
        reservation.setConferenceRoom(conferenceRoom);
        reservation.setReservationStartDate(reservationRequest.getReservationStartDate());
        reservation.setReservationEndDate(reservationRequest.getReservationEndDate());
        checkIfUniqueReservationForUpdate(reservation.getReservationId(), reservationRequest.getConferenceRoomName(), reservationRequest.getReservationEndDate(),
                reservationRequest.getReservationStartDate());

        return reservationMapper.mapFromEntityToResponse(reservationRepository.save(reservation));
    }

    public void checkIfUniqueIdentifier(String reservationIdentifier) {
        reservationRepository.findByReservationIdentifier(reservationIdentifier).ifPresent(reservation -> {
            throw new ReservationAlreadyExistsException();
        });
    }

    public Reservation getIfFound(Long reservationId) {
        return reservationRepository.findById(reservationId).orElseThrow(() -> {
            throw new ReservationNotFoundException();
        });
    }

    public void checkIfUniqueReservationForUpdate(Long reservationId, String conferenceRoomName, LocalDateTime endDate, LocalDateTime startDate) {
        reservationRepository.findByReservationIdNotAndConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(
                reservationId, conferenceRoomName, endDate,
                startDate).ifPresent(res -> {
            throw new ReservationAlreadyExistsException();
        });
    }

    public void checkIfUniqueReservation(String conferenceRoomName, LocalDateTime endDate, LocalDateTime startDate) {
        reservationRepository.findByConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(
                conferenceRoomName, endDate,
                startDate).ifPresent(res -> {
            throw new ReservationAlreadyExistsException();
        });
    }

    public void checkIfDateIsValid(LocalDateTime startDate,LocalDateTime endDate){
        if (startDate.isAfter(endDate)||startDate.isBefore(LocalDateTime.now())){
            throw new ReservationDateNotValidException();
        }
    }
}
