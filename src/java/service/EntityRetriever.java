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
import entity.CriminalCase;
import entity.FinalDischarge;
import entity.Incarceration;
import entity.JudicialDecision;
import entity.Prisoner;
import entity.ShortenedSentence;
import entity.primaryKeys.CriminalCasePK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SetAttribute;
import service.local.EntityRetriverLocal;

/**
 * The service provider to retrieve an entity.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Stateless(mappedName = "ejb/EntityRetrieverService")
public class EntityRetriever implements EntityRetriverLocal
{
    @PersistenceContext(unitName = "NYCPPU")
    private EntityManager entityManager;

    @Override
    public Prisoner findPrisoner(String prisonFileNumber)
    {
        assert(entityManager != null);
        
        return entityManager.find(Prisoner.class, prisonFileNumber);
    }

    @Override
    public List<Prisoner> findAllPrisoners()
    {
        return this.entityManager.createNamedQuery("Prisoner.findAll").getResultList();
    }
    
    @Override
    public List<Prisoner> findPrisonersOnRemand()
    {
        return this.entityManager.createNamedQuery("Prisoner.findOnRemand").getResultList();
    }

    @Override
    public CriminalCase findCriminalCase(String caseNumber, String jurisdictionName)
    {
        assert(entityManager != null);
        
        return entityManager.find(CriminalCase.class,
                new CriminalCasePK(caseNumber,jurisdictionName));
    }

    @Override
    public Incarceration findIncarceration(String prisonFileNumber)
    {
        assert(entityManager != null);
        
        return entityManager.find(Incarceration.class, prisonFileNumber);
    }

    @Override
    public List<Incarceration> findAllIncarcerations()
    {
        javax.persistence.criteria.CriteriaQuery criteriaQuery =
                entityManager.getCriteriaBuilder().createQuery();
        
        criteriaQuery.select( criteriaQuery.from(Incarceration.class) );
        
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Incarceration> findIncarcerationsInRange(int startIndex, int endIndex)
    {
        javax.persistence.criteria.CriteriaQuery criteriaQuery =
                entityManager.getCriteriaBuilder().createQuery();
        
        criteriaQuery.select( criteriaQuery.from(Incarceration.class) );
        
        javax.persistence.Query query = entityManager.createQuery(criteriaQuery);
        
        query.setMaxResults(endIndex - startIndex + 1);
        query.setFirstResult(startIndex);
        
        return query.getResultList();
    }

    @Override
    public List<JudicialDecision> findJudicialDecisions(String prisonFileNumber)
    {
        return entityManager.createNamedQuery("JudicialDecision.findByPrisonFileNumber")
                .setParameter("prisonFileNumber", prisonFileNumber)
                .getResultList();
    }

    @Override
    public List<JudicialDecision> findJudicialDecisions(Prisoner prisoner)
    {
        return findJudicialDecisions(prisoner.getPrisonFileNumber());
    }

    @Override
    public Conviction findConvictionDecision(String prisonFileNumber)
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
    public Conviction findConvictionDecision(Prisoner prisoner)
    {
        return findConvictionDecision(prisoner.getPrisonFileNumber());
    }

    @Override
    public FinalDischarge findDischargeDecision(String prisonFileNumber)
    {
        FinalDischarge decision = null;
        
        List<FinalDischarge> allDecisions =
                entityManager.createNamedQuery("FinalDischarge.findByPrisonFileNumber")
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
    public FinalDischarge findDischargeDecision(Prisoner prisoner)
    {
        return findDischargeDecision(prisoner.getPrisonFileNumber());
    }

    @Override
    public List<ShortenedSentence> findShorteningDecisions(String prisonFileNumber)
    {
        return entityManager.createNamedQuery("findShortening.findByPrisonFileNumber")
                .setParameter("prisonFileNumber", prisonFileNumber)
                .getResultList();
    }

    @Override
    public List<ShortenedSentence> findShorteningDecisions(Prisoner prisoner)
    {
        return findShorteningDecisions(prisoner.getPrisonFileNumber());
    }
}
