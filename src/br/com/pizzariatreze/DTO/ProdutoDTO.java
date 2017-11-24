package br.com.pizzariatreze.DTO;

import java.util.ArrayList;

public class ProdutoDTO {
    
    private int id;
    private String descricao;
    private String nome;
    private double preco;
    private ArrayList<IngredienteDTO> composicao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public ArrayList<IngredienteDTO> getComposicao() {
        return composicao;
    }

    public void setComposicao(IngredienteDTO ingrediente) {
        this.composicao.add(ingrediente);
    }
}
