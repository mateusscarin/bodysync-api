package com.br.bodysync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.bodysync.model.Question;

/**
 *
 * @author Carlos
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    public boolean existsByDescription(String description);
    
}
