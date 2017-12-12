package br.com.pizzariatreze.dto;

import java.util.ArrayList;
import java.util.List;

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
    private String composicaoString;
    protected List alterado;
    
    public PedidoDto() {
        this.alterado = new ArrayList<>();
        this.composicao = new ArrayList<ProdutoDto>();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        
        if(!this.alterado.contains("id")) this.alterado.add("id");
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        
        if(!this.alterado.contains("data")) this.alterado.add("data");
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        
        if(!this.alterado.contains("status")) this.alterado.add("status");
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        
        if(!this.alterado.contains("descricao")) this.alterado.add("descricao");
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
        
        if(!this.alterado.contains("tipo")) this.alterado.add("tipo");
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
        
        if(!this.alterado.contains("preco")) this.alterado.add("preco");
    }

    public ClienteDto getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDto cliente) {
        this.cliente = cliente;
        
        if(!this.alterado.contains("id_cliente")) this.alterado.add("id_cliente");
    }

    public FuncionarioDto getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioDto funcionario) {
        this.funcionario = funcionario;
        
        if(!this.alterado.contains("id_funcionario")) this.alterado.add("id_funcionario");
    }

    public ArrayList<ProdutoDto> getComposicao() {
        return composicao;
    }

    public void setComposicao(ProdutoDto composicao) {
        if(composicao != null) {
            this.composicao.add(composicao);

            if(!this.alterado.contains("composicao")) this.alterado.add("composicao");
        }
    }
    
    public void deleteComposicaoByPos(int posicao) {
        this.composicao.remove(posicao);
        
        if(!this.alterado.contains("composicao")) this.alterado.add("composicao");
    }

    public void deleteComposicaoByIdProduto(int idProduto) {
        for(int i = 0; i < this.composicao.size(); i++) {
            if(this.composicao.get(i).getId() == idProduto) {
                deleteComposicaoByPos(i);
            }
        }
        
        if(!this.alterado.contains("composicao")) this.alterado.add("composicao");
    }    

    public void setComposicao(String composicao) {
        this.composicaoString = composicao;
        
        if(!this.alterado.contains("composicao")) this.alterado.add("composicao");
    }

    public String getComposicaoString() {
        return this.composicaoString;
    }

    public List getAlterado(){
        return alterado;
    }
}
