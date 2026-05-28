package com.Spring.Cinetime.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TmdbResultDTO {

    private Long id;
    private String title; // Nome - filmes
    private String name;  // Nome - séries
    private String overview; // Sinopse

    @JsonProperty("poster_path")
    private String caminhoPoster;

    @JsonProperty("release_date")
    private String dataLancamentoFilme;

    @JsonProperty("first_air_date")
    private String dataLancamentoSerie;

    @JsonProperty("runtime")
    private Integer duracaoMinutos;

    //getter e setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getOverview() { return overview; }
    public void setOverview(String overview) { this.overview = overview; }

    public String getCaminhoPoster() { return caminhoPoster; }
    public void setCaminhoPoster(String caminhoPoster) { this.caminhoPoster = caminhoPoster; }

    public String getDataLancamentoFilme() { return dataLancamentoFilme; }
    public void setDataLancamentoFilme(String dataLancamentoFilme) { this.dataLancamentoFilme = dataLancamentoFilme; }

    public String getDataLancamentoSerie() { return dataLancamentoSerie; }
    public void setDataLancamentoSerie(String dataLancamentoSerie) { this.dataLancamentoSerie = dataLancamentoSerie; }

    public Integer getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(Integer duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }
}