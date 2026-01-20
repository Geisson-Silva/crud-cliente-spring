package com.example.dto;

public class ErroCampoDTO {

    private String campo;
    private String mensagem;

    public ErroCampoDTO(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
