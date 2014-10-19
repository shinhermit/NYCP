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

package service.remote;

import entity.CriminalCase;
import entity.Incarceration;
import entity.Prisoner;
import java.util.List;
import javax.ejb.Remote;

/**
 * The remote interface for the services allowing to retrive an entity.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Remote
public interface EntityRetriverRemote
{
    /**
     * Retrieves a prisoner based on is prison file number.
     * @param prisonFileNumber the file number of the prisoner which is required.
     * @return the entity representing the prisoner if it exists, null otherwise.
     */
    public Prisoner findPrisoner(String prisonFileNumber);
    
    /**
     * Retrieves the list of all incarcerated prisoners.
     * @return the list of all incarcerated prisoners.
     */
    public List<Prisoner> findAllPrisoners();
    
    /**
     * Retrieves a criminal case based on the case number and the original jurisdiction.
     * @param caseNumber the file number of the criminal case.
     * @param jurisdictionName the original jurisdiction of the crime.
     * @return the entity representing the criminal case if it exists, null otherwise.
     */
    public CriminalCase findCriminalCase(String caseNumber, String jurisdictionName);
    
    /**
     * Retrieves the incarceration information associated with a prisoner.
     * @param prisonFileNumber the file number of the prisoner for which the incarceration information is required.
     * @return the entity representing the incarceration if it exists, null otherwise.
     */
    public Incarceration findIncarceration(String prisonFileNumber);
}
