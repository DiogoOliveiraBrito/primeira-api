package com.example.primeira_api.service;

import com.example.primeira_api.model.Pessoa;
import com.example.primeira_api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional // Adicione esta linha
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa salvarPessoa(Pessoa pessoa) {
        // A lógica de negócio vai aqui
        return pessoaRepository.save(pessoa);
    }
}