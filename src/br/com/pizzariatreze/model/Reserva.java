package br.com.pizzariatreze.model;

import br.com.pizzariatreze.dao.MesaDao;
import br.com.pizzariatreze.dao.ReservaDao;
import br.com.pizzariatreze.dto.MesaDto;
import br.com.pizzariatreze.dto.ReservaDto;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Reserva {

    public boolean reservar(String nome, List<Integer> ids) {
        ReservaDao reservaDao = new ReservaDao();
        ReservaDto reservaDto = new ReservaDto();
        
        MesaDao mesaDao = new MesaDao();
        MesaDto mesaDto = new MesaDto();
        boolean first = true;
        String comp = "";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        for (Integer id : ids) {
            mesaDto.setId(id);
            mesaDto.setStatus(1);
            mesaDao.save(mesaDto);
            
            if(first){
                first = false;
                
                comp = id.toString();
            }else{
                comp += "," + id;
            }
        }
        
        reservaDto.setComposicao(comp);
        reservaDto.setNome(nome);
        reservaDto.setStatus(0);
        reservaDto.setData(formato.format(new Date()));
        
        return reservaDao.save(reservaDto);

    }

}
