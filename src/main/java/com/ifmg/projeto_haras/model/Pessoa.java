/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String senha;
    @Column(nullable = false, unique = true)
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
