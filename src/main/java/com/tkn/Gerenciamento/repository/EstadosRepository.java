package com.tkn.Gerenciamento.repository;

import com.tkn.Gerenciamento.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadosRepository extends JpaRepository<Estado, Long> {
}
