package model;


public class Pedido {
    private int id;
    private String descricao;
    private float valorTotal;
    private String status;
    private int idCliente;
    private int[] idPrdutos;

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

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int[] getIdPrdutos() {
        return idPrdutos;
    }

    public void setIdPrdutos(int[] idPrdutos) {
        this.idPrdutos = idPrdutos;
    }
    
    
    
}
