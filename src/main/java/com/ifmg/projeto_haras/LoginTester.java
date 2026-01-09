package com.ifmg.projeto_haras;

import com.ifmg.estudojpa.model.auth.Autenticador;
import com.ifmg.projeto_haras.controller.AutenticadorController;
import java.security.NoSuchAlgorithmException;

public class LoginTester {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        AutenticadorController ac = new AutenticadorController();

        test(ac, "joao.proprietario@example.com", "senha123");
        test(ac, "maria.cuidador@example.com", "senha123");
        test(ac, "pedro.veterinario@example.com", "senha123");
        test(ac, "adm@email.com", "123Mudar");
    }

    private static void test(AutenticadorController ac, String email, String senha) throws NoSuchAlgorithmException {
        String hash = Autenticador.textToHash(senha);
        char t = ac.autenticarPorEmailSenha(email, hash);
        System.out.println(email + " -> tipo: " + t + " (hashed senha: " + hash.substring(0,6) + "...)");
    }
}
