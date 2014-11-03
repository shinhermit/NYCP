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

import entity.Motive;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import main.names.NYCPFaces;
import service.local.EntityRetriverLocal;
import service.local.MotiveLocal;

/**
 *
 * @author Josuah Aron
 * @author Émilien Arino
 */
@ManagedBean
@RequestScoped
public class MotiveManagedBean
{
    private Motive selected;
    
    @EJB
    private MotiveLocal motiveService;
    @EJB
    private EntityRetriverLocal entityRetriver;
    
    public MotiveManagedBean()
    {
        this.selected = new Motive();
    }
    
    private String getRequestParameter(String parameterName)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        
        Map<String,String> params =
                context.getExternalContext().getRequestParameterMap();
        
        return params.get(parameterName);
    }
    
    private void updateSelected()
    {
        String motiveNumber = this.getRequestParameter("motiveNumber");
        
        if(motiveNumber != null)
        {
            Motive entity = this.entityRetriver.findMotive(motiveNumber);
        
            this.selected.setMotiveNumber(entity.getMotiveNumber());
            this.selected.setMotiveLabel(entity.getMotiveLabel());
        }
    }
    
    public Motive getSelected()
    {
        return this.selected;
    }
    
    public String showCreateForm()
    {
        this.updateSelected();
        
        return NYCPFaces.Motive.CREATE;
    }
    
    public String showUpdateForm()
    {
        this.updateSelected();
        
        return NYCPFaces.Motive.EDIT;
    }
    
    public String create()
    {
        this.motiveService.createMotive(selected);
        
        return NYCPFaces.Motive.CREATE;
    }
    
    public String update()
    {
        this.updateSelected();
        
        Motive updated = this.motiveService.updateMotive(
                this.selected.getMotiveNumber(),
                this.selected.getMotiveLabel());
        
        this.selected.setMotiveNumber(updated.getMotiveNumber());
        this.selected.setMotiveLabel(updated.getMotiveLabel());
        
        return NYCPFaces.Motive.EDIT;
    }
    
    public String view()
    {
        this.updateSelected();
        
        return NYCPFaces.Motive.VIEW;
    }
    
    public String destroy()
    {
        this.updateSelected();
        this.motiveService.deleteMotive(this.selected.getMotiveNumber());
        
        return NYCPFaces.Motive.LIST;
    }
    
    public List<Motive> getMotiveList()
    {
        return this.entityRetriver.findAllMotives();
    }
}
