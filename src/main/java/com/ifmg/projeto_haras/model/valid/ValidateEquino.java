
package com.ifmg.projeto_haras.model.valid;

import com.ifmg.projeto_haras.model.Baia;
import com.ifmg.projeto_haras.model.Cuidador;
import com.ifmg.projeto_haras.model.Equino;
import com.ifmg.projeto_haras.model.Proprietario;
import com.ifmg.projeto_haras.model.Veterinario;
import com.ifmg.projeto_haras.model.exceptions.EquinoException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidateEquino {
    public Equino validaCamposEntrada(String nome, String sexo, String raca,
            String nascimento, Proprietario prop, Veterinario vet,
            Baia baia, Cuidador cuidador){
        
        Equino equino = new Equino();
        
        if (nome.isEmpty()) {
            throw new EquinoException("Error - Campo vazio: 'nome'.");
        }
        equino.setNome(nome);
        if (nome.isEmpty()) {
            throw new EquinoException("Error - Campo vazio: 'sexo'.");
        }
        equino.setSexo(sexo.charAt(0));
        if (raca.isEmpty()) {
            throw new EquinoException("Error - Campo vazio: 'raca'.");
        }
        equino.setRaca(raca);
        if (nascimento.isEmpty()) {
            throw new EquinoException("Error - Campo vazio: 'data de inicio de contrato'.");
        }
        
        equino.setNascimento(LocalDate.parse(nascimento));
        
        equino.setProprietario(prop);
        equino.setVeterinario(vet);
        equino.setBaia(baia);
        equino.setCuidador(cuidador);
        
        return equino;
    }
}
