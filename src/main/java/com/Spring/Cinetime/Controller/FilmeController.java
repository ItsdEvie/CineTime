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
}