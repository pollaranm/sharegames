package manager;

import ejb.Squadra;
import ejb.Utente;
import ejbFacade.SquadraFacadeLocal;
import ejbFacade.UtenteFacadeLocal;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class GestoreSquadra implements GestoreSquadraLocal {

    @EJB
    private UtenteFacadeLocal utenteFacade;

    @EJB
    private SquadraFacadeLocal squadraFacade;

    @Override
    public Squadra getObjSquadra(Integer idSquadra) {
        return squadraFacade.getObjSquadra(idSquadra);
    }

    @Override
    public Squadra getObjSquadraByName(String nomeSquadra) {
        return squadraFacade.getObjSquadraByName(nomeSquadra);
    }

    @Override
    public void addSquadra(String nomeSquadra, String tipologia, String citta) {
        if (!checkNomeSquadra(nomeSquadra)) {
            return;
        }
        Squadra s = new Squadra();
        s.setNomesquadra(nomeSquadra);
        s.setTipologia(tipologia);
        s.setCitta(citta);
        s.setNumerocomponenti(0);
        squadraFacade.create(s);
    }

    @Override
    public void removeSquadra(Integer idSquadra) {
        Squadra temp = squadraFacade.getObjSquadra(idSquadra);
        for (Utente player : temp.getUtenteCollection()) {
            player.setIdsquadra(null);
            utenteFacade.edit(player);
        }
        squadraFacade.remove(temp);
    }

    @Override
    public Collection<Squadra> getAllSquadra() {
        return squadraFacade.findAll();
    }

    @Override
    public Boolean checkNomeSquadra(String name) {
        return squadraFacade.checkNomeSquadra(name);
    }

    @Override
    public Collection<Squadra> getSquadraByCitta(String city) {
        return squadraFacade.getSquadraByCitta(city);
    }

    @Override
    public Collection<Squadra> getSquadraByTipologia(String tipologia) {
        return squadraFacade.getSquadraByTipologia(tipologia);
    }

    @Override
    public void updateSquadra(int idSquadra, String nomeSquadra, int numeroComponenti, String tipologia, String citta) {
        if (!checkNomeSquadra(nomeSquadra)) {
            return;
        }
        Squadra s = squadraFacade.getObjSquadra(idSquadra);
        s.setNomesquadra(nomeSquadra);
        s.setTipologia(tipologia);
        s.setCitta(citta);
        s.setNumerocomponenti(numeroComponenti);
        squadraFacade.edit(s);
    }

    @Override
    public Collection<Squadra> getSquadraByCittaTipologia(String citta, String tipologia) {
        return squadraFacade.getSquadraByCittaTipologia(citta, tipologia);
    }

}
