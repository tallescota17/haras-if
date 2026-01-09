/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

// Persistence annotations removed
import lombok.Data;


@Data
public class Pessoa {
    private Integer id;
    private String nome;
    private String senha;
    private String email;
    
    public Pessoa(){
        this.id = null;
        this.nome = "";
        this.senha = "";
        this.email = "";
    }
    
    public Pessoa(String nome, String senha, String email){
        this.id = null;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }
}
