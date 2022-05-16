package com.sda.transporeon.finalconferenceroom.organization.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationDto;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganizationMapper {

    public Organization toEntity(OrganizationRequest organizationRequest) {
        Organization organization = new Organization();
        organization.setOrganizationName(organizationRequest.getOrganizationName());
        return organization;
    }

    public OrganizationDto toDto(Organization organization) {
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationId(organization.getOrganizationId());
        organizationDto.setOrganizationName(organization.getOrganizationName());
        String numberOfRooms = String.valueOf(organization.getConferenceRooms().size());
        organizationDto.setNumberOfRooms(numberOfRooms);
        return organizationDto;
    }

}
