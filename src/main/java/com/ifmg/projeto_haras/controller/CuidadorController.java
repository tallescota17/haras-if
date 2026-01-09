/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Cuidador;
import com.ifmg.projeto_haras.model.Equino;
import com.ifmg.projeto_haras.model.dao.CuidadorDAO;
import com.ifmg.projeto_haras.model.exceptions.CuidadorException;
import com.ifmg.projeto_haras.model.valid.ValidateCuidador;
import java.util.List;
import javax.swing.JTable;


public class CuidadorController {

    private CuidadorDAO repositorio;

    public CuidadorController() {
        this.repositorio = new CuidadorDAO();
    }

    public String buscarCuidadoresString() {
        List<Cuidador> cuidadores = repositorio.findAll();

        String cuidadorIdNomeString = "";
        for (Cuidador cuidador : cuidadores) {
            cuidadorIdNomeString += cuidador.getId() + " - " + cuidador.getNome() + "\n";
        }

        return cuidadorIdNomeString;
    }

    public Cuidador buscarCuidadorPorId(Integer id) {
        if (id == null) {
            return null;
        }
        Cuidador cuidador = (Cuidador) repositorio.find(id);
        return cuidador;
    }

    public void cadastrarCuidador(String nome, String senha, String email, String data) {
        ValidateCuidador valid = new ValidateCuidador();
        Cuidador novoCuidador = valid.validaCamposEntrada(nome, senha, email, data);

        repositorio.save(novoCuidador);
    }

    public void atualizarCuidador(int idCuidador, String nome, String senha, String email, String data) {
        ValidateCuidador valid = new ValidateCuidador();
        Cuidador novoCuidador = valid.validaCamposEntrada(nome, senha, email, data);

        novoCuidador.setId(idCuidador);
        repositorio.save(novoCuidador);
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMCadCuidador(repositorio.findAll()), null);
    }

    public void excluirCuidador(Cuidador cuidador) {
        System.out.println(cuidador.getId());
        if (cuidador != null) {
            repositorio.delete(cuidador);
        } else {
            throw new CuidadorException("Error: Cuidador inexistente.");
        }
    }

    public void atualizarTabelaEquino(JTable grdEquino, Integer id) {
        Cuidador cui= (Cuidador)repositorio.find(id);
        List<Equino> equinos = cui.getEquinos();
        Util.jTableShow(grdEquino, new TMCadEquino(equinos),null);
    }

}
