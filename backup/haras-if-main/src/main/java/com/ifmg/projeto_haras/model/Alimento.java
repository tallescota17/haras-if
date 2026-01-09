/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

// Persistence annotations removed
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;


@Data
public class Alimento {
    private Integer id;
    private String nome;
    private Double preco;
    private List<Equino> equinos = new ArrayList<>();
    private Date timestamp;
    
    
    public Alimento(){
        this.id = -1;
        this.nome = "";
        this.preco = -1.0;

    }
    
    public Alimento(String nome, Double preco){
        this.id = null;
        this.nome = nome;
        this.preco = preco;
    }
    
}
