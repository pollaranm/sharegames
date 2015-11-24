/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ejb.Impianto;
import ejbFacade.ImpiantoFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alex
 */
@Stateless
public class GestoreImpianto implements GestoreImpiantoLocal {
    @EJB
    private ImpiantoFacadeLocal impiantoFacade;

    @Override
    public void addImpianto(String nomeimpianto, String stato, String regione, String provincia, 
                            String citta, String indirizzo, String telefono, String partitaiva, 
                            String fasciaoraria, String servizi) 
    {
    
    Impianto i = new Impianto();
    
    i.setNome(nomeimpianto);
    i.setStato(stato);              i.setRegione(regione);              i.setProvincia(provincia);  
    i.setCitta(citta);              i.setIndirizzo(indirizzo);          i.setTelefono(telefono);
    i.setPartitaiva(partitaiva);    i.setFasciaoraria(fasciaoraria);    i.setServizi(servizi);
    
    impiantoFacade.create(i);
    
    }

    @Override
    public void removeImpianto(int id) {
        
        impiantoFacade.remove(impiantoFacade.getObjUtente(id));
    }

    @Override
    public List<Impianto> getImpiantoByCitta(String citta) {
        
        return (List<Impianto>)impiantoFacade.getImpiantoByCitta(citta);
        
    }

    @Override
    public List<Impianto> getImpiantoByProvincia(String provincia) {
        
       return (List<Impianto>) impiantoFacade.getImpiantoByProvincia(provincia);

    }

    @Override
    public Impianto getObjectImpiantoById(int id) {
        
        return (Impianto)impiantoFacade.getObjUtente(id);
        
    }

    @Override
    public void updateImpianto(String nomeimpianto, String stato, String regione, String provincia, 
                            String citta, String indirizzo, String telefono, String partitaiva, 
                            String fasciaoraria, String servizi) 
    {
        Impianto i = new Impianto();
    
    i.setNome(nomeimpianto);
    i.setStato(stato);              i.setRegione(regione);              i.setProvincia(provincia);  
    i.setCitta(citta);              i.setIndirizzo(indirizzo);          i.setTelefono(telefono);
    i.setPartitaiva(partitaiva);    i.setFasciaoraria(fasciaoraria);    i.setServizi(servizi);
    
    impiantoFacade.edit(i);
    }
    
    
    
    
    
    

    
}
