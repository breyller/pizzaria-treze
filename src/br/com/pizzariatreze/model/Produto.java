package br.com.pizzariatreze.model;

import java.util.ArrayList;
import java.util.Collections;

public class Produto {
    private int id;
    private String descricao;
    private String nome;
    private double preco;
    private ArrayList<Integer> composicao;

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

    public ArrayList<Integer> getComposicao() {
        return composicao;
    }

    public void setComposicao(int idIngrediente) {
        this.composicao.add(idIngrediente);
        Collections.sort(composicao);
    }

    public void deleteComposicaoByPos(int posicao) {
        this.composicao.remove(posicao);
    }

    public void deleteComposicaoByValor(int valor) {
        int pos = this.composicao.lastIndexOf(valor);
        deleteComposicaoByPos(pos);
    }
}
