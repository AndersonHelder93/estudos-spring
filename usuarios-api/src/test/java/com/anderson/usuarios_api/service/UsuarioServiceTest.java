package com.anderson.usuarios_api.service;

import com.anderson.usuarios_api.model.Usuario;
import com.anderson.usuarios_api.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveListarUsuariosComSucesso() {

        Usuario usuario = new Usuario(1L, "Anderson", 30,
                "anderson@email.com", "123456", "ROLE_USER");
        Mockito.when(usuarioRepository.findAll())
                .thenReturn(List.of(usuario));


        List<Usuario> resultado = usuarioService.listar();


        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Anderson", resultado.get(0).getNome());
        assertEquals(30, resultado.get(0).getIdade());
    }

    @Test
    void quandoUsuarioNaoExistir(){

        Long id = 99L;
        /*Mockito.when(usuarioRepository.findById(id))
                .thenReturn(Optional.empty());*/

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                ()-> usuarioService.buscarPorId(id)
        );

        assertEquals("Usuário não encontrado", exception.getMessage());
    }


}
