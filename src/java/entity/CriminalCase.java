package entity;

import entity.primaryKeys.CriminalCasePK;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Josuah Aron
 * @author Émilien Arino
 */
@Entity
@Table(name = "CRIMINAL_CASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CriminalCase.findAll", query = "SELECT c FROM CriminalCase c"),
    @NamedQuery(name = "CriminalCase.findByCriminalCaseNumber", query = "SELECT c FROM CriminalCase c WHERE c.criminalCasePK.criminalCaseNumber = :criminalCaseNumber"),
    @NamedQuery(name = "CriminalCase.findByJurisdictionName", query = "SELECT c FROM CriminalCase c WHERE c.criminalCasePK.jurisdictionName = :jurisdictionName"),
    @NamedQuery(name = "CriminalCase.findByDateOfCriminalCase", query = "SELECT c FROM CriminalCase c WHERE c.dateOfCriminalCase = :dateOfCriminalCase")})
public class CriminalCase implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected CriminalCasePK criminalCasePK;
    
    @Column(name = "DATE_OF_CRIMINAL_CASE")
    @Temporal(TemporalType.DATE)
    private Date dateOfCriminalCase;
    
    @ManyToMany(mappedBy = "criminalCaseSet", fetch = FetchType.EAGER)
    private Set<Prisoner> prisonersInvolved;
    
    public CriminalCase()
    {
        this.criminalCasePK = new CriminalCasePK();
    }

    public CriminalCase(CriminalCasePK criminalCasePK)
    {
        this.criminalCasePK = criminalCasePK;
    }

    public CriminalCase(String criminalCaseNumber, String jurisdictionName)
    {
        this.criminalCasePK = new CriminalCasePK(criminalCaseNumber, jurisdictionName);
    }

    public CriminalCasePK getCriminalCasePK()
    {
        return criminalCasePK;
    }

    public void setCriminalCasePK(CriminalCasePK criminalCasePK)
    {
        this.criminalCasePK = criminalCasePK;
    }

    public Date getDateOfCriminalCase()
    {
        return dateOfCriminalCase;
    }

    public void setDateOfCriminalCase(Date dateOfCriminalCase)
    {
        this.dateOfCriminalCase = dateOfCriminalCase;
    }

    public String getCriminalCaseNumber()
    {
        return this.criminalCasePK.getCriminalCaseNumber();
    }

    public void setCriminalCaseNumber(String criminalCaseNumber)
    {
        this.criminalCasePK.setCriminalCaseNumber(criminalCaseNumber);
    }

    public String getJurisdictionName()
    {
        return this.criminalCasePK.getJurisdictionName();
    }

    public void setJurisdictionName(String jurisdictionName)
    {
        this.criminalCasePK.setJurisdictionName(jurisdictionName);
    }

    /**
     * @return the prisoners
     */
    public Set<Prisoner> getPrisonersInvolved()
    {
        return prisonersInvolved;
    }

    /**
     * @param prisonersInvolved the prisoners to set
     */
    public void setPrisonersInvolved(Set<Prisoner> prisonersInvolved)
    {
        this.prisonersInvolved = prisonersInvolved;
    }
    
    /**
     * Sets the given prisoner as involed in this criminal case.
     * @param prisoner the prisoner which is to be set the given as involed in this criminal case.
     */
    public void addInvolvedPrisoner(Prisoner prisoner)
    {
        if(this.prisonersInvolved == null)
        {
            this.prisonersInvolved = new HashSet<>();
        }
        
        this.prisonersInvolved.add(prisoner);
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (criminalCasePK != null ? criminalCasePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof CriminalCase))
        {
            return false;
        }
        else
        {
            CriminalCase other = (CriminalCase) object;
            
            if ((this.criminalCasePK == null && other.criminalCasePK != null) || (this.criminalCasePK != null && !this.criminalCasePK.equals(other.criminalCasePK))) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public String toString()
    {
        return "entity.CriminalCase[ criminalCasePK=" + criminalCasePK + " ]";
    }
    
}
