package com.sda.transporeon.finalconferenceroom.conference_room.model;

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
    @NotBlank(message = "Organization name cannot be blank!")
    @Size(min = 2, max = 20, message = "Organization name has to be between 2 and 20 characters!")
    private String organizationName;
    @NotBlank(message = "Conference room name cannot be blank!")
    @Size(min = 2, max = 20, message = "Conference room name has to be between 2 and 20 characters!")
    private String conferenceRoomName;
    @Min(value = 0, message = "Level number too small!")
    @Max(value = 10, message = "Level number too large!")
    private Integer level;
    private Integer sittingPlaces;
    private Integer standingPlaces;
    private String availability;
}
