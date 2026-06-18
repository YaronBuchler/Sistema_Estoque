package com.joao.sistema_estoque.controller;

import com.joao.sistema_estoque.model.Inventario;
import com.joao.sistema_estoque.model.ItemInventario;
import com.joao.sistema_estoque.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventarios")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    @GetMapping
    public List<Inventario> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Inventario> criar(@RequestBody Inventario inventario) {
        return ResponseEntity.ok(service.criar(inventario));
    }

    @GetMapping("/{id}/itens")
    public List<ItemInventario> listarItens(@PathVariable Long id) {
        return service.listarItens(id);
    }

    @PostMapping("/{id}/itens")
    public ResponseEntity<ItemInventario> adicionarItem(
            @PathVariable Long id,
            @RequestBody Map<String, Object> body) {

        Long produtoId = Long.valueOf(body.get("produtoId").toString());
        Integer quantidade = Integer.valueOf(body.get("quantidade").toString());
        BigDecimal precoCompra = new BigDecimal(body.get("precoCompra").toString());

        return ResponseEntity.ok(service.adicionarItem(id, produtoId, quantidade, precoCompra));
    }

    @PatchMapping("/itens/{itemId}/quantidade")
    public ResponseEntity<ItemInventario> atualizarQuantidade(
            @PathVariable Long itemId,
            @RequestBody Map<String, Integer> body) {

        return ResponseEntity.ok(service.atualizarQuantidade(itemId, body.get("quantidade")));
    }

    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<Void> removerItem(@PathVariable Long itemId) {
        service.removerItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}