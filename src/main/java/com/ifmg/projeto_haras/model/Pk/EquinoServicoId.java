/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model.Pk;

import com.ifmg.projeto_haras.model.Equino;
import com.ifmg.projeto_haras.model.ServicoAdicional;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Embeddable
public class EquinoServicoId {

    @ManyToOne
    private Equino equino;
    @ManyToOne
    private ServicoAdicional servicoAdicional;
    
    
}
