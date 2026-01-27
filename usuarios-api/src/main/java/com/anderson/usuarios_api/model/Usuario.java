package com.anderson.usuarios_api.model;

public class Usuario {

    private String nome;
    private Integer idade;

    public Usuario(){
    }

    public Usuario(String nome, Integer idade){
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }
}
