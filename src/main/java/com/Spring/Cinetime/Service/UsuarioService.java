package com.Spring.Cinetime.Service;

import com.Spring.Cinetime.Dto.LoginDTO;
import com.Spring.Cinetime.Dto.UsuarioRequestDTO;
import com.Spring.Cinetime.Exception.UsuarioNotFoundException;
import com.Spring.Cinetime.Model.Usuario;
import com.Spring.Cinetime.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService {

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
        var usuario = new Usuario(usuarioDTO.name(),
                usuarioDTO.date(),
                usuarioDTO.email(),
                usuarioDTO.senha(),
                usuarioDTO.confirmarSenha());
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, UsuarioRequestDTO dto) {
        var usuario = findById(id);

        usuario.setName(dto.name());
        usuario.setEmail(dto.email());
        usuario.setDataNascimento(dto.date());

        if(dto.senha() != null && !dto.senha().isBlank()) {

            usuario.setSenha(dto.senha());
            usuario.setConfirmarSenha(dto.confirmarSenha());

        }

        return usuarioRepository.save(usuario);
    }
    
    public void delete(Long id){
        findById(id);
        usuarioRepository.deleteById(id);
    }

    public Usuario login(LoginDTO dto){
        var usuario = usuarioRepository.findByEmail(dto.email()).orElseThrow(() -> new UsuarioNotFoundException("Usuario não encontrado"));
        if (!usuario.getSenha().equals(dto.senha())){
            throw new RuntimeException("Senha Invalida");
        }
        return usuario;
    }

}
