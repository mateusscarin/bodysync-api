package com.br.bodysync.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;

import com.br.bodysync.model.enumerated.BiologicSex;
import com.br.bodysync.model.enumerated.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "tb_user", uniqueConstraints = @UniqueConstraint(columnNames = { "cpf", "email" }))
public abstract class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_type", insertable = false, updatable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserType type;

    @Column(name = "full_name", nullable = false)
    @NotNull(message = "O nome deve ser informado!")
    @NotBlank(message = "O nome não pode ser vazio!")
    private String fullName;

    @Column(name = "birth_date", nullable = false)
    @NotNull(message = "A data de aniversário deve ser informada!")
    private LocalDate birthDate;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @CPF
    @Column(unique = true, nullable = false)
    @NotNull(message = "O CPF deve ser informado!")
    private String cpf;

    @Column(nullable = false)
    @NotNull(message = "O sexo biológico deve ser informado!")
    private BiologicSex sex;

    @Column(unique = true, nullable = false)
    @NotNull(message = "O e-mail deve ser informado!")
    @Email(message = "O formato do e-mail é inválido!")
    private String email;

    @Column
    @NotNull(message = "Senha deve ser informada!")
    @NotBlank(message = "Senha não pode ser vazia!")
    @JsonIgnore
    private String password;

    @PrePersist
    private void prePersist() {
        setCreatedDate(LocalDateTime.now());
    }

    @PreUpdate
    private void preUpdate() {
        setUpdateDate(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
