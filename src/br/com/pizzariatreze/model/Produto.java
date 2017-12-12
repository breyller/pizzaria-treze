package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.ProdutoDao;
import br.com.pizzariatreze.dto.ProdutoDto;
import java.util.List;
import java.util.Map;

public class Produto {

    public boolean save(Map produto) throws Exception {
        ProdutoDao produtoDao = new ProdutoDao();
        ProdutoDto produtoDto = new ProdutoDto();

        if(produto.containsKey("id")) {
            produtoDto.setId(Integer.parseInt((String) produto.get("id")));
        }
        
        if(produto.containsKey("nome")) {
            if (produto.get("nome").toString().trim().isEmpty()) {
                throw new Exception("Nome deve estar preenchido.");
            }
            produtoDto.setNome(produto.get("nome").toString().trim());
        }
        
        if(produto.containsKey("descricao")) {
            if (produto.get("descricao").toString().trim().isEmpty()) {
                throw new Exception("Descricao deve estar preenchido.");
            }
            produtoDto.setDescricao(produto.get("descricao").toString().trim());
        }
        
        if(produto.containsKey("preco")) {
            if (produto.get("preco").toString().trim().isEmpty()) {
                throw new Exception("Preco deve estar preenchido.");
            }
            
            double preco;

            try {
                preco = Double.parseDouble(produto.get("preco").toString().trim());
            } catch (Exception e) {
                throw new Exception("Preco deve ser um valor numérico.");
            }
            
            produtoDto.setPreco(preco);
        }
        
        return produtoDao.save(produtoDto);
    }  

    public Object savePizza(String nome, String composicao) {
        ProdutoDao produtoDao = new ProdutoDao();
        ProdutoDto produtoDto = new ProdutoDto();
        
        Integer id = produtoDao.pesquisarPizza(composicao);
        
        if(id == 0){
            produtoDto.setNome(nome);
            produtoDto.setComposicao(composicao);
            
            if(produtoDao.save(produtoDto))
                return savePizza(nome, composicao);
        }
        
        return produtoDao.getById(id);
    }

    public List listar() {
        ProdutoDao produtoDao = new ProdutoDao();
        ProdutoDto produtoDto = new ProdutoDto();
        
        return produtoDao.search(produtoDto);
    }

    public List listarProdutos() {
        ProdutoDao produtoDao = new ProdutoDao();
        ProdutoDto produtoDto = new ProdutoDto();
        
        produtoDto.setComposicao("");
        
        return produtoDao.search(produtoDto);
    }
}
