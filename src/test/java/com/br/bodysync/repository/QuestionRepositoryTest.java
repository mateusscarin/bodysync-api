package com.br.bodysync.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.br.bodysync.model.Question;

@DataJpaTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    private Question question;

    @BeforeEach
    public void setUp() {
        question = new Question();
        question.setDescription("Test Question");
        question.setStatus(true);
        questionRepository.saveAndFlush(question);
    }

    @Test
    public void testSaveQuestion() {
        Question savedQuestion = questionRepository.saveAndFlush(question);
        assertThat(savedQuestion.getId()).isNotNull();
        assertThat(savedQuestion.getDescription()).isEqualTo("Test Question");
    }

    @Test
    public void testFindById() {
        Optional<Question> foundQuestion = questionRepository.findById(question.getId());
        assertThat(foundQuestion.isPresent()).isTrue();
        assertThat(foundQuestion.get().getDescription()).isEqualTo("Test Question");
    }

    @Test
    public void testExistsByDescription() {
        assertThat(questionRepository.findAll()).hasSize(1);
    }

}