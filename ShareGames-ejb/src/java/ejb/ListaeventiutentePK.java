/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Alex
 */
@Embeddable
public class ListaeventiutentePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idlistaeventiutente")
    private int idlistaeventiutente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idevento")
    private int idevento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idutente")
    private int idutente;

    public ListaeventiutentePK() {
    }

    public ListaeventiutentePK(int idlistaeventiutente, int idevento, int idutente) {
        this.idlistaeventiutente = idlistaeventiutente;
        this.idevento = idevento;
        this.idutente = idutente;
    }

    public int getIdlistaeventiutente() {
        return idlistaeventiutente;
    }

    public void setIdlistaeventiutente(int idlistaeventiutente) {
        this.idlistaeventiutente = idlistaeventiutente;
    }

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
    }

    public int getIdutente() {
        return idutente;
    }

    public void setIdutente(int idutente) {
        this.idutente = idutente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idlistaeventiutente;
        hash += (int) idevento;
        hash += (int) idutente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaeventiutentePK)) {
            return false;
        }
        ListaeventiutentePK other = (ListaeventiutentePK) object;
        if (this.idlistaeventiutente != other.idlistaeventiutente) {
            return false;
        }
        if (this.idevento != other.idevento) {
            return false;
        }
        if (this.idutente != other.idutente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.ListaeventiutentePK[ idlistaeventiutente=" + idlistaeventiutente + ", idevento=" + idevento + ", idutente=" + idutente + " ]";
    }
    
}
