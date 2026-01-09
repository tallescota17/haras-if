/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Fatura;
import com.ifmg.projeto_haras.model.Proprietario;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TMProprietarioFatura extends AbstractTableModel {
        private List<Fatura> lista;
    
    private final int COL_STATUSFATURA = 0;
    private final int COL_LIMFATURA = 1;
    private final int COL_VALOR = 2;
    private final int COL_CRIADA = 3;

    public TMProprietarioFatura(List<Fatura> lstFatura) {        
        lista = lstFatura;        
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
        Fatura aux = new Fatura();
        if (lista.isEmpty()) {
            return aux;
        } else {
            aux = lista.get(rowIndex);

            //verifica qual valor deve ser retornado
            switch (columnIndex) {
                case -1:
                    return aux;
                case COL_LIMFATURA:
                    return aux.getProprietario().getLimiteFatura();
                case COL_STATUSFATURA:
                    if(aux.getFoiPaga() == null)
                        return "Aberta";
                    if(aux.getFoiPaga())
                        return "Paga";
                    if(!aux.getFoiPaga())
                        return "Não paga";
                case COL_VALOR:
                    return aux.getValor();
                case COL_CRIADA:
                    return aux.getCreate_at();
               
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
            case COL_LIMFATURA:
                return "Limite Fatura";
            case COL_STATUSFATURA:
                return "Status";
            case COL_VALOR:
                return "Valor";
            case COL_CRIADA:
                return "Criação";
                        
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
