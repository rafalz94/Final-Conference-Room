package com.sda.transporeon.finalconferenceroom.conference_room.repository;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRoomRepository extends JpaRepository<ConferenceRoom, Integer> {
}
