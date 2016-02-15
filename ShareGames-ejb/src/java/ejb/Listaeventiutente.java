/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(catalog = "newsharegames", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listaeventiutente.findAll", query = "SELECT l FROM Listaeventiutente l"),
    @NamedQuery(name = "Listaeventiutente.deleteIdUtenteIdEvento", query = "DELETE FROM Listaeventiutente l WHERE l.listaeventiutentePK.idutente = :idutente AND l.listaeventiutentePK.idevento = :idevento"),
    @NamedQuery(name = "Listaeventiutente.findByIdlistaeventiutente", query = "SELECT l FROM Listaeventiutente l WHERE l.listaeventiutentePK.idlistaeventiutente = :idlistaeventiutente"),
    @NamedQuery(name = "Listaeventiutente.findByIdevento", query = "SELECT l FROM Listaeventiutente l WHERE l.listaeventiutentePK.idevento = :idevento"),
    @NamedQuery(name = "Listaeventiutente.findByIdutente", query = "SELECT l FROM Listaeventiutente l WHERE l.listaeventiutentePK.idutente = :idutente"),
    @NamedQuery(name = "Listaeventiutente.findByPostopagato", query = "SELECT l FROM Listaeventiutente l WHERE l.postopagato = :postopagato"),
    @NamedQuery(name = "Listaeventiutente.findByProprietario", query = "SELECT l FROM Listaeventiutente l WHERE l.proprietario = :proprietario")})
public class Listaeventiutente implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ListaeventiutentePK listaeventiutentePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String postopagato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String proprietario;
    @PrimaryKeyJoinColumn(name = "idevento", referencedColumnName = "idevento")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Evento evento;
    @PrimaryKeyJoinColumn(name = "idutente", referencedColumnName = "idutente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utente utente;

    public Listaeventiutente() {
    }

    public Listaeventiutente(ListaeventiutentePK listaeventiutentePK) {
        this.listaeventiutentePK = listaeventiutentePK;
    }

    public Listaeventiutente(ListaeventiutentePK listaeventiutentePK, String postopagato, String proprietario) {
        this.listaeventiutentePK = listaeventiutentePK;
        this.postopagato = postopagato;
        this.proprietario = proprietario;
    }

    public Listaeventiutente(int idlistaeventiutente, int idevento, int idutente) {
        this.listaeventiutentePK = new ListaeventiutentePK(idlistaeventiutente, idevento, idutente);
    }

    public ListaeventiutentePK getListaeventiutentePK() {
        return listaeventiutentePK;
    }

    public void setListaeventiutentePK(ListaeventiutentePK listaeventiutentePK) {
        this.listaeventiutentePK = listaeventiutentePK;
    }

    public String getPostopagato() {
        return postopagato;
    }

    public void setPostopagato(String postopagato) {
        this.postopagato = postopagato;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listaeventiutentePK != null ? listaeventiutentePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listaeventiutente)) {
            return false;
        }
        Listaeventiutente other = (Listaeventiutente) object;
        if ((this.listaeventiutentePK == null && other.listaeventiutentePK != null) || (this.listaeventiutentePK != null && !this.listaeventiutentePK.equals(other.listaeventiutentePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Listaeventiutente[ listaeventiutentePK=" + listaeventiutentePK + " ]";
    }
    
}
