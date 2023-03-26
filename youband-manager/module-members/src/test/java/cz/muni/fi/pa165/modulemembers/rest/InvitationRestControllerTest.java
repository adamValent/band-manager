package cz.muni.fi.pa165.modulemembers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.modulemembers.api.InvitationDto;
import cz.muni.fi.pa165.modulemembers.data.enums.InvitationStatus;
import cz.muni.fi.pa165.modulemembers.data.model.Invitation;
import cz.muni.fi.pa165.modulemembers.data.repository.InvitationRepository;
import cz.muni.fi.pa165.modulemembers.mappers.InvitationMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InvitationRestControllerTest {

    private final MockMvc mockMvc;
    private final InvitationRepository invitationRepository;
    private final InvitationMapper invitationMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public InvitationRestControllerTest(MockMvc mockMvc, InvitationRepository invitationRepository,
                                        InvitationMapper invitationMapper, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.invitationRepository = invitationRepository;
        this.invitationMapper = invitationMapper;
        this.objectMapper = objectMapper;
    }

    @Test
    void findByIdOk() throws Exception {
        String response = mockMvc.perform(get("/api/invitations/100"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(objectMapper.readValue(response, InvitationDto.class),
                is(equalTo(invitationMapper.mapToDto(invitationRepository.findById(100L)))));
    }

    @Test
    void findByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/invitations/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createInvitationOk() throws Exception {
        InvitationDto expectedResponse = invitationMapper.mapToDto(
                new Invitation(null, 0L, 1L, "message",
                        InvitationStatus.PENDING, LocalDate.now()));
        String response = mockMvc.perform(post("/api/invitations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        InvitationDto actual = objectMapper.readValue(response, InvitationDto.class);
        expectedResponse.setId(actual.getId());
        assertThat(actual,
                is(equalTo(expectedResponse)));
    }

    @Test
    void createInvitationBadRequest() throws Exception {
        JSONObject content = new JSONObject();
        content.put("test", "invalid");
        mockMvc.perform(post("/api/invitations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateInvitationOk() throws Exception {
        InvitationDto expectedResponse = invitationMapper.mapToDto(
                new Invitation(200L, 0L, 1L, "message",
                        InvitationStatus.PENDING, LocalDate.now()));
        String response = mockMvc.perform(put("/api/invitations/200")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(objectMapper.readValue(response, InvitationDto.class),
                is(equalTo(expectedResponse)));
    }

    @Test
    void updateInvitationBadRequest() throws Exception {
        JSONObject content = new JSONObject();
        content.put("test", "invalid");
        mockMvc.perform(put("/api/invitations/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteInvitationOk() throws Exception {
        mockMvc.perform(delete("/api/invitations/100"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteInvitationNotFound() throws Exception {
        mockMvc.perform(delete("/api/invitations/0"))
                .andExpect(status().isNotFound());
    }
}