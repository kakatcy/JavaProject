/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.*;

/**
 *
 * @author cytang
 */
public class Teacher extends Person{
    
    private int groupNum;  //记录该老师已经管理多少个group

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }
    
    
    Teacher(){
        super();
    }

    Teacher(String id, String firstname, String lastname){
        super(id,firstname,lastname);       
    }
    
    public Teacher(String teacher_info){
        String[] fields = teacher_info.split(",");
        super.setId(fields[0]);
        super.setFirstname(fields[1]);
        super.setLastname(fields[2]);       
    }
        
}