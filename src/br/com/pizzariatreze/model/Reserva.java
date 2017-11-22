package br.com.pizzariatreze.model;

import java.util.ArrayList;

public class Reserva {
    private int id;
    private String data;
    private ArrayList<Integer> composicao;
    private int status;
    private String nome;

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

    public ArrayList<Integer> getComposicao() {
        return composicao;
    }

    public void setComposicao(int idMesa) {
        this.composicao.add(idMesa);
    }

    public void deleteComposicaoByPos(int posicao) {
        this.composicao.remove(posicao);
    }

    public void deleteComposicaoByValor(int valor) {
        int pos = this.composicao.lastIndexOf(valor);
        deleteComposicaoByPos(pos);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
