
package com.ifmg.projeto_haras.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Data
@Entity
public class Baia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double tamanho;
    private String tipo;
    @OneToOne(mappedBy = "baia")
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
