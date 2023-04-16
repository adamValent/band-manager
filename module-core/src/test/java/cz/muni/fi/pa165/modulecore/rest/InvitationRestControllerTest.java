package cz.muni.fi.pa165.modulecore.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.modulecore.api.BandDto;
import cz.muni.fi.pa165.modulecore.api.InvitationDto;
import cz.muni.fi.pa165.modulecore.data.enums.Genre;
import cz.muni.fi.pa165.modulecore.data.enums.InvitationStatus;
import cz.muni.fi.pa165.modulecore.data.enums.UserType;
import cz.muni.fi.pa165.modulecore.data.model.Band;
import cz.muni.fi.pa165.modulecore.data.model.Invitation;
import cz.muni.fi.pa165.modulecore.data.model.User;
import cz.muni.fi.pa165.modulecore.data.repository.InvitationRepository;
import cz.muni.fi.pa165.modulecore.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.modulecore.mapper.InvitationMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InvitationRestControllerTest {
    private final MockMvc mockMvc;
    private final InvitationMapper invitationMapper;
    private final ObjectMapper objectMapper;

    @MockBean
    private InvitationRepository invitationRepository;

    private Invitation testingInvitation;

    @Autowired
    public InvitationRestControllerTest(MockMvc mockMvc,
                                        InvitationMapper invitationMapper,
                                        ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.invitationMapper = invitationMapper;
        this.objectMapper = objectMapper;

        User user = new User(1L, UserType.MANAGER, "test", "test", "test");
        Band band = new Band(1L, "TEST", Genre.ROCK, new Byte[]{}, user);
        testingInvitation = new Invitation(1L, "Invitation", InvitationStatus.PENDING, LocalDate.now(),
                band, user);
    }

    @Test
    void findByIdOk() throws Exception {

        Mockito.when(invitationRepository.findById(testingInvitation.getId())).thenReturn(Optional.of(testingInvitation));

        String response = mockMvc.perform(get(String.format("/invitations/%s", testingInvitation.getId())))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(objectMapper.readValue(response, InvitationDto.class),
                is(equalTo(invitationMapper.mapToDto(testingInvitation))));
    }

    @Test
    void findByIdNotFound() throws Exception {
        Mockito.when(invitationRepository.findById(0L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/invitations/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createInvitationOk() throws Exception {

        Mockito.when(invitationRepository.save(testingInvitation)).thenReturn(testingInvitation);
        InvitationDto expectedResponse = invitationMapper.mapToDto(testingInvitation);

        String response = mockMvc.perform(post("/invitations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expectedResponse)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        InvitationDto actual = objectMapper.readValue(response, InvitationDto.class);
        assertThat(actual, is(equalTo(expectedResponse)));
    }

    @Test
    void createInvitationBadRequest() throws Exception {
        JSONObject content = new JSONObject();
        content.put("test", "invalid");
        mockMvc.perform(post("/invitations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateInvitationOk() throws Exception {

        Mockito.when(invitationRepository.save(testingInvitation)).thenReturn(testingInvitation);
        Mockito.when(invitationRepository.existsById(testingInvitation.getId())).thenReturn(true);

        InvitationDto expectedResponse = invitationMapper.mapToDto(testingInvitation);

        String response = mockMvc.perform(put(String.format("/invitations/%s", testingInvitation.getId()))
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
        mockMvc.perform(put("/invitations/100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteInvitationOk() throws Exception {
        Mockito.doNothing().when(invitationRepository).deleteById(testingInvitation.getId());

        mockMvc.perform(delete(String.format("/invitations/%s", testingInvitation.getId())))
                .andExpect(status().isOk());
    }

    @Test
    void deleteInvitationNotFound() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(invitationRepository).deleteById(0L);

        mockMvc.perform(delete("/invitations/0"))
                .andExpect(status().isNotFound());
    }
}