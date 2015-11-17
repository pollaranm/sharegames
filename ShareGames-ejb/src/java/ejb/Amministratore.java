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
@Table(name = "amministratore")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amministratore.findAll", query = "SELECT a FROM Amministratore a"),
    @NamedQuery(name = "Amministratore.findByCodiceamministratore", query = "SELECT a FROM Amministratore a WHERE a.codiceamministratore = :codiceamministratore"),
    @NamedQuery(name = "Amministratore.findByNome", query = "SELECT a FROM Amministratore a WHERE a.nome = :nome"),
    @NamedQuery(name = "Amministratore.findByCognome", query = "SELECT a FROM Amministratore a WHERE a.cognome = :cognome")})
public class Amministratore implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiceamministratore")
    private Integer codiceamministratore;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cognome")
    private String cognome;
    @JoinColumn(name = "idimpianto", referencedColumnName = "idimpianto")
    @ManyToOne(optional = false)
    private Impianto idimpianto;

    public Amministratore() {
    }

    public Amministratore(Integer codiceamministratore) {
        this.codiceamministratore = codiceamministratore;
    }

    public Amministratore(Integer codiceamministratore, String nome, String cognome) {
        this.codiceamministratore = codiceamministratore;
        this.nome = nome;
        this.cognome = cognome;
    }

    public Integer getCodiceamministratore() {
        return codiceamministratore;
    }

    public void setCodiceamministratore(Integer codiceamministratore) {
        this.codiceamministratore = codiceamministratore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
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
        hash += (codiceamministratore != null ? codiceamministratore.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amministratore)) {
            return false;
        }
        Amministratore other = (Amministratore) object;
        if ((this.codiceamministratore == null && other.codiceamministratore != null) || (this.codiceamministratore != null && !this.codiceamministratore.equals(other.codiceamministratore))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.Amministratore[ codiceamministratore=" + codiceamministratore + " ]";
    }
    
}
