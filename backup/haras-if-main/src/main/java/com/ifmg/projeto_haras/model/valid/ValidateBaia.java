/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model.valid;

import com.ifmg.projeto_haras.model.Baia;

import com.ifmg.projeto_haras.model.exceptions.BaiaException;


public class ValidateBaia {

    public Baia validaCamposEntrada(Double tamanho, String tipo) {
        Baia baia = new Baia(tamanho, tipo);
        if (tamanho == null) {
            throw new BaiaException("Error - Campo vazio: 'tamanho'.");
        }
        baia.setTamanho(tamanho);
        if (tipo.isEmpty()) {
            throw new BaiaException("Error - Campo vazio: 'tipo'.");
        }
        baia.setTipo(tipo);

        return baia;
    }
}
