package com.ifmg.projeto_haras.view;

import com.ifmg.projeto_haras.controller.PessoaController;
import javax.swing.*;

public class FrLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    
    public FrLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Projeto Haras - Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel lblTitulo = new JLabel("Projeto Haras");
        lblTitulo.setBounds(130, 20, 200, 30);
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        panel.add(lblTitulo);
        
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(50, 70, 100, 25);
        panel.add(lblUsuario);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 70, 200, 25);
        panel.add(txtUsuario);
        
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 120, 100, 25);
        panel.add(lblSenha);
        
        txtSenha = new JPasswordField();
        txtSenha.setBounds(150, 120, 200, 25);
        panel.add(txtSenha);
        
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(130, 180, 100, 40);
        btnEntrar.addActionListener(e -> realizarLogin());
        panel.add(btnEntrar);
        
        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(250, 180, 100, 40);
        btnSair.addActionListener(e -> System.exit(0));
        panel.add(btnSair);
        
        add(panel);
    }
    
    private void realizarLogin() {
        String email = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());
        
        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }
        
        if (PessoaController.autenticarAdministrador(email, senha)) {
            JOptionPane.showMessageDialog(this, "Login realizado com sucesso!");
            FrAdministrador frAdmin = new FrAdministrador();
            frAdmin.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
            txtSenha.setText("");
        }
    }
}
