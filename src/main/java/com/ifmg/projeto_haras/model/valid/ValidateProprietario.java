
package com.ifmg.projeto_haras.model.valid;

import com.ifmg.projeto_haras.model.Proprietario;
import com.ifmg.projeto_haras.model.exceptions.ProprietarioException;
import java.time.LocalDate;


public class ValidateProprietario {
    public Proprietario validaCamposEntrada(String nome, String senha, String email, String cpf,
        String limiteFatura){
        Proprietario proprietario = new Proprietario(nome, senha, email, cpf, null);
        if (nome.isEmpty()) {
            throw new ProprietarioException("Error - Campo vazio: 'nome'.");
        }
        proprietario.setNome(nome);
        if (senha.isEmpty()) {
            throw new ProprietarioException("Error - Campo vazio: 'senha'.");
        }
        proprietario.setSenha(senha);
        if (email.isEmpty()) {
            throw new ProprietarioException("Error - Campo vazio: 'email'.");
        }
        proprietario.setEmail(email);
        if (cpf.isEmpty()) {
            throw new ProprietarioException("Error - Campo vazio: 'cpf'.");
        }
        if (!validaCPF(cpf)) {
            throw new ProprietarioException("Error - Campo Inválido: 'cpf'.");
        }
        proprietario.setCpf(cpf);
        if(limiteFatura.isEmpty()){
            throw new ProprietarioException("Error - Campo vazio: 'limite fatura'.");
        }
        if(!isInteger(limiteFatura)){
            throw new ProprietarioException("Error - Campo não é inteiro: 'limite fatura'.");
        }
        proprietario.setLimiteFatura(Integer.parseInt(limiteFatura));
        
        
        return proprietario;
    }
    
    private boolean isInteger(String str) {
        return str != null && str.matches("[0-9]*");
    }
    
     private boolean validaCPF(String cpf){        
        //vamos verificar o tamanho primeiro        
        if(cpf.length() != 11  && cpf.length() != 14){
            return false;
            //throw new RuntimeException("CPF inválido -  Tamanho inválido.");
        }
        
        //Retira os caracteres deixando apenas digitos
        if(cpf.length() == 14){
            cpf = cpf.replaceAll("\\.", "");
            cpf = cpf.replaceAll("-", "");
        }
        
        //se tem tamanho 11 e so possui digitos
        if(cpf.length() == 11 && cpf.matches("[0-9]*")){
            String []vet = cpf.split("");                        
            
            
            int digito1 = Integer.parseInt(vet[9]);
            int digito2 = Integer.parseInt(vet[10]);
            
            //vamos calcular o primeiro verificador
            int soma1 = 0;
            int fator1 = 10;
            for(int i=0; i <=8; i++){
                int val = Integer.parseInt(vet[i]);
                soma1 = soma1 + (fator1 * val);                 
                fator1--;                
            }
            
            int resultado1 = (soma1*10)%11;
            if(resultado1==10)
                resultado1 = 0;
            
            if(resultado1 != digito1){
                return false;
            }
            
            //vamos calcular o segundo verificador
            int soma2 = 0;
            int fator2 = 11;
            for(int i=0; i <= 9; i++){
                int val = Integer.parseInt(vet[i]);
                soma2 = soma2 + (fator2 * val);
                fator2--;
            }
                                   
            int resultado2 = (soma2*10)%11;
            if(resultado2 == 10)
                resultado2 = 0;
            
            if(resultado2 != digito2){
               return false;
            }                    
            
            //agora so basta verificar se todos são iguais
            for(int i=0; i <= 9; i++){
                int val = Integer.parseInt(vet[i]);
                int valProx = Integer.parseInt(vet[i+1]);
                if(val != valProx)
                    return true;
            }
        }
                        
        return false;
    
    }

}
