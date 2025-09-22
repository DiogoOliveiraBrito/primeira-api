package com.example.primeira_api.controller;


import com.example.primeira_api.repository.PessoaRepository;
import com.example.primeira_api.model.Pessoa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    @Operation(description = "Busca todas as pessoas")
    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }
    @Operation(description = "Cria uma Pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201" , description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400" , description = "Requisição inválida (dados errados)")
    })
    @PostMapping
    public Pessoa CreatePessoa(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Operation(description = "Busca as pessoas pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Retorna a Pessoa"),
            @ApiResponse(responseCode = "404" , description = "Pessoa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById (@PathVariable long id) {
        return pessoaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(description = "Atualiza uma pessoa existente pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "404" , description = "Pessoa não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> putPessoaById(@PathVariable long id , @RequestBody Pessoa pessoaDetalhes) {
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    pessoa.setNome(pessoaDetalhes.getNome());
                    pessoa.setCpf(pessoaDetalhes.getCpf());
                    pessoa.setTelefone(pessoaDetalhes.getTelefone());
                    pessoa.setEmail(pessoaDetalhes.getEmail());
                    Pessoa updatePessoa = pessoaRepository.save(pessoa);
                    return ResponseEntity.ok(updatePessoa);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(description = "Deleta uma pessoa pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204" , description = "Pessoa deletada com sucesso"),
            @ApiResponse(responseCode = "404" , description = "Pessoa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoaById(@PathVariable long id ) {
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    pessoaRepository.delete(pessoa);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
