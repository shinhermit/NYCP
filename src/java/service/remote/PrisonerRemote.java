/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service.remote;

import entity.CriminalCase;
import entity.Prisoner;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

@Remote
public interface PrisonerRemote
{
    /**
     * Retourne le prisonier dont le prisonFileNumber est passé en paramètre.
     * @param prisonFileNumber
     * @return 
     */
    public Prisoner find(String prisonFileNumber);
    
    /**
     * Retourne les affaires associées au prisonFileNumber passé en paramètre.
     * @param prisonFileNumber
     * @return 
     */
    public Set<CriminalCase> getCriminialCasesAssocWithPFN(String prisonFileNumber);
    
    public List<Prisoner> findAll();
    public List<Prisoner> findRange(int[] range);
}
