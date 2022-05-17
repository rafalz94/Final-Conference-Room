package com.sda.transporeon.finalconferenceroom.conference_room.repository;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ConferenceRoomRepositoryTest {

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    @Test
    void findByConferenceRoomNameShouldReturnConferenceRoomEntity() {

        String conferenceRoomName = "big_room";
        saveConferenceRoomWithName(conferenceRoomName);

        Optional<ConferenceRoom> conferenceRoomFoundByName = conferenceRoomRepository.findByConferenceRoomName(conferenceRoomName);

        Assertions.assertThat(conferenceRoomFoundByName.get().getConferenceRoomName()).isEqualTo(conferenceRoomName);
    }

    @Test
    void findByConferenceRoomNameShouldNotReturnConferenceRoomEntity() {

        String conferenceRoomName = "big_room";
        String anotherConferenceRoomName = "small_room";
        saveConferenceRoomWithName(conferenceRoomName);

        Optional<ConferenceRoom> conferenceRoomFoundByName = conferenceRoomRepository.findByConferenceRoomName(conferenceRoomName);

        Assertions.assertThat(conferenceRoomFoundByName.get().getConferenceRoomName()).isNotEqualTo(anotherConferenceRoomName);
    }

    // TODO
    // List<ConferenceRoom> findByOrganization_OrganizationName(String organizationName);
    @Test
    void findByOrganization_OrganizationNameShouldReturnConferenceRoomsForOrganization() {

    }

    private ConferenceRoom saveConferenceRoomWithName(String name) {
        final ConferenceRoom conferenceRoom = new ConferenceRoom();

        conferenceRoom.setConferenceRoomName(name);

        return conferenceRoomRepository.save(conferenceRoom);
    }
}
