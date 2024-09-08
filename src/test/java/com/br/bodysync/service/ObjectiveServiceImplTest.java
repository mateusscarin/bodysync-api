package com.br.bodysync.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.br.bodysync.model.Objective;
import com.br.bodysync.model.dto.ObjectiveDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;
import com.br.bodysync.repository.ObjectiveRepository;
import com.br.bodysync.service.impl.ObjectiveServiceImpl;
import com.br.bodysync.service.util.ApiResponse;

class ObjectiveServiceImplTest {

    @Mock
    private ObjectiveRepository objectiveRepository;

    @Mock
    private CustomObjectMapper<Objective, ObjectiveDTO> objectiveMapper;

    @InjectMocks
    private ObjectiveServiceImpl objectiveService;

    private Objective objective;
    private ObjectiveDTO objectiveDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        objective = new Objective();
        objective.setId(1L);
        objective.setName("Weight Loss");
        objective.setDescription("Lose 10kg in 3 months");
        objective.setCreatedDate(LocalDateTime.now());
        objective.setStatus(true);

        objectiveDTO = new ObjectiveDTO();
        objectiveDTO.setName("Weight Loss");
        objectiveDTO.setDescription("Lose 10kg in 3 months");
    }

    @Test
    void testSave_Success() throws Exception {
        when(objectiveRepository.existsByName(anyString())).thenReturn(false);
        when(objectiveRepository.saveAndFlush(any(Objective.class))).thenReturn(objective);

        ResponseEntity<Object> response = objectiveService.save(objectiveDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(objectiveRepository, times(1)).saveAndFlush(any(Objective.class));
    }

    @Test
    void testSave_ObjectiveAlreadyExists() throws Exception {
        when(objectiveRepository.existsByName(anyString())).thenReturn(true);

        ResponseEntity<Object> response = objectiveService.save(objectiveDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isInstanceOf(ApiResponse.class);
        verify(objectiveRepository, never()).saveAndFlush(any(Objective.class));
    }

    @Test
    void testFindById_Success() throws Exception {
        when(objectiveRepository.findById(anyLong())).thenReturn(Optional.of(objective));
        when(objectiveMapper.convertToDto(any(Objective.class))).thenReturn(objectiveDTO);

        ResponseEntity<Object> response = objectiveService.findById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(ApiResponse.class);
        verify(objectiveRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_ObjectiveNotFound() {
        when(objectiveRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            objectiveService.findById(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("O objetivo com ID 1 não foi encontrado!");
    }

    @Test
    void testEdit_Success() throws Exception {
        when(objectiveRepository.findById(anyLong())).thenReturn(Optional.of(objective));
        when(objectiveRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(objectiveRepository.saveAndFlush(any(Objective.class))).thenReturn(objective);
        when(objectiveMapper.convertToEntity(any(ObjectiveDTO.class))).thenReturn(objective);

        ResponseEntity<Object> response = objectiveService.edit(1L, objectiveDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(objectiveRepository, times(1)).saveAndFlush(any(Objective.class));
    }

    @Test
    void testEdit_ObjectiveAlreadyExists() throws Exception {
        when(objectiveRepository.findById(anyLong())).thenReturn(Optional.of(objective));
        when(objectiveRepository.findByName(anyString())).thenReturn(Optional.of(objective));

        ResponseEntity<Object> response = objectiveService.edit(1L, objectiveDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        verify(objectiveRepository, never()).saveAndFlush(any(Objective.class));
    }

    @Test
    void testDelete_Success() throws Exception {
        when(objectiveRepository.findById(anyLong())).thenReturn(Optional.of(objective));

        ResponseEntity<Object> response = objectiveService.delete(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(objectiveRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDelete_ObjectiveNotFound() {
        when(objectiveRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            objectiveService.delete(1L);
        });

        assertThat(exception.getMessage()).isEqualTo("O objetivo com ID 1 não foi encontrado!");
    }

}
