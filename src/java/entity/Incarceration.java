/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="INCARCERATION")
@SecondaryTable(name="MOTIVE", 
                pkJoinColumns=@PrimaryKeyJoinColumn(name="M"))
public class Incarceration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRISON_FILE_NUMBER")
    private String prisonFileNumber;
    
    @Column(name = "DATE_OF_INCARCERATION")
    @Temporal(TemporalType.DATE)
    private Date dateOfIncarceration;
    
    @JoinColumn(name = "MOTIVE_NUMBER", referencedColumnName = "MOTIVE_NUMBER")
    @ManyToOne(optional = false)
    private Integer motiveNumber;
    
    @Column(name = "MOTIVE_LABEL", table = "MOTIVE")    
    private String motiveLabel;
    
    public Incarceration() {
    }

    public Incarceration(String prisonFileNumber) {
        this.prisonFileNumber = prisonFileNumber;
    }

    public String getPrisonFileNumber() {
        return prisonFileNumber;
    }

    public void setPrisonFileNumber(String prisonFileNumber) {
        this.prisonFileNumber = prisonFileNumber;
    }

    public Date getDateOfIncarceration() {
        return dateOfIncarceration;
    }

    public void setDateOfIncarceration(Date dateOfIncarceration) {
        this.dateOfIncarceration = dateOfIncarceration;
    }

    public Integer getMotiveNumber() {
        return motiveNumber;
    }

    public void setMotiveNumber(Integer motiveNumber) {
        this.motiveNumber = motiveNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prisonFileNumber != null ? prisonFileNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incarceration)) {
            return false;
        }
        Incarceration other = (Incarceration) object;
        if ((this.prisonFileNumber == null && other.prisonFileNumber != null) || (this.prisonFileNumber != null && !this.prisonFileNumber.equals(other.prisonFileNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Incarceration[ prisonFileNumber=" + prisonFileNumber + " ]";
    }

    /**
     * @return the motiveLabel
     */
    public String getMotiveLabel() {
        return motiveLabel;
    }

    /**
     * @param motiveLabel the motiveLabel to set
     */
    public void setMotiveLabel(String motiveLabel) {
        this.motiveLabel = motiveLabel;
    }
    
}
