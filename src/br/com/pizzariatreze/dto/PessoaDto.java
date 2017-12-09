package br.com.pizzariatreze.dto;

import java.util.ArrayList;
import java.util.List;

public class PessoaDto {
    
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String cpf;
    private String senha;
    protected List alterado;

    public PessoaDto(){
        this.alterado = new ArrayList<>();
    }
    
    public PessoaDto(int id, String nome, String endereco, String telefone, String cpf){
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        
        this.alterado = new ArrayList<>();
        this.alterado.add("id");
        this.alterado.add("nome");
        this.alterado.add("endereco");
        this.alterado.add("telefone");
        this.alterado.add("cpf");
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        
        if(!this.alterado.contains("id")) this.alterado.add("id");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        
        if(!this.alterado.contains("nome")) this.alterado.add("nome");
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
        
        if(!this.alterado.contains("endereco")) this.alterado.add("endereco");
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
        
        if(!this.alterado.contains("telefone")) this.alterado.add("telefone");
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
        
        if(!this.alterado.contains("cpf")) this.alterado.add("cpf");
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
        
        if(!this.alterado.contains("senha")) this.alterado.add("senha");
    }
    
    public List getAlterado(){
        return alterado;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }
}
