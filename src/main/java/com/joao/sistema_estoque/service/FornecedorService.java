package com.joao.sistema_estoque.service;

import com.joao.sistema_estoque.model.Fornecedor;
import com.joao.sistema_estoque.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.joao.sistema_estoque.exception.ResourceNotFoundException;
import com.joao.sistema_estoque.exception.BusinessException;

import com.joao.sistema_estoque.repository.ProdutoRepository;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository repository;
    private final ProdutoRepository produtoRepository;

    public List<Fornecedor> listarTodos() {
        return repository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        if (repository.existsByCnpj(fornecedor.getCnpj())) {
            throw new BusinessException("CNPJ já cadastrado");
        }
        return repository.save(fornecedor);
    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = buscarPorId(id);
        fornecedor.setNome(fornecedorAtualizado.getNome());
        fornecedor.setEmail(fornecedorAtualizado.getEmail());
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());
        fornecedor.setCpf(fornecedorAtualizado.getCpf());
        fornecedor.setCnpj(fornecedorAtualizado.getCnpj());
        fornecedor.setCatalogo(fornecedorAtualizado.getCatalogo());
        return repository.save(fornecedor);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        if (!produtoRepository.findByFornecedor_Id(id).isEmpty()) {
            throw new BusinessException("Fornecedor está vinculado a um ou mais produtos e não pode ser excluído.");
        }
        repository.deleteById(id);
    }
}