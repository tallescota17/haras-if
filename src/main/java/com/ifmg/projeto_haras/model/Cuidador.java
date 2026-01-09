
package com.ifmg.projeto_haras.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class Cuidador extends Pessoa {
    private LocalDate dataInicioContrato;
    @OneToMany(mappedBy = "cuidador",cascade = CascadeType.PERSIST)
    private List<Equino> equinos = new ArrayList<>();
    
    public Cuidador(){
        super();
        this.dataInicioContrato = null;
    }

    public Cuidador(String nome, String senha, String email, LocalDate dataInicioContrato) {
        super(nome, senha, email);
        this.dataInicioContrato = dataInicioContrato;
    }
}
