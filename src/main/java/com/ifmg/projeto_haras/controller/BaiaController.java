/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Baia;
import com.ifmg.projeto_haras.model.dao.BaiaDAO;
import com.ifmg.projeto_haras.model.exceptions.BaiaException;
import com.ifmg.projeto_haras.model.valid.ValidateBaia;
import java.util.List;
import javax.swing.JTable;

public class BaiaController {

    private BaiaDAO repositorio;

    public BaiaController() {
        this.repositorio = new BaiaDAO();
    }

    public String buscarBaiasString() {
        List<Baia> baias = repositorio.findAll();

        String baiaString = "";
        for (Baia baia : baias) {
            baiaString += baia.getId() + "\n";
        }

        return baiaString;
    }

    public Baia buscarBaiaPorId(Integer id) {
        if (id == null) {
            return null;
        }
        Baia baia = (Baia) repositorio.find(id);
        return baia;
    }

    public void cadastrarBaia(Double tamanho, String tipo) {
        ValidateBaia valid = new ValidateBaia();
        Baia novaBaia = valid.validaCamposEntrada(tamanho, tipo);

        repositorio.save(novaBaia);
    }

    public void atualizarBaia(int idBaia, Double tamanho, String tipo) {
        ValidateBaia valid = new ValidateBaia();
        Baia novaBaia = valid.validaCamposEntrada(tamanho, tipo);

        novaBaia.setId(idBaia);
        repositorio.save(novaBaia);
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMCadBaia(repositorio.findAll()), null);
    }

    public void excluirBaia(Baia baia) {
        if (baia != null) {
            repositorio.delete(baia);
        } else {
            throw new BaiaException("Error: Baia inexistente.");
        }
    }
}
