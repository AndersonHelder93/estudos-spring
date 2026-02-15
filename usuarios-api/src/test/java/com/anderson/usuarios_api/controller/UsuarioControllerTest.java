package com.anderson.usuarios_api.controller;

import com.anderson.usuarios_api.dto.UsuarioResponseDTO;
import com.anderson.usuarios_api.model.Usuario;
import com.anderson.usuarios_api.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

@ActiveProfiles("test")
@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    void deveListarUsuarioComSucesso() throws Exception {
        Usuario usuario = new Usuario(1L, "Anderson", 25);

        UsuarioResponseDTO response =
                new UsuarioResponseDTO("Anderson", 25);

        Mockito.when(usuarioService.listar())
                .thenReturn(List.of(usuario));

        Mockito.when(usuarioService.toResponse(usuario))
                .thenReturn(response);

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nome").value("Anderson"))
                .andExpect(jsonPath("$[0].idade").value(25));
    }
        @Test
        void deveRetornar404QuandoUsuarioNaoExistir() throws Exception {
            // arrange
            Mockito.when(usuarioService.buscarPorId(99L))
                    .thenThrow(new RuntimeException("Usuário não encontrado"));


            // act + assert
            mockMvc.perform(get("/usuarios/99"))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.mensagem")
                            .value("Usuário não encontrado"));
        }

    @Test
    void deveRetornar400QuandoRequestForInvalido() throws Exception {
        // JSON inválido (nome vazio, idade ausente)
        String json = """
            {
              "nome": "",
              "idade": null
            }
        """;

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                // Procura em qualquer lugar da lista ($..),
                // um campo 'mensagem' que contenha o texto esperado
                .andExpect(jsonPath("$..mensagem").value(hasItems(
                        "nome: Nome é obrigatório",
                        "idade: idade é obrigatória"
                )));
    }
}

