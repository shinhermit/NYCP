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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

/**
 *
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Entity
@SecondaryTable(name="CONVICTION",
        pkJoinColumns={
            @PrimaryKeyJoinColumn(name="DECISION_TYPE_NUMBER", referencedColumnName = "DECISION_TYPE_NUMBER"),
            @PrimaryKeyJoinColumn(name="PRISON_FILE_NUMBER", referencedColumnName = "PRISON_FILE_NUMBER"),
            @PrimaryKeyJoinColumn(name="DATE_OF_DECISION", referencedColumnName = "DATE_OF_DECISION")
        })
@NamedQueries({
    @NamedQuery(name = "Conviction.findAll", query = "SELECT c FROM Conviction c"),
    @NamedQuery(name = "Conviction.findByDecisionTypeNumber", query = "SELECT c FROM Conviction c WHERE c.judicialDecisionPK.decisionTypeNumber = :decisionTypeNumber"),
    @NamedQuery(name = "Conviction.findByPrisonFileNumber", query = "SELECT c FROM Conviction c WHERE c.judicialDecisionPK.prisonFileNumber = :prisonFileNumber"),
    @NamedQuery(name = "Conviction.findByDateOfDecision", query = "SELECT c FROM Conviction c WHERE c.judicialDecisionPK.dateOfDecision = :dateOfDecision")})
public class Conviction extends JudicialDecision
{
    @Column(name = "DURATION")
    private Integer duration;
    
    private static final String CONVICTION_TYPE_NYMBER = "1";
    
    public Conviction()
    {
        this(null, null);
    }

    public Conviction(JudicialDecisionPK convictionPK)
    {
        this(convictionPK.getPrisonFileNumber(),
                convictionPK.getDateOfDecision());
    }

    public Conviction(String prisonFileNumber, Date dateOfDecision)
    {
        super(CONVICTION_TYPE_NYMBER, 
                prisonFileNumber, dateOfDecision);
    }

    public Integer getDuration()
    {
        return duration;
    }

    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    @Override
    public String toString()
    {
        return "entity.Conviction[ convictionPK=" + this.judicialDecisionPK + " ]";
    }
}
