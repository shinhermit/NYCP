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

import entity.CriminalCase;
import entity.Incarceration;
import entity.Prisoner;
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
import javax.persistence.metamodel.SingularAttribute;
import service.remote.EntityRetriverRemote;

/**
 * The service provider to retrieve an entity.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Stateless(mappedName = "ejb/EntityRetrieverService")
public class EntityRetriever implements EntityRetriverRemote
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
    
    public List<Prisoner> findPrisonersOnRemand()
    {
        //SELECT p FROM Prisoner p WHERE NOT EXISTS 
        //(SELECT p2 FROM Prisoner p2 JOIN p2.judicialDecisionSet j 
        //WHERE p2.prisonFileNumber = p.prisonFileNumber)
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        CriteriaQuery<Prisoner> mainQuery = criteriaBuilder.createQuery(Prisoner.class);
        Root<Prisoner> mainTabPrisoner =  mainQuery.from(Prisoner.class);
        
        // Get join attribute
        Metamodel model = entityManager.getMetamodel();
        ManagedType<Prisoner> metaPrisoner = model.managedType(Prisoner.class);
        SingularAttribute attrJudicialDecisionSet = (SingularAttribute)
                metaPrisoner.getAttribute("judicialDecisionSet");
        
        Subquery<Prisoner> subQuery = mainQuery.subquery(Prisoner.class);
        Root<Prisoner> subTabPrisoner = subQuery.from(Prisoner.class);
        
        subQuery.select(subTabPrisoner.join(attrJudicialDecisionSet))
                .where(criteriaBuilder.equal(
                        subTabPrisoner.get("prisonerFileNumber"),
                        mainTabPrisoner.get("prisonFileNumber")));
        
        mainQuery.select( mainTabPrisoner )
                .where(criteriaBuilder.not(criteriaBuilder.exists(subQuery)));
        
        return null;
    }
}
