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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex
 */
@Entity
@Table(catalog = "newsharegames", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utente.findAll", query = "SELECT u FROM Utente u"),
    @NamedQuery(name = "Utente.findByIdutente", query = "SELECT u FROM Utente u WHERE u.idutente = :idutente"),
    @NamedQuery(name = "Utente.findByNome", query = "SELECT u FROM Utente u WHERE u.nome = :nome"),
    @NamedQuery(name = "Utente.findByEmail", query = "SELECT u FROM Utente u WHERE u.email = :email"),
    @NamedQuery(name = "Utente.findByTelefono", query = "SELECT u FROM Utente u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Utente.findByIdgoogle", query = "SELECT u FROM Utente u WHERE u.idgoogle = :idgoogle"),
    @NamedQuery(name = "Utente.findByIdfacebook", query = "SELECT u FROM Utente u WHERE u.idfacebook = :idfacebook")})
public class Utente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idutente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(length = 100)
    private String email;
    @Size(max = 45)
    @Column(length = 45)
    private String telefono;
    @Size(max = 100)
    @Column(length = 100)
    private String idgoogle;
    @Size(max = 100)
    @Column(length = 100)
    private String idfacebook;
    @JoinColumn(name = "idsquadra", referencedColumnName = "idsquadra")
    @ManyToOne(fetch = FetchType.LAZY)
    private Squadra idsquadra;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idutente", fetch = FetchType.LAZY)
    private Collection<Evento> eventoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utente", fetch = FetchType.LAZY)
    private Collection<Listaeventiutente> listaeventiutenteCollection;

    public Utente() {
    }

    public Utente(Integer idutente) {
        this.idutente = idutente;
    }

    public Utente(Integer idutente, String nome) {
        this.idutente = idutente;
        this.nome = nome;
    }

    public Integer getIdutente() {
        return idutente;
    }

    public void setIdutente(Integer idutente) {
        this.idutente = idutente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIdgoogle() {
        return idgoogle;
    }

    public void setIdgoogle(String idgoogle) {
        this.idgoogle = idgoogle;
    }

    public String getIdfacebook() {
        return idfacebook;
    }

    public void setIdfacebook(String idfacebook) {
        this.idfacebook = idfacebook;
    }

    public Squadra getIdsquadra() {
        return idsquadra;
    }

    public void setIdsquadra(Squadra idsquadra) {
        this.idsquadra = idsquadra;
    }

    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
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
        hash += (idutente != null ? idutente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utente)) {
            return false;
        }
        Utente other = (Utente) object;
        if ((this.idutente == null && other.idutente != null) || (this.idutente != null && !this.idutente.equals(other.idutente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Utente[ idutente=" + idutente + " ]";
    }
    
}
