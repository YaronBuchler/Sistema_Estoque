package com.joao.sistema_estoque.controller;

import com.joao.sistema_estoque.model.DonoEstoque;
import com.joao.sistema_estoque.service.DonoEstoqueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/donos")
@RequiredArgsConstructor
public class DonoEstoqueController {

    private final DonoEstoqueService service;

    @GetMapping
    public List<DonoEstoque> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonoEstoque> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DonoEstoque> salvar(@RequestBody @Valid DonoEstoque dono) {
        return ResponseEntity.ok(service.salvar(dono));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonoEstoque> atualizar(@PathVariable Long id, @RequestBody @Valid DonoEstoque dono) {
        return ResponseEntity.ok(service.atualizar(id, dono));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}