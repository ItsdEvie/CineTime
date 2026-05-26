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

    private final String API_KEY = "SUA_API_KEY_AQUI";
    private final String BASE_URL = "https://api.themoviedb.org/3";

    // Método para pesquisar filmes na API externa pelo nome
    public List<Filme> pesquisarFilmesExternos(String nomeFilme) {
        RestTemplate restTemplate = new RestTemplate();

        // Monta a URL de busca do TMDB traduzida para português
        String url = BASE_URL + "/search/movie?api_key=" + API_KEY + "&query=" + nomeFilme + "&language=pt-BR";

        try {
            // Faz a chamada GET para a API externa
            TmdbResponseDTO response = restTemplate.getForObject(url, TmdbResponseDTO.class);

            List<Filme> filmesMapeados = new ArrayList<>();

            if (response != null && response.getResults() != null) {
                // Mapeia os dados do DTO da API para a sua Entidade "Filme" do banco
                for (TmdbResultDTO dto : response.getResults()) {
                    Filme filme = new Filme();
                    filme.setTitulo(dto.getTitle());
                    // Concatenamos a URL base de imagens do TMDB com o caminho retornado
                    filme.setImagemUrl("https://image.tmdb.org/t/p/w500" + dto.getPosterPath());
                    filme.setGenero("Cinema"); // O TMDB envia IDs de gênero, para simplificar deixamos um padrão

                    filmesMapeados.add(filme);
                }
            }

            return filmesMapeados;

        } catch (Exception e) {
            System.err.println("Erro ao consultar API externa: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}