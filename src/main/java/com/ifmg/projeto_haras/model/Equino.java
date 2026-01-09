/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode
@Entity
@Data
public class Equino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private char sexo;
    private String raca;
    private LocalDate nascimento;
    @OneToMany(mappedBy = "pk.equino", cascade = CascadeType.ALL)
    private List<EquinoServico> equinosServico = new ArrayList<>();
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Proprietario proprietario;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cuidador cuidador;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Veterinario veterinario;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Baia baia;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "Equino_Alimento"
            , joinColumns = @JoinColumn(name = "equino_id")
            , inverseJoinColumns = @JoinColumn(name = "alimento_id"))
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
