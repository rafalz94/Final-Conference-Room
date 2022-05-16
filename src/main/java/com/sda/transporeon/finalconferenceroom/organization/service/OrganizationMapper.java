package com.sda.transporeon.finalconferenceroom.organization.service;

import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationResponse;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public Organization fromRequestToEntity(OrganizationRequest organizationRequest) {
        Organization organization = new Organization();
        organization.setOrganizationName(organizationRequest.getOrganizationName());
        return organization;
    }

    public OrganizationResponse fromEntityToResponse(Organization organization) {
        OrganizationResponse organizationResponse = new OrganizationResponse();
        organizationResponse.setOrganizationId(organization.getOrganizationId());
        organizationResponse.setOrganizationName(organization.getOrganizationName());
        String numberOfRooms = String.valueOf(organization.getConferenceRooms().size());
        organizationResponse.setNumberOfRooms(numberOfRooms);
        return organizationResponse;
    }

}
