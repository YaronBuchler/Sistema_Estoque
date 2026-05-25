package com.joao.sistema_estoque.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "donos_estoque")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DonoEstoque extends Funcionario {

    @Column(unique = true)
    private String cnpj;

    private String nomeEmpresa;
}