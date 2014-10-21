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
import java.util.List;
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
    public List<JudicialDecision> findAll(String prisonFileNumber)
    {
        return entityManager.createNamedQuery("JudicialDecision.findByPrisonFileNumber")
                .setParameter("prisonFileNumber", prisonFileNumber)
                .getResultList();
    }

    @Override
    public List<JudicialDecision> findAll(Prisoner prisoner)
    {
        return findAll(prisoner.getPrisonFileNumber());
    }

    @Override
    public Conviction findConviction(String prisonFileNumber)
    {
        Conviction decision = null;
        
        List<Conviction> allDecisions = entityManager.createNamedQuery("Conviction.findByPrisonFileNumber")
                .setParameter("prisonFileNumber", prisonFileNumber)
                .getResultList();
        
        if(allDecisions != null)
        {
            if(!allDecisions.isEmpty())
            {
                decision = allDecisions.get(0);
            }
        }
        
        return decision;
    }

    @Override
    public Conviction findConviction(Prisoner prisoner)
    {
        return findConviction(prisoner.getPrisonFileNumber());
    }

    @Override
    public FinalDischarge findDischarge(String prisonFileNumber)
    {
        FinalDischarge decision = null;
        
        List<FinalDischarge> allDecisions = entityManager.createNamedQuery("FinalDischarge.findByPrisonFileNumber")
                .setParameter("prisonFileNumber", prisonFileNumber)
                .getResultList();
        
        if(allDecisions != null)
        {
            if(!allDecisions.isEmpty())
            {
                decision = allDecisions.get(0);
            }
        }
        
        return decision;
    }

    @Override
    public FinalDischarge findDischarge(Prisoner prisoner)
    {
        return findDischarge(prisoner.getPrisonFileNumber());
    }

    @Override
    public List<ShortenedSentence> findShortening(String prisonFileNumber)
    {
        return entityManager.createNamedQuery("findShortening.findByPrisonFileNumber")
                .setParameter("prisonFileNumber", prisonFileNumber)
                .getResultList();
    }

    @Override
    public List<ShortenedSentence> findShortening(Prisoner prisoner)
    {
        return findShortening(prisoner.getPrisonFileNumber());
    }
}
