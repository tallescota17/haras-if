
package com.ifmg.projeto_haras.model.valid;

import com.ifmg.projeto_haras.model.ServicoAdicional;
import com.ifmg.projeto_haras.model.exceptions.ServicoAdicionalException;


public class ValidateServicoAdicional {
    public ServicoAdicional validaCamposEntrada(String servico, String preco) {
        ServicoAdicional serAdicional = new ServicoAdicional();
        if (servico.isEmpty()) {
            throw new ServicoAdicionalException("Error - Campo vazio: 'serviço'.");
        }
        serAdicional.setServico(servico);
        if (preco.isEmpty()) {
            throw new ServicoAdicionalException("Error - Campo vazio: 'preco'.");
        }
        try {
            Double.parseDouble(preco);
        } catch (NumberFormatException ex) {
            throw new ServicoAdicionalException("Error - 'preco' não é um valor numérico.");
        }
        serAdicional.setPreco(Double.parseDouble(preco));

        return serAdicional;
    }
}
