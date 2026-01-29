package com.anderson.usuarios_api.service;

import com.anderson.usuarios_api.dto.UsuarioRequestDTO;
import com.anderson.usuarios_api.dto.UsuarioResponseDTO;
import com.anderson.usuarios_api.dto.UsuarioUpdateDTO;
import com.anderson.usuarios_api.exception.RecursoNaoEncontradoException;
import com.anderson.usuarios_api.model.Usuario;
import com.anderson.usuarios_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Usuario salvar(Usuario usuario){
        usuarioRepository.save(usuario);
        return usuario;
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
