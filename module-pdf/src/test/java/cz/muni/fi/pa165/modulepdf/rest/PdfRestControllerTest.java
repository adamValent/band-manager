package cz.muni.fi.pa165.modulepdf.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.modulepdf.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.modulepdf.service.CoreService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.opaqueToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PdfRestControllerTest {

    private static final Logger log = LoggerFactory.getLogger(PdfRestControllerTest.class);
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    @MockBean
    private CoreService coreService;


    @Autowired
    public PdfRestControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    void testGeneratePdfBandAllMembersMissingId() throws Exception {
        log.debug("testGeneratePdfBandAllMembersMissingId running");

        Mockito.when(coreService.getBandMembers(any(), any())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(String.format("/pdf/band/%s/members", 0L))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGeneratePdfBandAllMembersOk() throws Exception {
        log.debug("testGeneratePdfBandAllMembersOk running");

        Mockito.when(coreService.getBandMembers(anyLong(), anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(get(String.format("/pdf/band/%s/members", 1L))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }

    @Test
    void testGeneratePdfBandAllToursMissingId() throws Exception {
        log.debug("testGeneratePdfBandAllToursMissingId running");

        Mockito.when(coreService.getBandTours(anyLong(), anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(String.format("/pdf/band/%s/tours", 0L))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGeneratePdfBandAllToursOk() throws Exception {
        log.debug("testGeneratePdfBandAllToursOk running");

        Mockito.when(coreService.getBandTours(anyLong(), anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(get(String.format("/pdf/band/%s/tours", 1L))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }


    @Test
    void testGeneratePdfBandAllAlbumsMissingId() throws Exception {
        log.debug("testGeneratePdfBandAllAlbumsMissingId running");

        Mockito.when(coreService.getBandAlbums(anyLong(), anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(String.format("/pdf/band/%s/albums", 0L))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGeneratePdfBandAllAlbumsOk() throws Exception {
        log.debug("testGeneratePdfBandAllAlbumsOk running");

        Mockito.when(coreService.getBandAlbums(anyLong(), anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(get(String.format("/pdf/band/%s/albums", 1L))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }

    @Test
    void testGeneratePdfAlbumAllSongMissingId() throws Exception {
        log.debug("testGeneratePdfAlbumAllSongMissingId running");

        Mockito.when(coreService.getAlbumSongs(anyLong(), anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(String.format("/pdf/albums/%s/songs", 0L))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGeneratePdfAlbumAllSongOk() throws Exception {
        log.debug("testGeneratePdfAlbumAllSongOk running");

        Mockito.when(coreService.getAlbumSongs(anyLong(), anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(get(String.format("/pdf/albums/%s/songs", 100L))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }


    @Test
    void testGeneratePdfTourAllTourDatesMissingId() throws Exception {
        log.debug("testGeneratePdfTourAllTourDatesMissingId running");

        Mockito.when(coreService.getTourDatesOfTour(anyLong(), anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(String.format("/pdf/tours/%s/tourDates", 0L))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGeneratePdfTourAllTourDatesOk() throws Exception {
        log.debug("testGeneratePdfTourAllTourDatesOk running");

        Mockito.when(coreService.getTourDatesOfTour(anyLong(), anyString())).thenReturn(new ArrayList<>());

        mockMvc.perform(get(String.format("/pdf/tours/%s/tourDates", 101L))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }
}
