package com.anderson.usuarios_api.controller;

import com.anderson.usuarios_api.dto.UsuarioRequestDTO;
import com.anderson.usuarios_api.dto.UsuarioResponseDTO;
import com.anderson.usuarios_api.dto.UsuarioUpdateDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id){
        Usuario usuario = service.buscarPorId(id);
        return ResponseEntity.ok(service.toResponse(usuario));
    }


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvar(@RequestBody  @Valid UsuarioRequestDTO dto){

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setIdade(dto.getIdade());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());

        Usuario salvo = service.salvar(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.toResponse(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioUpdateDTO dto
    ) {
        Usuario usuarioAtualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(service.toResponse(usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
