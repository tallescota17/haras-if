/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

// Persistence annotations removed
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Administrador extends Pessoa {
    private String nomeEmpresa;
    
    public Administrador(){
        super();
        this.nomeEmpresa = "";
    }

    public Administrador(String nomeEmpresa, String nome, String senha, String email) {
        super(nome, senha, email);
        this.nomeEmpresa = nomeEmpresa;
    }
}
