package com.Spring.Cinetime.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

//infomações do nosso usuario
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String confirmarSenha;

    public Usuario(){

    }

    public Usuario(String name, LocalDate dataNascimento, String email, String senha, String confirmarSenha) {
        this.name = name;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.confirmarSenha = confirmarSenha;

    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDataNascimento() {return dataNascimento;}

    public void setDataNascimento(LocalDate dataNascimento) {this.dataNascimento = dataNascimento;}

    public String getEmail() {return email; }

    public void setEmail(String email) {this.email = email; }

    public String getSenha() {return senha; }

    public void setSenha(String senha) {this.senha = senha; }

    public String getConfirmarSenha() {return confirmarSenha;}

    public void setConfirmarSenha(String confirmarSenha) {this.confirmarSenha = confirmarSenha;}
}
