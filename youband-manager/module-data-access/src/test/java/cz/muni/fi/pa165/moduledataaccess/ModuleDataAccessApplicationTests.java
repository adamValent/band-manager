package cz.muni.fi.pa165.moduledataaccess;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.moduledataaccess.model.User;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ModuleDataAccessApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(ModuleDataAccessApplicationTests.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
    }

    // GET TESTS
    @Test
    void testActivityGetAll() throws Exception {
        log.debug("testActivityGetAll() running");
        String response = mockMvc.perform(get("/activity/all"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
    }

    @Test
    void testUserGetAll() throws Exception {
        log.debug("testUserGetAll() running");
        String response = mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
    }

    @Test
    void testSongGetAll() throws Exception {
        log.debug("testSongGetAll() running");
        String response = mockMvc.perform(get("/song/all"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
    }

    @Test
    void testBandGetAll() throws Exception {
        log.debug("testBandGetAll() running");
        String response = mockMvc.perform(get("/band/all"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
    }

    @Test
    void testAlbumGetAll() throws Exception {
        log.debug("testAlbumGetAll() running");
        String response = mockMvc.perform(get("/album/all"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
    }

    // DELETE TESTS

    @Test
    void testUserDelete() throws Exception {
        log.debug("testUserDelete() running");
        String response = mockMvc.perform(delete("/user/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
    }

    // POST TESTS

    @Test
    void testUserPost() throws Exception {
        log.debug("testUserPost() running");
        String expectedResponse = "First One";
        JSONObject json = new JSONObject();
        json.put("username", "First One");
        String response = mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
    }

    // PUT TESTS

    @Test
    void testUserPut() throws Exception {
        log.debug("testUserPut() running");
        String expectedResponse = "First One";
        JSONObject json = new JSONObject();
        json.put("id", "2");
        json.put("username", "Last one");
        String response = mockMvc.perform(put("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(json)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        log.debug("response: {}", response);
    }
}
