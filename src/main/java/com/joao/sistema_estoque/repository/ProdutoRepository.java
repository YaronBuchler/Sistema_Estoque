package com.joao.sistema_estoque.repository;

import com.joao.sistema_estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCategoria_Id(Long categoriaId);
    List<Produto> findByFornecedor_Id(Long fornecedorId);
}