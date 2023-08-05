package com.tkn.Gerenciamento.resource;

import com.tkn.Gerenciamento.model.Usuario;
import com.tkn.Gerenciamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll () {
        var usuarios = usuarioRepository.findAll();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getById(@PathVariable(name = "id") Long id) {
        var usuario = usuarioRepository.findById(id);

        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario.get());
    }

    @PostMapping
    public ResponseEntity<Usuario> create (
            @RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);

        return ResponseEntity.status(201).body(usuario);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(
            @PathVariable(name = "id") Long id,
            @RequestBody Usuario usuarioAtualizar
    ) {
        var usuario = usuarioRepository.findById(id);

        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        var usuarioPresente = usuario.get();

        usuarioPresente.setLogin(usuarioAtualizar.getLogin());
        usuarioPresente.setSenha(usuarioAtualizar.getSenha());

        usuarioRepository.save(usuarioPresente);

        return ResponseEntity.ok(usuarioPresente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable(name = "id") Long id) {
        var usuario = usuarioRepository.findById(id);

        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
