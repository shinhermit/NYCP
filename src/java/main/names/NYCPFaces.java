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
 * Provides the differents faces of the application.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
public class NYCPFaces
{
    /**
     * Provides the name of the faces of the incarceration service.
     */
    public static class Incarceration
    {
        /** The <i>list incarceration</i> face. */
        public static final String LIST;
        
        /** The <i>view incarceration</i> face. */
        public static final String VIEW;
        
        /** The <i>create new incarceration</i> face. */
        public static final String CREATE;
        
        static
        {
            LIST = "/incarcerate/List";
            VIEW = "/incarcerate/View";
            CREATE = "/incarcerate/Create";
        }
    }
    
    /**
     * Provides the name of the faces of the judicial decisions service.
     */
    public static class JudicialDecision
    {
        /** The <i>create new incarceration</i> face. */
        public static final String CONVICT;
        
        /** The <i>create new incarceration</i> face. */
        public static final String DISCHARGE;
        
        /** The <i>create new incarceration</i> face. */
        public static final String SHORTEN;
        
        static
        {
            CONVICT = "/judicialDecision/Convict";
            DISCHARGE = "/judicialDecision/Discharge";
            SHORTEN = "/judicialDecision/Shorten";
        }
    }
    
    /**
     * Provides the name of the faces of the motives CUD services.
     */
    public static class Motive
    {
        /** The <i>create new motive</i> face. */
        public static final String CREATE;
        
        /** The <i>edit motive</i> face. */
        public static final String EDIT;
        
        /** The <i>list motives</i> face. */
        public static final String LIST;
        
        /** The <i>view motive</i> face. */
        public static final String VIEW;
        
        static
        {
            CREATE = "/motive/Create";
            EDIT = "/motive/Edit";
            LIST = "/motive/List";
            VIEW = "/motive/View";
        }
    }
}
