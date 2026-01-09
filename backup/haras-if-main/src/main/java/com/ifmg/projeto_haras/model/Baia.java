/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

// Persistence annotations removed
import lombok.Data;


@Data
public class Baia {
    private Integer id;
    private Double tamanho;
    private String tipo;
    private Equino equino;
    
    public Baia(){
        this.id = -1;
        this.tamanho = -1.0;
        this.tipo = "";
    }
    
    public Baia(Double tamanho, String tipo){
        this.id = null;
        this.tamanho = tamanho;
        this.tipo = tipo;
    }
}
