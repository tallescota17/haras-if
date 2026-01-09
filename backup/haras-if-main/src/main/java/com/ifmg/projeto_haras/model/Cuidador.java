/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class Cuidador extends Pessoa {
    private LocalDate dataInicioContrato;
    private List<Equino> equinos = new ArrayList<>();
    
    public Cuidador(){
        super();
        this.dataInicioContrato = null;
    }

    public Cuidador(String nome, String senha, String email, LocalDate dataInicioContrato) {
        super(nome, senha, email);
        this.dataInicioContrato = dataInicioContrato;
    }
}
