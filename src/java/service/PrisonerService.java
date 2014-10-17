/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.CriminalCase;
import entity.Prisoner;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.remote.PrisonerRemote;


@Stateless(mappedName = "ejb/PrisonerService")
public class PrisonerService implements PrisonerRemote
{
    @PersistenceContext(unitName = "NYCPPU")
    private EntityManager entityManager;
    
    @Override
    public Prisoner find(String prisonFileNumber)
    {
        Prisoner prisoner = new Prisoner(prisonFileNumber);
        if (prisoner == null) {
            throw new IllegalStateException();
        }
        
        Prisoner p = entityManager.find(Prisoner.class, prisonFileNumber);

        return p;
    }

    @Override
    public Set<CriminalCase> getCriminialCasesAssocWithPFN(String prisonFileNumber) {
        Prisoner p = find(prisonFileNumber);
        
        return p.getCriminalCaseSet();
    }
    
    @Override
    public List<Prisoner> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Prisoner.class));
        
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<Prisoner> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Prisoner.class));
        javax.persistence.Query q = entityManager.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        
        return q.getResultList();
    }
}
