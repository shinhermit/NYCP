/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entity.CriminalCase;
import entity.Incarceration;
import entity.Motive;
import entity.Prisoner;
import entity.PrisonerCriminalCase;
import java.util.Date;
import service.remote.IncarcerateRemote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author josuah
 */
@Stateless(mappedName = "ejb/IncarcerateService")
public class IncarcerateService implements IncarcerateRemote
{
    @PersistenceContext(unitName = "NYCPPU")
    private EntityManager entityManager;
    
    @Override
    public Prisoner incarcerate(String fileNumber, String name, String surname,
            Date dateOfBirth, String placeOfBirth, Date dateOfIncarceration)
    {
        Prisoner prisoner = new Prisoner();
        
        prisoner.setGivenName(name);
        prisoner.setSurname(surname);
        prisoner.setDateOfBirth(dateOfBirth);
        prisoner.setPlaceOfBirth(placeOfBirth);
        prisoner.setPrisonFileNumber(fileNumber);
//        prisoner.setPrisonerCriminalCaseCollection(null);
        
        Motive motive = new Motive();
        motive.setMotiveLabel("Fake motive label");
        motive.setMotiveNumber("1213");
//        motive.setIncarcerationCollection(null);
        
        Incarceration incarceration = new Incarceration(fileNumber);
        
        incarceration.setDateOfIncarceration(dateOfIncarceration);
        incarceration.setMotiveNumber(motive);
        
        // look in data base if a case with the file number exists
        CriminalCase criminalCase = new CriminalCase("1416", "fake_juridiction_name");
        
        criminalCase.setDateOfCriminalCase(dateOfIncarceration); // Not true
//        criminalCase.setPrisonerCriminalCaseCollection(null);
        
        PrisonerCriminalCase pcc = new PrisonerCriminalCase(fileNumber, "5678", "fake_juridiction_name");
        pcc.setCriminalCase(criminalCase);
        pcc.setPrisoner(prisoner);
        
        assert(entityManager != null);
        
//        entityManager.persist(motive);
//        entityManager.persist(criminalCase);
        entityManager.persist(prisoner);
//        entityManager.persist(pcc);
//        entityManager.persist(incarceration);
        
        return prisoner;
    }
}
