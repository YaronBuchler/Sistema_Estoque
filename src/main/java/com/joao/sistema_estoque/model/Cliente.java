package com.joao.sistema_estoque.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends Pessoa {

    private String endereco;
}