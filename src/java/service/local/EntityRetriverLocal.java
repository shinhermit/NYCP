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

import entity.Conviction;
import entity.CriminalCase;
import entity.FinalDischarge;
import entity.Incarceration;
import entity.JudicialDecision;
import entity.Motive;
import entity.Prisoner;
import entity.ShortenedSentence;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * The remote interface for the services allowing to retrive an entity.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Local
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public interface EntityRetriverLocal
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
     * Retrieves the list of incarcerated prisoners which are on remand.
     * @return the list of incarcerated prisoners which are on remand.
     */
    public List<Prisoner> findPrisonersOnRemand();
    
    /**
     * Retrieves a criminal case based on the case number and the original jurisdiction.
     * @param caseNumber the file number of the criminal case.
     * @param jurisdictionName the original jurisdiction of the crime.
     * @return the entity representing the criminal case if it exists, null otherwise.
     */
    public CriminalCase findCriminalCase(String caseNumber, String jurisdictionName);
    
    /**
     * Retrieves all the registered incarceration motives.
     * @return the list of all the registered incarceration motives.
     */
    public List<Motive> findAllMotives();
    
    /**
     * Retrieves the motive with the corresponding number.
     * @param motiveNumber the number of the motive.
     * @return the motive with the given number.
     */
    public Motive findMotive(String motiveNumber);
    
    /**
     * Retrieves the incarceration information associated with a prisoner.
     * @param prisonFileNumber the file number of the prisoner for which the incarceration information is required.
     * @return the entity representing the incarceration if it exists, null otherwise.
     */
    public Incarceration findIncarceration(String prisonFileNumber);
    
    /**
     * Finds all the incarcerations registered.
     * @return all the incarcerations registered.
     */
    public List<Incarceration> findAllIncarcerations();
    
    /**
     * Finds the list of all incarcerations, those which are at
     * positions going from from start index to end index.
     * @param startIndex the start position of the look up.
     * @param endIndex the ending position of the look up.
     * @return the list of all incarcerations, those which are at
     * positions going from from start index to end index.
     */
    public List<Incarceration> findIncarcerationsInRange(int startIndex, int endIndex);
    
    /**
     * Retrieves all the decision concerning a prisoner.
     * @param prisonFileNumber the file number of the prisoner of which we want the existing decisions.
     * @return all the decision concerning a prisoner.
     */
    public List<JudicialDecision> findJudicialDecisions(String prisonFileNumber);
    
    /**
     * Retrieves all the decision concerning a prisoner.
     * @param prisoner the prisoner of which we want the existing decisions.
     * @return all the decision concerning a prisoner.
     */
    public List<JudicialDecision> findJudicialDecisions(Prisoner prisoner);
    
    /**
     * Retrieves the conviction decision that has been taken for the specified prisoner.
     * @param prisonFileNumber the file number of the prisoner of which the conviction decision is requested.
     * @return if a conviction decision has been taken for the specified prisoner, this conviction decision, null otherwise.
     */
    public Conviction findConvictionDecision(String prisonFileNumber);
    
    /**
     * Retrieves the conviction decision that has been taken for the specified prisoner.
     * @param prisoner the prisoner of which the conviction decision is requested.
     * @return if a conviction decision has been taken for the specified prisoner, this conviction decision, null otherwise.
     */
    public Conviction findConvictionDecision(Prisoner prisoner);
    
    /**
     * Retrieves the final discharge decision that has been taken for the specified prisoner.
     * @param prisonFileNumber the file number of the prisoner of which the final discharge decision is requested.
     * @return if a final discharge decision has been taken for the specified prisoner, this final discharge decision, null otherwise.
     */
    public FinalDischarge findDischargeDecision(String prisonFileNumber);
    
    /**
     * Retrieves the final discharge decision that has been taken for the specified prisoner.
     * @param prisoner the prisoner of which the final discharge decision is requested.
     * @return if a final discharge decision has been taken for the specified prisoner, this final discharge decision, null otherwise.
     */
    public FinalDischarge findDischargeDecision(Prisoner prisoner);
    
    /**
     * Retrieves the sentence shortening decisions that has been taken for the specified prisoner.
     * @param prisonFileNumber the file number of the prisoner of which the sentence shortening decisions are requested.
     * @return if sentence shortening decisions have been taken for the specified prisoner, these sentence shortening decisions, null otherwise.
     */
    public List<ShortenedSentence> findShorteningDecisions(String prisonFileNumber);
    
    /**
     * Retrieves the sentence shortening decisions that has been taken for the specified prisoner.
     * @param prisoner the prisoner of which the sentence shortening decisions are requested.
     * @return if sentence shortening decisions have been taken for the specified prisoner, these sentence shortening decisions, null otherwise.
     */
    public List<ShortenedSentence> findShorteningDecisions(Prisoner prisoner);
}
