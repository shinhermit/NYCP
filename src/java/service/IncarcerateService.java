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
import entity.primaryKeys.CriminalCasePK;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    
//    @Override
//    public Prisoner incarcerate(String fileNumber, String name, String surname,
//            Date dateOfBirth, String placeOfBirth, Date dateOfIncarceration)
//    {
//        Prisoner prisoner = new Prisoner();
//        
//        prisoner.setGivenName(name);
//        prisoner.setSurname(surname);
//        prisoner.setDateOfBirth(dateOfBirth);
//        prisoner.setPlaceOfBirth(placeOfBirth);
//        prisoner.setPrisonFileNumber(fileNumber);
//        
//        Motive motive = new Motive();
//        motive.setMotiveLabel("label "+fileNumber);
//        motive.setMotiveNumber(fileNumber+1);
//        
//        // look in data base if a case with the file number exists
//        CriminalCase criminalCase = new CriminalCase(fileNumber+2, "j"+fileNumber);
//        if(criminalCase.getCriminalCasePK().getCriminalCaseNumber() == null)
//            throw new IllegalStateException();
//        System.err.println("\n\n\n\n"+criminalCase.getCriminalCasePK().getCriminalCaseNumber()+"\n\n\n\n");
//                
//        
//        criminalCase.setDateOfCriminalCase(dateOfIncarceration); // Not true
//        
//        PrisonerCriminalCase pcc = new PrisonerCriminalCase(fileNumber,
//                criminalCase.getCriminalCasePK().getCriminalCaseNumber(), "j"+fileNumber);
//        pcc.setCriminalCase(criminalCase);
//        pcc.setPrisoner(prisoner);
//        
//        Incarceration incarceration = new Incarceration(fileNumber);
//        
//        incarceration.setPrisonFileNumber(fileNumber);
//        incarceration.setPrisonerCriminalCase(pcc);
//        incarceration.setDateOfIncarceration(dateOfIncarceration);
//        incarceration.setMotiveNumber(motive);
//        
//        assert(entityManager != null);
//        
//        entityManager.persist(motive);
//        entityManager.persist(criminalCase);
//        entityManager.persist(prisoner);
//        entityManager.persist(pcc);
//        entityManager.persist(incarceration);
//        
//        return prisoner;
//    }
    
    @Override
    public Prisoner incarcerate(String prisonFileNumber, String givenName, String surname, 
            Date dateOfBirth, String placeOfBirth, Date dateOfIncarceration,
            String motiveNumber, String motiveLabel, String criminalCaseNumber,
            String jurisdictionName, Date dateOfCriminalCase){

        System.err.println("##########################");
        System.err.println("####incarcerat service####");
        System.err.println("##########################");
        //Entité criminal case
        CriminalCase cc = new CriminalCase(criminalCaseNumber, jurisdictionName);
        cc.setDateOfCriminalCase(dateOfCriminalCase);
        //Entité prisoner
        Prisoner p = new Prisoner(prisonFileNumber);
        
        p.setGivenName(givenName);
        p.setSurname(surname);
        p.setDateOfBirth(dateOfBirth);
        p.setPlaceOfBirth(placeOfBirth);

        //On associe le criminal case au prisoner
        p.addCriminalCase(cc);
        
        //Entité motive
        Motive m = new Motive(motiveNumber);
        m.setMotiveLabel(motiveLabel);
        
        //Entité incarceration
        Incarceration i = new Incarceration(prisonFileNumber);

        i.setCriminalCaseNumber(criminalCaseNumber);
        i.setJurisdictionName(jurisdictionName);
        i.setMotive(m);
        //i.setMotiveNumber(motiveNumber);
        i.setDateOfIncarceration(dateOfIncarceration);
        
        assert(entityManager != null);
        
        entityManager.persist(p);
        entityManager.flush();
        entityManager.persist(m);
        entityManager.persist(i);
        
        return p;
    }
    
    public Incarceration getIncarceration (String prisonFileNumber) {
        assert(entityManager != null);
        System.out.println("entityManager is not null");
        Incarceration i = entityManager.find(Incarceration.class, prisonFileNumber);
        
        return i;
    }

    @Override
    public Map<String, Object> getIncarcerateData(String prisonFileNumber) {
        assert(entityManager != null);
        System.out.println("entityManager is not null");
        Incarceration i = entityManager.find(Incarceration.class, prisonFileNumber);
        Prisoner p = entityManager.find(Prisoner.class, prisonFileNumber);
        CriminalCasePK ccpk = new CriminalCasePK(i.getCriminalCaseNumber(), i.getJurisdictionName());
        CriminalCase cc = entityManager.find(CriminalCase.class, ccpk);
        p.addCriminalCase(cc);
        Map<String, Object> iData = new HashMap();
        iData.put("prisonFileNumber", prisonFileNumber);
        iData.put("criminalCaseNumber", i.getCriminalCaseNumber());
        iData.put("jurisdictionName", i.getJurisdictionName());
        iData.put("dateOfIncarceration", i.getDateOfIncarceration());
        iData.put("motiveNumber", i.getMotiveNumber());
        iData.put("motiveLabel", i.getMotiveLabel());
        iData.put("givenName", p.getGivenName());
        iData.put("surname", p.getSurname());
        iData.put("dateOfBirth", p.getDateOfBirth());
        iData.put("placeOfBirth", p.getPlaceOfBirth());

        return iData;
    }
    
    
}
