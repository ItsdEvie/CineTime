package com.Spring.Cinetime.Controller;

import com.Spring.Cinetime.Dto.LoginDTO;
import com.Spring.Cinetime.Dto.UsuarioRequestDTO;
import com.Spring.Cinetime.Model.Usuario;
import com.Spring.Cinetime.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Usuarios")
@CrossOrigin("*")

public class UsuarioController {

    private final UsuarioService service;

        public UsuarioController(UsuarioService service) {
            this.service = service;
        }

        @GetMapping
        public ResponseEntity<List<Usuario>> listAll()   {
            return ResponseEntity.ok(service.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Usuario> findById(@PathVariable Long id) {
            return ResponseEntity.ok(service.findById(id));
        }

        @PostMapping("/login")
        public ResponseEntity<Usuario> Logar(@RequestBody @Valid LoginDTO dto){
            return ResponseEntity.ok(service.login(dto));
        }


        @PostMapping("/cadastro")
        public ResponseEntity<Usuario> create(@Valid @RequestBody UsuarioRequestDTO dto) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.createUser(dto));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Usuario> update(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO dto) {

            return ResponseEntity.ok(service.update(id, dto));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }


    }

