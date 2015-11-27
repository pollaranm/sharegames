/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex
 */
@Entity
@Table(catalog = "newsharegames", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"data", "idcampo", "idimpianto"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.updateAll", query = "UPDATE Evento e SET e.eventoPK.idimpianto = :idimpianto, e.eventoPK.idcampo = :idcampo, e.eventoPK.data = :data, e.eventoPK.ora = :ora, e.completo= :completo, e.pagato = :pagato, e.giocatoripagato = :gioatoripagato, e.sport = :sport WHERE e.eventoPK.idevento = :idevento"),
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByIdevento", query = "SELECT e FROM Evento e WHERE e.eventoPK.idevento = :idevento"),
    @NamedQuery(name = "Evento.findByIdimpianto", query = "SELECT e FROM Evento e WHERE e.eventoPK.idimpianto = :idimpianto"),
    @NamedQuery(name = "Evento.findByIdcampo", query = "SELECT e FROM Evento e WHERE e.eventoPK.idcampo = :idcampo"),
    @NamedQuery(name = "Evento.findByData", query = "SELECT e FROM Evento e WHERE e.eventoPK.data = :data"),
    @NamedQuery(name = "Evento.findByOra", query = "SELECT e FROM Evento e WHERE e.eventoPK.ora = :ora"),
    @NamedQuery(name = "Evento.findBySport", query = "SELECT e FROM Evento e WHERE e.sport = :sport"),
    @NamedQuery(name = "Evento.findByPagato", query = "SELECT e FROM Evento e WHERE e.pagato = :pagato"),
    @NamedQuery(name = "Evento.findByCompleto", query = "SELECT e FROM Evento e WHERE e.completo = :completo"),
    @NamedQuery(name = "Evento.findByGiocatoripagato", query = "SELECT e FROM Evento e WHERE e.giocatoripagato = :giocatoripagato")})
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EventoPK eventoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(nullable = false, length = 9)
    private String sport;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(nullable = false, length = 6)
    private String pagato;
    @Size(max = 3)
    @Column(length = 3)
    private String completo;
    private Integer giocatoripagato;
    @PrimaryKeyJoinColumn(name = "idcampo", referencedColumnName = "idcampo")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Campo campo;
    @PrimaryKeyJoinColumn(name = "idimpianto", referencedColumnName = "idimpianto")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Impianto impianto;
    @JoinColumn(name = "idutente", referencedColumnName = "idutente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Utente idutente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento", fetch = FetchType.LAZY)
    private Collection<Listaeventiutente> listaeventiutenteCollection;

    public Evento() {
    }

    public Evento(EventoPK eventoPK) {
        this.eventoPK = eventoPK;
    }

    public Evento(EventoPK eventoPK, String sport, String pagato) {
        this.eventoPK = eventoPK;
        this.sport = sport;
        this.pagato = pagato;
    }

    public Evento(int idevento, int idimpianto, int idcampo, String data, String ora) {
        this.eventoPK = new EventoPK(idevento, idimpianto, idcampo, data, ora);
    }

    public EventoPK getEventoPK() {
        return eventoPK;
    }

    public void setEventoPK(EventoPK eventoPK) {
        this.eventoPK = eventoPK;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getPagato() {
        return pagato;
    }

    public void setPagato(String pagato) {
        this.pagato = pagato;
    }

    public String getCompleto() {
        return completo;
    }

    public void setCompleto(String completo) {
        this.completo = completo;
    }

    public Integer getGiocatoripagato() {
        return giocatoripagato;
    }

    public void setGiocatoripagato(Integer giocatoripagato) {
        this.giocatoripagato = giocatoripagato;
    }

    public Campo getCampo() {
        return campo;
    }

    public void setCampo(Campo campo) {
        this.campo = campo;
    }

    public Impianto getImpianto() {
        return impianto;
    }

    public void setImpianto(Impianto impianto) {
        this.impianto = impianto;
    }

    public Utente getIdutente() {
        return idutente;
    }

    public void setIdutente(Utente idutente) {
        this.idutente = idutente;
    }

    @XmlTransient
    public Collection<Listaeventiutente> getListaeventiutenteCollection() {
        return listaeventiutenteCollection;
    }

    public void setListaeventiutenteCollection(Collection<Listaeventiutente> listaeventiutenteCollection) {
        this.listaeventiutenteCollection = listaeventiutenteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventoPK != null ? eventoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.eventoPK == null && other.eventoPK != null) || (this.eventoPK != null && !this.eventoPK.equals(other.eventoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Evento[ eventoPK=" + eventoPK + " ]";
    }
    
}
