package br.com.pizzariatreze.util;

import br.com.pizzariatreze.dao.FuncionarioDao;
import br.com.pizzariatreze.dto.FuncionarioDto;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    
    public static String criptografar(String texto){
        
        MessageDigest algorithm = null;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte messageDigest[] = null;
        try {
            messageDigest = algorithm.digest(texto.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
          hexString.append(String.format("%02X", 0xFF & b));
        }
        String senha = hexString.toString();
        
        return senha;
    }
    
    public static Object getUsuarioLogado() {
        FuncionarioDto func = new FuncionarioDto();
        FuncionarioDao funcDao = new FuncionarioDao();
        func = funcDao.getById(Integer.parseInt(System.getProperty("id_usuario_logado")));
        
        return func;
    }
}
