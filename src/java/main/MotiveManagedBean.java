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
    
    public Motive getSelected()
    {
        return this.selected;
    }
    
    public String create()
    {
        this.motiveService.createMotive(selected);
        
        return NYCPFaces.Motive.CREATE;
    }
    
    public String showUpdateForm()
    {
        String motiveNumber = this.getRequestParameter("motiveNumber");
        
        this.selected = this.entityRetriver.findMotive(motiveNumber);
        
        return NYCPFaces.Motive.EDIT;
    }
    
    public String update()
    {
        this.selected = this.motiveService.updateMotive(this.selected.getMotiveNumber(),
                this.selected.getMotiveLabel());
        
        return NYCPFaces.Motive.EDIT;
    }
    
    public String view()
    {
        String motiveNumber = this.getRequestParameter("motiveNumber");
        this.selected = this.entityRetriver.findMotive(motiveNumber);
        
        return NYCPFaces.Motive.VIEW;
    }
    
    public String destroy()
    {
        String motiveNumber = this.getRequestParameter("motiveNumber");
        this.motiveService.deleteMotive(motiveNumber);
        
        return NYCPFaces.Motive.LIST;
    }
    
    public List<Motive> getMotiveList()
    {
        return this.entityRetriver.findAllMotives();
    }
}
