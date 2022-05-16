package com.sda.transporeon.finalconferenceroom.reservation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReservationResponse {

    private Integer reservationId;
    private String reservationIdentifier;
    private LocalDateTime reservationStartDate;
    private LocalDateTime ReservationEndDate;
    private String conferenceRoomName;
}