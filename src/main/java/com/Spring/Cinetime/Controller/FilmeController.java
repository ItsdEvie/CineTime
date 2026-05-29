package com.Spring.Cinetime.Controller;

import com.Spring.Cinetime.Model.Filme;
import com.Spring.Cinetime.Service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
@CrossOrigin(origins = "*")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/pesquisar")
    public ResponseEntity<List<Filme>> pesquisarFilmes(@RequestParam String nome) {
        List<Filme> filmes = filmeService.pesquisarFilmesExternos(nome);
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/detalhes")
    public ResponseEntity<Filme> obterDetalhes(@RequestParam Long id, @RequestParam String tipo) {
        Filme filme = filmeService.obterDetalhesExternos(id, tipo);
        return ResponseEntity.ok(filme);
    }

    @PostMapping("/salvar/{usuarioId}")
    public ResponseEntity<?> salvarFilme(
            @PathVariable Long usuarioId,
            @RequestBody Filme filme
    ) {
        filmeService.salvarFilme(usuarioId, filme);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/minha-lista/{usuarioId}")
    public ResponseEntity<List<Filme>> obterMinhaLista(
            @PathVariable Long usuarioId
    ) {
        List<Filme> filmes = filmeService.listarMinhaLista(usuarioId);
        return ResponseEntity.ok(filmes);
    }
}