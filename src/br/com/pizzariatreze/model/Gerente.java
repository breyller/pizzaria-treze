package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.FuncionarioDao;
import br.com.pizzariatreze.dao.IngredienteDao;
import br.com.pizzariatreze.dao.ProdutoDao;
import br.com.pizzariatreze.dto.FuncionarioDto;
import br.com.pizzariatreze.dto.IngredienteDto;
import br.com.pizzariatreze.dto.ProdutoDto;

public class Gerente extends Funcionario {
    
    public boolean cadastrarIngrediente(IngredienteDto ingrediente){
        IngredienteDao ingDao = new IngredienteDao();
        return ingDao.save(ingrediente);
    }
    
    public boolean cadastrarProduto(ProdutoDto produto){
        ProdutoDao prodDao = new ProdutoDao();
        return prodDao.save(produto);
    }
    
    public boolean cadastrarFuncionario(FuncionarioDto funcionario){
        FuncionarioDao funcDao = new FuncionarioDao();
        return funcDao.save(funcionario);
    }
    
    public boolean alterarIngrediente(IngredienteDto ingrediente){
        return cadastrarIngrediente(ingrediente);
    }
    
    public boolean alterarProduto(ProdutoDto produto){
        return cadastrarProduto(produto);
    }
    
    public boolean alterarFuncionario(FuncionarioDto funcionario){
        return cadastrarFuncionario(funcionario);
    }
    
    public boolean excluirIngrediente(int idIngrediente){
        IngredienteDao ingDao = new IngredienteDao();
        return ingDao.delete(idIngrediente);
    }

    public boolean excluirProduto(int idProduto){
        ProdutoDao prodDao = new ProdutoDao();
        return prodDao.delete(idProduto);
    }

    public boolean excluirFuncionario(int idFuncionario){
        FuncionarioDao funcDao = new FuncionarioDao();
        return funcDao.delete(idFuncionario);
    }
}
