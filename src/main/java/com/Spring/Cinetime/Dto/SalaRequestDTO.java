package com.Spring.Cinetime.Dto;

public record SalaRequestDTO(

        String nome,

        String idioma,

        Boolean visivel,

        String senha,

        Integer participantesMax,

        Long donoId

) {
}