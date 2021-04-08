package com.accenture.userservice.error;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseErrors {
    private Integer status;
    private OffsetDateTime data;
    public String message;
    public List<Body> bodys;

    public static class Body{
        private String nome;
        private String mensagem;
        public Body(String nome, String mensagem) {
            this.setNome(nome);
            this.setMensagem(mensagem);
        }
        public String getNome() {
            return nome;
        }
        public void setNome(String nome) {
            this.nome = nome;
        }
        public String getMensagem() {
            return mensagem;
        }
        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }

    public ResponseErrors(Integer status, OffsetDateTime data, String message, List<Body> bodys) {
        super();
        this.status = status;
        this.data = data;
        this.message = message;
        this.bodys = bodys;
    }
    public ResponseErrors() {

    }

    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public OffsetDateTime getData() {
        return data;
    }
    public void setData(OffsetDateTime data) {
        this.data = data;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Body> getBodys() {
        return bodys;
    }
    public void setBodys(List<Body> bodys) {
        this.bodys = bodys;
    }
}
