package com.example.primeira_api.dto;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;

public class ErroValidacao {

    private String mensagem;
    private int status;
    private List<CampoErro> erros;

    public ErroValidacao(int status, String mensagem, List<FieldError> fieldErrors) {
        this.status = status;
        this.mensagem = mensagem;
        this.erros = fieldErrors.stream()
                .map(CampoErro::new)
                .collect(Collectors.toList());
    }

    public static class CampoErro {
        private String campo;
        private String erro;

        public CampoErro(FieldError fieldError) {
            this.campo = fieldError.getField();
            this.erro = fieldError.getDefaultMessage();
        }
        public String getCampo() {
            return campo;
        }
        public String getErro() {
            return erro;
        }
    }
    public String getMensagem() {
        return mensagem;
    }
    public int getStatus() {
        return status;
    }
    public List<CampoErro> getErros() {
        return erros;
    }
}