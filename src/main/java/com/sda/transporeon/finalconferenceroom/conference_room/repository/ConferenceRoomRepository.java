package com.sda.transporeon.finalconferenceroom.conference_room.repository;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Long> {
    Optional<ConferenceRoom> findByConferenceRoomName(String name);

    List<ConferenceRoom> findByOrganization_OrganizationName(String organizationName);

}
