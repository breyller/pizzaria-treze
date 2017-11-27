package br.com.pizzariatreze.dto;

import java.util.ArrayList;
import java.util.Collections;

public class ProdutoDto {
    
    private int id;
    private String descricao;
    private String nome;
    private double preco;
    private ArrayList<IngredienteDto> composicao;

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

    public ArrayList<IngredienteDto> getComposicao() {
        return composicao;
    }

    public void setComposicao(IngredienteDto ingrediente) {
        this.composicao.add(ingrediente);
        Collections.sort(this.composicao, (ingrediente1, ingrediente2) -> ingrediente1.getId() - ingrediente2.getId());
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
