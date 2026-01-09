/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.ServicoAdicional;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TMCadServicoAdicional extends AbstractTableModel {
    private List<ServicoAdicional> lista;
    
    private final int COL_SERVICO = 0;   
    private final int COL_PRECO = 1;    

    public TMCadServicoAdicional(List<ServicoAdicional> lstSerAdicional) {        
        lista = lstSerAdicional;        
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
        ServicoAdicional aux = new ServicoAdicional();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);

            //verifica qual valor deve ser retornado
            switch (columnIndex) {
                case -1:
                    return aux;
                case COL_SERVICO:
                    return aux.getServico();
                case COL_PRECO:
                    return aux.getPreco();
               
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
            case COL_SERVICO:
                return "Serviço";
            case COL_PRECO:
                return "Preço";
                        
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
