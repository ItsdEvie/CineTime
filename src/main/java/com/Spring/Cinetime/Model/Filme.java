package com.Spring.Cinetime.Model;

import jakarta.persistence.*;

    @Entity
    @Table(name = "filmes")
    public class Filme {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String titulo;

        private String genero;
        private String imagemUrl; // Para guardar o nome/caminho da thumb do filme (ex: "matrix")

        public Filme() {
        }

        public Filme(String titulo, String genero, String imagemUrl) {
            this.titulo = titulo;
            this.genero = genero;
            this.imagemUrl = imagemUrl;
        }

        // Getters e Setters
        public Long getId() { return id; }
        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) { this.titulo = titulo; }
        public String getGenero() { return genero; }
        public void setGenero(String genero) { this.genero = genero; }
        public String getImagemUrl() { return imagemUrl; }
        public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
    }
