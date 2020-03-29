/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;

/**
 *
 * @author cytang
 * 记录一个学生的接种信息
 */
public class Immunization {
    //private String firstname;  //学生姓名
    private boolean[] injected ; //该学生疫苗信息
    private static ArrayList<VaccineRule> vaccineRules;
    /**public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }*/

    
    public Immunization(){
        injected = new boolean[8];
    }
    
    public Immunization(String immuInfo){
        injected = new boolean[8];
        String[] fields = immuInfo.split(",");
        for(int i = 1; i<9; i++){
                if(fields[i].equals("1")) injected[i-1] = true;
                
            
        
        }
     
    }

    public boolean[] getInjected() {
        return injected;
    }

    public void setInjected(boolean[] injected) {
        this.injected = injected;
    }
    
    
    
    public static ArrayList<VaccineRule> getVaccineRules() {
        return vaccineRules;
    }

    public static void setVaccineRules(ArrayList<VaccineRule> vaccineRules) {
        Immunization.vaccineRules = vaccineRules;
    }
    
}
