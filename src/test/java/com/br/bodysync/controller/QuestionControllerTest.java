package com.br.bodysync.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.br.bodysync.model.dto.QuestionDTO;
import com.br.bodysync.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MockitoExtension.class)
public class QuestionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
    }

    @Test
    public void testSaveQuestion() throws Exception {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setDescription("Test Question");
        questionDTO.setStatus(true);

        when(questionService.save(any(QuestionDTO.class))).thenReturn(ResponseEntity.created(null).build());

        mockMvc.perform(post("/question")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(questionDTO)))
                .andExpect(status().isCreated());

        verify(questionService, times(1)).save(any(QuestionDTO.class));
    }

    @Test
    public void testFindAllQuestions() throws Exception {
        when(questionService.findAll()).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(get("/question")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(questionService, times(1)).findAll();
    }

    @Test
    public void testFindQuestionById() throws Exception {
        Long questionId = 1L;
        when(questionService.findById(questionId)).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(get("/question/{idObjeto}", questionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(questionService, times(1)).findById(questionId);
    }

    @Test
    public void testEditQuestion() throws Exception {
        Long questionId = 1L;
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setDescription("Updated Question");
        questionDTO.setStatus(true);

        when(questionService.edit(eq(questionId), any(QuestionDTO.class))).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(put("/question/{idObjeto}", questionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(questionDTO)))
                .andExpect(status().isOk());

        verify(questionService, times(1)).edit(eq(questionId), any(QuestionDTO.class));
    }

    @Test
    public void testDeleteQuestion() throws Exception {
        Long questionId = 1L;

        when(questionService.delete(questionId)).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(delete("/question/{idObjeto}", questionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(questionService, times(1)).delete(questionId);
    }

    @Test
    public void testChangeStatus() throws Exception {
        Long questionId = 1L;

        when(questionService.changeStatus(questionId)).thenReturn(ResponseEntity.ok().build());

        mockMvc.perform(patch("/question/{idObjeto}/status", questionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(questionService, times(1)).changeStatus(questionId);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
