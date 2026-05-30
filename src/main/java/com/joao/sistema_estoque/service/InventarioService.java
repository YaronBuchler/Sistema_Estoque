package com.joao.sistema_estoque.service;

import com.joao.sistema_estoque.model.Inventario;
import com.joao.sistema_estoque.model.ItemInventario;
import com.joao.sistema_estoque.model.Produto;
import com.joao.sistema_estoque.repository.InventarioRepository;
import com.joao.sistema_estoque.repository.ItemInventarioRepository;
import com.joao.sistema_estoque.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.joao.sistema_estoque.exception.ResourceNotFoundException;
import com.joao.sistema_estoque.exception.BusinessException;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final ItemInventarioRepository itemInventarioRepository;
    private final ProdutoRepository produtoRepository;

    public List<Inventario> listarTodos() {
        return inventarioRepository.findAll();
    }

    public Inventario buscarPorId(Long id) {
        return inventarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventário não encontrado"));
    }

    public Inventario criar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public List<ItemInventario> listarItens(Long inventarioId) {
        buscarPorId(inventarioId);
        return itemInventarioRepository.findByInventario_Id(inventarioId);
    }

    public ItemInventario adicionarItem(Long inventarioId, Long produtoId, Integer quantidade, java.math.BigDecimal precoCompra) {
        Inventario inventario = buscarPorId(inventarioId);

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        // Se o produto já existe no inventário, soma a quantidade
        return itemInventarioRepository
                .findByInventario_IdAndProduto_Id(inventarioId, produtoId)
                .map(item -> {
                    item.setQuantidade(item.getQuantidade() + quantidade);
                    item.setPrecoCompra(precoCompra);
                    return itemInventarioRepository.save(item);
                })
                .orElseGet(() -> {
                    ItemInventario novoItem = new ItemInventario();
                    novoItem.setInventario(inventario);
                    novoItem.setProduto(produto);
                    novoItem.setQuantidade(quantidade);
                    novoItem.setPrecoCompra(precoCompra);
                    return itemInventarioRepository.save(novoItem);
                });
    }

    public ItemInventario atualizarQuantidade(Long itemId, Integer novaQuantidade) {
        ItemInventario item = itemInventarioRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado no inventário"));
        item.setQuantidade(novaQuantidade);
        return itemInventarioRepository.save(item);
    }

    public void removerItem(Long itemId) {
        if (!itemInventarioRepository.existsById(itemId)) {
            throw new ResourceNotFoundException("Item não encontrado no inventário");
        }
        itemInventarioRepository.deleteById(itemId);
    }

    public void baixarEstoque(Long inventarioId, Long produtoId, Integer quantidade) {
        ItemInventario item = itemInventarioRepository
                .findByInventario_IdAndProduto_Id(inventarioId, produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado no inventário"));

        if (item.getQuantidade() < quantidade) {
            throw new BusinessException("Estoque insuficiente. Disponível: " + item.getQuantidade());
        }

        item.setQuantidade(item.getQuantidade() - quantidade);
        itemInventarioRepository.save(item);
    }
}