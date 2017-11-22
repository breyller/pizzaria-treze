package br.com.pizzariatreze.model;

public class Pessoa {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;
    
    public Pessoa(){
        return;
    }
    
    public Pessoa(int id, String nome, String endereco, String telefone, String cpf) {
        this.setId(id);
        this.setNome(nome);
        this.setEndereco(endereco);
        this.setTelefone(telefone);
        this.setCpf(cpf);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
    
}
