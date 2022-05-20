package com.sda.transporeon.finalconferenceroom.reservation.model;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;
    private String reservationIdentifier;
    private LocalDateTime reservationStartDate;
    private LocalDateTime reservationEndDate;
    @ManyToOne
    private ConferenceRoom conferenceRoom;
}
