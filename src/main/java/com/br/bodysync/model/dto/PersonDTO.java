package com.br.bodysync.model.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PersonDTO {

    private Long id;

    @NotNull(message = "O nome completo precisa ser informado!")
    @NotBlank(message = "O nome completo não pode estar em branco!")
    private String fullName;

    @NotNull(message = "A data de nascimento precisa ser informada!")
    private LocalDate dataNascimento;

    @NotNull(message = "O CPF precisa ser informado!")
    @NotBlank
    @CPF(message = "O CPF precisa ser válido!")
    private String cpf;

    @NotNull(message = "A senha precisa ser informada!")
    @NotBlank(message = "A senha não pode estar em branco!")
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
