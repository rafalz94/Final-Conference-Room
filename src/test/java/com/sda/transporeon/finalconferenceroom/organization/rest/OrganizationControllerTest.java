package com.sda.transporeon.finalconferenceroom.organization.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrganizationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void ifGetAllOrganizationIsUsedAndDatabaseIsEmptyShouldReturnEmptyDatabaseAndRespondWithOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/organizations"))
                .andDo(print())
                .andExpect(content().string("[]"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void ifAddOrganizationIsUsedRecordShouldBeAddedToDatabaseAndRespondWithOk() throws Exception {
        OrganizationRequest organizationRequest = new OrganizationRequest();
        organizationRequest.setOrganizationId(1L);
        organizationRequest.setOrganizationName("Organization123");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organizationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/organizations"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void ifDeleteOrganizationIsUsedRecordShouldBeDeletedFromDatabaseAndRespondWithNoContentStatus() throws Exception {
        OrganizationRequest organizationRequest = new OrganizationRequest();
        organizationRequest.setOrganizationId(1L);
        organizationRequest.setOrganizationName("Organization123");

        long idToRemove = 1;

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(organizationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/organizations"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:" + port + "/organizations/"+ idToRemove))
                .andExpect(status().is2xxSuccessful())
                .andExpect(status().isNoContent());


        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/organizations"))
                .andDo(print())
                .andExpect(content().string("[]"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

    }
}