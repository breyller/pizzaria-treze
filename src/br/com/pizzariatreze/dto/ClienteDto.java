package br.com.pizzariatreze.dto;

public class ClienteDto  extends PessoaDto {
    
    public ClienteDto(){
    }
    
    public ClienteDto(int id, String nome, String endereco, String telefone, String cpf){
        super(id,nome,endereco,telefone,cpf);
    }    
    
}
