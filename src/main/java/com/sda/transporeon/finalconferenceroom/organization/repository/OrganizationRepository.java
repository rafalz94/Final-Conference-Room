package com.sda.transporeon.finalconferenceroom.organization.repository;

import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findByOrganizationName(String name);

    Optional<Organization> findByOrganizationIdNotAndOrganizationName(Long organizationId, String organizationName);
}
