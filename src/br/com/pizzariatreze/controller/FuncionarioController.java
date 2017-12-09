package br.com.pizzariatreze.controller;

import br.com.pizzariatreze.model.Funcionario;
import java.util.List;
import java.util.Map;

public class FuncionarioController {

    public List<Object> listar(int id) {
        Funcionario funcionario = new Funcionario();
        return funcionario.listar(id);
    }
    
    public List<Object> listar() {
        Funcionario funcionario = new Funcionario();
        return funcionario.listar();
    } 

        public boolean save(Map mapFuncionario) throws Exception{
        Funcionario funcionario = new Funcionario();
        return funcionario.save(mapFuncionario);
    }

    public boolean delete(int id) {
        Funcionario funcionario = new Funcionario();
        return funcionario.delete(id);
    }
}
