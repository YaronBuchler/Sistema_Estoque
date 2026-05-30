package com.joao.sistema_estoque.repository;

import com.joao.sistema_estoque.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByCliente_Id(Long clienteId);
    List<Venda> findByFuncionario_Id(Long funcionarioId);
}