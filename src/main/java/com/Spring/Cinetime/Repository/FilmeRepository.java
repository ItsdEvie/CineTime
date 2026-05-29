package com.Spring.Cinetime.Repository;

import com.Spring.Cinetime.Model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByStatusLista(String statusLista);

    Optional<Filme> findByIdExternoAndStatusLista(
            Long idExterno,
            String statusLista
    );

    List<Filme> findByUsuarioIdAndStatusLista(
            Long usuarioId,
            String statusLista
    );

    Optional<Filme> findByUsuarioIdAndIdExternoAndStatusLista(
            Long usuarioId,
            Long idExterno,
            String statusLista
    );
}