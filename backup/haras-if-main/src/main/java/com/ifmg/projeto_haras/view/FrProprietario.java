package com.ifmg.projeto_haras.view;

import javax.swing.*;

public class FrProprietario extends JFrame {
    
    public FrProprietario() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Projeto Haras - Painel Proprietário");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setResizable(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Painel do Proprietário");
        lblTitulo.setBounds(150, 30, 200, 30);
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        panel.add(lblTitulo);
        
        JButton btnMeusEquinos = new JButton("Meus Equinos");
        btnMeusEquinos.setBounds(100, 100, 300, 40);
        panel.add(btnMeusEquinos);
        
        JButton btnMinhasFaturas = new JButton("Minhas Faturas");
        btnMinhasFaturas.setBounds(100, 160, 300, 40);
        panel.add(btnMinhasFaturas);
        
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(200, 250, 100, 40);
        btnSair.addActionListener(e -> dispose());
        panel.add(btnSair);
        
        add(panel);
    }
}
