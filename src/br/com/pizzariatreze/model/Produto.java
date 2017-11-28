package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.ProdutoDao;
import br.com.pizzariatreze.dto.ProdutoDto;
import java.util.Map;

public class Produto {

    public boolean save(Map produto) {
        ProdutoDao produtoDao = new ProdutoDao();
        ProdutoDto produtoDto = new ProdutoDto();

        if(produto.containsKey("id")) produtoDto.setId((int)produto.get("id"));
        if(produto.containsKey("nome")) produtoDto.setNome((String)produto.get("nome"));
        if(produto.containsKey("descricao")) produtoDto.setDescricao((String)produto.get("descricao"));
        if(produto.containsKey("preco")) produtoDto.setPreco(Double.parseDouble((String)produto.get("preco")));
 
        return produtoDao.save(produtoDto);
    }  

}
