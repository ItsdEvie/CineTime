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

    public List<Filme> pesquisarFilmesExternos(String nomeFilme) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + "/search/multi?api_key=" + API_KEY + "&query=" + nomeFilme + "&language=pt-BR";
        try {
            TmdbResponseDTO response = restTemplate.getForObject(url, TmdbResponseDTO.class);
            List<Filme> filmesMapeados = new ArrayList<>();
            if (response != null && response.getResults() != null) {
                for (TmdbResultDTO dto : response.getResults()) {
                    if (dto.getTitle() == null && dto.getName() == null) continue;
                    Filme filme = new Filme();
                    filme.setIdExterno(dto.getId());
                    if (dto.getTitle() != null) {
                        filme.setTitulo(dto.getTitle());
                        filme.setGenero("Filme");
                    } else {
                        filme.setTitulo(dto.getName());
                        filme.setGenero("Série");
                    }
                    filme.setImagemUrl("https://image.tmdb.org/t/p/w500" + dto.getCaminhoPoster());
                    filmesMapeados.add(filme);
                }
            }
            return filmesMapeados;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public Filme obterDetalhesExternos(Long id, String tipo) {
        RestTemplate restTemplate = new RestTemplate();
        // Definimos o endpoint aqui: /tv/ para séries e /movie/ para filmes
        String endpoint = "Série".equalsIgnoreCase(tipo) ? "/tv/" : "/movie/";
        String url = BASE_URL + endpoint + id + "?api_key=" + API_KEY + "&language=pt-BR";

        try {
            TmdbResultDTO dto = restTemplate.getForObject(url, TmdbResultDTO.class);
            Filme filme = new Filme();
            if (dto != null) {
                filme.setIdExterno(dto.getId());
                filme.setTitulo(dto.getTitle() != null ? dto.getTitle() : dto.getName());
                filme.setSinopse(dto.getOverview());
                filme.setImagemUrl("https://image.tmdb.org/t/p/w500" + dto.getCaminhoPoster());
                filme.setGenero(tipo);

                String data;
                if ("/tv/".equals(endpoint)) {
                    data = dto.getDataLancamentoSerie(); // Pega o first_air_date (Séries)
                } else {
                    data = dto.getDataLancamentoFilme(); // Pega o release_date (Filmes)
                }

                if (data != null && data.length() >= 4) {
                    filme.setAnoLancamento(data.substring(0, 4));
                } else {
                    filme.setAnoLancamento("N/A");
                }

                // Trata a duração / temporadas
                if ("/tv/".equals(endpoint)) {
                    if (dto.getNumeroTemporadas() != null) {
                        filme.setDuracao(dto.getNumeroTemporadas() + " Temporada" + (dto.getNumeroTemporadas() > 1 ? "s" : ""));
                    } else {
                        filme.setDuracao("Temporadas indisponíveis");
                    }
                } else {
                    if (dto.getDuracaoMinutos() != null && dto.getDuracaoMinutos() > 0) {
                        filme.setDuracao(dto.getDuracaoMinutos() + " min");
                    } else {
                        filme.setDuracao("Duração indisponível");
                    }
                }
            }
            return filme;
        } catch (Exception e) {
            System.err.println("Erro ao obter detalhes do TMDB: " + e.getMessage());
            return null;
        }
    }

    public Filme salvarFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    public List<Filme> listarMinhaLista() {
        return filmeRepository.findByStatusLista("QUERO_ASSISTIR");
    }
}