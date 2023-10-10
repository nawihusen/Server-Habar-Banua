package com.habarbanua.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.habarbanua.model.LoginRegisterRequest;
import com.habarbanua.model.Response;
import com.habarbanua.repository.mysql.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    void deleteData() {
//        userRepository.deleteAll();
//    } // if you want to delete all data you can use this

    @Test
    void registerSuccess() throws Exception {
        var req = new LoginRegisterRequest();
        req.setUsername("username");
        req.setPassword("password");
        mockMvc.perform(
                post("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            Response<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<>() {
                    });
            assertEquals("Success", response.getData());
        });
    }


    @Test
    void registerBadRequest() throws Exception {
        var req = new LoginRegisterRequest();
        req.setPassword("password");
        mockMvc.perform(
                post("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            Response<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<>() {
                    });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void registerDuplicateUsername() throws Exception {
        var req = new LoginRegisterRequest();
        req.setUsername("username");
        req.setPassword("password");
        mockMvc.perform(
                post("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            Response<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<>() {
                    });
            assertEquals("username already used", response.getErrors());
        });
    }

    @Test
    void createPerson() throws Exception {
        mockMvc.perform(
                post("/test")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("hello", "Location")
        ).andExpectAll(
        );
    }


}