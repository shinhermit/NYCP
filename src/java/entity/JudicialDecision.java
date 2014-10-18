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

package entity;

import entity.primaryKeys.JudicialDecisionPK;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author josuah
 */
@Entity
@Table(name = "JUDICIAL_DECISION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JudicialDecision.findAll", query = "SELECT j FROM JudicialDecision j"),
    @NamedQuery(name = "JudicialDecision.findByDecisionTypeNumber", query = "SELECT j FROM JudicialDecision j WHERE j.judicialDecisionPK.decisionTypeNumber = :decisionTypeNumber"),
    @NamedQuery(name = "JudicialDecision.findByPrisonFileNumber", query = "SELECT j FROM JudicialDecision j WHERE j.judicialDecisionPK.prisonFileNumber = :prisonFileNumber"),
    @NamedQuery(name = "JudicialDecision.findByDateOfDecision", query = "SELECT j FROM JudicialDecision j WHERE j.judicialDecisionPK.dateOfDecision = :dateOfDecision")})
public class JudicialDecision implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected JudicialDecisionPK judicialDecisionPK;
    
    @JoinColumn(name = "PRISON_FILE_NUMBER", referencedColumnName = "PRISON_FILE_NUMBER",
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Prisoner prisoner;

    public JudicialDecision()
    {
        this.judicialDecisionPK = new JudicialDecisionPK();
    }

    public JudicialDecision(JudicialDecisionPK judicialDecisionPK)
    {
        this.judicialDecisionPK = judicialDecisionPK;
    }

    public JudicialDecision(String decisionTypeNumber, String prisonFileNumber, Date dateOfDecision)
    {
        this.judicialDecisionPK = new JudicialDecisionPK(decisionTypeNumber, prisonFileNumber, dateOfDecision);
    }

    public JudicialDecisionPK getJudicialDecisionPK()
    {
        return judicialDecisionPK;
    }

    public void setJudicialDecisionPK(JudicialDecisionPK judicialDecisionPK)
    {
        this.judicialDecisionPK = judicialDecisionPK;
    }

    public Prisoner getPrisoner()
    {
        return prisoner;
    }

    public void setPrisoner(Prisoner prisoner)
    {
        this.prisoner = prisoner;
    }

    public String getDecisionTypeNumber()
    {
        return this.judicialDecisionPK.getDecisionTypeNumber();
    }

    public void setDecisionTypeNumber(String decisionTypeNumber)
    {
        this.judicialDecisionPK.setDecisionTypeNumber(decisionTypeNumber);
    }

    public String getPrisonFileNumber()
    {
        return this.judicialDecisionPK.getPrisonFileNumber();
    }

    public void setPrisonFileNumber(String prisonFileNumber)
    {
        this.judicialDecisionPK.setPrisonFileNumber(prisonFileNumber);
    }

    public Date getDateOfDecision()
    {
        return this.judicialDecisionPK.getDateOfDecision();
    }

    public void setDateOfDecision(Date dateOfDecision)
    {
        this.judicialDecisionPK.setDateOfDecision(dateOfDecision);
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (judicialDecisionPK != null ? judicialDecisionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof JudicialDecision))
        {
            return false;
        }
        else
        {
            JudicialDecision other = (JudicialDecision) object;
            
            if ((this.judicialDecisionPK == null && other.judicialDecisionPK != null) || (this.judicialDecisionPK != null && !this.judicialDecisionPK.equals(other.judicialDecisionPK))) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.JudicialDecision[ judicialDecisionPK=" + judicialDecisionPK + " ]";
    }
}