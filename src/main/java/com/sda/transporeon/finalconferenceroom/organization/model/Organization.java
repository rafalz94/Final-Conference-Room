package com.sda.transporeon.finalconferenceroom.organization.model;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organizationId;
    private String organizationName;
    @OneToMany(mappedBy = "organization", cascade = CascadeType.REMOVE)
    private List<ConferenceRoom> conferenceRooms = new ArrayList<>();
}
