package com.sda.transporeon.finalconferenceroom.organization.service;

import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationDto;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationMapperTest {

    private final OrganizationMapper organizationMapper = new OrganizationMapper();

    @Test
    void when_toDto_is_used_then_dto_object_should_be_returned() {
        //given
        Organization organization = new Organization();
        organization.setOrganizationId(1L);
        organization.setOrganizationName("organization1");
        //when
        OrganizationDto returnedOrganization = organizationMapper.toDto(organization);
        //then
        assertAll(
                () -> assertEquals(organization.getOrganizationId(), returnedOrganization.getOrganizationId()),
                () -> assertEquals(organization.getOrganizationName(), returnedOrganization.getOrganizationName())
        );
    }

    @Test
    void when_toEntity_is_used_then_entity_object_should_be_returned() {
        //given
        OrganizationRequest organizationRequest=new OrganizationRequest();
        organizationRequest.setOrganizationName("organization1");
        //when
        Organization returnedOrganization = organizationMapper.toEntity(organizationRequest);
        //then
        assertEquals(organizationRequest.getOrganizationName(), returnedOrganization.getOrganizationName());
    }

}