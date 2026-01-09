package com.ifmg.projeto_haras;

import com.ifmg.estudojpa.model.auth.Autenticador;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        if (args.length == 0) {
            System.out.println("Usage: java com.ifmg.projeto_haras.HashUtil <text> [<text> ...]");
            return;
        }
        for (String s : args) {
            System.out.println(s + " -> " + Autenticador.textToHash(s));
        }
    }
}
