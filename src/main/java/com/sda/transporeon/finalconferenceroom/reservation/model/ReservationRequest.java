package com.sda.transporeon.finalconferenceroom.reservation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sda.transporeon.finalconferenceroom.conference_room.model.AddRoomGroup;
import com.sda.transporeon.finalconferenceroom.conference_room.model.UpdateRoomGroup;
import com.sda.transporeon.finalconferenceroom.organization.model.AddOrganizationGroup;
import com.sda.transporeon.finalconferenceroom.organization.model.UpdateOrganizationGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReservationRequest {
    private Long reservationId;
    @NotBlank(message = "Reservation identifier cannot be blank!", groups = AddReservationGroup.class)
    @Size(min = 2, max = 20, message = "Reservation identifier has to be between 2 and 20 characters!", groups = {AddReservationGroup.class, UpdateReservationGroup.class})
    private String reservationIdentifier;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime reservationStartDate;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime reservationEndDate;

    @NotBlank(message = "Conference room name cannot be blank!", groups = AddRoomGroup.class)
    @Size(min = 2, max = 20, message = "Conference room name has to be between 2 and 20 characters!", groups = {AddRoomGroup.class, UpdateRoomGroup.class})
    private String conferenceRoomName;

    @NotBlank(message = "Organization name cannot be blank!", groups = AddOrganizationGroup.class)
    @Size(min = 2, max = 20, message = "Organization name has to be between 2 and 20 characters!", groups = {AddOrganizationGroup.class, UpdateOrganizationGroup.class})
    private String organizationName;

}
