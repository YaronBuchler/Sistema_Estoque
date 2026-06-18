package com.joao.sistema_estoque.repository;

import com.joao.sistema_estoque.model.ItemInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemInventarioRepository extends JpaRepository<ItemInventario, Long> {
    List<ItemInventario> findByInventario_Id(Long inventarioId);
    Optional<ItemInventario> findByInventario_IdAndProduto_Id(Long inventarioId, Long produtoId);
    List<ItemInventario> findByProduto_Id(Long produtoId);
}