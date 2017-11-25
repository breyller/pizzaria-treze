package br.com.pizzariatreze.dto;

import java.util.ArrayList;

public class PedidoDto {
    private int id;
    private String data;
    private int status;
    private String descricao;
    private int tipo;
    private double preco;
    private ClienteDto cliente;
    private FuncionarioDto funcionario;
    private ArrayList<ProdutoDto> composicao;

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

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
    }

    public FuncionarioDto getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioDto funcionario) {
        this.funcionario = funcionario;
    }

    public ArrayList<ProdutoDto> getComposicao() {
        return composicao;
    }

    public void setComposicao(ProdutoDto composicao) {
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
