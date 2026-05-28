package com.Spring.Cinetime.Repository;

import com.Spring.Cinetime.Model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    // Busca os filmes filtrando pela coluna statusLista
    List<Filme> findByStatusLista(String statusLista);
}