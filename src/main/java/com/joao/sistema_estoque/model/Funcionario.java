package com.joao.sistema_estoque.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "funcionarios")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends Pessoa {

    private String cargo;
    private String registro;
}