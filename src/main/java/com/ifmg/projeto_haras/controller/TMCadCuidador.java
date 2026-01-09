/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Cuidador;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TMCadCuidador extends AbstractTableModel {
    private List<Cuidador> lista;
    
    private final int COL_ID = 0;
    private final int COL_NOME = 1;   
    private final int COL_EMAIL = 2;
    private final int COL_DATA_INICIO = 3;    

    public TMCadCuidador(List<Cuidador> lstCuidador) {        
        lista = lstCuidador;        
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {               
        Cuidador aux = new Cuidador();
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
                case COL_EMAIL:
                    return aux.getEmail();
                case COL_DATA_INICIO:
                    return aux.getDataInicioContrato();
                 
               
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
            case COL_EMAIL:
                return "E-Mail";
            case COL_DATA_INICIO:
                return "Data contrato";
                        
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
