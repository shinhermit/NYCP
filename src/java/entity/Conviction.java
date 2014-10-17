/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package entity;
//
//import entity.primaryKeys.JudicialDecisionPK;
//import java.io.Serializable;
//import java.util.Date;
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
//public class Conviction extends JudicialDecision implements Serializable {
//   private static final long serialVersionUID = 1L;
//
//    private String duration;
// 
//    public Conviction() {
//    }
//
//    public Conviction(JudicialDecisionPK judicialDecisionPK) {
//        super(judicialDecisionPK);
//    }
//
//    public Conviction(String decisionTypeNumber, String prisonFileNumber, Date dateOfDecision) {
//        super(decisionTypeNumber, prisonFileNumber, dateOfDecision);
//    }
//
//    
//    public String getDuration() {
//        return duration;
//    }
//
//    public void setDuration(String duration) {
//        this.duration = duration;
//    }
//    
//    
//}
