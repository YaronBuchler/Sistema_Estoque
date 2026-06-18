package com.joao.sistema_estoque.service;

import com.joao.sistema_estoque.model.Cliente;
import com.joao.sistema_estoque.repository.ClienteRepository;
import com.joao.sistema_estoque.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.joao.sistema_estoque.exception.ResourceNotFoundException;
import com.joao.sistema_estoque.exception.BusinessException;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final VendaRepository vendaRepository;

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setTelefone(clienteAtualizado.getTelefone());
        cliente.setCpf(clienteAtualizado.getCpf());
        cliente.setEndereco(clienteAtualizado.getEndereco());
        return repository.save(cliente);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        if (!vendaRepository.findByCliente_Id(id).isEmpty()) {
            throw new BusinessException("Cliente está vinculado a uma ou mais vendas e não pode ser excluído.");
        }
        repository.deleteById(id);
    }
}