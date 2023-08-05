package com.tkn.Gerenciamento.resource;

import com.tkn.Gerenciamento.model.Estado;
import com.tkn.Gerenciamento.repository.EstadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadosController {
    @Autowired
    private EstadosRepository repository;

    @GetMapping
    public List<Estado> getAll () {
        return repository.findAll();
    }

    @PostMapping
    public Estado create (@RequestBody Estado estado) {
        return repository.save(estado);
    }

    @PutMapping("/{id}")
    public Estado update (
            @PathVariable(value = "id") Long id,
            @RequestBody Estado estadoAtualizar) {
        var estado = repository.findById(id).orElse(null);

        if (estado == null) return null;

        estado.setNome(estadoAtualizar.getNome());
        estado.setSigla(estadoAtualizar.getSigla());
        estado.setPopulacao(estadoAtualizar.getPopulacao());
        estado.setPais(estadoAtualizar.getPais());

        return repository.save(estado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable(value = "id") Long id) {
        Estado estado = repository.findById(id).orElse(null);

        if (estado != null) {
            repository.deleteById(id);
        }

        return ResponseEntity.noContent().build();
    }
}
