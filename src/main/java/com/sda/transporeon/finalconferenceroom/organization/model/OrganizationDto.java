package com.sda.transporeon.finalconferenceroom.organization.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrganizationDto {

    private Integer id;
    private String organizationName;
    private List<String> conferenceRoomDtoList = new ArrayList<>();
}
