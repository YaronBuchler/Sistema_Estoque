package com.joao.sistema_estoque.repository;

import com.joao.sistema_estoque.model.DonoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonoEstoqueRepository extends JpaRepository<DonoEstoque, Long> {
}