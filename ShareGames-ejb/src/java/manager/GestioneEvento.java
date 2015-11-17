/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.*;
import ejbFacade.EventoFacadeLocal;
import java.sql.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */
@Stateless
public class GestioneEvento implements GestioneEventoLocal {
    @EJB
    private EventoFacadeLocal eventoFacade;

    @Override
    public void addEvento() {

        Impianto i = new Impianto();
        i.setIdimpianto(19);
        
        Campo c = new Campo();
        c.setIdcampo(3);
        
        
        EventoPK e_pk = new EventoPK();
        
        e_pk.setData("17/11/2015");
        e_pk.setOra("13-14");
        
        Evento e = new Evento();

        e.setCompleto("si");
        e.setEventoPK(e_pk);
        e.setGiocatoripagato(5);
        e.setIdcampo(c);
        e.setIdimpianto(i);
        e.setPagato("si");
        e.setSport("calcio");
        
        eventoFacade.create(e);

    }

    
}
