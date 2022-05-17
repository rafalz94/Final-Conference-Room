package com.sda.transporeon.finalconferenceroom.conference_room.controller;

import com.sda.transporeon.finalconferenceroom.conference_room.rest.ConferenceRoomController;
import com.sda.transporeon.finalconferenceroom.conference_room.service.ConferenceRoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConferenceRoomController.class)
public class ConferenceRoomControllerTest {

    @MockBean
    private ConferenceRoomService conferenceRoomService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllRecordsReturnNoConferenceRoomRecordsAndRespondWithOkWhenConferenceRoomRecordsAreNotCreated() throws Exception {
        Mockito.when(conferenceRoomService.getAllConferenceRooms())
                .thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/conference-room"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is(200));
    }

}
