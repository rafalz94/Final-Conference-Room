package com.sda.transporeon.finalconferenceroom.conference_room.service;

import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomResponse;
import com.sda.transporeon.finalconferenceroom.conference_room.repository.ConferenceRoomRepository;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ConferenceRoomService {

    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ConferenceRoomMapper conferenceRoomMapper;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public ConferenceRoomService(ConferenceRoomRepository conferenceRoomRepository, ConferenceRoomMapper conferenceRoomMapper, OrganizationRepository organizationRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.conferenceRoomMapper = conferenceRoomMapper;
        this.organizationRepository = organizationRepository;
    }

    public List<ConferenceRoomResponse> getAllConferenceRooms() {
        return conferenceRoomRepository.findAll().stream().map(conferenceRoomMapper::fromEntityToResponse).collect(Collectors.toList());
    }

    public List<ConferenceRoomResponse> getAllByOrganizationName(String organizationName) {
        return conferenceRoomRepository.findByOrganization_OrganizationName(organizationName).stream()
                .map(conferenceRoomMapper::fromEntityToResponse).collect(Collectors.toList());
    }

    public ConferenceRoomResponse getConferenceRoomByName(String conferenceRoomName) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(conferenceRoomName).orElseThrow(() -> {
            throw new NoSuchElementException();
        });

        return conferenceRoomMapper.fromEntityToResponse(conferenceRoom);
    }

    public ConferenceRoomResponse addConferenceRoom(ConferenceRoomRequest conferenceRoomRequest) {
        ConferenceRoom conferenceRoom = conferenceRoomMapper.fromRequestToEntity(conferenceRoomRequest);
        conferenceRoomRepository.findByConferenceRoomName(conferenceRoom.getConferenceRoomName()).ifPresent(room -> {
            throw new IllegalArgumentException();
        });
        Organization organization = organizationRepository.findByOrganizationName(conferenceRoomRequest.getOrganizationName()).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        conferenceRoom.setOrganization(organization);

        return conferenceRoomMapper.fromEntityToResponse(conferenceRoomRepository.save(conferenceRoom));
    }

    public void deleteConferenceRoom(String conferenceRoomName) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(conferenceRoomName).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        conferenceRoomRepository.delete(conferenceRoom);
    }

    public ConferenceRoomResponse updateConferenceRoom(String conferenceRoomName, ConferenceRoomRequest conferenceRoomRequest) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findByConferenceRoomName(conferenceRoomName).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        conferenceRoomRepository.findByConferenceRoomName(conferenceRoomRequest.getConferenceRoomName()).ifPresent(room -> {
            throw new IllegalArgumentException();
        });
        conferenceRoom.setConferenceRoomName(conferenceRoomRequest.getConferenceRoomName());
        conferenceRoom.setLevel(conferenceRoomRequest.getLevel());
        conferenceRoom.setStandingPlaces(conferenceRoomRequest.getStandingPlaces());
        conferenceRoom.setSittingPlaces(conferenceRoomRequest.getSittingPlaces());

        return conferenceRoomMapper.fromEntityToResponse(conferenceRoomRepository.save(conferenceRoom));
    }
}
