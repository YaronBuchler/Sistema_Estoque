package com.joao.sistema_estoque.service;

import com.joao.sistema_estoque.model.*;
import com.joao.sistema_estoque.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import com.joao.sistema_estoque.exception.ResourceNotFoundException;


@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final FornecedorRepository fornecedorRepository;
    private final DonoEstoqueRepository donoRepository;
    private final ProdutoRepository produtoRepository;
    private final InventarioRepository inventarioRepository;
    private final InventarioService inventarioService;

    public List<Compra> listarTodos() {
        return compraRepository.findAll();
    }

    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compra não encontrada"));
    }

    public List<Compra> listarPorFornecedor(Long fornecedorId) {
        return compraRepository.findByFornecedor_Id(fornecedorId);
    }

    @Transactional
    public Compra realizarCompra(Long fornecedorId, Long donoId, Long inventarioId,
                                 List<Map<String, Object>> itens) {

        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));

        DonoEstoque dono = donoRepository.findById(donoId)
                .orElseThrow(() -> new ResourceNotFoundException("Dono não encontrado"));

        Compra compra = new Compra();
        compra.setFornecedor(fornecedor);
        compra.setDono(dono);
        compra.setData(LocalDate.now());
        compra = compraRepository.save(compra);

        for (Map<String, Object> itemData : itens) {
            Long produtoId = Long.valueOf(itemData.get("produtoId").toString());
            Integer quantidade = Integer.valueOf(itemData.get("quantidade").toString());
            BigDecimal precoUnitario = new BigDecimal(itemData.get("precoUnitario").toString());

            Produto produto = produtoRepository.findById(produtoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + produtoId));

            ItemCompra item = new ItemCompra();
            item.setCompra(compra);
            item.setProduto(produto);
            item.setQuantidade(quantidade);
            item.setPrecoUnitario(precoUnitario);
            compra.getItens().add(item);

            // Dar entrada automática no inventário
            inventarioService.adicionarItem(inventarioId, produtoId, quantidade, precoUnitario);
        }

        return compraRepository.save(compra);
    }

    @Transactional(readOnly = true)
    public BigDecimal calcularTotal(Long compraId) {
        return buscarPorId(compraId).calcularTotal();
    }
}