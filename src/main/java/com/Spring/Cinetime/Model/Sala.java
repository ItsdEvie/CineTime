package com.Spring.Cinetime.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "salas")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // nome da sala
    @Column(nullable = false)
    private String nome;

    // idioma
    @Column(nullable = false)
    private String idioma;

    // pública ou privada
    @Column(nullable = false)
    private Boolean visivel;

    // senha opcional
    private String senha;

    // quantidade máxima
    @Column(nullable = false)
    private Integer participantesMax;

    // participantes atuais
    @Column(nullable = false)
    private Integer participantesAtual = 0;

    // dono da sala
    @Column(nullable = false)
    private Long donoId;

    public Sala() {
    }

    public Sala(
            String nome,
            String idioma,
            Boolean visivel,
            String senha,
            Integer participantesMax,
            Integer participantesAtual,
            Long donoId
    ) {

        this.nome = nome;
        this.idioma = idioma;
        this.visivel = visivel;
        this.senha = senha;
        this.participantesMax = participantesMax;
        this.participantesAtual = participantesAtual;
        this.donoId = donoId;

    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Boolean getVisivel() {
        return visivel;
    }

    public void setVisivel(Boolean visivel) {
        this.visivel = visivel;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getParticipantesMax() {
        return participantesMax;
    }

    public void setParticipantesMax(Integer participantesMax) {
        this.participantesMax = participantesMax;
    }

    public Integer getParticipantesAtual() {
        return participantesAtual;
    }

    public void setParticipantesAtual(Integer participantesAtual) {
        this.participantesAtual = participantesAtual;
    }

    public Long getDonoId() {
        return donoId;
    }

    public void setDonoId(Long donoId) {
        this.donoId = donoId;
    }
}