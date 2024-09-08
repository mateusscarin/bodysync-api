package com.br.bodysync.model.dto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Carlos
 */
public class QuestionDTO {
 
    private Long id;

    @NotBlank(message = "A descrição não pode ser em branco")
    private String description;
    
    private boolean status;

    public QuestionDTO() {
    }

    public QuestionDTO(Long id) {
        this.id = id;
    }

    public QuestionDTO(Long id, String description, boolean status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
  
}
