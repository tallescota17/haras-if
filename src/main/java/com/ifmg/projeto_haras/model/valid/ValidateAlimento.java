
package com.ifmg.projeto_haras.model.valid;

import com.ifmg.projeto_haras.model.Alimento;
import com.ifmg.projeto_haras.model.exceptions.AlimentoException;


public class ValidateAlimento {

    public Alimento validaCamposEntrada(String nome, String preco) {
        Alimento alimento = new Alimento();
        if (nome.isEmpty()) {
            throw new AlimentoException("Error - Campo vazio: 'nome'.");
        }
        alimento.setNome(nome);
        if (preco.isEmpty()) {
            throw new AlimentoException("Error - Campo vazio: 'preco'.");
        }

        try {
            Double.parseDouble(preco);
        } catch (NumberFormatException ex) {
            throw new AlimentoException("Error - 'preco' não é um valor numérico.");
        }

        alimento.setPreco(Double.parseDouble(preco));

        return alimento;
    }
}
