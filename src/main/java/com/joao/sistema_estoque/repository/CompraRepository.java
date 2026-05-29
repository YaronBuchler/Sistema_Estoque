package com.joao.sistema_estoque.repository;

import com.joao.sistema_estoque.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByFornecedor_Id(Long fornecedorId);
    List<Compra> findByDono_Id(Long donoId);
}