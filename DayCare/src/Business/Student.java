/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cytang
 */
public class Student extends Person{
    private int age;
    private int group_num;
    private Date registrationDate;
    private Date birthday;
    private Immunization immus;
    public Student(){
        super();
        registrationDate = new Date();
        immus = new Immunization();
        
    }
    
    public Student(String id, String firstname, String lastname, int age, int group_num){
        super(id,firstname,lastname);
        this.age = age;
        this.group_num = group_num;
        registrationDate = new Date();
        immus = new Immunization();
        
    }
    
    public Student(String student_info){
        
        String[] fields = student_info.split(",");
        super.setId(fields[0]);
        super.setFirstname(fields[1]);
        super.setLastname(fields[2]);       
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            birthday = format.parse(fields[3]);
        } catch (ParseException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Students.csv has incorrect birthday format.");
        }
        setAgeByBirth();
        registrationDate = new Date();
        immus = new Immunization();
        
    }
    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    /**
     * @return the group_num
     */
    public int getGroup_num() {
        return group_num;
    }

    /**
     * @param group_num the group_num to set
     */
    public void setGroup_num(int group_num) {
        this.group_num = group_num;
    }
    
   
 /*  public void showAllStudents(List<Student> student_list){
        
        
    }*/

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    
    public void setAgeByBirth() {
        
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = 12*(now.get(Calendar.YEAR)-birth.get(Calendar.YEAR))+now.get(Calendar.MONTH) - birth.get(Calendar.MONTH);
                
            }
            
        
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Immunization getImmus() {
        return immus;
    }

    public void setImmus(Immunization immus) {
        this.immus = immus;
    }
    
    
    
}