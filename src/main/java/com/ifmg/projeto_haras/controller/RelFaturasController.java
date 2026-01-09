/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Alimento;
import com.ifmg.projeto_haras.model.Equino;
import com.ifmg.projeto_haras.model.EquinoServico;
import com.ifmg.projeto_haras.model.Fatura;
import com.ifmg.projeto_haras.model.Proprietario;
import com.ifmg.projeto_haras.model.dao.ProprietarioDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RelFaturasController {
    private ProprietarioDAO repositorio;
    
    public RelFaturasController(){
        this.repositorio = new ProprietarioDAO();
    }
    
    public boolean gerarRelatorio(Integer id){
        Document document = new Document();
        String arquivoPdf = "relatorioFaturas.pdf";
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream(arquivoPdf));
            document.open();
            
            Proprietario prop = (Proprietario) repositorio.find(id);
            
            String faturas = "";
            PdfPTable table = new PdfPTable(1);
            
            Paragraph p1 = new Paragraph("Proprietário: " + prop.getNome() + "\n\n");
            document.add(p1);

            for (Fatura fatura : prop.getFaturas()) {
                table.addCell(
                        "Id: "+fatura.getId() + "\n" +
                        "Valor: "+fatura.getValor() + "\n" +
                        "Criação: "+fatura.getCreate_at() + "\n" +
                        "Vencimento: "+fatura.getDiaMaxPagamento() + "\n"+
                        "Valor: "+fatura.getValor()
                );
            }
            
            document.add(table);
            document.close();
            
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RelServicosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(RelServicosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
