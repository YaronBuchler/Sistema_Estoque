package com.joao.sistema_estoque.controller;

import com.joao.sistema_estoque.model.Compra;
import com.joao.sistema_estoque.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.joao.sistema_estoque.exception.BusinessException;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService service;

    @GetMapping
    public List<Compra> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/fornecedor/{fornecedorId}")
    public List<Compra> listarPorFornecedor(@PathVariable Long fornecedorId) {
        return service.listarPorFornecedor(fornecedorId);
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<BigDecimal> calcularTotal(@PathVariable Long id) {
        return ResponseEntity.ok(service.calcularTotal(id));
    }

    @PostMapping
    public ResponseEntity<Compra> realizarCompra(@RequestBody Map<String, Object> body) {
        if (body.get("fornecedorId") == null || body.get("donoId") == null || body.get("inventarioId") == null) {
            throw new BusinessException("fornecedorId, donoId e inventarioId são obrigatórios");
        }

        Object itensObj = body.get("itens");
        if (itensObj == null) {
            throw new BusinessException("Itens são obrigatórios");
        }

        Long fornecedorId = Long.valueOf(body.get("fornecedorId").toString());
        Long donoId = Long.valueOf(body.get("donoId").toString());
        Long inventarioId = Long.valueOf(body.get("inventarioId").toString());
        List<Map<String, Object>> itens = (List<Map<String, Object>>) itensObj;

        return ResponseEntity.ok(service.realizarCompra(fornecedorId, donoId, inventarioId, itens));
    }
}