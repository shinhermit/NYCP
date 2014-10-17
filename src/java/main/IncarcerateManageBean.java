/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import service.remote.IncarcerateRemote;

/**
 *
 * @author milou
 */
@ManagedBean
@RequestScoped
public class IncarcerateManageBean {
    
    private String prisonFileNumber;
    private String criminalCaseNumber;
    private String jurisdictionName;
    @Temporal(DATE)
    private Date dateOfIncarceration;
    private String motiveNumber;
    private String motiveLabel;
    private String givenName;
    private String surname;
    @Temporal(DATE)
    private Date dateOfBirth;
    private String placeOfBirth;
    
    private IncarcerateRemote incarcerateService = null;
    
    /**
     * Creates a new instance of IncarcerateManageBean
     */
    public IncarcerateManageBean() {
        
    }

    private void reset () {
        setPrisonFileNumber("");
        setCriminalCaseNumber("");
        setJurisdictionName("");
        setDateOfIncarceration(null);
        setMotiveNumber("");
        setMotiveLabel("");
        setGivenName("");
        setSurname("");
        setDateOfBirth(null);
        setPlaceOfBirth("");
    }
    
    public String prepareIncarcerate() {
        reset();
        
        return "Create";
    }
        
    public String incarcerate () {
        javax.naming.Context jndi_context = null;
        
        try {
            jndi_context = new javax.naming.InitialContext();
            incarcerateService =
                (service.remote.IncarcerateRemote) jndi_context.lookup("ejb/IncarcerateService");
        } catch (NamingException ex) {
            Logger.getLogger(IncarcerateManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        incarcerateService.incarcerate(
                prisonFileNumber, givenName, surname, dateOfBirth, placeOfBirth, 
                dateOfIncarceration, motiveNumber, motiveLabel, 
                criminalCaseNumber, jurisdictionName, dateOfBirth);
        
        return prepareIncarcerate();
    }
    
    public String prepareView (String prisonFileNumber) {
        javax.naming.Context jndi_context = null;
        
        try {
            jndi_context = new javax.naming.InitialContext();
            incarcerateService =
                (service.remote.IncarcerateRemote) jndi_context.lookup("ejb/IncarcerateService");
        } catch (NamingException ex) {
            Logger.getLogger(IncarcerateManageBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        Map<String, String> iData = incarcerateService.getIncarcerateData(prisonFileNumber);
        setPrisonFileNumber(iData.get("prisonFileNumber"));
        
        return "View";
    }
    
    public void list () {
        
    }
    
    public String getPrisonFileNumber() {
        return prisonFileNumber;
    }

    public void setPrisonFileNumber(String prisonFileNumber) {
        this.prisonFileNumber = prisonFileNumber;
    }

    public String getCriminalCaseNumber() {
        return criminalCaseNumber;
    }

    public void setCriminalCaseNumber(String criminalCaseNumber) {
        this.criminalCaseNumber = criminalCaseNumber;
    }

    public String getJurisdictionName() {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName) {
        this.jurisdictionName = jurisdictionName;
    }

    public Date getDateOfIncarceration() {
        return dateOfIncarceration;
    }

    public void setDateOfIncarceration(Date dateOfIncarceration) {
        this.dateOfIncarceration = dateOfIncarceration;
    }

    public String getMotiveNumber() {
        return motiveNumber;
    }

    public void setMotiveNumber(String motiveNumber) {
        this.motiveNumber = motiveNumber;
    }

    public String getMotiveLabel() {
        return motiveLabel;
    }

    public void setMotiveLabel(String motiveLabel) {
        this.motiveLabel = motiveLabel;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }
    
    
    
}
