/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

// Persistence annotations removed
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode
@Data
public class ServicoAdicional {

    private Integer id;
    private String servico;
    private Double preco;
    private List<EquinoServico> equinosServico = new ArrayList<>();

    public ServicoAdicional() {
        this.id = -1;
        this.servico = " ";
        this.preco = -1.0;
    }

    public ServicoAdicional(String servico, Double preco) {
        this.id = null;
        this.servico = servico;
        this.preco = preco;
    }

}
