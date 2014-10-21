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

package main;

import entity.Prisoner;
import entity.primaryKeys.JudicialDecisionPK;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;
import main.names.NYCPFaces;
import main.names.NYCPServices;
import service.remote.EntityRetriverRemote;
import service.remote.JudicialDecisionRemote;

/**
 *
 * @author Josuah Aron
 * @author Émilien Arino
 */
@ManagedBean
@RequestScoped
public class JudicialDecisionManagedBean
{
    private Prisoner prisoner;
    private JudicialDecisionPK decisionPK;
    private Integer duration;
    private Date dateOfDischarge;
    
    private JudicialDecisionRemote decisionService;
    private EntityRetriverRemote entityRetriver;
    
    /**
     * Creates a new instance of JudicialDecisionManagedBean
     */
    public JudicialDecisionManagedBean()
    {
        this.prisoner = new Prisoner();
        this.decisionPK = new JudicialDecisionPK();
        this.duration = new Integer(0);
        this.dateOfDischarge = new Date();
    }
    
    private void lookUpIncarcerateService()
    {
        if(this.decisionService == null)
        {
            try
            {
                javax.naming.Context jndi_context = new javax.naming.InitialContext();
                
                this.decisionService = (service.remote.JudicialDecisionRemote)
                        jndi_context.lookup(NYCPServices.ejb.JUDICIAL_DECISION);
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
                    (service.remote.EntityRetriverRemote) jndi_context.lookup(NYCPServices.ejb.ENTITY_RETRIEVER);
            }
            catch (NamingException ex)
            {
                Logger.getLogger(IncarcerateManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String getRequestParameter(String parameterName)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        
        Map<String,String> params =
                context.getExternalContext().getRequestParameterMap();
        
        return params.get(parameterName);
    }
    
    public String showConvictForm()
    {
        this.lookUpEntityRetriver();
        
        this.prisoner = entityRetriver.findPrisoner(this.getRequestParameter("prisonFileNumber"));
        
        return NYCPFaces.JudicialDecision.CONVICT;
    }
    
    public String showDischargeForm()
    {
        this.lookUpEntityRetriver();
        
        this.prisoner = entityRetriver.findPrisoner(this.getRequestParameter("prisonFileNumber"));
        
        return NYCPFaces.JudicialDecision.DISCHARGE;
    }
    
    public String showShorteningForm()
    {
        this.lookUpEntityRetriver();
        
        this.prisoner = entityRetriver.findPrisoner(this.getRequestParameter("prisonFileNumber"));
        
        return NYCPFaces.JudicialDecision.SHORTEN;
    }
    
    public String convict()
    {
        this.lookUpIncarcerateService();
        
        this.decisionService.convict(this.prisoner.getPrisonFileNumber(),
                this.decisionPK.getDateOfDecision(), this.duration);
        
        return NYCPFaces.JudicialDecision.CONVICT;
    }
    
    public String discharge()
    {
        this.lookUpIncarcerateService();
        
        this.decisionService.discharge(this.prisoner.getPrisonFileNumber(),
                this.decisionPK.getDateOfDecision(), this.dateOfDischarge);
        
        return NYCPFaces.JudicialDecision.DISCHARGE;
    }
    
    public String shortenSentence()
    {
        this.lookUpIncarcerateService();
        
        this.decisionService.shortenSentence(this.prisoner.getPrisonFileNumber(),
                this.decisionPK.getDateOfDecision(), this.duration);
        
        return NYCPFaces.JudicialDecision.SHORTEN;
    }

    public Prisoner getPrisoner()
    {
        return prisoner;
    }

    public Prisoner getSelectedPrisoner()
    {
        return prisoner;
    }
    
    public List<Prisoner> getPrisonerList()
    {
        this.lookUpEntityRetriver();
        
        return this.entityRetriver.findAllPrisoners();
    }

    public void setPrisoner(Prisoner prisoner)
    {
        this.prisoner = prisoner;
    }

    public JudicialDecisionPK getDecisionPK()
    {
        return decisionPK;
    }

    public void setDecisionPK(JudicialDecisionPK decisionPK)
    {
        this.decisionPK = decisionPK;
    }

    public Integer getDuration()
    {
        return duration;
    }

    public void setDuration(Integer duration)
    {
        this.duration = duration;
    }

    public Date getDateOfDischarge()
    {
        return dateOfDischarge;
    }

    public void setDateOfDischarge(Date dateOfDischarge)
    {
        this.dateOfDischarge = dateOfDischarge;
    }
}
