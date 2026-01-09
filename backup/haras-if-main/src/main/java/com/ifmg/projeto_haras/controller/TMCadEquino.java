/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Equino;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TMCadEquino  extends AbstractTableModel  {
    private List<Equino> lista;
    
    private final int COL_ID = 0;
    private final int COL_NOME = 1;   
    private final int COL_SEXO = 2;
    private final int COL_RACA = 3;
    private final int COL_NASCIMENTO = 4;
    private final int COL_PROP = 5;
    private final int COL_VETERINARIO = 6;
    private final int COL_BAIA = 7;
    private final int COL_CUIDADOR = 8; 

    public TMCadEquino(List<Equino> lstCuidador) {        
        lista = lstCuidador;        
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {               
        Equino aux = new Equino();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);

            //verifica qual valor deve ser retornado
            switch (columnIndex) {
                case -1:
                    return aux;
                case COL_ID:
                    return aux.getId();
                case COL_NOME:
                    return aux.getNome();
                case COL_SEXO:
                    return aux.getSexo();
                case COL_RACA:
                    return aux.getRaca();
                case COL_NASCIMENTO:
                    return aux.getNascimento();
                case COL_PROP:
                    if(aux.getProprietario() == null){
                        return "";
                    }
                    return aux.getProprietario().getId()+ " - "+aux.getProprietario().getNome();
                case COL_CUIDADOR:
                    if(aux.getCuidador() == null){
                        return "";
                    }
                    return aux.getCuidador().getId()+ " - "+aux.getCuidador().getNome();
                case COL_VETERINARIO:
                    if(aux.getVeterinario() == null){
                        return "";
                    }
                    return aux.getVeterinario().getId()+ " - "+aux.getVeterinario().getNome();
                case COL_BAIA:
                    if(aux.getBaia() == null){
                        return "";
                    }
                    return aux.getBaia().getId();
                 
               
                default: 
                    break;
            }
        }
        return aux;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        
        switch (column) {
            case COL_ID:
                return "Id";
            case COL_NOME:
                return "Nome";
            case COL_SEXO:
                return "Sexo";
            case COL_RACA:
                return "Raça";
            case COL_NASCIMENTO:
                return "Nascimento";
            case COL_VETERINARIO:
                return "Veterinário";
            case COL_PROP:
                return "Proprietário";
            case COL_CUIDADOR:
                return "Cuidador";
            case COL_BAIA:
                return "Baia";
                        
            default:
                break;
        }

        return "";
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }
}
