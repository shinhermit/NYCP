/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.primaryKeys;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class JudicialDecisionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "DECISION_TYPE_NUMBER")
    private String decisionTypeNumber;
    
    @Basic(optional = false)    
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "PRISON_FILE_NUMBER")
    private String prisonFileNumber;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_OF_DECISION")
    @Temporal(DATE)
    private Date dateOfDecision;

    public JudicialDecisionPK() {
    }

    public JudicialDecisionPK(String decisionTypeNumber, String prisonFileNumber, Date dateOfDecision) {
        this.decisionTypeNumber = decisionTypeNumber;
        this.prisonFileNumber = prisonFileNumber;
        this.dateOfDecision = dateOfDecision;
    }

    public String getDecisionTypeNumber() {
        return decisionTypeNumber;
    }

    public void setDecisionTypeNumber(String decisionTypeNumber) {
        this.decisionTypeNumber = decisionTypeNumber;
    }

    public String getPrisonFileNumber() {
        return prisonFileNumber;
    }

    public void setPrisonFileNumber(String prisonFileNumber) {
        this.prisonFileNumber = prisonFileNumber;
    }

    public Date getDateOfDecision() {
        return dateOfDecision;
    }

    public void setDateOfDecision(Date dateOfDecision) {
        this.dateOfDecision = dateOfDecision;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.decisionTypeNumber);
        hash = 53 * hash + Objects.hashCode(this.prisonFileNumber);
        hash = 53 * hash + Objects.hashCode(this.dateOfDecision);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JudicialDecisionPK other = (JudicialDecisionPK) obj;
        if (!Objects.equals(this.decisionTypeNumber, other.decisionTypeNumber)) {
            return false;
        }
        if (!Objects.equals(this.prisonFileNumber, other.prisonFileNumber)) {
            return false;
        }
        if (!Objects.equals(this.dateOfDecision, other.dateOfDecision)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JudicialDecisionPK{" 
                + "decisionTypeNumber=" + decisionTypeNumber 
                + ", prisonFileNumber=" + prisonFileNumber 
                + ", dateOfDecision=" + dateOfDecision 
        + '}';
    }
    

    
}
