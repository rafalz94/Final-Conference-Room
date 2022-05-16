package com.sda.transporeon.finalconferenceroom.reservation.repository;

import com.sda.transporeon.finalconferenceroom.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(String conferenceRoomName, LocalDateTime endDate, LocalDateTime startDate);

    Optional<Reservation> findByReservationIdNotAndConferenceRoom_ConferenceRoomNameAndReservationStartDateLessThanEqualAndReservationEndDateGreaterThanEqual(Long id, String conferenceRoomName, LocalDateTime endDate, LocalDateTime startDate);

    Optional<Reservation> findByReservationIdentifier(String reservationIdentifier);
}
