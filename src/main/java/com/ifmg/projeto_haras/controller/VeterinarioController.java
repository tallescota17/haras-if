/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Equino;
import com.ifmg.projeto_haras.model.Veterinario;
import com.ifmg.projeto_haras.model.dao.VeterinarioDAO;
import com.ifmg.projeto_haras.model.exceptions.VeterinarioException;
import com.ifmg.projeto_haras.model.valid.ValidateVeterinario;
import java.util.List;
import javax.swing.JTable;

public class VeterinarioController {
    private VeterinarioDAO repositorio;
    
    public VeterinarioController(){
        this.repositorio = new VeterinarioDAO();
    }
    
    public String buscarEquinosString(Integer id){
        Veterinario vet = (Veterinario) repositorio.find(id);
        List<Equino> equinos = vet.getEquinos();
        
        String equinoIdNomeString = "";
        for (Equino equino : equinos) {
            equinoIdNomeString += equino.getId()+" - "+equino.getNome()+"\n";
        }
        
        return equinoIdNomeString;
    }
    
    public String buscarVeterinariosString(){
        List<Veterinario> veterinarios = repositorio.findAll();
        
        String veterinarioIdNomeString = "";
        for (Veterinario vet : veterinarios) {
            veterinarioIdNomeString += vet.getId()+" - "+vet.getNome()+"\n";
        }
        
        return veterinarioIdNomeString;
    }
    
    public Veterinario buscarVeterinarioPorId(Integer id){
        if(id == null){
            return null;
        }
        Veterinario vet = (Veterinario) repositorio.find(id);
        return vet;
    }
    
    public void cadastrarVeterinario(String nome, String senha, String email, String crmv){
        ValidateVeterinario valid = new ValidateVeterinario();
        Veterinario novoVeterinario = valid.validaCamposEntrada(nome, senha, email, crmv);
             
        repositorio.save(novoVeterinario);
    }
    
    public void atualizarVeterinario(int idVeterinario, String nome, String senha, String email, String crmv){
        ValidateVeterinario valid = new ValidateVeterinario();
        Veterinario novoVeterinario = valid.validaCamposEntrada(nome, senha, email, crmv);
        
        novoVeterinario.setId(idVeterinario);
        repositorio.save(novoVeterinario);
    }
    
    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMCadVeterinario(repositorio.findAll()), null);
    }
    
    public void atualizarTabelaEquinos(JTable grd, Integer id) {
        Veterinario vet = (Veterinario) repositorio.find(id);
        List<Equino> equinos = vet.getEquinos();
         Util.jTableShow(grd, new TMCadEquino(equinos), null);
    }
    
    public void excluirVeterinario(Veterinario veterinario){
        if (veterinario != null) {
            repositorio.delete(veterinario);
        }else{
            throw new VeterinarioException("Error: Veterinario inexistente.");
        }
    }
}
