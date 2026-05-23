package com.Spring.Cinetime.Service;

import com.Spring.Cinetime.Dto.SalaRequestDTO;
import com.Spring.Cinetime.Exception.SalaNotFoundException;
import com.Spring.Cinetime.Model.Sala;
import com.Spring.Cinetime.Repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    // =========================
    // LISTAR TODAS AS SALAS
    // =========================
    public List<Sala> findAll() {

        return salaRepository.findAll();

    }

    // =========================
    // BUSCAR SALA POR ID
    // =========================
    public Sala findById(Long id) {

        return salaRepository.findById(id)
                .orElseThrow(() ->
                        new SalaNotFoundException(id));

    }

    // =========================
    // CRIAR SALA
    // =========================
    public Sala createSala(SalaRequestDTO dto) {

        Sala sala = new Sala(

                dto.nome(),
                dto.idioma(),
                dto.visivel(),

                // senha só existe se privada
                dto.visivel() ? null : dto.senha(),

                dto.participantesMax(),

                // começa com 1 participante
                1,

                dto.donoId()

        );

        return salaRepository.save(sala);

    }

    // =========================
    // ENTRAR NA SALA
    // =========================
    public Sala entrarSala(Long salaId) {

        Sala sala = findById(salaId);

        // verifica limite
        if (sala.getParticipantesAtual()
                >= sala.getParticipantesMax()) {

            throw new RuntimeException(
                    "Sala cheia");

        }

        sala.setParticipantesAtual(
                sala.getParticipantesAtual() + 1
        );

        return salaRepository.save(sala);

    }

    // =========================
    // SAIR DA SALA
    // =========================
    public void sairSala(Long salaId, Long usuarioId) {

        Sala sala = findById(salaId);

        // remove participante
        sala.setParticipantesAtual(
                sala.getParticipantesAtual() - 1
        );

        // =========================
        // SALA PRIVADA
        // =========================
        if (!sala.getVisivel()) {

            // dono saiu
            if (sala.getDonoId()
                    .equals(usuarioId)) {

                salaRepository.delete(sala);

                return;

            }

        }

        // =========================
        // SALA PÚBLICA
        // =========================
        if (sala.getParticipantesAtual() <= 0) {

            salaRepository.delete(sala);

            return;

        }

        salaRepository.save(sala);

    }

    // =========================
    // DELETAR SALA
    // =========================
    public void deleteSala(Long id) {

        findById(id);

        salaRepository.deleteById(id);

    }

}