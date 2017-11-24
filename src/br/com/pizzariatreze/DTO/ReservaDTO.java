package br.com.pizzariatreze.DTO;

import java.util.ArrayList;

public class ReservaDTO {
    private int id;
    private String data;
    private ArrayList<MesaDTO> composicao;
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

    public ArrayList<MesaDTO> getComposicao() {
        return composicao;
    }

    public void setComposicao(MesaDTO mesa) {
        this.composicao.add(mesa);
    }

    public void deleteComposicaoByPos(int posicao) {
        this.composicao.remove(posicao);
    }

    public void deleteComposicaoByIdMesa(int idMesa) {
        for (int pos = 0; pos < this.composicao.size(); pos++) {
            if (this.composicao.get(pos).getId() == idMesa) {
                deleteComposicaoByPos(pos);
            }
        }
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
