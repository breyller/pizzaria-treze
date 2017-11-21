package model;

import DAO.ClienteDAO;
import java.util.ArrayList;

public class Cliente extends Pessoa {
    
    public ArrayList<Cliente> getByNome(String nome) {
        ArrayList<Cliente> result = null;
        ClienteDAO clienteDao = new ClienteDAO();
        
        result = clienteDao.getByNome(nome);
        return result;
    }

    public Cliente getById(int id) {
        Cliente result = null;
        ClienteDAO clienteDao = new ClienteDAO();
        
        result = clienteDao.getById(id);
        return result;
    }
    
    public String save() {
        String result = null;
        ClienteDAO clienteDao = new ClienteDAO();
        
        result = clienteDao.save(this);
        return result;
    }    
}
