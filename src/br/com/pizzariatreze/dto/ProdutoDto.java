package br.com.pizzariatreze.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdutoDto {
    
    private int id;
    private String descricao;
    private String nome;
    private double preco;
    private ArrayList<IngredienteDto> composicao;
    private String composicaoString;
    protected List alterado;
    
    public ProdutoDto(){
        this.alterado = new ArrayList<>();
        this.composicao = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        
        if(!this.alterado.contains("id")) this.alterado.add("id");
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        
        if(!this.alterado.contains("descricao")) this.alterado.add("descricao");
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        
        if(!this.alterado.contains("nome")) this.alterado.add("nome");
    }
    
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
        
        if(!this.alterado.contains("preco")) this.alterado.add("preco");
    }

    public ArrayList<IngredienteDto> getComposicao() {
        return composicao;
    }

    public void setComposicao(IngredienteDto ingrediente) {
        this.composicao.add(ingrediente);
        Collections.sort(this.composicao, (ingrediente1, ingrediente2) -> ingrediente1.getId() - ingrediente2.getId());
        
        if(!this.alterado.contains("composicao")) this.alterado.add("composicao");
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

    public void setComposicao(String composicao) {
        this.composicaoString = composicao;
        
        if(!this.alterado.contains("composicao")) this.alterado.add("composicao");
    }
    
     public String getComposicaoString() {
        return composicaoString;
    }
     
     @Override
     public String toString(){
         return this.nome;
     }

    public List getAlterado() {
        return this.alterado;
    }
}
