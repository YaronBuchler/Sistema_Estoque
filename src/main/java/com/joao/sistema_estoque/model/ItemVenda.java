package com.joao.sistema_estoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_venda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NotNull
    @Min(1)
    private Integer quantidade;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precoUnitario;
}