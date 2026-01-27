package com.anderson.usuarios_api.service;

import com.anderson.usuarios_api.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> listar(){
        return usuarios;
    }

    public Usuario salvar(Usuario usuario){
        usuarios.add(usuario);
        return usuario;
    }

    public UsuarioService(){
        usuarios.add(new Usuario("Admin", 30));
    }
}
