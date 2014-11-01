package service;

import entity.CriminalCase;
import entity.Incarceration;
import entity.Motive;
import entity.Prisoner;
import java.util.Date;
import java.util.List;
import service.local.IncarcerateLocal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The service provider for the incarcerations.
 * 
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Stateless(mappedName = "ejb/IncarcerateService")
public class IncarcerateService implements IncarcerateLocal
{
    @PersistenceContext(unitName = "NYCPPU")
    private EntityManager entityManager;
        
    @Override
    public Prisoner incarcerate(String prisonFileNumber, String givenName, String surname, 
            Date dateOfBirth, String placeOfBirth, Date dateOfIncarceration,
            String motiveNumber, String motiveLabel, String criminalCaseNumber,
            String jurisdictionName, Date dateOfCriminalCase)
    {
        // Create a criminal case
        CriminalCase crime = new CriminalCase(criminalCaseNumber, jurisdictionName);
        crime.setDateOfCriminalCase(dateOfCriminalCase);
        
        //Create a prisoner
        Prisoner prisoner = new Prisoner(prisonFileNumber);
        
        prisoner.setGivenName(givenName);
        prisoner.setSurname(surname);
        prisoner.setDateOfBirth(dateOfBirth);
        prisoner.setPlaceOfBirth(placeOfBirth);

        // Associate prisoner and criminal case
        prisoner.addCriminalCase(crime);
        
        //Create an incarceration motive
        Motive motive = new Motive(motiveNumber);
        motive.setMotiveLabel(motiveLabel);
        
        // Create an incarceration
        Incarceration incarceration = new Incarceration(prisonFileNumber);

        incarceration.setCriminalCaseNumber(criminalCaseNumber);
        incarceration.setJurisdictionName(jurisdictionName);
        incarceration.setMotive(motive);
        
        incarceration.setDateOfIncarceration(dateOfIncarceration);
        
        assert(entityManager != null);
        
        entityManager.persist(prisoner);
        entityManager.flush();   //TODO: what's this useful for ??????????????????
        entityManager.persist(motive);
        entityManager.persist(incarceration);
        
        return prisoner;
    }
    
    @Override
    public void incarcerate(Prisoner prisoner, CriminalCase crime, Motive motive,
            Date dateOfIncarceration)
    {
        incarcerate(prisoner.getPrisonFileNumber(), prisoner.getGivenName(),
                prisoner.getSurname(), prisoner.getDateOfBirth(),
                prisoner.getPlaceOfBirth(), dateOfIncarceration,
                motive.getMotiveNumber(), motive.getMotiveLabel(),
                crime.getCriminalCasePK().getCriminalCaseNumber(),
                crime.getCriminalCasePK().getJurisdictionName(),
                crime.getDateOfCriminalCase());
    }

    @Override
    public List<Incarceration> findAll()
    {
        javax.persistence.criteria.CriteriaQuery criteriaQuery =
                entityManager.getCriteriaBuilder().createQuery();
        
        criteriaQuery.select( criteriaQuery.from(Incarceration.class) );
        
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Incarceration> findInRange(int startIndex, int endIndex)
    {
        javax.persistence.criteria.CriteriaQuery criteriaQuery =
                entityManager.getCriteriaBuilder().createQuery();
        
        criteriaQuery.select( criteriaQuery.from(Incarceration.class) );
        
        javax.persistence.Query query = entityManager.createQuery(criteriaQuery);
        
        query.setMaxResults(endIndex - startIndex + 1);
        query.setFirstResult(startIndex);
        
        return query.getResultList();
    }
}
    