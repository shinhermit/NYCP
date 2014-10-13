/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.CriminalCase;
import entity.Prisoner;
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
    
    
}
