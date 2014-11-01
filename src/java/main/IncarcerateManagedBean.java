package main;

import main.names.NYCPFaces;
import entity.CriminalCase;
import entity.Incarceration;
import entity.Motive;
import entity.Prisoner;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import service.local.EntityRetriverLocal;
import service.local.IncarcerateLocal;

/**
 *
 * @author milou
 */
@ManagedBean
@RequestScoped
public class IncarcerateManagedBean
{
    private Prisoner prisoner;
    private CriminalCase criminalCase;
    private Motive motive;
    private Date dateOfIncarceration;
    
    private List<Incarceration> items;
    
    @EJB
    private IncarcerateLocal incarcerateService;
    @EJB
    private EntityRetriverLocal entityRetriver;
    
    /**
     * Creates a new instance of IncarcerateManageBean
     */
    public IncarcerateManagedBean()
    {
        this.prisoner = new Prisoner();
        this.criminalCase = new CriminalCase();
        this.motive = new Motive();
    }
        
    public String incarcerate ()
    {
        this.incarcerateService.incarcerate(this.prisoner, this.criminalCase,
                this.motive, this.dateOfIncarceration);
        
        return NYCPFaces.Incarceration.CREATE;
    }
    
    public String ViewIncarceration()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        
        Map<String,String> params =
                context.getExternalContext().getRequestParameterMap();
        
        String prisonFileNumber =  params.get("prisonFileNumber");
        
        Incarceration incarceration = entityRetriver.findIncarceration(prisonFileNumber);
        
        this.prisoner = entityRetriver.findPrisoner(prisonFileNumber);
        this.criminalCase = entityRetriver.findCriminalCase(incarceration.getCriminalCaseNumber(),
                incarceration.getJurisdictionName());
        this.motive = incarceration.getMotive();
        this.dateOfIncarceration = incarceration.getDateOfIncarceration();

        return NYCPFaces.Incarceration.VIEW;
    }
    
    public List<Prisoner> getRemands()
    {
        return this.entityRetriver.findPrisonersOnRemand();
    }
    
    public List<Incarceration> getItems() 
    {
        return entityRetriver.findAllIncarcerations();
    }

    public Prisoner getPrisoner()
    {
        return prisoner;
    }

    public void setPrisoner(Prisoner prisoner)
    {
        this.prisoner = prisoner;
    }

    public CriminalCase getCriminalCase()
    {
        return criminalCase;
    }

    public void setCriminalCase(CriminalCase criminalCase)
    {
        this.criminalCase = criminalCase;
    }

    public Motive getMotive()
    {
        return motive;
    }

    public void setMotive(Motive motive)
    {
        this.motive = motive;
    }

    public Date getDateOfIncarceration()
    {
        return dateOfIncarceration;
    }

    public void setDateOfIncarceration(Date dateOfIncarceration)
    {
        this.dateOfIncarceration = dateOfIncarceration;
    }
}