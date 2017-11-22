package br.com.pizzariatreze.model;

import java.util.ArrayList;

public class Cliente extends Pessoa {
    
    public ArrayList<Cliente> getByNome(String nome) {
        ArrayList<Cliente> result = null;
        br.com.pizzariatreze.DAO.ClienteDAO clienteDao = new br.com.pizzariatreze.DAO.ClienteDAO();
        
        result = clienteDao.getByNome(nome);
        return result;
    }

    public Cliente getById(int id) {
        Cliente result = null;
        br.com.pizzariatreze.DAO.ClienteDAO clienteDao = new br.com.pizzariatreze.DAO.ClienteDAO();
        
        result = clienteDao.getById(id);
        return result;
    }
    
    public String save() {
        String result = null;
        br.com.pizzariatreze.DAO.ClienteDAO clienteDao = new br.com.pizzariatreze.DAO.ClienteDAO();
        
        result = clienteDao.save(this);
        return result;
    }    
}
