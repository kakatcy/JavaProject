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
public class Group {
    private Teacher teacher;
    private List<Student> list;
    private int Type;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    private int groupSize;
    private int groupID;

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }
     
    public Group(){
        list = new ArrayList<>();
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }


    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }
    
    public void addStudent(Student s){
        if(!isFull()){
            this.list.add((Student) s);
        }
    }
    
    public void removeStudent(Student s){
        if(!isEmpty()){
            this.list.remove(s);
        }
    }
    
    public boolean isFull(){
        if(this.list.size() == groupSize){
            return true;
        }
        return false;
    }
    
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
    
    
}