package com.joao.sistema_estoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventario_id")
    private Inventario inventario;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NotNull
    @Min(0)
    private Integer quantidade;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precoCompra;
}