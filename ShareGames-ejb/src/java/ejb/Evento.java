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
@Table(name = "evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByIdevento", query = "SELECT e FROM Evento e WHERE e.eventoPK.idevento = :idevento"),
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
    @Column(name = "sport")
    private String sport;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "pagato")
    private String pagato;
    @Size(max = 3)
    @Column(name = "completo")
    private String completo;
    @Column(name = "giocatoripagato")
    private Integer giocatoripagato;
    @JoinColumn(name = "idcampo", referencedColumnName = "idcampo")
    @ManyToOne(optional = false)
    private Campo idcampo;
    @JoinColumn(name = "idimpianto", referencedColumnName = "idimpianto")
    @ManyToOne(optional = false)
    private Impianto idimpianto;

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

    public Evento(int idevento, String data, String ora) {
        this.eventoPK = new EventoPK(idevento, data, ora);
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

    public Campo getIdcampo() {
        return idcampo;
    }

    public void setIdcampo(Campo idcampo) {
        this.idcampo = idcampo;
    }

    public Impianto getIdimpianto() {
        return idimpianto;
    }

    public void setIdimpianto(Impianto idimpianto) {
        this.idimpianto = idimpianto;
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
