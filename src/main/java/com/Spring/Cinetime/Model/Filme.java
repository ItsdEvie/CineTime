package com.Spring.Cinetime.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "filmes")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String genero;
    private String imagemUrl; // guardar o nome/caminho da thumb do filme

    @Column(length = 1000) // para sinopses longas não quebrarem o db
    private String sinopse;
    private String anoLancamento;
    private String duracao;
    private Integer numeroTemporadas;
    private String statusLista;
    private Long idExterno;

    public Filme() {
    }

    public Filme(String titulo, String genero, String imagemUrl) {
        this.titulo = titulo;
        this.genero = genero;
        this.imagemUrl = imagemUrl;
    }

    // Getters e Setters
    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }

    public String getSinopse() { return sinopse; }
    public void setSinopse(String sinopse) { this.sinopse = sinopse; }

    public String getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(String anoLancamento) { this.anoLancamento = anoLancamento; }

    public String getDuracao() { return duracao; }
    public void setDuracao(String duracao) { this.duracao = duracao; }

    public Integer getNumeroTemporadas() { return numeroTemporadas; }
    public void setNumeroTemporadas(Integer numeroTemporadas) { this.numeroTemporadas = numeroTemporadas; }

    public String getStatusLista() { return statusLista;}
    public void setStatusLista(String statusLista) { this.statusLista = statusLista; }

    public Long getIdExterno() { return idExterno; }
    public void setIdExterno(Long idExterno) { this.idExterno = idExterno; }

}
