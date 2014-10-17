package main;

import entity.CriminalCase;
import entity.Incarceration;
import entity.Motive;
import entity.Prisoner;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import service.remote.EntityRetriverRemote;
import service.remote.IncarcerateRemote;

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
    
    private IncarcerateRemote incarcerateService;
    private EntityRetriverRemote entityRetriver;
    
    /**
     * Creates a new instance of IncarcerateManageBean
     */
    public IncarcerateManagedBean()
    {
        this.prisoner = new Prisoner();
        this.criminalCase = new CriminalCase();
        this.motive = new Motive();
    }
    
    private void lookUpIncarcerateService()
    {
        if(this.incarcerateService == null)
        {
            try
            {
                javax.naming.Context jndi_context = new javax.naming.InitialContext();
                
                this.incarcerateService =
                    (service.remote.IncarcerateRemote) jndi_context.lookup("ejb/IncarcerateService");
            }
            catch (NamingException ex)
            {
                Logger.getLogger(IncarcerateManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void lookUpEntityRetriver()
    {
        if(this.entityRetriver == null)
        {
            try
            {
                javax.naming.Context jndi_context = new javax.naming.InitialContext();
                
                this.entityRetriver =
                    (service.remote.EntityRetriverRemote) jndi_context.lookup("ejb/EntityRetriver");
            }
            catch (NamingException ex)
            {
                Logger.getLogger(IncarcerateManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    public String incarcerate ()
    {
        this.lookUpIncarcerateService();
        
        incarcerateService.incarcerate(this.prisoner, this.criminalCase,
                this.motive, this.dateOfIncarceration);
        
        return "Create";
    }
    
    public String ViewIncarceration()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        
        Map<String,String> params =
                context.getExternalContext().getRequestParameterMap();
        
        String prisonFileNumber =  params.get("prisonFileNumber");
        
        this.lookUpEntityRetriver();
        
        Incarceration incarceration = entityRetriver.findIncarceration(prisonFileNumber);
        
        this.prisoner = entityRetriver.findPrisoner(prisonFileNumber);
        this.criminalCase = entityRetriver.findCriminalCase(incarceration.getCriminalCaseNumber(),
                incarceration.getJurisdictionName());
        this.motive = incarceration.getMotive();
        this.dateOfIncarceration = incarceration.getDateOfIncarceration();

        return "View";
    }
    
    public List<Incarceration> getItems() 
    {
        this.lookUpIncarcerateService();
        
        return incarcerateService.findAll();
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