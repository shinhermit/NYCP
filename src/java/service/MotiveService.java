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

import entity.Motive;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import service.local.MotiveLocal;

/**
 * The CUD services provider for the motives.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Stateless(mappedName = "ejb/MotiveService")
public class MotiveService implements MotiveLocal
{
    @PersistenceContext(unitName = "NYCPPU")
    private EntityManager entityManager;

    @Override
    public Motive createMotive(String motiveNumber, String motiveLabel)
    {
        Motive motive = new Motive(motiveNumber);
        motive.setMotiveLabel(motiveLabel);
        
        this.entityManager.persist(motive);
        
        return motive;
    }
    
    @Override
    public Motive createMotive(Motive motive)
    {
        return createMotive(motive.getMotiveNumber(), motive.getMotiveLabel());
    }

    @Override
    public Motive updateMotive(String motiveNumber, String newLabel)
    {
        Motive motive = this.entityManager.find(Motive.class, motiveNumber);
        
        if(motive != null)
        {
            motive.setMotiveLabel(newLabel);
            
            motive = this.entityManager.merge(motive);
        }
        
        return motive;
    }

    @Override
    public Motive updateMotive(Motive motive, String newLabel)
    {
        return updateMotive(motive.getMotiveNumber(), newLabel);
    }

    @Override
    public boolean deleteMotive(String motiveNumber)
    {
        Boolean deleted = false;
        Motive motive = this.entityManager.find(Motive.class, motiveNumber);
        
        if(motive != null)
        {
            this.entityManager.remove(motive);
        }
        
        return deleted;
    }

    @Override
    public boolean deleteMotive(Motive motive)
    {
        return deleteMotive(motive.getMotiveNumber());
    }
}
