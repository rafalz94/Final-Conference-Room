package com.sda.transporeon.finalconferenceroom.organization.repository;

import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    Optional<Organization> findByName(String name);
}
