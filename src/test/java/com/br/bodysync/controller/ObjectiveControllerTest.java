package com.br.bodysync.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.br.bodysync.model.dto.ObjectiveDTO;
import com.br.bodysync.service.ObjectiveService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ObjectiveController.class)
public class ObjectiveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ObjectiveService objectiveService;

    @Autowired
    private ObjectMapper objectMapper;

    private ObjectiveDTO objectiveDTO;

    @BeforeEach
    public void setup() {
        objectiveDTO = new ObjectiveDTO();
        objectiveDTO.setName("Objective Test");
        objectiveDTO.setDescription("Description Test");
    }

    @Test
    public void testSaveObjective() throws Exception {
        when(objectiveService.save(any(ObjectiveDTO.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        mockMvc.perform(post("/objective")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(objectiveDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testFindAllObjectives() throws Exception {
        when(objectiveService.findAll())
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(get("/objective"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindObjectiveById() throws Exception {
        when(objectiveService.findById(anyLong()))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(get("/objective/{idObjeto}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testEditObjective() throws Exception {
        when(objectiveService.edit(anyLong(), any(ObjectiveDTO.class)))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(put("/objective/{idObjeto}", 1L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(objectiveDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteObjective() throws Exception {
        when(objectiveService.delete(anyLong()))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(delete("/objective/{idObjeto}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    public void testChangeStatus() throws Exception {
        when(objectiveService.changeStatus(anyLong()))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(patch("/objective/{idObjeto}/status", 1L))
                .andExpect(status().isOk());
    }

}
