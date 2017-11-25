package br.com.pizzariatreze.dto;

public class Clientedto  extends Pessoadto {
    
    public Clientedto(){
    }
    
    public Clientedto(int id, String nome, String endereco, String telefone, String cpf){
        super(id,nome,endereco,telefone,cpf);
    }    
    
}
