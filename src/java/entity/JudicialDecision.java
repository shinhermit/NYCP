///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package entity;
//
//import entity.primaryKeys.JudicialDecisionPK;
//import java.io.Serializable;
//import java.util.Date;
//import java.util.Objects;
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
///**
// *
// * @author milou
// */
//@Entity
//abstract class JudicialDecision implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @EmbeddedId    
//    private JudicialDecisionPK judicialDecisionPK;
//
//    public JudicialDecision () {
//        
//    }
//    
//    public JudicialDecision (JudicialDecisionPK judicialDecisionPK) {
//        this.setJudicialDecisionPK(judicialDecisionPK);
//    }    
//
//    public JudicialDecision (String decisionTypeNumber, String prisonFileNumber, Date dateOfDecision) {
//        this.setJudicialDecisionPK(decisionTypeNumber, prisonFileNumber, dateOfDecision);
//    }    
//  
//    public JudicialDecisionPK getJudicialDecisionPK() {
//        return judicialDecisionPK;
//    }
//
//    public void setJudicialDecisionPK(JudicialDecisionPK judicialDecisionPK) {
//        this.judicialDecisionPK = judicialDecisionPK;
//    }
//
//    public void setJudicialDecisionPK(String decisionTypeNumber, String prisonFileNumber, Date dateOfDecision) {
//        this.judicialDecisionPK = new JudicialDecisionPK(decisionTypeNumber, prisonFileNumber, dateOfDecision);
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 53 * hash + Objects.hashCode(this.judicialDecisionPK);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final JudicialDecision other = (JudicialDecision) obj;
//        if (!Objects.equals(this.judicialDecisionPK, other.judicialDecisionPK)) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "JudicialDecision{" + "judicialDecisionPK=" + judicialDecisionPK + '}';
//    }
//
//
//    
//}
