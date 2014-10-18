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
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;

/**
 *
 * @author josuah
 */
@Entity
public abstract class AbstractDecision implements Serializable
{
    @EmbeddedId
    protected JudicialDecisionPK primaryKey;
    
    @JoinColumns({
        @JoinColumn(name = "DECISION_TYPE_NUMBER", referencedColumnName = "DECISION_TYPE_NUMBER", insertable = false, updatable = false),
        @JoinColumn(name = "PRISON_FILE_NUMBER", referencedColumnName = "PRISON_FILE_NUMBER", insertable = false, updatable = false),
        @JoinColumn(name = "DATE_OF_DECISION", referencedColumnName = "DATE_OF_DECISION", insertable = false, updatable = false)})
    @OneToOne(optional = false)
    private JudicialDecision judicialDecision;

    private static final long serialVersionUID = 1L;
    
    public AbstractDecision()
    {
        this(null, null, null);
    }

    public AbstractDecision(JudicialDecisionPK convictionPK)
    {
        this(convictionPK.getDecisionTypeNumber(),
                convictionPK.getPrisonFileNumber(),
                convictionPK.getDateOfDecision());
    }

    public AbstractDecision(String decisionTypeNumber, String prisonFileNumber, Date dateOfDecision)
    {
        this.primaryKey = new JudicialDecisionPK(decisionTypeNumber, 
                prisonFileNumber, dateOfDecision);
        this.judicialDecision = new JudicialDecision(decisionTypeNumber,
                prisonFileNumber, dateOfDecision);
    }

    public JudicialDecisionPK getPrimaryKey()
    {
        return primaryKey;
    }

    public void setPrimaryKey(JudicialDecisionPK convictionPK)
    {
        this.primaryKey.copy(convictionPK);
        this.judicialDecision.setJudicialDecisionPK(convictionPK);
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (primaryKey != null ? primaryKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof AbstractDecision))
        {
            return false;
        }
        else
        {
            AbstractDecision other = (AbstractDecision) object;
            
            if ((this.primaryKey == null && other.primaryKey != null)
                    || (this.primaryKey != null && !this.primaryKey.equals(other.primaryKey)))
            {
                return false;
            }
        }
        
        return true;
    }
}
