package com.sda.transporeon.finalconferenceroom.conference_room.model;

import com.sda.transporeon.finalconferenceroom.organization.model.AddOrganizationGroup;
import com.sda.transporeon.finalconferenceroom.organization.model.UpdateOrganizationGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceRoomRequest {
    private Long conferenceRoomId;
    @NotBlank(message = "Organization name cannot be blank!", groups = AddOrganizationGroup.class)
    @Size(min = 2, max = 20, message = "Organization name has to be between 2 and 20 characters!", groups = {AddOrganizationGroup.class, UpdateOrganizationGroup.class})
    private String organizationName;
    @NotBlank(message = "Conference room name cannot be blank!", groups = AddRoomGroup.class)
    @Size(min = 2, max = 20, message = "Conference room name has to be between 2 and 20 characters!", groups = {AddRoomGroup.class, UpdateRoomGroup.class})
    private String conferenceRoomName;
    @Min(value = 0, message = "Level number too small!", groups = {AddRoomGroup.class, UpdateRoomGroup.class})
    @Max(value = 10, message = "Level number too large!", groups = {AddRoomGroup.class, UpdateRoomGroup.class})
    private Integer level;
    private Integer sittingPlaces;
    private Integer standingPlaces;
}
