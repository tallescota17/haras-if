
package com.ifmg.estudojpa.model.auth;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public abstract class Autenticador {
    
    private static int idLogado;

    public static int getIdLogado() {
        return idLogado;
    }

    public static void setIdLogado(int idLogado) {
        Autenticador.idLogado = idLogado;
    }
    
    public static boolean verificar(String texto, String hash) throws NoSuchAlgorithmException{
        String hashTexto = toHexString(getSHA(texto));
        
        return (hash.equals(hashTexto));
    }
    
    public static String textToHash(String texto) throws NoSuchAlgorithmException{
        if(texto.isEmpty()){
            return "";
        }
        return toHexString(getSHA(texto));
    }

    private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}
