package com.joao.sistema_estoque.repository;

import com.joao.sistema_estoque.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
    List<ItemVenda> findByVenda_Id(Long vendaId);
    List<ItemVenda> findByProduto_Id(Long produtoId);
}