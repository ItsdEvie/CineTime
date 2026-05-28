package com.Spring.Cinetime.Controller;

import com.Spring.Cinetime.Model.Filme;
import com.Spring.Cinetime.Service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@CrossOrigin(origins = "*") // Permite que seu HTML acesse a API sem erros de CORS
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    // Rota para buscar filmes: GET http://localhost:8080/api/filmes/pesquisar?nome=Matrix
    @GetMapping("/pesquisar")
    public ResponseEntity<List<Filme>> pesquisarFilmes(@RequestParam String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<Filme> filmesEncontrados = filmeService.pesquisarFilmesExternos(nome);
        return ResponseEntity.ok(filmesEncontrados);
    }
    // Rota para buscar os detalhes completos de um filme/série específico pelo ID do TMDB
    // GET http://localhost:8080/api/filmes/detalhes/123?tipo=Filme
    @GetMapping("/detalhes/{id}")
    public ResponseEntity<Filme> obterDetalhes(@PathVariable Long id, @RequestParam String tipo) {
        Filme filme = filmeService.obterDetalhesExternos(id, tipo);
        if (filme == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(filme);
    }
}