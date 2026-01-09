package com.ifmg.projeto_haras.view;

import javax.swing.*;

public class FrVeterinario extends JFrame {
    
    public FrVeterinario() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Projeto Haras - Painel Veterinário");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setResizable(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Painel do Veterinário");
        lblTitulo.setBounds(150, 30, 200, 30);
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        panel.add(lblTitulo);
        
        JButton btnEquinos = new JButton("Equinos");
        btnEquinos.setBounds(100, 100, 300, 40);
        panel.add(btnEquinos);
        
        JButton btnRelatorios = new JButton("Relatórios");
        btnRelatorios.setBounds(100, 160, 300, 40);
        panel.add(btnRelatorios);
        
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(200, 250, 100, 40);
        btnSair.addActionListener(e -> dispose());
        panel.add(btnSair);
        
        add(panel);
    }
}
