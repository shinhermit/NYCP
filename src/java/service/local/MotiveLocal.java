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

package service.local;

import entity.Motive;
import javax.ejb.Local;

/**
 * The remote interface for the CUD on motives.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Local
public interface MotiveLocal
{
    /**
     * Creates a new incarceration motive.
     * @param motiveNumber the unique id of the motive.
     * @param motiveLabel the descriptive label of the motive.
     * @return  the newly created motive.
     */
    public Motive createMotive(String motiveNumber, String motiveLabel);
    
    /**
     * Creates a new incarceration motive.
     * @param motive the motive which is to be inserted.
     * @return  the newly inserted motive.
     */
    public Motive createMotive(Motive motive);
    
    /**
     * Updates the label of a motive identified by it's ID.
     * @param motiveNumber the unique id of the motive.
     * @param newLabel the new descriptive label of the motive.
     * @return the updated motive.
     */
    public Motive updateMotive(String motiveNumber, String newLabel);
    
    /**
     * Updates the label of a motive.
     * @param motive the unique id of the motive.
     * @param newLabel the new descriptive label of the motive.
     * @return the updated motive.
     */
    public Motive updateMotive(Motive motive, String newLabel);
    
    /**
     * Removes a motive from the database.
     * @param motiveNumber the ID of the motive that is to be removed.
     * @return true if successfully removed, false otherwise.
     */
    public boolean deleteMotive(String motiveNumber);
    
    /**
     * Removes a motive from the database.
     * @param motive the motive which is to be removed.
     * @return true if successfully removed, false otherwise.
     */
    public boolean deleteMotive(Motive motive);
}
