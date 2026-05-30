package com.joao.sistema_estoque.service;

import com.joao.sistema_estoque.model.DonoEstoque;
import com.joao.sistema_estoque.repository.DonoEstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.joao.sistema_estoque.exception.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class DonoEstoqueService {

    private final DonoEstoqueRepository repository;

    public List<DonoEstoque> listarTodos() {
        return repository.findAll();
    }

    public DonoEstoque buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dono do estoque não encontrado"));
    }

    public DonoEstoque salvar(DonoEstoque dono) {
        return repository.save(dono);
    }

    public DonoEstoque atualizar(Long id, DonoEstoque donoAtualizado) {
        DonoEstoque dono = buscarPorId(id);
        dono.setNome(donoAtualizado.getNome());
        dono.setEmail(donoAtualizado.getEmail());
        dono.setTelefone(donoAtualizado.getTelefone());
        dono.setCpf(donoAtualizado.getCpf());
        dono.setCargo(donoAtualizado.getCargo());
        dono.setRegistro(donoAtualizado.getRegistro());
        dono.setCnpj(donoAtualizado.getCnpj());
        dono.setNomeEmpresa(donoAtualizado.getNomeEmpresa());
        return repository.save(dono);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}