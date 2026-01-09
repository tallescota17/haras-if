package com.ifmg.projeto_haras.view;

import javax.swing.*;

public class FrAdministrador extends JFrame {
    
    public FrAdministrador() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Projeto Haras - Painel Administrativo");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Painel Administrativo");
        lblTitulo.setBounds(250, 30, 200, 30);
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        panel.add(lblTitulo);
        
        JButton btnGerenciarEquinos = new JButton("Gerenciar Equinos");
        btnGerenciarEquinos.setBounds(100, 100, 200, 50);
        btnGerenciarEquinos.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Módulo de Gerenciamento de Equinos")
        );
        panel.add(btnGerenciarEquinos);
        
        JButton btnGerenciarProprietarios = new JButton("Gerenciar Proprietários");
        btnGerenciarProprietarios.setBounds(400, 100, 200, 50);
        btnGerenciarProprietarios.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Módulo de Gerenciamento de Proprietários")
        );
        panel.add(btnGerenciarProprietarios);
        
        JButton btnGerenciarVeterinarios = new JButton("Gerenciar Veterinários");
        btnGerenciarVeterinarios.setBounds(100, 180, 200, 50);
        btnGerenciarVeterinarios.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Módulo de Gerenciamento de Veterinários")
        );
        panel.add(btnGerenciarVeterinarios);
        
        JButton btnGerenciarCuidadores = new JButton("Gerenciar Cuidadores");
        btnGerenciarCuidadores.setBounds(400, 180, 200, 50);
        btnGerenciarCuidadores.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Módulo de Gerenciamento de Cuidadores")
        );
        panel.add(btnGerenciarCuidadores);
        
        JButton btnGerenciarBaias = new JButton("Gerenciar Baias");
        btnGerenciarBaias.setBounds(100, 260, 200, 50);
        btnGerenciarBaias.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Módulo de Gerenciamento de Baias")
        );
        panel.add(btnGerenciarBaias);
        
        JButton btnGerenciarAlimentos = new JButton("Gerenciar Alimentos");
        btnGerenciarAlimentos.setBounds(400, 260, 200, 50);
        btnGerenciarAlimentos.addActionListener(e -> 
            JOptionPane.showMessageDialog(this, "Módulo de Gerenciamento de Alimentos")
        );
        panel.add(btnGerenciarAlimentos);
        
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(300, 380, 100, 50);
        btnSair.addActionListener(e -> {
            FrLogin frLogin = new FrLogin();
            frLogin.setVisible(true);
            dispose();
        });
        panel.add(btnSair);
        
        add(panel);
    }
}
