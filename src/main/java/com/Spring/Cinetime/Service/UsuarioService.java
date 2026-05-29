package com.Spring.Cinetime.Service;

import com.Spring.Cinetime.Dto.LoginDTO;
import com.Spring.Cinetime.Dto.UsuarioRequestDTO;
import com.Spring.Cinetime.Dto.UsuarioUpdateDTO;
import com.Spring.Cinetime.Exception.UsuarioNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.Spring.Cinetime.Model.Usuario;
import com.Spring.Cinetime.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public Usuario createUser(UsuarioRequestDTO usuarioDTO) {

        if (usuarioRepository.findByEmail(usuarioDTO.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        String senhaCriptografada = passwordEncoder.encode(usuarioDTO.senha());

        var usuario = new Usuario(
                usuarioDTO.name(),
                usuarioDTO.date(),
                usuarioDTO.email(),
                senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, UsuarioUpdateDTO dto) {

        var usuario = findById(id);

        var usuarioComMesmoEmail = usuarioRepository.findByEmail(dto.email());

        if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(id)) {

            throw new RuntimeException("Email já cadastrado");
        }

        usuario.setName(dto.name());
        usuario.setEmail(dto.email());

        if (dto.date() != null) {
            usuario.setDataNascimento(dto.date());
        }

        if (dto.senha() != null && !dto.senha().isBlank()) {

            if (dto.confirmarSenha() == null || !dto.senha().equals(dto.confirmarSenha())) {
                throw new RuntimeException("As senhas não coincidem");
            }

            String senhaCriptografada = passwordEncoder.encode(dto.senha());

            usuario.setSenha(senhaCriptografada);
        }
        usuario.setBiografia(dto.biografia());
        usuario.setFotoPerfil(dto.fotoPerfil());
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        findById(id);
        usuarioRepository.deleteById(id);
    }

    public Usuario login(LoginDTO dto) {
        var usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario não encontrado"));
        if (!passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }
        return usuario;
    }

}
