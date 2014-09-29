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
        Prisoner incarcerated = new Prisoner();
        
        incarcerated.setGivenName(name);
        incarcerated.setSurname(surname);
        incarcerated.setDateOfBirth(dateOfBirth);
        incarcerated.setPlaceOfBirth(placeOfBirth);
        incarcerated.setPrisonFileNumber(fileNumber);
//        incarcerated.setPrisonerCriminalCaseCollection(null);
        
        Incarceration incarceration = new Incarceration();
        
        incarceration.setDateOfIncarceration(dateOfIncarceration);
        incarceration.setPrisonFileNumber(fileNumber);
//        incarceration.setMotiveNumber(null);
        
        Motive motive = new Motive();
        motive.setMotiveLabel("Fake motive label");
        motive.setMotiveNumber("FakeNumber");
//        motive.setIncarcerationCollection(null);
        
        // look in data base if a case with the file number exists
        CriminalCase criminalCase = new CriminalCase();
        
        criminalCase.setDateOfCriminalCase(dateOfIncarceration); // Not true
//        criminalCase.setPrisonerCriminalCaseCollection(null);
        
        PrisonerCriminalCase pcc = new PrisonerCriminalCase();
        pcc.setCriminalCase(criminalCase);
        pcc.setPrisoner(incarcerated);
        
        assert(entityManager != null);
        
        entityManager.getTransaction().begin();
        entityManager.persist(motive);
        entityManager.persist(incarceration);
        entityManager.persist(incarcerated);
        entityManager.persist(criminalCase);
        entityManager.persist(pcc);
        entityManager.getTransaction().commit();
        
        return incarcerated;
    }
    
}
