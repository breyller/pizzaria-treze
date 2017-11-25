package br.com.pizzariatreze.dto;

import java.util.ArrayList;

public class Produtodto {
    
    private int id;
    private String descricao;
    private String nome;
    private double preco;
    private ArrayList<Ingredientedto> composicao;

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

    public ArrayList<Ingredientedto> getComposicao() {
        return composicao;
    }

    public void setComposicao(Ingredientedto ingrediente) {
        this.composicao.add(ingrediente);
    }
    
    public void deleteComposicaoByPos(int posicao) {
        this.composicao.remove(posicao);
    }

    public void deleteComposicaoByIdIngrediente(int idIngrediente) {
        for (int i = 0; i < this.composicao.size(); i++) {
            if(this.composicao.get(i).getId() == idIngrediente) {
                deleteComposicaoByPos(i);
            }
        }
    }    
}
