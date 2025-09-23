package com.example.primeira_api.controller;


import com.example.primeira_api.dto.PessoaDTO;
import com.example.primeira_api.dto.PessoaUpdateDTO;
import com.example.primeira_api.repository.PessoaRepository;
import com.example.primeira_api.model.Pessoa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.example.primeira_api.dto.ErroValidacao;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoas", description = "Gerenciamento de pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroValidacao handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ErroValidacao(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                ex.getBindingResult().getFieldErrors()
        );
    }

    @GetMapping
    @Operation(summary = "Busca todas as pessoas cadastradas",
            description = "Retorna uma lista completa de todas as pessoas no banco de dados.")
    public List<Pessoa> getAllPessoas() {
        return pessoaRepository.findAll();
    }


    @Operation(summary = "Cria uma Pessoa",
            description = "Cadastra uma nova pessoa com nome, telefone, email e CPF.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso", content = @Content(schema = @Schema(implementation = Pessoa.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (erros de validação)",
                    content = @Content(schema = @Schema(implementation = ErroValidacao.class))),
            @ApiResponse(responseCode = "409", description = "Conflito: CPF já cadastrado",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<?> createPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findByCpf(pessoaDTO.getCpf());

        if (pessoaExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Erro: Este CPF já está cadastrado.");
        }

        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome(pessoaDTO.getNome());
        novaPessoa.setCpf(pessoaDTO.getCpf());
        novaPessoa.setTelefone(pessoaDTO.getTelefone());
        novaPessoa.setEmail(pessoaDTO.getEmail());

        Pessoa savedPessoa = pessoaRepository.save(novaPessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
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
    public ResponseEntity<?> putPessoaById(@PathVariable long id, @Valid @RequestBody PessoaUpdateDTO pessoaDetalhes) {
        Optional<Pessoa> pessoaData = pessoaRepository.findById(id);

        if (pessoaData.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Pessoa pessoa = pessoaData.get();

        if (StringUtils.hasText(pessoaDetalhes.getCpf())) {
            Optional<Pessoa> pessoaExistente = pessoaRepository.findByCpf(pessoaDetalhes.getCpf());
            if (pessoaExistente.isPresent() && !pessoaExistente.get().getId().equals(id)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Este CPF já está cadastrado.");
            }
            pessoa.setCpf(pessoaDetalhes.getCpf());
        }

        if (StringUtils.hasText(pessoaDetalhes.getNome())) {
            pessoa.setNome(pessoaDetalhes.getNome());
        }
        if (StringUtils.hasText(pessoaDetalhes.getTelefone())) {
            pessoa.setTelefone(pessoaDetalhes.getTelefone());
        }
        if (StringUtils.hasText(pessoaDetalhes.getEmail())) {
            pessoa.setEmail(pessoaDetalhes.getEmail());
        }

        Pessoa updatedPessoa = pessoaRepository.save(pessoa);
        return ResponseEntity.ok(updatedPessoa);
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