package com.joao.sistema_estoque.controller;

import com.joao.sistema_estoque.model.Venda;
import com.joao.sistema_estoque.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService service;

    @GetMapping
    public List<Venda> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Venda> listarPorCliente(@PathVariable Long clienteId) {
        return service.listarPorCliente(clienteId);
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public List<Venda> listarPorFuncionario(@PathVariable Long funcionarioId) {
        return service.listarPorFuncionario(funcionarioId);
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<BigDecimal> calcularTotal(@PathVariable Long id) {
        return ResponseEntity.ok(service.calcularTotal(id));
    }

    @PostMapping
    public ResponseEntity<Venda> realizarVenda(@RequestBody Map<String, Object> body) {
        Long clienteId = Long.valueOf(body.get("clienteId").toString());
        Long funcionarioId = Long.valueOf(body.get("funcionarioId").toString());
        Long inventarioId = Long.valueOf(body.get("inventarioId").toString());
        List<Map<String, Object>> itens = (List<Map<String, Object>>) body.get("itens");

        return ResponseEntity.ok(service.realizarVenda(clienteId, funcionarioId, inventarioId, itens));
    }
}