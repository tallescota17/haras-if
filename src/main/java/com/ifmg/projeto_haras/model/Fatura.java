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
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
public class Fatura {
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean foiPaga;
    private LocalDate create_at;
    private LocalDate diaMaxPagamento;
    private Double valor;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Proprietario proprietario;
    
    
    public Fatura(){
        this.id = -1;
        this.foiPaga = false;
    }

    public Fatura(Boolean foiPaga, Double valor, Proprietario proprietario,
            LocalDate diaMaxPagamento, LocalDate create_at) {
        this.id = null;
        this.foiPaga = foiPaga;
        this.diaMaxPagamento = diaMaxPagamento;
        this.valor = valor;
        this.proprietario = proprietario;
        this.create_at = create_at;
    }
    
    
}
