/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.bodysync.repository;

import com.br.bodysync.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos
 */

@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long>{

    public boolean existsByDescription(String description); 
}
