/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzariatreze.Controller;

import br.com.pizzariatreze.model.Funcionario;

/**
 *
 * @author Fabio
 */
public class LoginController {
    
    public LoginController(){}
    
    public boolean logar(String usuario, String senha){
        Funcionario funcionario = new Funcionario();
        
        return funcionario.login(usuario, senha);
    }
}
