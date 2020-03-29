/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author huangyimin
 */

import java.util.*;
public class Classroom{
    private int classId;
    private int classCapacity = 30;
    private int StudentType;
    private boolean isUsed;
    
    private List<Group> Onclass;
    
    public Classroom(){
        this.Onclass = new ArrayList<>();
    }
    
    public Classroom(int classId, boolean isUsed, List<Group> Onclass, int StudentTpye){
        this.classId = classId;
        this.isUsed = isUsed;
        this.Onclass = Onclass;
        this.StudentType = StudentTpye;
        this.classCapacity = 30;
        
    }

    public int getStudentType() {
        return StudentType;
    }

    public void setStudentType(int StudentType) {
        this.StudentType = StudentType;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getClassCapacity() {
        return classCapacity;
    }

    public void setClassCapacity(int classCapacity) {
        this.classCapacity = classCapacity;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public List<Group> getOnclass() {
        return Onclass;
    }

    public void setOnclass(List<Group> Onclass) {
        this.Onclass = Onclass;
    }
    
    public void addGroup(Group g){
        if(isFull()){
            System.out.println("This classroom is full!");
            return;
        }
        this.Onclass.add((Group) g);
    }
    
    public void removeGroup(Group g){
        if(isEmpty()){
            System.out.println("This class has no group!");
            return;
        }
        this.Onclass.remove((Group) g);
    }
    
    public boolean isFull(){
        return this.Onclass.size() == classCapacity;
    }
    
    public boolean isEmpty(){
        return this.Onclass.isEmpty();
    }
}
