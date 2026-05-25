package com.Spring.Cinetime.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/salas")
    public String salas() {
        return "salas";
    }

    @GetMapping("/sala")
    public String sala() {
        return "sala";
    }
}