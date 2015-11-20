/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "listaeventiutente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Listaeventiutente.findAll", query = "SELECT l FROM Listaeventiutente l"),
    @NamedQuery(name = "Listaeventiutente.findByIdlistaeventiutente", query = "SELECT l FROM Listaeventiutente l WHERE l.idlistaeventiutente = :idlistaeventiutente"),
    @NamedQuery(name = "Listaeventiutente.findByIdevento", query = "SELECT l FROM Listaeventiutente l WHERE l.idevento = :idevento"),
    @NamedQuery(name = "Listaeventiutente.findByPostopagato", query = "SELECT l FROM Listaeventiutente l WHERE l.postopagato = :postopagato"),
    @NamedQuery(name = "Listaeventiutente.findByProprietario", query = "SELECT l FROM Listaeventiutente l WHERE l.proprietario = :proprietario")})
public class Listaeventiutente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlistaeventiutente")
    private Integer idlistaeventiutente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idevento")
    private int idevento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "postopagato")
    private String postopagato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "proprietario")
    private String proprietario;
    @JoinColumn(name = "idutente", referencedColumnName = "idutente")
    @ManyToOne(optional = false)
    private Utente idutente;

    public Listaeventiutente() {
    }

    public Listaeventiutente(Integer idlistaeventiutente) {
        this.idlistaeventiutente = idlistaeventiutente;
    }

    public Listaeventiutente(Integer idlistaeventiutente, int idevento, String postopagato, String proprietario) {
        this.idlistaeventiutente = idlistaeventiutente;
        this.idevento = idevento;
        this.postopagato = postopagato;
        this.proprietario = proprietario;
    }

    public Integer getIdlistaeventiutente() {
        return idlistaeventiutente;
    }

    public void setIdlistaeventiutente(Integer idlistaeventiutente) {
        this.idlistaeventiutente = idlistaeventiutente;
    }

    public int getIdevento() {
        return idevento;
    }

    public void setIdevento(int idevento) {
        this.idevento = idevento;
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

    public Utente getIdutente() {
        return idutente;
    }

    public void setIdutente(Utente idutente) {
        this.idutente = idutente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlistaeventiutente != null ? idlistaeventiutente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listaeventiutente)) {
            return false;
        }
        Listaeventiutente other = (Listaeventiutente) object;
        if ((this.idlistaeventiutente == null && other.idlistaeventiutente != null) || (this.idlistaeventiutente != null && !this.idlistaeventiutente.equals(other.idlistaeventiutente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Listaeventiutente[ idlistaeventiutente=" + idlistaeventiutente + " ]";
    }
    
}
