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

import entity.Conviction;
import entity.FinalDischarge;
import entity.JudicialDecision;
import entity.Prisoner;
import entity.ShortenedSentence;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 * The remote interface for the services concerning the judicial decisions.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Remote
public interface JudicialDecisionRemote
{
    /**
     * Adds a new conviction decision for the specified prisoner.
     * @param prisonFileNumber the file number of the prisoner for which the conviction decision is taken.
     * @param dateOfDecision the date of the conviction decision.
     * @param duration the duration of the conviction.
     * @return the newly created conviction decision object.
     */
    public Conviction convict(String prisonFileNumber, Date dateOfDecision,
            Integer duration);
    
    /**
     * Adds a new conviction decision for the specified prisoner.
     * @param prisoner the prisoner for which the conviction decision is taken.
     * @param dateOfDecision the date of the conviction decision.
     * @param duration the duration of the conviction.
     * @return the newly created conviction decision object.
     */
    public Conviction convict(Prisoner prisoner, Date dateOfDecision,
            Integer duration);
    
    /**
     * 
     * Add a new conviction decision for the specified prisoner.
     * @param prisonFileNumber the file number of the prisoner for which the conviction decision is taken.
     * @param dateOfDecision the date of the conviction decision.
     * @param dateOfDischarge the date at which the discharge must be applied.
     * @return the newly created discharge decision object.
     */
    public FinalDischarge discharge(String prisonFileNumber, Date dateOfDecision,
            Date dateOfDischarge);
    
    /**
     * 
     * Add a new conviction decision for the specified prisoner.
     * @param prisoner the prisoner for which the conviction decision is taken.
     * @param dateOfDecision the date of the conviction decision.
     * @param dateOfDischarge the date at which the discharge must be applied.
     * @return the newly created discharge decision object.
     */
    public FinalDischarge discharge(Prisoner prisoner, Date dateOfDecision,
            Date dateOfDischarge);
    
    /**
     * Add a new sentence shortening decision for the specified prisoner.
     * @param prisonFileNumber the file number of the prisoner for which the conviction decision is taken.
     * @param dateOfDecision the date of the conviction decision.
     * @param duration the duration of the shortening.
     * @return the newly created sentence shortening decision object.
     */
    public ShortenedSentence shortenSentence(String prisonFileNumber, Date dateOfDecision,
            Integer duration);
    
    /**
     * Add a new sentence shortening decision for the specified prisoner.
     * @param prisoner the prisoner for which the conviction decision is taken.
     * @param dateOfDecision the date of the conviction decision.
     * @param duration the duration of the shortening.
     * @return the newly created sentence shortening decision object.
     */
    public ShortenedSentence shortenSentence(Prisoner prisoner, Date dateOfDecision,
            Integer duration);
    
    /**
     * Retrieves all the decision concerning a prisoner.
     * @param prisonFileNumber the file number of the prisoner of which we want the existing decisions.
     * @return all the decision concerning a prisoner.
     */
    public List<JudicialDecision> findAll(String prisonFileNumber);
    
    /**
     * Retrieves all the decision concerning a prisoner.
     * @param prisoner the prisoner of which we want the existing decisions.
     * @return all the decision concerning a prisoner.
     */
    public List<JudicialDecision> findAll(Prisoner prisoner);
    
    /**
     * Retrieves the conviction decision that has been taken for the specified prisoner.
     * @param prisonFileNumber the file number of the prisoner of which the conviction decision is requested.
     * @return if a conviction decision has been taken for the specified prisoner, this conviction decision, null otherwise.
     */
    public Conviction findConviction(String prisonFileNumber);
    
    /**
     * Retrieves the conviction decision that has been taken for the specified prisoner.
     * @param prisoner the prisoner of which the conviction decision is requested.
     * @return if a conviction decision has been taken for the specified prisoner, this conviction decision, null otherwise.
     */
    public Conviction findConviction(Prisoner prisoner);
    
    /**
     * Retrieves the final discharge decision that has been taken for the specified prisoner.
     * @param prisonFileNumber the file number of the prisoner of which the final discharge decision is requested.
     * @return if a final discharge decision has been taken for the specified prisoner, this final discharge decision, null otherwise.
     */
    public FinalDischarge findDischarge(String prisonFileNumber);
    
    /**
     * Retrieves the final discharge decision that has been taken for the specified prisoner.
     * @param prisoner the prisoner of which the final discharge decision is requested.
     * @return if a final discharge decision has been taken for the specified prisoner, this final discharge decision, null otherwise.
     */
    public FinalDischarge findDischarge(Prisoner prisoner);
    
    /**
     * Retrieves the sentence shortening decisions that has been taken for the specified prisoner.
     * @param prisonFileNumber the file number of the prisoner of which the sentence shortening decisions are requested.
     * @return if sentence shortening decisions have been taken for the specified prisoner, these sentence shortening decisions, null otherwise.
     */
    public Set<ShortenedSentence> findShortening(String prisonFileNumber);
    
    /**
     * Retrieves the sentence shortening decisions that has been taken for the specified prisoner.
     * @param prisoner the prisoner of which the sentence shortening decisions are requested.
     * @return if sentence shortening decisions have been taken for the specified prisoner, these sentence shortening decisions, null otherwise.
     */
    public Set<ShortenedSentence> findShortening(Prisoner prisoner);
}
