/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model.Pk;

import com.ifmg.projeto_haras.model.Equino;
import com.ifmg.projeto_haras.model.ServicoAdicional;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode
@Data
public class EquinoServicoId {

    private Equino equino;
    private ServicoAdicional servicoAdicional;
    
    
}
