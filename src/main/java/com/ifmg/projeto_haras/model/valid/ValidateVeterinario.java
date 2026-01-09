
package com.ifmg.projeto_haras.model.valid;

import com.ifmg.projeto_haras.model.Veterinario;
import com.ifmg.projeto_haras.model.exceptions.VeterinarioException;


public class ValidateVeterinario {
    public Veterinario validaCamposEntrada(String nome, String senha, String email, String crmv){
        Veterinario veterinario = new Veterinario(nome, senha, email, crmv);
        if (nome.isEmpty()) {
            throw new VeterinarioException("Error - Campo vazio: 'nome'.");
        }
        veterinario.setNome(nome);
        if (senha.isEmpty()) {
            throw new VeterinarioException("Error - Campo vazio: 'senha'.");
        }
        veterinario.setSenha(senha);
        if (email.isEmpty()) {
            throw new VeterinarioException("Error - Campo vazio: 'email'.");
        }
        veterinario.setEmail(email);
        if (crmv.isEmpty()) {
            throw new VeterinarioException("Error - Campo vazio: 'crmv'.");
        }
        veterinario.setCrmv(crmv);
        
        
        return veterinario;
    }
}
