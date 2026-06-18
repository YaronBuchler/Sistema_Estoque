package com.joao.sistema_estoque.service;

import com.joao.sistema_estoque.model.Funcionario;
import com.joao.sistema_estoque.repository.FuncionarioRepository;
import com.joao.sistema_estoque.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.joao.sistema_estoque.exception.ResourceNotFoundException;
import com.joao.sistema_estoque.exception.BusinessException;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final VendaRepository vendaRepository;

    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado"));
    }

    public Funcionario salvar(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado) {
        Funcionario funcionario = buscarPorId(id);
        funcionario.setNome(funcionarioAtualizado.getNome());
        funcionario.setEmail(funcionarioAtualizado.getEmail());
        funcionario.setTelefone(funcionarioAtualizado.getTelefone());
        funcionario.setCpf(funcionarioAtualizado.getCpf());
        funcionario.setCargo(funcionarioAtualizado.getCargo());
        funcionario.setRegistro(funcionarioAtualizado.getRegistro());
        return repository.save(funcionario);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        if (!vendaRepository.findByFuncionario_Id(id).isEmpty()) {
            throw new BusinessException("Funcionário está vinculado a uma ou mais vendas e não pode ser excluído.");
        }
        repository.deleteById(id);
    }
}