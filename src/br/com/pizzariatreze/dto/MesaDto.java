package br.com.pizzariatreze.dto;

import java.util.ArrayList;
import java.util.List;

public class MesaDto {
    private int id;
    private int numero;
    private int qtdLugares;
    private ArrayList<Integer> codReserva = new ArrayList<Integer>();
    protected List alterado;
    
    public MesaDto() {
        this.alterado = new ArrayList<>();
    }
    
    public MesaDto(int id, int numero, int qtdLugares, ArrayList<Integer> codReserva) {
        this.setId(id);
        this.setNumero(numero);
        this.setQtdLugares(qtdLugares);
        
        for(int i = 0; i < codReserva.size(); i++) {
            this.setCodReserva(codReserva.get(i));
        }
        
        this.alterado = new ArrayList<>();
        this.alterado.add("id");
        this.alterado.add("numero");
        this.alterado.add("qtdLugares");
        this.alterado.add("codReserva");
    }

    public ArrayList<Integer> getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(int codReserva) {
        this.codReserva.add(codReserva);
        if(!this.alterado.contains("codReserva")) this.alterado.add("codReserva");
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        
        if(!this.alterado.contains("numero")) this.alterado.add("numero");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        
        if(!this.alterado.contains("id")) this.alterado.add("id");
    }

    public int getQtdLugares() {
        return qtdLugares;
    }

    public void setQtdLugares(int qtdLugares) {
        this.qtdLugares = qtdLugares;
        
        if(!this.alterado.contains("qtdLugares")) this.alterado.add("qtdLugares");
    }

    public List getAlterado() {
        return this.alterado;
    }
}
