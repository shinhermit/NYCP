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
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author josuah
 */
@Remote
public interface JudicialDecisionRemote
{
    /**
     * Add a new conviction decision for the specified prisoner.
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
     * @param prisoner the prisoner for which the conviction decision is taken.
     * @param dateOfDecision the date of the conviction decision.
     * @param dateOfDischarge the date at which the discharge must be applied.
     * @return the newly created discharge decision object.
     */
    public FinalDischarge discharge(Prisoner prisoner, Date dateOfDecision,
            Date dateOfDischarge);
    
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
     * @param prisoner the prisoner of which we want the existing decisions.
     * @return all the decision concerning a prisoner.
     */
    public Set<JudicialDecision> findFor(Prisoner prisoner);
}
