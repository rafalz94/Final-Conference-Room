package com.sda.transporeon.finalconferenceroom.organization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private Integer organizationId;
    private String organizationName;
    private List<String> conferenceRoomDtoList = new ArrayList<>();
}
