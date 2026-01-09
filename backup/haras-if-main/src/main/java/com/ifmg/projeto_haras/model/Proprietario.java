/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

// Persistence annotations removed
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Proprietario extends Pessoa {
    private String cpf;
    private Integer limiteFatura;
    
    private List<Fatura> faturas;
    private List<Equino> equinos;
    
    public Proprietario(){
        super();
        this.cpf = "";
    }

    public Proprietario(String cpf, String nome, String senha, String email, Integer limiteFatura) {
        super(nome, senha, email);
        this.cpf = cpf;
    }
    
}
