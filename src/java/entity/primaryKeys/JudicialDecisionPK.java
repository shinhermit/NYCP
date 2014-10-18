/*
 * Copyright (C) 2014 josuah
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package entity.primaryKeys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author josuah
 */
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
    @Temporal(TemporalType.DATE)
    private Date dateOfDecision;

    public JudicialDecisionPK()
    {}

    public JudicialDecisionPK(String decisionTypeNumber, String prisonFileNumber,
            Date dateOfDecision)
    {
        this.decisionTypeNumber = decisionTypeNumber;
        this.prisonFileNumber = prisonFileNumber;
        this.dateOfDecision = dateOfDecision;
    }

    public String getDecisionTypeNumber()
    {
        return decisionTypeNumber;
    }

    public void setDecisionTypeNumber(String decisionTypeNumber)
    {
        this.decisionTypeNumber = decisionTypeNumber;
    }

    public String getPrisonFileNumber()
    {
        return prisonFileNumber;
    }

    public void setPrisonFileNumber(String prisonFileNumber)
    {
        this.prisonFileNumber = prisonFileNumber;
    }

    public Date getDateOfDecision()
    {
        return dateOfDecision;
    }

    public void setDateOfDecision(Date dateOfDecision)
    {
        this.dateOfDecision = dateOfDecision;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (decisionTypeNumber != null ? decisionTypeNumber.hashCode() : 0);
        hash += (prisonFileNumber != null ? prisonFileNumber.hashCode() : 0);
        hash += (dateOfDecision != null ? dateOfDecision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof JudicialDecisionPK))
        {
            return false;
        }
        else
        {
            JudicialDecisionPK other = (JudicialDecisionPK) object;
            
            if (!this.decisionTypeNumber.equals(other.decisionTypeNumber)
                    || !this.prisonFileNumber.equals(other.prisonFileNumber)
                    || !this.dateOfDecision.equals(other.dateOfDecision))
            {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.JudicialDecisionPK[ decisionTypeNumber=" +
                decisionTypeNumber + ", prisonFileNumber=" + prisonFileNumber +
                ", dateOfDecision=" + dateOfDecision + " ]";
    }
    
}
