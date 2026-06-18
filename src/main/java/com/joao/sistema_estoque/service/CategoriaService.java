package com.joao.sistema_estoque.service;

import com.joao.sistema_estoque.model.Categoria;
import com.joao.sistema_estoque.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.joao.sistema_estoque.exception.ResourceNotFoundException;
import com.joao.sistema_estoque.exception.BusinessException;

import com.joao.sistema_estoque.repository.ProdutoRepository;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;
    private final ProdutoRepository produtoRepository;

    public List<Categoria> listarTodos() {
        return repository.findAll();
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
    }

    public Categoria salvar(Categoria categoria) {
        if (repository.existsByNome(categoria.getNome())) {
            throw new BusinessException("Categoria já existe");
        }
        return repository.save(categoria);
    }

    public Categoria atualizar(Long id, Categoria categoriaAtualizada) {
        Categoria categoria = buscarPorId(id);
        categoria.setNome(categoriaAtualizada.getNome());
        categoria.setDescricao(categoriaAtualizada.getDescricao());
        return repository.save(categoria);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        if (!produtoRepository.findByCategoria_Id(id).isEmpty()) {
            throw new BusinessException("Categoria está vinculada a um ou mais produtos e não pode ser excluída.");
        }
        repository.deleteById(id);
    }
}