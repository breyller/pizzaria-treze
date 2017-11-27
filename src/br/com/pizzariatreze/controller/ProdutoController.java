package br.com.pizzariatreze.controller;

import br.com.pizzariatreze.model.Produto;
import java.util.Map;

public class ProdutoController {

        public boolean save(Map mapProduto) {
        Produto produto = new Produto();
        return produto.save(mapProduto);
    }
    
}
