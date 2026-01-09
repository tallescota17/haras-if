/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class Veterinario extends Pessoa {
    private String crmv;
    
    @OneToMany(mappedBy = "veterinario", cascade = CascadeType.ALL)
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
