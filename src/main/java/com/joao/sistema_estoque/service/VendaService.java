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
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ProdutoRepository produtoRepository;
    private final InventarioService inventarioService;
    private final InventarioRepository inventarioRepository;

    public List<Venda> listarTodos() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));
    }

    public List<Venda> listarPorCliente(Long clienteId) {
        return vendaRepository.findByCliente_Id(clienteId);
    }

    public List<Venda> listarPorFuncionario(Long funcionarioId) {
        return vendaRepository.findByFuncionario_Id(funcionarioId);
    }

    @Transactional
    public Venda realizarVenda(Long clienteId, Long funcionarioId, Long inventarioId,
                               List<Map<String, Object>> itens) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setData(LocalDate.now());
        venda = vendaRepository.save(venda);

        for (Map<String, Object> itemData : itens) {
            Long produtoId = Long.valueOf(itemData.get("produtoId").toString());
            Integer quantidade = Integer.valueOf(itemData.get("quantidade").toString());
            BigDecimal precoUnitario = new BigDecimal(itemData.get("precoUnitario").toString());

            Produto produto = produtoRepository.findById(produtoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + produtoId));

            // Baixa estoque automaticamente
            inventarioService.baixarEstoque(inventarioId, produtoId, quantidade);

            ItemVenda item = new ItemVenda();
            item.setVenda(venda);
            item.setProduto(produto);
            item.setQuantidade(quantidade);
            item.setPrecoUnitario(precoUnitario);
            venda.getItens().add(item);
        }

        return vendaRepository.save(venda);
    }

    public BigDecimal calcularTotal(Long vendaId) {
        return buscarPorId(vendaId).calcularTotal();
    }
}