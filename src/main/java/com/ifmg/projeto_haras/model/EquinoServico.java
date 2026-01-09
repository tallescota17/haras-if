/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model;

import com.ifmg.projeto_haras.model.Pk.EquinoServicoId;
import jakarta.persistence.AssociationOverride;
import jakarta.persistence.AssociationOverrides;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;


@EqualsAndHashCode
@Data
@Entity
@Table(name = "equino_servico")
@AssociationOverrides({
    @AssociationOverride(name = "pk.equino",
            joinColumns = @JoinColumn(name = "equino_id")),
    @AssociationOverride(name = "pk.servicoAdicional",
            joinColumns = @JoinColumn(name = "servico_adicional_id"))})
public class EquinoServico {

    @EmbeddedId
    private EquinoServicoId pk = new EquinoServicoId();
    private Integer qtd;
    @CreationTimestamp
    private Date timestamp;

    @Transient
    public Equino getEquino() {
        return getPk().getEquino();
    }

    public void setEquino(Equino equino) {
        getPk().setEquino(equino);
    }

    @Transient
    public ServicoAdicional getServicoAdicional() {
        return getPk().getServicoAdicional();
    }

    public void setServicoAdicional(ServicoAdicional servicoadicional) {
        getPk().setServicoAdicional(servicoadicional);
    }
}
