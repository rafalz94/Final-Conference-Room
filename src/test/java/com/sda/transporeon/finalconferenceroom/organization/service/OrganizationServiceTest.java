package com.sda.transporeon.finalconferenceroom.organization.service;

import com.sda.transporeon.finalconferenceroom.organization.exception.OrganizationAlreadyExistsException;
import com.sda.transporeon.finalconferenceroom.organization.exception.OrganizationNotFoundException;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationResponse;
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
    static class OrganizationServiceConfiguration {
        @Bean
        OrganizationService OrganizationService(OrganizationRepository organizationRepository) {
            return new OrganizationService(organizationRepository, new OrganizationMapper());
        }
    }

    @Test
    void ifAddOrganizationIsUsedThenAddedOrganizationShouldBeReturned() {
        //given
        final long organizationId = 1;

        OrganizationRequest organizationToAdd = new OrganizationRequest();
        organizationToAdd.setOrganizationName("organization1");

        Organization addedOrganization = new Organization();
        addedOrganization.setOrganizationName("organization1");

        Organization organizationFromDb = new Organization();
        organizationFromDb.setOrganizationId(organizationId);
        organizationFromDb.setOrganizationName("organization1");

        Mockito.when(organizationRepository.save(addedOrganization)).thenReturn(organizationFromDb);
        //when
        OrganizationResponse returnedOrganization = organizationService.addOrganization(organizationToAdd);
        //then
        assertAll(
                () -> assertEquals(organizationFromDb.getOrganizationId(), returnedOrganization.getOrganizationId()),
                () -> assertEquals(organizationFromDb.getOrganizationName(), returnedOrganization.getOrganizationName())
        );
    }

    @Test
    void ifAddOrganizationIsUsedAndOrganizationAlreadyExistThenExceptionShouldBeThrown() {
        //given
        Mockito.when(organizationRepository.findByOrganizationName("organization1")).thenReturn(Optional.of(new Organization()));
        //when
        //then
        assertThrows(OrganizationAlreadyExistsException.class, () -> {
            organizationService.addOrganization(new OrganizationRequest(1L, "organization1"));
        });
    }

    @Test
    void ifGetOrganizationByIdIsUsedThenOrganizationShouldBeReturned() {
        //given
        Organization organizationFromDb = new Organization();
        organizationFromDb.setOrganizationId(1L);
        organizationFromDb.setOrganizationName("organization1");

        Mockito.when(organizationRepository.findById(1L)).thenReturn(Optional.of(organizationFromDb));
        //when
        OrganizationResponse returnedOrganization = organizationService.getOrganizationById(1L);
        //then
        assertAll(
                () -> assertEquals(organizationFromDb.getOrganizationId(), returnedOrganization.getOrganizationId()),
                () -> assertEquals(organizationFromDb.getOrganizationName(), returnedOrganization.getOrganizationName())
        );
    }

    @Test
    void ifGetOrganizationByIdIsUsedAndOrganizationDoesNotExistThenExceptionShouldBeThrown() {
        //given
        Mockito.when(organizationRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(OrganizationNotFoundException.class, () -> {
            organizationService.getOrganizationById(1L);
        });
    }

    @Test
    void ifDeleteOrganizationIsUsedThenOrganizationShouldBeDeleted() {
        //given
        Long organizationId = 1L;

        Organization organizationFromDb = new Organization();

        Mockito.when(organizationRepository.findById(organizationId)).thenReturn(Optional.of(organizationFromDb));
        //when
        organizationService.deleteOrganization(organizationId);
        //then
        Mockito.verify(organizationRepository).delete(organizationFromDb);
    }

    @Test
    void ifDeleteOrganizationIsUsedAndOrganizationDoesNotExistThenExceptionShouldBeThrown() {
        //given
        Mockito.when(organizationRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(OrganizationNotFoundException.class, () -> {
            organizationService.deleteOrganization(1L);
        });
    }

    @Test
    void ifUpdateOrganizationIsUsedThenUpdatedOrganizationShouldBeReturned() {
        //given
        Organization organizationFromDb = new Organization();
        organizationFromDb.setOrganizationId(1L);
        organizationFromDb.setOrganizationName("organization1");

        Organization updatedOrganizationFromDb = new Organization();
        updatedOrganizationFromDb.setOrganizationId(1L);
        updatedOrganizationFromDb.setOrganizationName("updatedOrganization");

        Mockito.when(organizationRepository.findById(1L)).thenReturn(Optional.of(organizationFromDb));
        Mockito.when(organizationRepository.save(updatedOrganizationFromDb)).thenReturn(updatedOrganizationFromDb);

        OrganizationRequest updatedOrganization = new OrganizationRequest();
        updatedOrganization.setOrganizationId(1L);
        updatedOrganization.setOrganizationName("updatedOrganization");

        //when
        OrganizationResponse returnedOrganization = organizationService.updateOrganization(updatedOrganization);
        //then
        assertAll(
                () -> assertEquals(organizationFromDb.getOrganizationId(), returnedOrganization.getOrganizationId()),
                () -> assertEquals(organizationFromDb.getOrganizationName(), returnedOrganization.getOrganizationName())
        );
    }

    @Test
    void ifUpdateOrganizationIsUsedAndOrganizationDoesNotExistThenExceptionShouldBeThrown() {
        //given
        Mockito.when(organizationRepository.findById(1L)).thenReturn(Optional.empty());
        //when
        //then
        assertThrows(OrganizationNotFoundException.class, () -> {
            organizationService.updateOrganization(new OrganizationRequest(1L, "organization1"));
        });
    }

    @Test
    void ifUpdateOrganizationIsUsedAndOrganizationNameIsNotUniqueThenExceptionShouldBeThrown() {
        //given
        Mockito.when(organizationRepository.findById(1L)).thenReturn(Optional.of(new Organization()));
        Mockito.when(organizationRepository.findByOrganizationIdNotAndOrganizationName(1L,"organization2")).thenReturn(Optional.of(new Organization()));
        //when
        //then
        assertThrows(OrganizationAlreadyExistsException.class, () -> {
            organizationService.updateOrganization(new OrganizationRequest(1L, "organization2"));
        });
    }


}