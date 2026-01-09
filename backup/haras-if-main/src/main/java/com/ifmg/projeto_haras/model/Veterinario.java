/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

// Persistence annotations removed
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = false)
@Data
public class Veterinario extends Pessoa {
    private String crmv;
    private List<Equino> equinos;


    public Veterinario(String nome, String senha, String email, String crmv) {
        super(nome, senha, email);
        this.crmv = crmv;
    }

    public Veterinario() {
        super();
        this.crmv = "";
    }
}
