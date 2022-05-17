package com.sda.transporeon.finalconferenceroom.organization.repository;

import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrganizationRepositoryTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    void testIf_FindByOrganizationName_ReturnsOrganizationEntity() {
        String organizationName = "Company123";

        Organization organization = new Organization();
        organization.setOrganizationName(organizationName);

        organizationRepository.save(organization);

        Optional<Organization> organizationFoundByName = organizationRepository.findByOrganizationName(organizationName);
        assertThat(organizationFoundByName.get().getOrganizationName()).isEqualTo(organizationName);
    }
}