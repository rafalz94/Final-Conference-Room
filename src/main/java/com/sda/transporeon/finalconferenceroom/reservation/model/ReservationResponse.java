package com.sda.transporeon.finalconferenceroom.reservation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationResponse {

    private Long reservationId;
    private String reservationIdentifier;
//    TODO
    private String reservationStartDate;
    private String ReservationEndDate;
    private String conferenceRoomName;
    private String organizationName;
}
