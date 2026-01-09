
package com.ifmg.projeto_haras.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode
@Data
@Entity
public class ServicoAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servico_adicional_id")
    private Integer id;
    private String servico;
    private Double preco;
    @OneToMany(mappedBy = "pk.servicoAdicional")
    private List<EquinoServico> equinosServico = new ArrayList<>();

    public ServicoAdicional() {
        this.id = -1;
        this.servico = " ";
        this.preco = -1.0;
    }

    public ServicoAdicional(String servico, Double preco) {
        this.id = null;
        this.servico = servico;
        this.preco = preco;
    }

}
