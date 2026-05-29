package com.Spring.Cinetime.Service;

import com.Spring.Cinetime.Dto.TmdbResponseDTO;
import com.Spring.Cinetime.Dto.TmdbResultDTO;
import com.Spring.Cinetime.Model.Filme;
import com.Spring.Cinetime.Model.Usuario;
import com.Spring.Cinetime.Repository.FilmeRepository;
import com.Spring.Cinetime.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmeService {

    @Value("${tmdb.api.key}")
    private String API_KEY;

    @Value("${tmdb.base.url}")
    private String BASE_URL;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Filme> pesquisarFilmesExternos(String nomeFilme) {
        RestTemplate restTemplate = new RestTemplate();

        String url = BASE_URL
                + "/search/multi?api_key="
                + API_KEY
                + "&query="
                + nomeFilme
                + "&language=pt-BR";

        try {
            TmdbResponseDTO response = restTemplate.getForObject(url, TmdbResponseDTO.class);

            List<Filme> filmesMapeados = new ArrayList<>();

            if (response != null && response.getResults() != null) {

                for (TmdbResultDTO dto : response.getResults()) {

                    if (dto.getTitle() == null && dto.getName() == null) {
                        continue;
                    }

                    Filme filme = new Filme();

                    filme.setIdExterno(dto.getId());

                    if (dto.getTitle() != null) {
                        filme.setTitulo(dto.getTitle());
                        filme.setGenero("Filme");
                    } else {
                        filme.setTitulo(dto.getName());
                        filme.setGenero("Série");
                    }

                    if (dto.getCaminhoPoster() != null) {
                        filme.setImagemUrl(
                                "https://image.tmdb.org/t/p/w500"
                                        + dto.getCaminhoPoster());
                    }

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

        String endpoint = "Série".equalsIgnoreCase(tipo) ? "/tv/" : "/movie/";

        String url = BASE_URL
                + endpoint
                + id
                + "?api_key="
                + API_KEY
                + "&language=pt-BR";

        try {
            TmdbResultDTO dto = restTemplate.getForObject(url, TmdbResultDTO.class);

            Filme filme = new Filme();

            if (dto != null) {

                filme.setIdExterno(dto.getId());

                filme.setTitulo(
                        dto.getTitle() != null
                                ? dto.getTitle()
                                : dto.getName());

                filme.setSinopse(dto.getOverview());

                if (dto.getCaminhoPoster() != null) {
                    filme.setImagemUrl(
                            "https://image.tmdb.org/t/p/w500"
                                    + dto.getCaminhoPoster());
                }

                filme.setGenero(tipo);

                String data;

                if ("/tv/".equals(endpoint)) {
                    data = dto.getDataLancamentoSerie();
                } else {
                    data = dto.getDataLancamentoFilme();
                }

                if (data != null && data.length() >= 4) {
                    filme.setAnoLancamento(data.substring(0, 4));
                } else {
                    filme.setAnoLancamento("N/A");
                }

                if ("/tv/".equals(endpoint)) {

                    if (dto.getNumeroTemporadas() != null) {
                        filme.setDuracao(
                                dto.getNumeroTemporadas()
                                        + " Temporada"
                                        + (dto.getNumeroTemporadas() > 1 ? "s" : ""));
                    } else {
                        filme.setDuracao("Temporadas indisponíveis");
                    }

                } else {

                    if (dto.getDuracaoMinutos() != null
                            && dto.getDuracaoMinutos() > 0) {

                        filme.setDuracao(dto.getDuracaoMinutos() + " min");

                    } else {
                        filme.setDuracao("Duração indisponível");
                    }
                }
            }

            return filme;

        } catch (Exception e) {
            System.err.println(
                    "Erro ao obter detalhes do TMDB: " + e.getMessage());

            return null;
        }
    }

    public Filme salvarFilme(Long usuarioId, Filme filme) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var filmeExistente = filmeRepository
                .findByUsuarioIdAndIdExternoAndStatusLista(
                        usuarioId,
                        filme.getIdExterno(),
                        filme.getStatusLista());

        if (filmeExistente.isPresent()) {
            throw new RuntimeException("Filme já está nessa lista");
        }

        filme.setUsuario(usuario);

        return filmeRepository.save(filme);
    }

    public List<Filme> listarMinhaLista(Long usuarioId) {

        return filmeRepository.findByUsuarioIdAndStatusLista(
                usuarioId,
                "QUERO_ASSISTIR");
    }
}