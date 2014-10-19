/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Prisoner;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author milou
 */
@ManagedBean
@RequestScoped
public class JudicialDecisionManagedBean {
    private Prisoner current;
    
    /**
     * Creates a new instance of JudicialDecisionManagedBean
     */
    public JudicialDecisionManagedBean() {
    }
    
    public String prepareCreate () {
        
        return "Create";
    }
    
    private List<Prisoner>  prisoners;
    
    private Map<String, Object> prisonerSelect;
    
    public List<Prisoner> getPrisoners () {
        javax.naming.Context jndi_context = null;
        
        
//        prisoners = prisonerService.findAll();
        
        return prisoners;
    }
    
    public Map<String, Object> getPrisonerSelect () {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Map<String, Object> prisonerSelect = new HashMap();
        
        for (Prisoner p : getPrisoners()) {
            prisonerSelect.put(p.getGivenName() + " " + p.getSurname() + "(" + df.format(p .getDateOfBirth()) + ")", p);
        }
        
        return prisonerSelect;
        
    }

    public Prisoner getCurrent() {
        return current;
    }

    public void setCurrent(Prisoner current) {
        this.current = current;
    }
    
}
