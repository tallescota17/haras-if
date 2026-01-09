/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Alimento;
import com.ifmg.projeto_haras.model.dao.AlimentoDAO;
import com.ifmg.projeto_haras.model.exceptions.AlimentoException;
import com.ifmg.projeto_haras.model.valid.ValidateAlimento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

public class AlimentoController {
    private AlimentoDAO repositorio;
    
    public AlimentoController(){
        this.repositorio = new AlimentoDAO();
    }
    
    public Alimento buscarProprietarioPorId(Integer id){
        if(id == null){
            return null;
        }
        Alimento alimento = (Alimento) repositorio.find(id);
        return alimento;
    }
    
    public String buscarAlimentosString(){
        List<Alimento> alimentos = repositorio.findAll();
        
        String alimentoIdNomeString = "";
        for (Alimento ali : alimentos) {
            alimentoIdNomeString += ali.getId()+" - "+ali.getNome()+"\n";
        }
        
        return alimentoIdNomeString;
    }
    
    public void cadastrarAlimento(String nome, String preco){
        ValidateAlimento valid = new ValidateAlimento();
        Alimento novoAlimento = valid.validaCamposEntrada(nome, preco);
        
        if (repositorio.findByNome(novoAlimento.getNome()) != null) {
            throw new AlimentoException("Error: Esse alimento já existe");
        }       
        repositorio.save(novoAlimento);
    }
    
    public void atualizarAlimento(int idAlimento, String nome, String preco){
        ValidateAlimento valid = new ValidateAlimento();
        Alimento novoAlimento = valid.validaCamposEntrada(nome, preco);
        
        novoAlimento.setId(idAlimento);
        repositorio.save(novoAlimento);
    }
    
    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMCadAlimento(repositorio.findAll()), null);
    }
    
    public void excluirAlimento(Alimento alimento){
        if (alimento != null) {
            repositorio.delete(alimento);
        }else{
            throw new AlimentoException("Error: Alimento inexistente.");
        }
    }
}
