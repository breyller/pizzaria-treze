package br.com.pizzariatreze.controller;

import br.com.pizzariatreze.model.Reserva;
import java.util.List;

public class ReservaController {

    public boolean reservar(String nome, List<Integer> ids) {
        Reserva reserva = new Reserva();
        
        return reserva.reservar(nome, ids);
    }
    
}
