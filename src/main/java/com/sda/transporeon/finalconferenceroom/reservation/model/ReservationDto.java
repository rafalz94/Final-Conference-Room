package com.sda.transporeon.finalconferenceroom.reservation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReservationDto {

    private Integer id;
    private String identifier;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String conferenceRoomName;
}
