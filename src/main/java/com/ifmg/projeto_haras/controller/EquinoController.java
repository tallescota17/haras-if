/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Alimento;
import com.ifmg.projeto_haras.model.Baia;
import com.ifmg.projeto_haras.model.Cuidador;
import com.ifmg.projeto_haras.model.Equino;
import com.ifmg.projeto_haras.model.EquinoServico;
import com.ifmg.projeto_haras.model.Proprietario;
import com.ifmg.projeto_haras.model.ServicoAdicional;
import com.ifmg.projeto_haras.model.Veterinario;
import com.ifmg.projeto_haras.model.dao.EquinoDAO;
import com.ifmg.projeto_haras.model.exceptions.EquinoException;
import com.ifmg.projeto_haras.model.valid.ValidateEquino;
import com.ifmg.projeto_haras.model.valid.ValidateQtdEqServico;
import java.util.List;
import javax.swing.JTable;


public class EquinoController {

    private EquinoDAO repositorio;

    public EquinoController() {
        this.repositorio = new EquinoDAO();
    }

    private Integer getIdDoidNome(String idNome) {
        if (idNome.equals("")) {
            return null;
        }
        String[] novoIdNome = idNome.split(" - ");
        return Integer.parseInt(novoIdNome[0]);
    }
    
    public void relacionarEquinoAlimento(String equino, String alimento){
        Integer idEquino = getIdDoidNome(equino);
        Integer idAlimento = getIdDoidNome(alimento);
        
        Equino equinoO = this.buscarProprietarioPorId(idEquino);
        
        AlimentoController alimentoController = new AlimentoController();
        Alimento alimentoO = alimentoController.buscarProprietarioPorId(idAlimento);
        
        if(!equinoO.getAlimentos().contains(alimentoO)){
            equinoO.getAlimentos().add(alimentoO);
            repositorio.save(equinoO);
        }   
    }
    
    public void relacionarEquinoServico(String equino, String servico, String quantidade){
        Integer idEquino = getIdDoidNome(equino);
        Integer idServico = getIdDoidNome(servico);
        
        ValidateQtdEqServico valid = new ValidateQtdEqServico();
        
        Integer quantidadeInt = valid.validaQuantidade(quantidade);
        
        Equino equinoO = this.buscarProprietarioPorId(idEquino);
        
        ServicoAdicionalController servicoController = new ServicoAdicionalController();
        ServicoAdicional servicoO = servicoController.buscarServicoAdicionalPorId(idServico);
        
        EquinoServico equinoServico = new EquinoServico();
        
        equinoServico.setEquino(equinoO);
        equinoServico.setServicoAdicional(servicoO);
        equinoServico.setQtd(quantidadeInt);
        
        equinoO.getEquinosServico().add(equinoServico);
        
        repositorio.save(equinoO);
    }
    
    public Equino buscarProprietarioPorId(Integer id){
        if(id == null){
            return null;
        }
        Equino equino = (Equino) repositorio.find(id);
        return equino;
    }

    public void cadastrarEquino(String nome, String sexo,
            String raca, String nascimento, String prop, String vet,
            String baia, String cuidador) {

        System.out.println("ENTROU NO CADASTRAR");

        Integer idProprietario = getIdDoidNome(String.valueOf(prop));
        Integer idCuidador = getIdDoidNome(String.valueOf(cuidador));
        Integer idBaia = null;
        if (!String.valueOf(baia).equals("")) {
            idBaia = Integer.parseInt(String.valueOf(baia));
        }
        Integer idVeterinario = getIdDoidNome(String.valueOf(vet));

        ProprietarioController propC = new ProprietarioController();
        CuidadorController cuidC = new CuidadorController();
        BaiaController baiaC = new BaiaController();
        VeterinarioController vetC = new VeterinarioController();

        Proprietario propO = propC.buscarProprietarioPorId(idProprietario);
        Veterinario vetO = vetC.buscarVeterinarioPorId(idVeterinario);
        Baia baiaO = baiaC.buscarBaiaPorId(idBaia);
        Cuidador cuidO = cuidC.buscarCuidadorPorId(idCuidador);

        ValidateEquino valid = new ValidateEquino();
        Equino novoEquino = valid.validaCamposEntrada(nome, sexo,
                raca, nascimento, propO, vetO, baiaO, cuidO);

        repositorio.save(novoEquino);
    }

    public void atualizarEquino(int idEquino, String nome, String sexo,
            String raca, String nascimento, String prop, String vet,
            String baia, String cuidador) {

        System.out.println("ENTROU NO ATUALIZAR");

        Integer idProprietario = getIdDoidNome(String.valueOf(prop));
        Integer idCuidador = getIdDoidNome(String.valueOf(cuidador));
        Integer idBaia = null;
        if (!String.valueOf(baia).equals("")) {
            idBaia = Integer.parseInt(String.valueOf(baia));
        }
        Integer idVeterinario = getIdDoidNome(String.valueOf(vet));

        ProprietarioController propC = new ProprietarioController();
        CuidadorController cuidC = new CuidadorController();
        BaiaController baiaC = new BaiaController();
        VeterinarioController vetC = new VeterinarioController();

        Proprietario propO = propC.buscarProprietarioPorId(idProprietario);
        Veterinario vetO = vetC.buscarVeterinarioPorId(idVeterinario);
        Baia baiaO = baiaC.buscarBaiaPorId(idBaia);
        Cuidador cuidO = cuidC.buscarCuidadorPorId(idCuidador);

        ValidateEquino valid = new ValidateEquino();
        Equino novoEquino = valid.validaCamposEntrada(nome, sexo,
                raca, nascimento, propO, vetO, baiaO, cuidO);

        novoEquino.setId(idEquino);
        repositorio.save(novoEquino);
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMCadEquino(repositorio.findAll()), null);
    }
    
    public void atualizarTabelaRelacionamentos(JTable grd) {
        Util.jTableShow(grd, new TMShowRelacaoEqAlimento(repositorio.getLeftJoinAlimentos()), null);
    }
    
    public void atualizarTabelaRelacionamentosServicos(JTable grd){
        Util.jTableShow(grd, new TMShowRelacaoEqServico(repositorio.getLeftJoinServicoAdicional()), null);
    }
    
    public void excluirRelacionamentoAlimentos(Integer equino_id, Integer alimento_id){
        if (equino_id != null && alimento_id != null) {
            repositorio.deleteRelacionamentoAlimentos(equino_id, alimento_id);
//            Equino equinoO = this.buscarProprietarioPorId(equino_id);;
//            equinoO.getAlimentos().clear();
            
        }else{
            throw new EquinoException("Error: Relacionamento inexistente.");
        }
    }
    
    public void excluirRelacionamentoServicos(Integer equino_id, Integer servico_id){
        if (equino_id != null && servico_id != null) {
            repositorio.deleteRelacionamentoServicos(equino_id, servico_id);
            Equino equinoO = this.buscarProprietarioPorId(equino_id);
            equinoO.getEquinosServico().clear();
            
        }else{
            throw new EquinoException("Error: Relacionamento inexistente.");
        }
    }

    public void excluirEquino(Equino equino) {
        if (equino != null) {
            repositorio.delete(equino);
        } else {
            throw new EquinoException("Error: Equino inexistente.");
        }
    }
}
