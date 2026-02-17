package com.anderson.usuarios_api.service;

import com.anderson.usuarios_api.dto.UsuarioRequestDTO;
import com.anderson.usuarios_api.dto.UsuarioResponseDTO;
import com.anderson.usuarios_api.dto.UsuarioUpdateDTO;
import com.anderson.usuarios_api.exception.RecursoNaoEncontradoException;
import com.anderson.usuarios_api.model.Usuario;
import com.anderson.usuarios_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario){
        if (usuario.getSenha() == null){
            throw new RuntimeException("A senha não foi enviada corretamente!");
        }
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
            throw new RuntimeException("Email já cadastrado");
        }

        usuario.setSenha(
                passwordEncoder.encode(usuario.getSenha())
        );

        usuario.setRole("ROLE_USER");

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Usuário não encontrado")
                );
    }

    public Usuario atualizar(Long id, UsuarioUpdateDTO dto){
        Usuario usuario = buscarPorId(id);

        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());

        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id){
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

    public UsuarioResponseDTO toResponse(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getNome(),
                usuario.getIdade()
        );
    }
}
