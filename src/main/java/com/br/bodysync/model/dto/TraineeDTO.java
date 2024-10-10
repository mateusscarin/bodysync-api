package com.br.bodysync.model.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.br.bodysync.model.enumerated.BiologicSex;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TraineeDTO(
        @NotNull(message = "O nome completo precisa ser informado!") @NotBlank(message = "O nome completo não pode estar em branco!") String fullName,
        @NotNull(message = "O CPF precisa ser informado!") @NotBlank @CPF(message = "O CPF precisa ser válido!") String cpf,
        @NotNull(message = "A senha precisa ser informada!") @NotBlank(message = "A senha não pode estar em branco!") String password,
        @NotNull(message = "A data de nascimento deve ser informada!") @Schema(type = "string", pattern = "^\\d{4}-\\d{2}-\\d{2}$", example = "2024-01-01") LocalDate birthDate,
        @NotNull(message = "O sexo biológico deve ser informado!") BiologicSex sex,
        @NotNull(message = "O e-mail deve ser informado!") @Email(message = "O formato do e-mail é inválido!") String email,
        @NotNull(message = "Usuário já ter treinado deve ser informada!") boolean hasAlreadyTrained,
        @NotNull(message = "O tempo de treino deve ser informado!") Double time,
        @NotNull(message = "O objetivo deve ser informado!") Long objectiveId,
        @NotNull(message = "O personal trainer deve ser informado!") Long personalTrainerId) {
}