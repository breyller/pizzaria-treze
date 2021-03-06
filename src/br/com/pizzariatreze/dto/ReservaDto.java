package br.com.pizzariatreze.dto;

import java.util.ArrayList;

public class ReservaDto {
    private int id;
    private String data;
    private ArrayList<MesaDto> composicao;
    private int status;
    private String nome;
    private String composicaoString;

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

    public ArrayList<MesaDto> getComposicao() {
        return composicao;
    }

    public void setComposicao(MesaDto mesa) {
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
    
    public String getComposicaoString() {
        return composicaoString;
    }

    public void setComposicao(String composicao) {
        this.composicaoString = composicao;
    }
}
