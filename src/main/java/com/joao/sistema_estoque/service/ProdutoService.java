package com.joao.sistema_estoque.service;

import com.joao.sistema_estoque.model.Produto;
import com.joao.sistema_estoque.repository.ProdutoRepository;
import com.joao.sistema_estoque.repository.ItemInventarioRepository;
import com.joao.sistema_estoque.repository.ItemVendaRepository;
import com.joao.sistema_estoque.repository.ItemCompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.joao.sistema_estoque.exception.ResourceNotFoundException;
import com.joao.sistema_estoque.exception.BusinessException;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ItemInventarioRepository itemInventarioRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final ItemCompraRepository itemCompraRepository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    public List<Produto> listarPorCategoria(Long categoriaId) {
        return repository.findByCategoria_Id(categoriaId);
    }

    public List<Produto> listarPorFornecedor(Long fornecedorId) {
        return repository.findByFornecedor_Id(fornecedorId);
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produto = buscarPorId(id);
        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setCategoria(produtoAtualizado.getCategoria());
        produto.setFornecedor(produtoAtualizado.getFornecedor());
        return repository.save(produto);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        if (!itemInventarioRepository.findByProduto_Id(id).isEmpty()) {
            throw new BusinessException("Produto está vinculado a um ou mais inventários e não pode ser excluído.");
        }
        if (!itemVendaRepository.findByProduto_Id(id).isEmpty()) {
            throw new BusinessException("Produto está vinculado a uma ou mais vendas e não pode ser excluído.");
        }
        if (!itemCompraRepository.findByProduto_Id(id).isEmpty()) {
            throw new BusinessException("Produto está vinculado a uma ou mais compras e não pode ser excluído.");
        }
        repository.deleteById(id);
    }
}