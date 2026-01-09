/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Equino;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TMShowRelacaoEqAlimento extends AbstractTableModel  {
    private List<Object[]> lista;
    
    private final int COL_EQUINO = 0;
    private final int COL_ALIMENTO = 1;      

    public TMShowRelacaoEqAlimento(List<Object[]> lstEquino) {        
        lista = lstEquino;        
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {               
        Object[] aux = new Object[4];
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);

            //verifica qual valor deve ser retornado
            switch (columnIndex) {
                case -1:
                    return aux;
                case COL_EQUINO:
                    return aux[0] + " - " + aux[1];
                case COL_ALIMENTO:
                    return aux[2] + " - " + aux[3];
                 
               
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
            case COL_EQUINO:
                return "Equino";
            case COL_ALIMENTO:
                return "Alimento";
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
