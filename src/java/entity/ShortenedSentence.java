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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author josuah
 */
@Entity
@Table(name = "SHORTENED_SENTENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShortenedSentence.findAll", query = "SELECT s FROM ShortenedSentence s"),
    @NamedQuery(name = "ShortenedSentence.findByDecisionTypeNumber", query = "SELECT s FROM ShortenedSentence s WHERE s.primaryKey.decisionTypeNumber = :decisionTypeNumber"),
    @NamedQuery(name = "ShortenedSentence.findByPrisonFileNumber", query = "SELECT s FROM ShortenedSentence s WHERE s.primaryKey.prisonFileNumber = :prisonFileNumber"),
    @NamedQuery(name = "ShortenedSentence.findByDateOfDecision", query = "SELECT s FROM ShortenedSentence s WHERE s.primaryKey.dateOfDecision = :dateOfDecision"),
    @NamedQuery(name = "ShortenedSentence.findByDuration", query = "SELECT s FROM ShortenedSentence s WHERE s.duration = :duration")})
public class ShortenedSentence extends AbstractDecision
{
    @Column(name = "DURATION")
    private Integer duration;
    
    private static final String SHORTENING_TYPE_NYMBER = "3";

    public ShortenedSentence()
    {
        this(null, null);
    }

    public ShortenedSentence(JudicialDecisionPK shortenedSentencePK)
    {
        this(shortenedSentencePK.getPrisonFileNumber(),
                shortenedSentencePK.getDateOfDecision());
    }

    public ShortenedSentence(String prisonFileNumber, Date dateOfDecision)
    {
        super(SHORTENING_TYPE_NYMBER, 
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
        return "entity.ShortenedSentence[ shortenedSentencePK=" + this.primaryKey + " ]";
    }
}
