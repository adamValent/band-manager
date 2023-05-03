package cz.muni.fi.pa165.moduleemail.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.moduleemail.api.EmailDto;
import cz.muni.fi.pa165.moduleemail.api.EmailWithoutRecipientsDto;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.opaqueToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmailRestControllerTest {

    private static final Logger log = LoggerFactory.getLogger(EmailRestControllerTest.class);
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;


    @Autowired
    public EmailRestControllerTest(ObjectMapper objectMapper,
                                   MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void testSendEmailOK() throws Exception {
        log.debug("testSendEmailOK running");

        EmailDto request = new EmailDto(
                "test",
                new String[]{"test@gmail.com"},
                "test"
        );

        mockMvc.perform(post("/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }

    @Test
    void testSendEmailEmptyRecipients() throws Exception {
        log.debug("testSendEmailEmptyRecipients running");

        EmailDto request = new EmailDto(
                "test",
                new String[]{},
                "test"
        );

        mockMvc.perform(post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSendEmailInvalidEmailDto() throws Exception {
        log.debug("testSendEmailInvalidEmailDto running");

        JSONObject json = new JSONObject();
        json.put("subject", "INVALID");

        mockMvc.perform(post("/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString())
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testSendEmailToAllBandMembersMissingId() throws Exception {
        log.debug("testAlbumUpdateMissingId running");

        EmailWithoutRecipientsDto request = new EmailWithoutRecipientsDto(
                "test",
                "test"
        );

        mockMvc.perform(post(String.format("/email/band/%s", 0L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSendEmailToAllBandMembersInvalidDto() throws Exception {
        log.debug("testSendEmailToAllBandMembersInvalidDto running");

        JSONObject json = new JSONObject();
        json.put("subject", "INVALID");

        mockMvc.perform(post(String.format("/email/band/%s", 0L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString())
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSendEmailToAllBandMembersOk() throws Exception {
        log.debug("testSendEmailToAllBandMembersOk running");

        EmailWithoutRecipientsDto request = new EmailWithoutRecipientsDto(
                "test",
                "test"
        );

        mockMvc.perform(post(String.format("/email/band/%s", 1L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }

    @Test
    void testSendEmailToBandManagerMissingId() throws Exception {
        log.debug("testAlbumUpdateMissingId running");

        EmailWithoutRecipientsDto request = new EmailWithoutRecipientsDto(
                "test",
                "test"
        );

        mockMvc.perform(post(String.format("/email/band/%s/manager", 0L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSendEmailToBandManagerInvalidDto() throws Exception {
        log.debug("testSendEmailToAllBandMembersInvalidDto running");

        JSONObject json = new JSONObject();
        json.put("subject", "INVALID");

        mockMvc.perform(post(String.format("/email/band/%s/manager", 0L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString())
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSendEmailToBandManagerOk() throws Exception {
        log.debug("testSendEmailToAllBandMembersOk running");

        EmailWithoutRecipientsDto request = new EmailWithoutRecipientsDto(
                "test",
                "test"
        );

        mockMvc.perform(post(String.format("/email/band/%s/manager", 1L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }


    @Test
    void testSendEmailToTourBandMissingId() throws Exception {
        log.debug("testAlbumUpdateMissingId running");

        EmailWithoutRecipientsDto request = new EmailWithoutRecipientsDto(
                "test",
                "test"
        );

        mockMvc.perform(post(String.format("/email/tour/%s", 0L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testSendEmailToTourBandInvalidDto() throws Exception {
        log.debug("testSendEmailToAllBandMembersInvalidDto running");

        JSONObject json = new JSONObject();
        json.put("subject", "INVALID");

        mockMvc.perform(post(String.format("/email/tour/%s", 0L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.toString())
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSendEmailToTourBandOk() throws Exception {
        log.debug("testSendEmailToAllBandMembersOk running");

        EmailWithoutRecipientsDto request = new EmailWithoutRecipientsDto(
                "test",
                "test"
        );

        mockMvc.perform(post(String.format("/email/tour/%s", 1L))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }

}
