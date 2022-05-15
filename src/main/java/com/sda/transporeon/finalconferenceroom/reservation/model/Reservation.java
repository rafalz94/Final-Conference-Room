package com.sda.transporeon.finalconferenceroom.reservation.model;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identifier;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @ManyToOne
    private ConferenceRoom conferenceRoom;
}
