
package com.ifmg.projeto_haras.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
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
