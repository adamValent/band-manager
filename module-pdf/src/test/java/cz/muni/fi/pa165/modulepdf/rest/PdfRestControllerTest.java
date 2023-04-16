package cz.muni.fi.pa165.modulepdf.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PdfRestControllerTest {

    private static final Logger log = LoggerFactory.getLogger(PdfRestControllerTest.class);
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;


    @Autowired
    public PdfRestControllerTest(ObjectMapper objectMapper,
                                   MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void testGeneratePdfBandAllMembersMissingId() throws Exception {
        log.debug("testGeneratePdfBandAllMembersMissingId running");

        mockMvc.perform(get(String.format("/pdf/band/%s/members", 0L)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGeneratePdfBandAllMembersOk() throws Exception {
        log.debug("testGeneratePdfBandAllMembersOk running");

        mockMvc.perform(get(String.format("/pdf/band/%s/members", 1L)))
                .andExpect(status().isOk());
    }

    @Test
    void testGeneratePdfBandAllToursMissingId() throws Exception {
        log.debug("testGeneratePdfBandAllToursMissingId running");

        mockMvc.perform(get(String.format("/pdf/band/%s/tours", 0L)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGeneratePdfBandAllToursOk() throws Exception {
        log.debug("testGeneratePdfBandAllToursOk running");

        mockMvc.perform(get(String.format("/pdf/band/%s/tours", 1L)))
                .andExpect(status().isOk());
    }


    @Test
    void testGeneratePdfBandAllAlbumsMissingId() throws Exception {
        log.debug("testGeneratePdfBandAllAlbumsMissingId running");

        mockMvc.perform(get(String.format("/pdf/band/%s/albums", 0L)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGeneratePdfBandAllAlbumsOk() throws Exception {
        log.debug("testGeneratePdfBandAllAlbumsOk running");

        mockMvc.perform(get(String.format("/pdf/band/%s/albums", 1L)))
                .andExpect(status().isOk());
    }

    @Test
    void testGeneratePdfAlbumAllSongMissingId() throws Exception {
        log.debug("testGeneratePdfAlbumAllSongMissingId running");

        mockMvc.perform(get(String.format("/pdf/albums/%s/songs", 0L)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGeneratePdfAlbumAllSongOk() throws Exception {
        log.debug("testGeneratePdfAlbumAllSongOk running");

        mockMvc.perform(get(String.format("/pdf/albums/%s/songs", 100L)))
                .andExpect(status().isOk());
    }


    @Test
    void testGeneratePdfTourAllTourDatesMissingId() throws Exception {
        log.debug("testGeneratePdfTourAllTourDatesMissingId running");

        mockMvc.perform(get(String.format("/pdf/tours/%s/tourDates", 0L)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGeneratePdfTourAllTourDatesOk() throws Exception {
        log.debug("testGeneratePdfTourAllTourDatesOk running");

        mockMvc.perform(get(String.format("/pdf/tours/%s/tourDates", 101L)))
                .andExpect(status().isOk());
    }
}
