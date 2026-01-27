package com.anderson.usuarios_api.controller;

import com.anderson.usuarios_api.dto.UsuarioRequestDTO;
import com.anderson.usuarios_api.dto.UsuarioResponseDTO;
import com.anderson.usuarios_api.model.Usuario;
import com.anderson.usuarios_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {

        List<UsuarioResponseDTO> resposta = service.listar()
                .stream()
                .map(service::toResponse)
                .toList();

        return ResponseEntity.ok(resposta);
    }


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvar(@RequestBody  @Valid UsuarioRequestDTO dto){

        Usuario usuario = new Usuario(dto.getNome(), dto.getIdade());
        Usuario salvo = service.salvar(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.toResponse(salvo));
    }



}
