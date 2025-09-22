package com.example.primeira_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.primeira_api.model.Pessoa;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa , Long> {

}
