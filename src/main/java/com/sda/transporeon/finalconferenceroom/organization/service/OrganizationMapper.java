package com.sda.transporeon.finalconferenceroom.organization.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganizationMapper {

    public Organization toEntity(OrganizationDto organizationDto) {
        Organization organization = new Organization();
        organization.setName(organizationDto.getOrganizationName());
        return organization;
    }

    public OrganizationDto toDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setId(organization.getOrganizationId());
        organizationDto.setOrganizationName(organization.getName());
        List<String> conferenceRooms = organization.getConferenceRooms().stream().map(ConferenceRoom::getRoomName).collect(Collectors.toList());
        organizationDto.setConferenceRoomDtoList(conferenceRooms);
        return organizationDto;
    }

}
