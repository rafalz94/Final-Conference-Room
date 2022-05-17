package com.sda.transporeon.finalconferenceroom.organization.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.transporeon.finalconferenceroom.organization.model.OrganizationRequest;
import com.sda.transporeon.finalconferenceroom.organization.service.OrganizationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class OrganizationControllerTest {

    @Autowired
    private OrganizationController organizationController;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testIf_getAllOrganization_returnEmptyDatabase () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/organization"))
                .andDo(print())
                .andExpect(content().string("[]"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    void testIf_addOrganization_AddRecordToDatabase() throws Exception {
        OrganizationRequest organizationRequest = OrganizationRequest.of(123L, "Organization123");

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/organization/add")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organizationRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/organization"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}