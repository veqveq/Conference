package ru.veqveq.conference.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "Test", roles = "TEST")
public class ScheduleControllerTest {
    @Autowired
    private MockMvc mvc;
    private final static String API_PATH = "/api/v1/schedule";

    @Test
    public void findAllTest() throws Exception {
        mvc.perform(get(API_PATH)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(status().isOk());
    }

    @Test
    public void getMySpeaksTest() throws Exception {
        Principal mockPrincipal = mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("u2");

        mvc.perform(get(API_PATH + "/my_speaks")
                .principal(mockPrincipal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void getMyTalksTest() throws Exception {
        Principal mockPrincipal = mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("u4");

        mvc.perform(get(API_PATH + "/my_talks")
                .principal(mockPrincipal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void subscribeAsListenerTest() throws Exception {
        Principal mockPrincipal = mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("u4");

        mvc.perform(post(API_PATH + "/sub")
                .param("talkId", "4")
                .principal(mockPrincipal))
                .andExpect(status().isOk());

        mvc.perform(get(API_PATH + "/my_talks")
                .principal(mockPrincipal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)));

        mvc.perform(post(API_PATH + "/sub")
                .param("talkId", "4")
                .principal(mockPrincipal))
                .andExpect(status().isConflict());

        mvc.perform(post(API_PATH + "/unsub")
                .param("talkId", "4")
                .principal(mockPrincipal))
                .andExpect(status().isOk());
    }

    @Test
    public void unsubscribeAsListenerTest() throws Exception {
        Principal mockPrincipal = mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("u4");

        mvc.perform(post(API_PATH + "/unsub")
                .param("talkId", "1")
                .principal(mockPrincipal))
                .andExpect(status().isOk());

        mvc.perform(get(API_PATH + "/my_talks")
                .principal(mockPrincipal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));

        mvc.perform(post(API_PATH + "/unsub")
                .param("talkId", "1")
                .principal(mockPrincipal))
                .andExpect(status().isNotFound());

        mvc.perform(post(API_PATH + "/sub")
                .param("talkId", "1")
                .principal(mockPrincipal))
                .andExpect(status().isOk());
    }
}
