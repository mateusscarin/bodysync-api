/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.bodysync.model.dto;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author Carlos
 */
public class MetricDTO {
    
    private Long id;
    
    @NotBlank(message = "A Metricas precisa ser informada!")
    private String description;

    public MetricDTO() {
    }

    public MetricDTO(Long id) {
        this.id = id;
    }

    public MetricDTO(Long id, String description) {
        this.id = id;
        this.description = description;
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
    
    
}
