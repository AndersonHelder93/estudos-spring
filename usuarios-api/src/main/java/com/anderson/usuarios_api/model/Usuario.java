package com.anderson.usuarios_api.model;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer idade;

    @Column(unique = true, nullable = false)
    private String email;

    private String senha;

    private String role;


    public Usuario(){
    }

    public Usuario(long id, String nome, Integer idade, String email, String senha, String role){
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public Usuario(Long id, String nome, Integer idade, String email, String role) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.role = role;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
