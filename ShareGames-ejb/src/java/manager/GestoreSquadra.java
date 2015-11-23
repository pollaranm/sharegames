/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Squadra;
import ejbFacade.SquadraFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */
@Stateless
public class GestoreSquadra implements GestoreSquadraLocal {
    @EJB
    private SquadraFacadeLocal squadraFacade;

    
    @Override
    public Squadra getObjSquadra(Integer idSquadra) {
        return squadraFacade.getObjSquadra(idSquadra);
    }
    
}
