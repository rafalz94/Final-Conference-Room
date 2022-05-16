package com.sda.transporeon.finalconferenceroom.organization.service;

import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationDto;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import com.sda.transporeon.finalconferenceroom.organization.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class OrganizationServiceTest {

    @MockBean
    private OrganizationRepository organizationRepository;
    @Autowired
    private OrganizationService organizationService;

    @TestConfiguration
    static class CurrencyServiceTestConfiguration {
        @Bean
        OrganizationService OrganizationService(OrganizationRepository organizationRepository) {
            return new OrganizationService(organizationRepository, new OrganizationMapper());
        }
    }

    @Test
    void ifAddOrganizationIsUsedThenAddedOrganizationShouldBeReturned() {
        //given
        final long organizationId = 1;

        OrganizationRequest organizationToAdd=new OrganizationRequest();
        organizationToAdd.setOrganizationName("organization1");

        Organization addedOrganization = new Organization();
        addedOrganization.setOrganizationName("organization1");

        Organization organizationFromDb = new Organization();
        organizationFromDb.setOrganizationId(organizationId);
        organizationFromDb.setOrganizationName("organization1");

        Mockito.when(organizationRepository.save(addedOrganization)).thenReturn(organizationFromDb);
        //when
        OrganizationDto returnedOrganization = organizationService.addOrganization(organizationToAdd);
        //then
        assertAll(
                () -> assertEquals(organizationFromDb.getOrganizationId(), returnedOrganization.getOrganizationId()),
                () -> assertEquals(organizationFromDb.getOrganizationName(), returnedOrganization.getOrganizationName())
        );
    }

    @Test
    void ifGetOrganizationByNameIsUsedThenOrganizationShouldBeReturned() {
        //given
        final String organizationName="organization1";

        Organization organizationFromDb = new Organization();
        organizationFromDb.setOrganizationId(1L);
        organizationFromDb.setOrganizationName("organization1");

        Mockito.when(organizationRepository.findByOrganizationName(organizationName)).thenReturn(Optional.of(organizationFromDb));
        //when
        OrganizationDto returnedOrganization = organizationService.getOrganizationByName(organizationName);
        //then
        assertAll(
                () -> assertEquals(organizationFromDb.getOrganizationId(), returnedOrganization.getOrganizationId()),
                () -> assertEquals(organizationFromDb.getOrganizationName(), returnedOrganization.getOrganizationName())
        );
    }

    @Test
    void ifDeleteOrganizationIsUsedThenOrganizationShouldBeDeleted() {
        //given
        final String organizationName="organization1";

        Organization organizationFromDb = new Organization();

        Mockito.when(organizationRepository.findByOrganizationName(organizationName)).thenReturn(Optional.of(organizationFromDb));
        //when
        organizationService.deleteOrganization(organizationName);
        //then
        Mockito.verify(organizationRepository).delete(organizationFromDb);
    }

    @Test
    void ifUpdateOrganizationIsUsedThenUpdatedOrganizationShouldBeReturned() {
        //given
        final String organizationName="organization1";

        Organization organizationFromDb = new Organization();
        organizationFromDb.setOrganizationId(1L);
        organizationFromDb.setOrganizationName("organization1");


        Organization updatedOrganizationFromDb = new Organization();
        updatedOrganizationFromDb.setOrganizationId(1L);
        updatedOrganizationFromDb.setOrganizationName("updatedOrganization");

        Mockito.when(organizationRepository.findByOrganizationName(organizationName)).thenReturn(Optional.of(organizationFromDb));
        Mockito.when(organizationRepository.save(updatedOrganizationFromDb)).thenReturn(updatedOrganizationFromDb);

        OrganizationRequest updatedOrganization = new OrganizationRequest();
        updatedOrganization.setOrganizationName("updatedOrganization");

        //when
        OrganizationDto returnedOrganization = organizationService.updateOrganization("organization1",updatedOrganization);
        //then
        assertAll(
                () -> assertEquals(organizationFromDb.getOrganizationId(), returnedOrganization.getOrganizationId()),
                () -> assertEquals(organizationFromDb.getOrganizationName(), returnedOrganization.getOrganizationName())
        );
    }


}