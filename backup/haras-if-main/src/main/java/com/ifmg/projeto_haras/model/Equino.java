/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

// JPA annotations removed: this project no longer uses a database layer
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode
@Data
public class Equino {
    private Integer id;
    private String nome;
    private char sexo;
    private String raca;
    private LocalDate nascimento;
    // relationship metadata removed
    private List<EquinoServico> equinosServico = new ArrayList<>();
    private Proprietario proprietario;
    private Cuidador cuidador;
    private Veterinario veterinario;
    private Baia baia;
    private List <Alimento> alimentos = new ArrayList<>();
    
    public Equino(){
        this.id = -1;
        this.nome = "";
        this.sexo = '-';
        this.raca = "";
        this.nascimento = null;
        this.proprietario = new Proprietario();
        this.veterinario = new Veterinario();
        this.baia = new Baia();
        this.cuidador = new Cuidador();      
    }

    public Equino(String nome, char sexo, String raca, LocalDate nascimento, 
            Proprietario prop, Veterinario vet, Baia baia, Cuidador cuidador) {
        this.id = null;
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.nascimento = nascimento;
        this.proprietario = prop;
        this.veterinario = vet;
        this.baia = baia;
        this.cuidador = cuidador;
    }
    
}
