package com.example.primeira_api.dto;

import com.example.primeira_api.validation.CpfValido;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class PessoaUpdateDTO {

    @Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 dígitos.")
    private String telefone;

    @Email(message = "O email deve ser válido.")
    private String email;

    @CpfValido
    private String cpf;


    private String nome;


    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}