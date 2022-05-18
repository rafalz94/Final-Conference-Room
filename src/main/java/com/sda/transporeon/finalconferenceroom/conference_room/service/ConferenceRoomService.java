package com.sda.transporeon.finalconferenceroom.conference_room.service;

import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomAlreadyExistsException;
import com.sda.transporeon.finalconferenceroom.conference_room.exception.ConferenceRoomNotFoundException;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomResponse;
import com.sda.transporeon.finalconferenceroom.conference_room.repository.ConferenceRoomRepository;
import com.sda.transporeon.finalconferenceroom.organization.exception.OrganizationNotFoundException;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return conferenceRoomRepository.findAll().stream().map(conferenceRoomMapper::mapFromEntityToResponse).collect(Collectors.toList());
    }

    public List<ConferenceRoomResponse> getAllByOrganizationName(String organizationName) {
        return conferenceRoomRepository.findByOrganization_OrganizationName(organizationName).stream()
                .map(conferenceRoomMapper::mapFromEntityToResponse).collect(Collectors.toList());
    }

    public ConferenceRoomResponse getConferenceRoomById(Long conferenceRoomId) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(conferenceRoomId).orElseThrow(() -> {
            throw new ConferenceRoomNotFoundException();
        });

        return conferenceRoomMapper.mapFromEntityToResponse(conferenceRoom);
    }

        public ConferenceRoomResponse addConferenceRoom(ConferenceRoomRequest conferenceRoomRequest) {
        ConferenceRoom conferenceRoom = conferenceRoomMapper.mapFromRequestToEntity(conferenceRoomRequest);
        conferenceRoomRepository.findByConferenceRoomName(conferenceRoom.getConferenceRoomName()).ifPresent(room -> {
            throw new ConferenceRoomAlreadyExistsException(conferenceRoom.getConferenceRoomName());
        });
        Organization organization = organizationRepository.findByOrganizationName(conferenceRoomRequest.getOrganizationName()).orElseThrow(() -> {
            throw new OrganizationNotFoundException();
        });
        conferenceRoom.setOrganization(organization);

        return conferenceRoomMapper.mapFromEntityToResponse(conferenceRoomRepository.save(conferenceRoom));
    }

    public void deleteConferenceRoom(Long conferenceRoomId) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(conferenceRoomId).orElseThrow(() -> {
            throw new ConferenceRoomNotFoundException();
        });
        conferenceRoomRepository.delete(conferenceRoom);
    }

    public ConferenceRoomResponse updateConferenceRoom(ConferenceRoomRequest conferenceRoomRequest) {
        ConferenceRoom conferenceRoom = conferenceRoomRepository.findById(conferenceRoomRequest.getConferenceRoomId()).orElseThrow(() -> {
            throw new ConferenceRoomNotFoundException();
        });
        //TODO dodanie unikalności
        conferenceRoomRepository.findByConferenceRoomName(conferenceRoomRequest.getConferenceRoomName()).ifPresent(room -> {
            throw new ConferenceRoomAlreadyExistsException(conferenceRoomRequest.getConferenceRoomName());
        });
        conferenceRoom.setConferenceRoomName(conferenceRoomRequest.getConferenceRoomName());
        conferenceRoom.setLevel(conferenceRoomRequest.getLevel());
        conferenceRoom.setStandingPlaces(conferenceRoomRequest.getStandingPlaces());
        conferenceRoom.setSittingPlaces(conferenceRoomRequest.getSittingPlaces());

        return conferenceRoomMapper.mapFromEntityToResponse(conferenceRoomRepository.save(conferenceRoom));
    }
}
