package br.com.pizzariatreze.model;

import java.util.ArrayList;

public class Pedido {
    private int id;
    private String data;
    private int status;
    private String descricao;
    private int tipo;
    private double preco;
    private int idCliente;
    private int idFuncionario;
    private ArrayList<Integer> composicao;

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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public ArrayList<Integer> getComposicao() {
        return composicao;
    }

    public void setComposicao(int idProduto) {
        this.composicao.add(idProduto);
    }

    public void deleteComposicaoByPos(int posicao) {
        this.composicao.remove(posicao);
    }

    public void deleteComposicaoByValor(int valor) {
        int pos = this.composicao.lastIndexOf(valor);
        deleteComposicaoByPos(pos);
    }
    
    
}
