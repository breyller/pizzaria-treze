package br.com.pizzariatreze.dto;

import java.util.ArrayList;
import java.util.List;

public class IngredienteDto {

    private int id;
    private String nome;
    private String descricao;
    private int quantidade;
    private double valor;
    protected List alterado;
    
    public IngredienteDto(){
        this.alterado = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        
        if(!this.alterado.contains("id")) this.alterado.add("id");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        
        if(!this.alterado.contains("nome")) this.alterado.add("nome");
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        
        if(!this.alterado.contains("quantidade")) this.alterado.add("quantidade");
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
        
        if(!this.alterado.contains("valor")) this.alterado.add("valor");
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        
        if(!this.alterado.contains("descricao")) this.alterado.add("descricao");
    }

    public List getAlterado() {
        return this.alterado;
    }
}
