package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author milou
 */
@Entity
@Table(name="INCARCERATION")
public class Incarceration implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="PRISON_FILE_NUMBER")
    private String prisonFileNumber;

    @Column(name="CRIMINAL_CASE_NUMBER")
    private String criminalCaseNumber; 

    @Column(name="JURISDICTION_NAME")
    private String jurisdictionName;

    @Temporal(value=DATE)
    @Column(name="DATE_OF_INCARCERATION")
    private Date dateOfIncarceration;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "MOTIVE_NUMBER")
    private Motive motive;
    
    public Incarceration () {}
    
    public Incarceration (String prisonFileNumber)
    {
        this.prisonFileNumber = prisonFileNumber;
    }

    public String getPrisonFileNumber()
    {
        return prisonFileNumber;
    }

    public void setPrisonFileNumber(String prisonFileNumber)
    {
        this.prisonFileNumber = prisonFileNumber;
    }

    public String getCriminalCaseNumber()
    {
        return criminalCaseNumber;
    }

    public void setCriminalCaseNumber(String criminalCaseNumber)
    {
        this.criminalCaseNumber = criminalCaseNumber;
    }

    public String getJurisdictionName()
    {
        return jurisdictionName;
    }

    public void setJurisdictionName(String jurisdictionName)
    {
        this.jurisdictionName = jurisdictionName;
    }

    public Date getDateOfIncarceration()
    {
        return dateOfIncarceration;
    }

    public void setDateOfIncarceration(Date dateOfIncarceration)
    {
        this.dateOfIncarceration = dateOfIncarceration;
    }

    public Motive getMotive()
    {
        return motive;
    }

    public void setMotive(Motive motive)
    {
        this.motive = motive;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.prisonFileNumber);
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
            final Incarceration other = (Incarceration) obj;
            
            if (!Objects.equals(this.prisonFileNumber, other.prisonFileNumber))
            {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public String toString()
    {
        return "[" + getClass() + " : " + getPrisonFileNumber() + "] " + "Motive=>" + getMotive();
    }
}
