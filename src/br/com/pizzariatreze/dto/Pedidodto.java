package br.com.pizzariatreze.dto;

import java.util.ArrayList;

public class Pedidodto {
    private int id;
    private String data;
    private int status;
    private String descricao;
    private int tipo;
    private double preco;
    private Clientedto cliente;
    private Funcionariodto funcionario;
    private ArrayList<Produtodto> composicao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Clientedto getCliente() {
        return cliente;
    }

    public void setCliente(Clientedto cliente) {
        this.cliente = cliente;
    }

    public Funcionariodto getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionariodto funcionario) {
        this.funcionario = funcionario;
    }

    public ArrayList<Produtodto> getComposicao() {
        return composicao;
    }

    public void setComposicao(Produtodto composicao) {
        this.composicao.add(composicao);
    }
    
    public void deleteComposicaoByPos(int posicao) {
        this.composicao.remove(posicao);
    }

    public void deleteComposicaoByIdProduto(int idProduto) {
        for(int i = 0; i < this.composicao.size(); i++) {
            if(this.composicao.get(i).getId() == idProduto) {
                deleteComposicaoByPos(i);
            }
        }
    }    
}
