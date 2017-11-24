package br.com.pizzariatreze.Controller;

import br.com.pizzariatreze.model.Funcionario;

public class LoginController {
    
    public LoginController(){
    }
    
    public boolean logar(String usuario, String senha){
        Funcionario funcionario = new Funcionario();
        
        return funcionario.login(usuario, senha);
    }
}
