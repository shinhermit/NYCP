/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author milou
 */
@Entity
@Table(name = "MOTIVE")
@NamedQueries({
    @NamedQuery(name = "Motive.findAll", query = "SELECT p FROM Prisoner p")})
public class Motive implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "MOTIVE_NUMBER")
    private String motiveNumber;

    @Column(name = "MOTIVE_LABEL")
    private String motiveLabel;
    
    @OneToMany(mappedBy = "motive", fetch = FetchType.EAGER)
    private Set<Incarceration> incarcerations;
    
    public Motive (String motiveNumber, String motiveLabel)
    {
        this.motiveNumber = motiveNumber;
        this.motiveLabel = motiveLabel;
    }
    
    public Motive ()
    {
        this("", "");
    }
    
    public Motive (String motiveNumber)
    {
        this(motiveNumber, "");
    }
    
    public String getMotiveNumber()
    {
        return motiveNumber;
    }

    public void setMotiveNumber(String motiveNumber)
    {
        this.motiveNumber = motiveNumber;
    }

    /**
     * @return the motiveLabel
     */
    public String getMotiveLabel()
    {
        return motiveLabel;
    }

    /**
     * @param motiveLabel the motiveLabel to set
     */
    public void setMotiveLabel(String motiveLabel)
    {
        this.motiveLabel = motiveLabel;
    }

    public Set<Incarceration> getIncarcerations()
    {
        return this.incarcerations != null ?
                this.incarcerations :
                new HashSet<Incarceration>();
    }

    public void setIncarcerations(Set<Incarceration> incarcerations)
    {
        this.incarcerations = incarcerations;
    }
    
    public void addIncarceration (Incarceration i)
    {
        if (incarcerations == null) {
            incarcerations = new HashSet();
        }
        
        incarcerations.add(i);
    }
    
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.motiveNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        else if (getClass() != obj.getClass())
        {
            return false;
        }
        else
        {
            final Motive other = (Motive) obj;
            
            if (!Objects.equals(this.motiveNumber, other.motiveNumber))
            {
                return false;
            }
        }
        
        return true;
    }
}
