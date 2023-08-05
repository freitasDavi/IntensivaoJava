package com.tkn.Gerenciamento.resource;

import com.tkn.Gerenciamento.model.Pais;
import com.tkn.Gerenciamento.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paises")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping
    public List<Pais> getAll () {
        return paisRepository.findAll();
    }

    @PostMapping
    public Pais create (@RequestBody Pais pais) {
        return paisRepository.save(pais);
    }

    @PutMapping("/{id}")
    public Pais update (
            @PathVariable(value = "id") Long id,
            @RequestBody Pais paisAtualizar) {
        var pais = paisRepository.findById(id).orElse(null);

        if (pais == null) return null;

        pais.setNome(paisAtualizar.getNome());
        pais.setSigla(paisAtualizar.getSigla());
        pais.setPopulacao(paisAtualizar.getPopulacao());

        return paisRepository.save(pais);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable(value = "id") Long id) {
        Pais paisFind = paisRepository.findById(id).orElse(null);

        if (paisFind != null) {
            paisRepository.deleteById(id);
        }

        return ResponseEntity.noContent().build();
    }

}
