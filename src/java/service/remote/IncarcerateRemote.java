/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service.remote;

import entity.Incarceration;
import entity.Prisoner;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

@Remote
public interface IncarcerateRemote
{
    public Prisoner incarcerate(String prisonFileNumber, String givenName, String surname, 
            Date dateOfBirth, String placeOfBirth, Date dateOfIncarceration,
            String motiveNumber, String motiveLabel, String criminalCaseNumber,
            String jurisdictionName, Date dateOfCriminalCase);
    
    public Map<String, Object> getIncarcerateData(String prisonFileNumber);
    
    public List<Incarceration> findAll();
    
    public List<Incarceration> findRange(int[] range);
}
