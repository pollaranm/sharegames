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
import javax.validation.constraints.Size;

/**
 *
 * @author Alex
 */
@Embeddable
public class AmministratorePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idamministratore", nullable = false)
    private int idamministratore;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password", nullable = false, length = 45)
    private String password;

    public AmministratorePK() {
    }

    public AmministratorePK(int idamministratore, String password) {
        this.idamministratore = idamministratore;
        this.password = password;
    }

    public int getIdamministratore() {
        return idamministratore;
    }

    public void setIdamministratore(int idamministratore) {
        this.idamministratore = idamministratore;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idamministratore;
        hash += (password != null ? password.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmministratorePK)) {
            return false;
        }
        AmministratorePK other = (AmministratorePK) object;
        if (this.idamministratore != other.idamministratore) {
            return false;
        }
        if ((this.password == null && other.password != null) || (this.password != null && !this.password.equals(other.password))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ejb.AmministratorePK[ idamministratore=" + idamministratore + ", password=" + password + " ]";
    }
    
}
