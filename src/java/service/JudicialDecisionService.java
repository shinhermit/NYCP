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

package service;

import entity.Conviction;
import entity.FinalDischarge;
import entity.JudicialDecision;
import entity.Prisoner;
import entity.ShortenedSentence;
import java.util.Date;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.remote.JudicialDecisionRemote;

/**
 * The service provider for judicial decisions.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Stateless(mappedName = "ejb/JudicialDecisionService")
public class JudicialDecisionService implements JudicialDecisionRemote
{
    @PersistenceContext(unitName = "NYCPPU")
    private EntityManager entityManager;
    
    @Override
    public Conviction convict(String prisonFileNumber, Date dateOfDecision,
            Integer duration)
    {
        assert(entityManager != null);
        
        Prisoner prisoner = entityManager.find(Prisoner.class, prisonFileNumber);
        
        Conviction decision = new Conviction(prisonFileNumber, dateOfDecision);
        decision.setPrisoner(prisoner);
        decision.setDuration(duration);
        
        entityManager.persist(decision);
        
        return decision;
    }
    
    @Override
    public Conviction convict(Prisoner prisoner, Date dateOfDecision,
            Integer duration)
    {
        return convict(prisoner.getPrisonFileNumber(), dateOfDecision,
                duration);
    }

    @Override
    public FinalDischarge discharge(String prisonFileNumber,
            Date dateOfDecision, Date dateOfDischarge)
    {
        assert(entityManager != null);
        
        Prisoner prisoner = entityManager.find(Prisoner.class, prisonFileNumber);
        
        FinalDischarge decision = new FinalDischarge(prisonFileNumber, dateOfDecision);
        decision.setPrisoner(prisoner);
        decision.setDateOfFinalDischarge(dateOfDischarge);
        
        entityManager.persist(decision);
        
        return decision;
    }

    @Override
    public FinalDischarge discharge(Prisoner prisoner, Date dateOfDecision,
            Date dateOfDischarge)
    {
        return discharge(prisoner.getPrisonFileNumber(), dateOfDecision,
                dateOfDischarge);
    }

    @Override
    public ShortenedSentence shortenSentence(String prisonFileNumber,
            Date dateOfDecision, Integer duration)
    {
        assert(entityManager != null);
        
        Prisoner prisoner = entityManager.find(Prisoner.class, prisonFileNumber);
        
        ShortenedSentence decision = new ShortenedSentence(prisonFileNumber, dateOfDecision);
        decision.setPrisoner(prisoner);
        decision.setDuration(duration);
        
        entityManager.persist(decision);
        
        return decision;
    }

    @Override
    public ShortenedSentence shortenSentence(Prisoner prisoner,
            Date dateOfDecision, Integer duration)
    {
        return shortenSentence(prisoner.getPrisonFileNumber(), dateOfDecision,
                duration);
    }

    @Override
    public Set<JudicialDecision> findAll(String prisonFileNumber)
    {
        return null;
    }

    @Override
    public Set<JudicialDecision> findAll(Prisoner prisoner)
    {
        return null;
    }

    @Override
    public Conviction findConviction(String prisonFileNumber)
    {
        return null;
    }

    @Override
    public Conviction findConviction(Prisoner prisoner)
    {
        return null;
    }

    @Override
    public FinalDischarge findDischarge(String prisonFileNumber)
    {
        return null;
    }

    @Override
    public FinalDischarge findDischarge(Prisoner prisoner)
    {
        return null;
    }

    @Override
    public Set<ShortenedSentence> findShortening(String prisonFileNumber)
    {
        return null;
    }

    @Override
    public Set<ShortenedSentence> findShortening(Prisoner prisoner)
    {
        return null;
    }
}
