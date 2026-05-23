package com.Spring.Cinetime.Controller;

import com.Spring.Cinetime.Dto.SalaRequestDTO;
import com.Spring.Cinetime.Model.Sala;
import com.Spring.Cinetime.Service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
@CrossOrigin("*")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    // =========================
    // LISTAR TODAS AS SALAS
    // =========================
    @GetMapping
    public List<Sala> findAll() {

        return salaService.findAll();

    }

    // =========================
    // BUSCAR SALA POR ID
    // =========================
    @GetMapping("/{id}")
    public Sala findById(@PathVariable Long id) {

        return salaService.findById(id);

    }

    // =========================
    // CRIAR SALA
    // =========================
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sala createSala(
            @RequestBody SalaRequestDTO dto
    ) {

        return salaService.createSala(dto);

    }

    // =========================
    // ENTRAR NA SALA
    // =========================
    @PutMapping("/{id}/entrar")
    public Sala entrarSala(
            @PathVariable Long id
    ) {

        return salaService.entrarSala(id);

    }

    // =========================
    // SAIR DA SALA
    // =========================
    @PutMapping("/{id}/sair/{usuarioId}")
    public void sairSala(

            @PathVariable Long id,
            @PathVariable Long usuarioId

    ) {

        salaService.sairSala(id, usuarioId);

    }

    // =========================
    // DELETAR SALA
    // =========================
    @DeleteMapping("/{id}")
    public void deleteSala(
            @PathVariable Long id
    ) {

        salaService.deleteSala(id);

    }

}