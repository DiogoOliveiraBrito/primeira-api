package com.example.primeira_api.dto;

import com.example.primeira_api.validation.CpfValido;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PessoaDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Schema(description = "Nome completo da pessoa", example = "Diogo de Oliveira")
    private String nome;

    @NotBlank(message = "O telefone é obrigatório.")
    @Pattern(regexp = "^[0-9]+$", message = "O telefone deve conter apenas números.")
    @Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 dígitos.")
    @Schema(description = "Número de telefone com DDD")
    private String telefone;

    @NotBlank(message = "O email é obrigatório.")
    @Column(unique = true)
    @Email(message = "O email deve ser válido.")
    @Schema(description = "os emails devem ser únicos", example = "diogo@exemplo.com")
    private String email;

    @NotBlank(message = "O CPF é obrigatório.")
    @CpfValido
    @Column(unique = true)
    @Schema(description = "CPF com 11 dígitos, apenas números", example = "02912365476")
    private String cpf;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}