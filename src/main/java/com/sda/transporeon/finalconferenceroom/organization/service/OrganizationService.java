package com.sda.transporeon.finalconferenceroom.organization.service;

import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    //TODO
    public List<OrganizationDto> getAllOrganizations() {
        return List.of(new OrganizationDto(),new OrganizationDto());
    }

    //TODO
    public OrganizationDto getOrganizationById(Integer id) {
        return null;
    }

    //TODO
    public OrganizationDto addOrganization(OrganizationDto organizationDto) {
        return null;
    }

    //TODO
    public void deleteOrganizationById(Integer id) {
    }

    //TODO
    public OrganizationDto updateOrganizationById(Integer id, OrganizationDto organizationDto) {
        return null;
    }
}
