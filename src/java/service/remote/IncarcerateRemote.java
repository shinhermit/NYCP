/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service.remote;

import entity.CriminalCase;
import entity.Incarceration;
import entity.Motive;
import entity.Prisoner;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 * The remote interface for the services concerning the incarcerations.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Remote
public interface IncarcerateRemote
{
    /**
     * Creates a new incarceration, and provides the prisoner profile.
     * @param prisonFileNumber the desired prisoner file number.
     * @param givenName the name fo the prisoner.
     * @param surname the surname of the prisoner.
     * @param dateOfBirth the date of birth pf the new prisoner.
     * @param placeOfBirth the palce of birth pf the new prisoner.
     * @param dateOfIncarceration the date of the incarceration.
     * @param motiveNumber the number of the motive of the incaceration.
     * @param motiveLabel the label of the motive pf the incarceration.
     * @param criminalCaseNumber the number of the criminal case.
     * @param jurisdictionName the name of the original jurisdiction of the crime.
     * @param dateOfCriminalCase the date at which the crime was done.
     * @return the newly created prisoner profile.
     */
    public Prisoner incarcerate(String prisonFileNumber, String givenName, String surname, 
            Date dateOfBirth, String placeOfBirth, Date dateOfIncarceration,
            String motiveNumber, String motiveLabel, String criminalCaseNumber,
            String jurisdictionName, Date dateOfCriminalCase);
    
    /**
     * Adds an incarceration for the given prisoner.
     * @param prisoner the prisoner to incarcerate.
     * @param crime the criminal case for which the prisoner is incarcerated (main case).
     * @param motive the motive of the incarceration.
     * @param dateOfIncarceration the date of the incarceration.
     */
    public void incarcerate(Prisoner prisoner, CriminalCase crime, Motive motive,
            Date dateOfIncarceration);
    
    /**
     * Finds all the incarcerations registered.
     * @return all the incarcerations registered.
     */
    public List<Incarceration> findAll();
    
    /**
     * Finds the list of all incarcerations, those which are at
     * positions going from from start index to end index.
     * @param startIndex the start position of the look up.
     * @param endIndex the ending position of the look up.
     * @return the list of all incarcerations, those which are at
     * positions going from from start index to end index.
     */
    public List<Incarceration> findInRange(int startIndex, int endIndex);
}
