package com.sda.transporeon.finalconferenceroom.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    private Long reservationId;
    @NotBlank(message = "Reservation identifier cannot be blank!")
    @Size(min = 2, max = 20, message = "Reservation identifier has to be between 2 and 20 characters!")
    private String reservationIdentifier;
    private LocalDateTime reservationStartDate;
    private LocalDateTime reservationEndDate;

    @NotBlank(message = "Conference room name cannot be blank!")
    @Size(min = 2, max = 20, message = "Conference room name has to be between 2 and 20 characters!")
    private String conferenceRoomName;
}
