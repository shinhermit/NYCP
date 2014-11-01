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
import entity.FinalDischarge;
import entity.JudicialDecision;
import entity.Prisoner;
import entity.ShortenedSentence;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 * The remote interface for the services concerning the judicial decisions.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Local
public interface JudicialDecisionLocal
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
}
