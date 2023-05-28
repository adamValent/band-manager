package cz.muni.fi.pa165.moduleuser.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.librarymodel.api.UserDto;
import cz.muni.fi.pa165.librarymodel.enums.UserType;
import cz.muni.fi.pa165.moduleuser.data.model.User;
import cz.muni.fi.pa165.moduleuser.data.repository.UserRepository;
import cz.muni.fi.pa165.moduleuser.exception.ResourceNotFoundException;
import cz.muni.fi.pa165.moduleuser.mapper.UserMapper;
import cz.muni.fi.pa165.moduleuser.service.CoreService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.opaqueToken;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserAuthRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private CoreService coreService;

    @Test
    void findByIdOk() throws Exception {
        User user = new User(1L, "me@mail.com", "usr");
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        String response = mockMvc.perform(get("/users-auth/1")
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(objectMapper.readValue(response, UserDto.class),
                is(equalTo(userMapper.mapToDto(userRepository.findById(1L).get()))));
    }

    @Test
    void findByIdNotFound() throws Exception {
        Mockito.when(userRepository.findById(0L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/users-auth/0")
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUserOk() throws Exception {
        User user = new User(1L, "me@mail.com", "usr");
        UserDto request = new UserDto(user.getId(), user.getEmail(), UserType.MANAGER, "John", "Doe");
        Mockito.when(userRepository.save(any())).thenReturn(user);
        Mockito.doNothing().when(coreService).createUser(any(), any());
        String response = mockMvc.perform(post("/users-auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(objectMapper.readValue(response, UserDto.class),
                is(equalTo(request)));
    }

    @Test
    void createUserBadRequest() throws Exception {
        JSONObject content = new JSONObject();
        content.put("test", "invalid");
        mockMvc.perform(post("/users-auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.toString())
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUserOk() throws Exception {
        User user = new User(1L, "me@mail.com", "usr");
        UserDto request = new UserDto(user.getId(), user.getEmail(), UserType.MANAGER, "John", "Doe");
        Mockito.when(userRepository.save(any())).thenReturn(user);
        Mockito.when(userRepository.existsById(user.getId())).thenReturn(true);
        Mockito.doNothing().when(coreService).updateUser(any(), any());
        String response = mockMvc.perform(put("/users-auth/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(objectMapper.readValue(response, UserDto.class),
                is(equalTo(userMapper.mapToDto(user))));
    }


    @Test
    void updateUserEmailOk() throws Exception {
        User user = new User(1L, "me@mail.com", "usr");
        UserDto request = new UserDto(1L, user.getEmail(), UserType.MANAGER, "John", "Doe");
        Mockito.when(userRepository.save(any())).thenReturn(user);
        Mockito.when(userRepository.existsById(user.getId())).thenReturn(true);
        Mockito.doNothing().when(coreService).updateUser(any(), any());
        String response = mockMvc.perform(put("/users-auth/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("Authorization", "token")
                        .with(opaqueToken()))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertThat(objectMapper.readValue(response, UserDto.class),
                is(equalTo(userMapper.mapToDto(user))));
    }

    @Test
    void updateUserBadRequest() throws Exception {
        JSONObject content = new JSONObject();
        content.put("test", "invalid");
        mockMvc.perform(put("/users-auth/20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.toString())
                        .with(opaqueToken()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteUserOk() throws Exception {
        Mockito.doNothing().when(userRepository).deleteById(1L);
        mockMvc.perform(delete("/users-auth/1")
                        .with(opaqueToken()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserNotFound() throws Exception {
        Mockito.doThrow(new ResourceNotFoundException()).when(userRepository).deleteById(0L);
        mockMvc.perform(delete("/users-auth/0")
                        .with(opaqueToken()))
                .andExpect(status().isNotFound());
    }
}