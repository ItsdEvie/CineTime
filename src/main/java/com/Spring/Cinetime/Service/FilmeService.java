package com.Spring.Cinetime.Service;

import com.Spring.Cinetime.Dto.TmdbResponseDTO;
import com.Spring.Cinetime.Dto.TmdbResultDTO;
import com.Spring.Cinetime.Model.Filme;
import com.Spring.Cinetime.Repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    private final String API_KEY = "7b72e720c4cd9979546e42ea35cb1c2f";
    private final String BASE_URL = "https://api.themoviedb.org/3";

    // 1. SEU MÉTODO DE PESQUISA ATUALIZADO (Guarda o ID externo e usa variáveis em PT)
    public List<Filme> pesquisarFilmesExternos(String nomeFilme) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + "/search/multi?api_key=" + API_KEY + "&query=" + nomeFilme + "&language=pt-BR";

        try {
            TmdbResponseDTO response = restTemplate.getForObject(url, TmdbResponseDTO.class);
            List<Filme> filmesMapeados = new ArrayList<>();

            if (response != null && response.getResults() != null) {
                for (TmdbResultDTO dto : response.getResults()) {

                    // Ignora pessoas (atores) que o multi-search também traz
                    if (dto.getTitle() == null && dto.getName() == null) {
                        continue;
                    }

                    Filme filme = new Filme();

                    // IMPORTANTE: Guarda o ID do TMDB na nossa nova coluna de ID externo
                    filme.setIdExterno(dto.getId());

                    // Se tiver 'title' é filme, se não, é série (usa 'name')
                    if (dto.getTitle() != null) {
                        filme.setTitulo(dto.getTitle());
                        filme.setGenero("Filme");
                    } else {
                        filme.setTitulo(dto.getName());
                        filme.setGenero("Série");
                    }

                    // Usa o getter em português do DTO
                    filme.setImagemUrl("https://image.tmdb.org/t/p/w500" + dto.getCaminhoPoster());
                    filmesMapeados.add(filme);
                }
            }
            return filmesMapeados;
        } catch (Exception e) {
            System.err.println("Erro ao consultar API externa Multi: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 2. NOVO MÉTODO: BUSCA OS DETALHES COMPLETOS DE UM SÓ TÍTULO PELO ID
    public Filme obterDetalhesExternos(Long id, String tipo) {
        RestTemplate restTemplate = new RestTemplate();

        // Define se bate na caixinha de filme (/movie/) ou de série (/tv/) do TMDB
        String endpoint = "Série".equalsIgnoreCase(tipo) ? "/tv/" : "/movie/";
        String url = BASE_URL + endpoint + id + "?api_key=" + API_KEY + "&language=pt-BR";

        try {
            // Faz o GET trazendo apenas o filme/série específico
            TmdbResultDTO dto = restTemplate.getForObject(url, TmdbResultDTO.class);
            Filme filme = new Filme();

            if (dto != null) {
                filme.setIdExterno(dto.getId());

                String titulo = dto.getTitle() != null ? dto.getTitle() : dto.getName();
                filme.setTitulo(titulo);
                filme.setSinopse(dto.getOverview());
                filme.setImagemUrl("https://image.tmdb.org/t/p/w500" + dto.getCaminhoPoster());
                filme.setGenero(tipo);

                // Trata o ano de lançamento pegando apenas os 4 primeiros dígitos (Ex: 2008-11-21 vira 2008)
                String data = dto.getTitle() != null ? dto.getDataLancamentoFilme() : dto.getDataLancamentoSerie();
                if (data != null && data.length() >= 4) {
                    filme.setAnoLancamento(data.substring(0, 4));
                } else {
                    filme.setAnoLancamento("N/A");
                }

                // Trata a duração em minutos que vem no DTO
                if (dto.getDuracaoMinutos() != null && dto.getDuracaoMinutos() > 0) {
                    filme.setDuracao(dto.getDuracaoMinutos() + " min");
                } else {
                    filme.setDuracao("Duração indisponível");
                }
            }
            return filme;

        } catch (Exception e) {
            System.err.println("Erro ao buscar detalhes no TMDB: " + e.getMessage());
            return null;
        }
    }
}