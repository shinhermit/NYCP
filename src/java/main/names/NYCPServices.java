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

package main.names;

/**
 * Provides the names of the services (session) beans.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
public class NYCPServices
{
    /**
     * Just a namespace.
     */
    public static final class ejb
    {
        /** The name of the incarceration service bean. */
        public static final String INCARCERATION;
        
        /** The name of the judicial decision service bean. */
        public static final String JUDICIAL_DECISION;
        
        /** The name of the entity retriver service bean. */
        public static final String ENTITY_RETRIEVER;
        
        /** The name of the CUD services for motives. */
        public static final String MOTIVE;
        
        static
        {
            INCARCERATION = "ejb/IncarcerateService";
            
            JUDICIAL_DECISION = "ejb/JudicialDecisionService";
            
            ENTITY_RETRIEVER = "ejb/EntityRetrieverService";
            
            MOTIVE = "ejb/MotiveService";
        }
    }
}
