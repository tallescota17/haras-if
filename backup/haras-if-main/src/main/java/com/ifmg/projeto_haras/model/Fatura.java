/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;
// Persistence annotations removed
import java.time.LocalDate;
import lombok.Data;

@Data
public class Fatura {
      
    private Integer id;
    private Boolean foiPaga;
    private LocalDate create_at;
    private LocalDate diaMaxPagamento;
    private Double valor;
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
