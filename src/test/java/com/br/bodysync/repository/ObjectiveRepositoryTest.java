package com.br.bodysync.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.br.bodysync.model.Objective;

@DataJpaTest
public class ObjectiveRepositoryTest {

    @Autowired
    private ObjectiveRepository objectiveRepository;

    private Objective objective;

    @BeforeEach
    public void setup() {
        objective = new Objective();
        objective.setName("Test Objective");
        objective.setDescription("Objective for testing");
    }

    @Test
    public void testSaveObjective() {
        Objective savedObjective = objectiveRepository.save(objective);
        assertThat(savedObjective).isNotNull();
        assertThat(savedObjective.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindByName() {
        objectiveRepository.save(objective);
        Optional<Objective> foundObjective = objectiveRepository.findByName("Test Objective");
        assertThat(foundObjective).isPresent();
        assertThat(foundObjective.get().getName()).isEqualTo("Test Objective");
    }

    @Test
    public void testExistsByName() {
        objectiveRepository.save(objective);
        boolean exists = objectiveRepository.existsByName("Test Objective");
        assertThat(exists).isTrue();
    }

    @Test
    public void testDeleteObjective() {
        Objective savedObjective = objectiveRepository.save(objective);
        objectiveRepository.deleteById(savedObjective.getId());

        Optional<Objective> deletedObjective = objectiveRepository.findById(savedObjective.getId());
        assertThat(deletedObjective).isEmpty();
    }

}
