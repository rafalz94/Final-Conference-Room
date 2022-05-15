package com.sda.transporeon.finalconferenceroom.reservation.repository;

import com.sda.transporeon.finalconferenceroom.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
