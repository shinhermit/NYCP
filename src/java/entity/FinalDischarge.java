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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Entity
@SecondaryTable(name="FINAL_DISCHARGE",
        pkJoinColumns={
            @PrimaryKeyJoinColumn(name="DECISION_TYPE_NUMBER", referencedColumnName = "DECISION_TYPE_NUMBER"),
            @PrimaryKeyJoinColumn(name="PRISON_FILE_NUMBER", referencedColumnName = "PRISON_FILE_NUMBER"),
            @PrimaryKeyJoinColumn(name="DATE_OF_DECISION", referencedColumnName = "DATE_OF_DECISION")
        })
@NamedQueries({
    @NamedQuery(name = "FinalDischarge.findAll", query = "SELECT f FROM FinalDischarge f"),
    @NamedQuery(name = "FinalDischarge.findByPrisonFileNumber", query = "SELECT f FROM FinalDischarge f WHERE f.judicialDecisionPK.prisonFileNumber = :prisonFileNumber"),
    @NamedQuery(name = "FinalDischarge.findByDateOfDecision", query = "SELECT f FROM FinalDischarge f WHERE f.judicialDecisionPK.dateOfDecision = :dateOfDecision")})
@DiscriminatorValue("2")
public class FinalDischarge extends JudicialDecision
{
    @Column(name = "DATE_OF_FINAL_DISCHARGE", table = "FINAL_DISCHARGE")
    @Temporal(TemporalType.DATE)
    private Date dateOfFinalDischarge;
    
    private static final String DISCHARGE_TYPE_NYMBER = "2";

    public FinalDischarge()
    {
        this(null, null);
    }

    public FinalDischarge(JudicialDecisionPK finalDischargePK)
    {
        this(finalDischargePK.getPrisonFileNumber(),
                finalDischargePK.getDateOfDecision());
    }

    public FinalDischarge(String prisonFileNumber, Date dateOfDecision)
    {
        super(DISCHARGE_TYPE_NYMBER, 
                prisonFileNumber, dateOfDecision);
    }

    public Date getDateOfFinalDischarge()
    {
        return dateOfFinalDischarge;
    }

    public void setDateOfFinalDischarge(Date dateOfFinalDischarge)
    {
        this.dateOfFinalDischarge = dateOfFinalDischarge;
    }

    @Override
    public String toString()
    {
        return "entity.FinalDischarge[ finalDischargePK=" + this.judicialDecisionPK + " ]";
    }
}
