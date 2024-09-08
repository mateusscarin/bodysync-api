package com.br.bodysync.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.br.bodysync.model.Question;
import com.br.bodysync.model.dto.QuestionDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;
import com.br.bodysync.repository.QuestionRepository;
import com.br.bodysync.service.impl.QuestionServiceImpl;
import com.br.bodysync.service.util.ApiResponse;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private CustomObjectMapper<Question, QuestionDTO> questionsMapper;

    @InjectMocks
    private QuestionServiceImpl questionService;

    private QuestionDTO questionDTO;
    private Question question;

    @BeforeEach
    public void setup() {
        questionDTO = new QuestionDTO();
        questionDTO.setDescription("Teste");
        questionDTO.setStatus(true);

        question = new Question();
        question.setId(1L);
        question.setDescription("Teste");
        question.setStatus(true);
    }

    @Test
    public void testSave_Success() throws Exception {
        when(questionRepository.existsByDescription(questionDTO.getDescription())).thenReturn(false);
        when(questionRepository.saveAndFlush(any(Question.class))).thenReturn(question);

        ResponseEntity<Object> response = questionService.save(questionDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testSave_DuplicateDescription() throws Exception {
        when(questionRepository.existsByDescription(questionDTO.getDescription())).thenReturn(true);

        ResponseEntity<Object> response = questionService.save(questionDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Não é possivel cadastrar o Pergunta. Já existe outra Pergunta com o mesmo nome.",
                ((ApiResponse<?>) response.getBody()).getMessage());
    }

    @Test
    public void testEdit_Success() throws Exception {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(questionRepository.existsByDescription(questionDTO.getDescription())).thenReturn(false);
        when(questionsMapper.convertToEntity(questionDTO)).thenReturn(question);
        when(questionRepository.saveAndFlush(any(Question.class))).thenReturn(question);
        when(questionsMapper.convertToDto(any(Question.class))).thenReturn(questionDTO);

        ResponseEntity<Object> response = questionService.edit(1L, questionDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindById_Success() throws Exception {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(questionsMapper.convertToDto(any(Question.class))).thenReturn(questionDTO);

        ResponseEntity<Object> response = questionService.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDelete_Success() throws Exception {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        ResponseEntity<Object> response = questionService.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testChangeStatus_Success() throws Exception {
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        when(questionRepository.saveAndFlush(any(Question.class))).thenReturn(question);
        when(questionsMapper.convertToDto(any(Question.class))).thenReturn(questionDTO);

        ResponseEntity<Object> response = questionService.changeStatus(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
