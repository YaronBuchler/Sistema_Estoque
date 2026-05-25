package com.joao.sistema_estoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "fornecedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fornecedor extends Pessoa {

    @Column(unique = true)
    private String cnpj;

    private String catalogo;
}