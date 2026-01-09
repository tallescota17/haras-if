/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.estudojpa.model.auth.Autenticador;
import com.ifmg.projeto_haras.model.dao.CuidadorDAO;
import com.ifmg.projeto_haras.model.dao.ProprietarioDAO;
import com.ifmg.projeto_haras.model.dao.VeterinarioDAO;


public class AutenticadorController {

    private CuidadorDAO repCuidador;
    private VeterinarioDAO repVeterinario;
    private ProprietarioDAO repProprietario;

    public AutenticadorController() {
        repCuidador = new CuidadorDAO();
        repVeterinario = new VeterinarioDAO();
        repProprietario = new ProprietarioDAO();
    }

    public char autenticarPorEmailSenha(String email, String senha) {
        Integer idCuidador = repCuidador.getCuidadorByEmailAndSenha(email, senha);
        if (!(idCuidador == 0)) {
            Autenticador.setIdLogado(idCuidador);
            return 'c';
        }

        Integer idVeterinario = repVeterinario.getVeterinarioByEmailAndSenha(email, senha);
        if (!(idVeterinario == 0)) {
            Autenticador.setIdLogado(idVeterinario);
            return 'v';
        }

        Integer idProprietario = repProprietario.getProprietarioByEmailAndSenha(email, senha);
        if (!(idProprietario == 0)) {
            Autenticador.setIdLogado(idProprietario);
            return 'p';
        }
        return 'n';
    }

}
