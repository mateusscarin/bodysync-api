package com.br.bodysync.model.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.br.bodysync.model.enumerated.BiologicSex;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {

    private Long id;

    @NotNull(message = "O nome deve ser informado!")
    @NotBlank(message = "O nome não pode ser vazio!")
    private String fullName;

    @NotNull(message = "A data de aniversário deve ser informada!")
    private LocalDate birthDate;

    @CPF(message = "O CPF informado tem formato inválida!")
    @NotNull(message = "O CPF deve ser informado!")
    private String cpf;

    @NotNull(message = "O sexo biológico deve ser informado!")
    private BiologicSex sex;

    @NotNull(message = "O e-mail deve ser informado!")
    @Email(message = "O formato do e-mail é inválido!")
    private String email;

    private boolean hasAlreadyTrained;

    private Double time;

    private Long objectiveId;

    private Long objectiveName;

    @NotNull(message = "A senha não pode ser nula!")
    @NotBlank(message = "A senha deve ser informada!")
    private String password;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BiologicSex getSex() {
        return sex;
    }

    public void setSex(BiologicSex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isHasAlreadyTrained() {
        return hasAlreadyTrained;
    }

    public void setHasAlreadyTrained(boolean hasAlreadyTrained) {
        this.hasAlreadyTrained = hasAlreadyTrained;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Long getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public Long getObjectiveName() {
        return objectiveName;
    }

    public void setObjectiveName(Long objectiveName) {
        this.objectiveName = objectiveName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
