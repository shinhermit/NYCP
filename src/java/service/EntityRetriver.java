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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.remote.EntityRetriverRemote;

/**
 *
 * @author josuah
 */
@Stateless(mappedName = "ejb/EntityRetriver")
public class EntityRetriver implements EntityRetriverRemote
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
}
