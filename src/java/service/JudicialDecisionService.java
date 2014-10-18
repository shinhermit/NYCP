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

import entity.Conviction;
import entity.FinalDischarge;
import entity.JudicialDecision;
import entity.Prisoner;
import entity.ShortenedSentence;
import java.util.Date;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import service.remote.JudicialDecisionRemote;

/**
 *
 * @author josuah
 */
@Stateless
@LocalBean
public class JudicialDecisionService implements JudicialDecisionRemote
{
    @Override
    public Conviction convict(Prisoner prisoner, Date dateOfDecision,
            Integer duration)
    {
        return null;
    }

    @Override
    public FinalDischarge discharge(Prisoner prisoner, Date dateOfDecision,
            Date dateOfDischarge)
    {
        return null;
    }

    @Override
    public ShortenedSentence shortenSentence(Prisoner prisoner,
            Date dateOfDecision, Integer duration)
    {
        return null;
    }

    @Override
    public Set<JudicialDecision> findFor(Prisoner prisoner)
    {
        return null;
    }
}
