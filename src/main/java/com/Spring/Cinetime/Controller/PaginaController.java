package com.Spring.Cinetime.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaginaController {

    // =========================
    // INDEX
    // =========================
    @GetMapping("/")
    public String index() {

        return "forward:/index.html";

    }

    // =========================
    // HOME
    // =========================
    @GetMapping("/home")
    public String home() {

        return "forward:/home.html";

    }

    // =========================
    // LOGIN
    // =========================
    @GetMapping("/login")
    public String login() {

        return "forward:/login.html";

    }

    // =========================
    // LISTA DE SALAS
    // =========================
    @GetMapping("/salas")
    public String salas() {

        return "forward:/salas.html";

    }

    // =========================
    // SALA SEM ID
    // =========================
    @GetMapping("/sala")
    public String sala() {

        return "forward:/sala.html";

    }

    // =========================
    // SALA COM ID
    // =========================
    @GetMapping("/sala/{id}")
    public String salaId(@PathVariable Long id) {

        System.out.println("Sala ID: " + id);

        return "forward:/sala.html";

    }

}