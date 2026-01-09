/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Equino;
import com.ifmg.projeto_haras.model.Fatura;
import com.ifmg.projeto_haras.model.Proprietario;
import com.ifmg.projeto_haras.model.dao.ProprietarioDAO;
import com.ifmg.projeto_haras.model.exceptions.ProprietarioException;
import com.ifmg.projeto_haras.model.valid.ValidateProprietario;
import java.util.List;
import javax.swing.JTable;


public class ProprietarioController {
    private ProprietarioDAO repositorio;
    
    public ProprietarioController(){
        this.repositorio = new ProprietarioDAO();
    }
    
    private Integer getIdDoidNome(String idNome) {
        if (idNome.equals("")) {
            return null;
        }
        String[] novoIdNome = idNome.split(" - ");
        return Integer.parseInt(novoIdNome[0]);
    }
    
    public void atualizarTabelaEquinos(JTable grd, Integer id) {
        Proprietario prop = (Proprietario) repositorio.find(id);
        List<Equino> equinos = prop.getEquinos();
         Util.jTableShow(grd, new TMCadEquino(equinos), null);
    }
    
    public void atualizarTabelaFaturas(JTable grd, String idNome) {
        
        Integer id = getIdDoidNome(idNome);
        
        Proprietario prop = (Proprietario) repositorio.find(id);
        List<Fatura> faturas = prop.getFaturas();
         Util.jTableShow(grd, new TMProprietarioFatura(faturas), null);
    }
    
    public String buscarProprietariosString(){
        List<Proprietario> proprietarios = repositorio.findAll();
        
        String proprietarioIdNomeString = "";
        for (Proprietario prop : proprietarios) {
            proprietarioIdNomeString += prop.getId()+" - "+prop.getNome()+"\n";
        }
        
        return proprietarioIdNomeString;
    }
    
    public Proprietario buscarProprietarioPorId(Integer id){
        if(id == null){
            return null;
        }
        Proprietario prop = (Proprietario) repositorio.find(id);
        return prop;
    }
    
    public void cadastrarProprietario(String nome, String senha, String email, String cpf,
            String limiteFatura){
        ValidateProprietario valid = new ValidateProprietario();
        Proprietario novoCuidador = valid.validaCamposEntrada(nome, senha, email, cpf, limiteFatura);
             
        repositorio.save(novoCuidador);
    }
    
    public void atualizarProprietario(int idCuidador, String nome, String senha, String email, String cpf,
            String limiteFatura){
        ValidateProprietario valid = new ValidateProprietario();
        Proprietario novoCuidador = valid.validaCamposEntrada(nome, senha, email, cpf, limiteFatura);
        
        novoCuidador.setId(idCuidador);
        repositorio.save(novoCuidador);
    }
    
    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMCadProprietario(repositorio.findAll()), null);
    }
    
    public void excluirProprietario(Proprietario proprietario){
        if (proprietario != null) {
            repositorio.delete(proprietario);
        }else{
            throw new ProprietarioException("Error: Proprietario inexistente.");
        }
    }
}
