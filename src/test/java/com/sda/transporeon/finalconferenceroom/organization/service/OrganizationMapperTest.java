package com.sda.transporeon.finalconferenceroom.organization.service;

import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationResponse;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationMapperTest {

    private final OrganizationMapper organizationMapper = new OrganizationMapper();

    @Test
    void ifFromEntityToResponseIsUsedThenDtoObjectShouldBeReturned() {
        //given
        Organization organization = new Organization();
        organization.setOrganizationId(1L);
        organization.setOrganizationName("organization1");
        //when
        OrganizationResponse returnedOrganization = organizationMapper.fromEntityToResponse(organization);
        System.out.println(LocalDateTime.now());
        //then
        assertAll(
                () -> assertEquals(organization.getOrganizationId(), returnedOrganization.getOrganizationId()),
                () -> assertEquals(organization.getOrganizationName(), returnedOrganization.getOrganizationName())
        );
    }

    @Test
    void ifFromRequestToEntityIsUsedThenEntityObjectShouldBeReturned() {
        //given
        OrganizationRequest organizationRequest=new OrganizationRequest();
        organizationRequest.setOrganizationName("organization1");
        //when
        Organization returnedOrganization = organizationMapper.fromRequestToEntity(organizationRequest);
        //then
        assertEquals(organizationRequest.getOrganizationName(), returnedOrganization.getOrganizationName());
    }

}