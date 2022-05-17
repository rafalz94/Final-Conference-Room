package com.sda.transporeon.finalconferenceroom;

import com.sda.transporeon.finalconferenceroom.conference_room.model.Availability;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoom;
import com.sda.transporeon.finalconferenceroom.conference_room.model.ConferenceRoomRequest;
import com.sda.transporeon.finalconferenceroom.conference_room.repository.ConferenceRoomRepository;
import com.sda.transporeon.finalconferenceroom.conference_room.service.ConferenceRoomService;
import com.sda.transporeon.finalconferenceroom.organization.model.Organization;
import com.sda.transporeon.finalconferenceroom.organization.repository.OrganizationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class FinalConferenceRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalConferenceRoomApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Bean
    CommandLineRunner run(OrganizationRepository organizationRepository, ConferenceRoomService conferenceRoomService) {
        Organization organization1 = new Organization(1L, "Transporeon", null);
        Organization organization2 = new Organization(2L, "SDA", null);
        Organization organization3 = new Organization(3L, "Alledrogo", null);
        ConferenceRoomRequest conferenceRoomRequest1 = new ConferenceRoomRequest(1L, "Transporeon", "room1", 10, 50, 15);
        ConferenceRoomRequest conferenceRoomRequest2 = new ConferenceRoomRequest(2L, "Transporeon", "room2", 10, 40, 10);
        ConferenceRoomRequest conferenceRoomRequest3 = new ConferenceRoomRequest(3L, "SDA", "room3", 8, 35, 10);
        ConferenceRoomRequest conferenceRoomRequest4 = new ConferenceRoomRequest(4L, "SDA", "room4", 8, 50, 15);
        ConferenceRoomRequest conferenceRoomRequest5 = new ConferenceRoomRequest(5L, "Alledrogo", "room5", 7, 40, 10);
        ConferenceRoomRequest conferenceRoomRequest6 = new ConferenceRoomRequest(6L, "Alledrogo", "room6", 7, 50, 15);
        return args -> {
            organizationRepository.save(organization1);
            organizationRepository.save(organization2);
            organizationRepository.save(organization3);
            conferenceRoomService.addConferenceRoom(conferenceRoomRequest1);
            conferenceRoomService.addConferenceRoom(conferenceRoomRequest2);
            conferenceRoomService.addConferenceRoom(conferenceRoomRequest3);
            conferenceRoomService.addConferenceRoom(conferenceRoomRequest4);
            conferenceRoomService.addConferenceRoom(conferenceRoomRequest5);
            conferenceRoomService.addConferenceRoom(conferenceRoomRequest6);
        };
    }

}
