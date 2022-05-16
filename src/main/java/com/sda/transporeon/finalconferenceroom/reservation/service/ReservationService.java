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

    //    public ReservationResponse getReservationByIdentifier(String reservationIdentifier) {
//        Reservation reservation = reservationRepository.findByReservationIdentifier(reservationIdentifier).orElseThrow(() -> {
//            throw new NoSuchElementException();
//        });
//        return reservationMapper.mapFromEntityToResponse(reservation);
//    }

    public ReservationResponse getReservationByIdentifier(String reservationIdentifier) {
        Reservation reservation = getIfFound(reservationIdentifier);
        return reservationMapper.mapFromEntityToResponse(reservation);
    }

//     public ReservationResponse addReservation(ReservationRequest reservationRequest) {
//        Reservation reservation = reservationMapper.mapFromRequestToEntity(reservationRequest);
//        reservationRepository.findByConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual
//                (reservationRequest.getConferenceRoomName(),
//                        reservationRequest.getReservationEndDate(), reservationRequest.getReservationStartDate()).ifPresent(res -> {
//            throw new IllegalArgumentException();
//        });
//        reservationRepository.findByReservationIdentifier(reservationRequest.getReservationIdentifier()).ifPresent(res -> {
//            throw new IllegalArgumentException();
//        });
//        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(reservation.getConferenceRoom().getConferenceRoomName()).orElseThrow(() -> {
//            throw new NoSuchElementException();
//        });
//        reservation.setConferenceRoom(conferenceRoom);
//        return reservationMapper.mapFromEntityToResponse(reservationRepository.save(reservation));
//    }

    public ReservationResponse addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = reservationMapper.mapFromRequestToEntity(reservationRequest);
        checkIfUniqueReservation(reservationRequest.getConferenceRoomName(),
                reservationRequest.getReservationEndDate(), reservationRequest.getReservationStartDate());
        checkIfUniqueIdentifier(reservationRequest.getReservationIdentifier());
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(reservation.getConferenceRoom().getConferenceRoomName()).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        reservation.setConferenceRoom(conferenceRoom);
        return reservationMapper.mapFromEntityToResponse(reservationRepository.save(reservation));
    }

    //    public void deleteReservationByIdentifier(String reservationIdentifier) {
//        Reservation reservation = reservationRepository.findByReservationIdentifier(reservationIdentifier).orElseThrow(() -> {
//            throw new NoSuchElementException();
//        });
//        reservationRepository.delete(reservation);
//    }

    public void deleteReservationByIdentifier(String reservationIdentifier) {
        Reservation reservation = getIfFound(reservationIdentifier);
        reservationRepository.delete(reservation);
    }

//    public ReservationResponse updateReservation(String reservationIdentifier, ReservationRequest reservationRequest) {
//        Reservation reservation = reservationRepository.findByReservationIdentifier(reservationIdentifier).orElseThrow(() -> {
//            throw new NoSuchElementException();
//        });
//        reservation.setReservationIdentifier(reservationRequest.getReservationIdentifier());
//        reservationRepository.findByReservationIdentifier(reservationRequest.getReservationIdentifier()).ifPresent(res -> {
//            throw new IllegalArgumentException();
//        });
//        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(reservationRequest.getConferenceRoomName()).orElseThrow(() -> {
//            throw new NoSuchElementException();
//        });
//        reservation.setConferenceRoom(conferenceRoom);
//        reservation.setReservationStartDate(reservationRequest.getReservationStartDate());
//        reservation.setReservationEndDate(reservationRequest.getReservationEndDate());
//        reservationRepository.findByReservationIdNotAndConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(
//                reservation.getReservationId(), reservationRequest.getConferenceRoomName(), reservationRequest.getReservationEndDate(),
//                reservationRequest.getReservationStartDate()).ifPresent(res -> {
//            throw new IllegalArgumentException();
//        });
//        return reservationMapper.mapFromEntityToResponse(reservationRepository.save(reservation));
//    }

    public ReservationResponse updateReservation(String reservationIdentifier, ReservationRequest reservationRequest) {
        Reservation reservation = getIfFound(reservationIdentifier);
        reservation.setReservationIdentifier(reservationRequest.getReservationIdentifier());
        checkIfUniqueIdentifier(reservationRequest.getReservationIdentifier());
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(reservationRequest.getConferenceRoomName()).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        reservation.setConferenceRoom(conferenceRoom);
        reservation.setReservationStartDate(reservationRequest.getReservationStartDate());
        reservation.setReservationEndDate(reservationRequest.getReservationEndDate());
        checkIfUniqueReservationForUpdate(reservation.getReservationId(), reservationRequest.getConferenceRoomName(), reservationRequest.getReservationEndDate(),
                reservationRequest.getReservationStartDate());
        return reservationMapper.mapFromEntityToResponse(reservationRepository.save(reservation));
    }

    //1
    public void checkIfUniqueIdentifier(String reservationIdentifier) {
        reservationRepository.findByReservationIdentifier(reservationIdentifier).ifPresent(reservation -> {
            throw new IllegalArgumentException();
        });
    }

    //2
    public Reservation getIfFound(String reservationIdentifier) {
        return reservationRepository.findByReservationIdentifier(reservationIdentifier).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
    }

    //3
    public void checkIfUniqueReservationForUpdate(Long reservationId, String conferenceRoomName, LocalDateTime endDate, LocalDateTime startDate) {
        reservationRepository.findByReservationIdNotAndConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(
                reservationId, conferenceRoomName, endDate,
                startDate).ifPresent(res -> {
            throw new IllegalArgumentException();
        });
    }

    //4
    public void checkIfUniqueReservation(String conferenceRoomName, LocalDateTime endDate, LocalDateTime startDate) {
        reservationRepository.findByConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(
                conferenceRoomName, endDate,
                startDate).ifPresent(res -> {
            throw new IllegalArgumentException();
        });
    }


}
