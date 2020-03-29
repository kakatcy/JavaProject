/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author cytang
 */
public class DayCare {
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<Group> groups; //所有组
    private ArrayList<Classroom> classrooms;
    private ArrayList<VaccineRule> vaccineRules; 
    private int[][] stateRules;

    public DayCare() throws ParseException {
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        groups = new ArrayList<>();
        classrooms = new ArrayList<>();
        vaccineRules = new ArrayList<>();
        stateRules = new int[6][5];
        readFromCSV();
        readTeacherFromCSV();
        readStateRulesFromCSV();
        readImmuRulesFromCSV();
        readImmuFromCSV();
        Immunization.setVaccineRules(vaccineRules);
        
        
    }

    public void readFromCSV() throws ParseException {
        String student_dir = "students.csv";
        try (BufferedReader inLine = new BufferedReader(new FileReader(student_dir));) {
            String inputLine = null;
            while ((inputLine = inLine.readLine()) != null) {
                Student student = new Student(inputLine);
                students.add(student);
            }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    public Student addStudent() {
        Student newstu = new Student();
        students.add(newstu);
        return newstu;

    }

    public void showAllStudents(ArrayList<Student> students) {
        for (Student e : students) {
            System.out.println(e.getId() + e.getFirstname() + e.getLastname() + e.getAge() + e.getGroup_num());

        }
        System.out.println();
    }

    public void deleteStudent(Student student) {
        students.remove(student);
        writeCsv();
        writeImmuCsv();
    }

    public void readTeacherFromCSV() {
        String teacher_dir = "teachers.csv";
        try (BufferedReader inLine = new BufferedReader(new FileReader(teacher_dir));) {
            String inputLine = null;
            while ((inputLine = inLine.readLine()) != null) {
                Teacher teacher = new Teacher(inputLine);
                teachers.add(teacher);
            }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public Teacher addTeacher() {
        //这些参数从界面中获取
        String id = "sadg";
        String firstname = "gad";
        String lastname = "bsdh";
        //

        Teacher teacher = new Teacher();
        teachers.add(teacher);
        return teacher;

    }

    public void showAllTeachers(ArrayList<Teacher> teachers) {
        for (Teacher e : teachers) {
            System.out.println(e.getId() + e.getFirstname() + e.getLastname());

        }
        System.out.println();
    }

    public void deleteTeacher(Teacher teacher) {
        teachers.remove(teacher);
        writeTeacherCsv();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }
    
    public void readStateRulesFromCSV(){
        String student_dir = "stateRules.csv";
        try (BufferedReader inLine = new BufferedReader(new FileReader(student_dir));) {
            String inputLine = null;
            int i = 0;
            while (i<6 &&(inputLine = inLine.readLine()) != null) {
               
                String[] fields = inputLine.split(",");
                stateRules[i][0] = Integer.parseInt(fields[0]);
                stateRules[i][1] = Integer.parseInt(fields[1]);
                stateRules[i][2] = Integer.parseInt(fields[2]);
                stateRules[i][3] = Integer.parseInt(fields[3]);
                stateRules[i][4] = Integer.parseInt(fields[4]);
                i++;    
            }
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[][] getStateRules() {
        return stateRules;
    }

    public void setStateRules(int[][] stateRules) {
        this.stateRules = stateRules;
    }
    
    //assign free teacher
    public Teacher assignTeacher(ArrayList<Teacher> teachers){
        Teacher teacher = new Teacher();
        for(Teacher e : teachers){
            if(e.getGroupNum() == 0){
                e.setGroupNum(1);
                return e;
            }   
        }
        System.out.println("老师不足");
        return teachers.get(0);
    }
    
    //返回需要分配学生group在groups的index,如果没有group，则创建group
    public int searchGroup(ArrayList<Group> groups, int size, ArrayList<Teacher> teachers){
        for(Group g: groups){
            if((g.getGroupSize()== size) && (!g.isFull())){
                return groups.indexOf(g);
            }
        }
        Group group =  new Group();
        group.setGroupID(groups.size()+1);
        group.setType(size);
        
        
        group.setGroupSize(size);
        Teacher t = assignTeacher(teachers);
        group.setTeacher(t);
        t.setGroupNum(groups.size() + 1);
        // Also need to assign the students' groupID
        
        groups.add(group);
        return groups.size()-1;
    }
    
    //assign a student to a group
    public void assign(ArrayList<Group> groups,Student student,ArrayList<Teacher> teachers){
        int index=0;
        if(student.getAge()> stateRules[0][0] && student.getAge() <stateRules[0][1] ){
            index = searchGroup(groups,stateRules[0][2],teachers);
            student.setGroup_num(index + 1);
            groups.get(index).addStudent(student);
        }
        if(student.getAge()> stateRules[1][0] && student.getAge() <stateRules[1][1] ){
            index = searchGroup(groups,stateRules[1][2],teachers);
            student.setGroup_num(index + 1);
            groups.get(index).addStudent(student);
        }
        if(student.getAge()> stateRules[2][0] && student.getAge() <stateRules[2][1] ){
            index = searchGroup(groups,stateRules[2][2],teachers);
            student.setGroup_num(index + 1);
            groups.get(index).addStudent(student);
        }
        if(student.getAge()> stateRules[3][0] && student.getAge() <stateRules[3][1] ){
            index = searchGroup(groups,stateRules[3][2],teachers);
            student.setGroup_num(index + 1);
            groups.get(index).addStudent(student);
        }
        if(student.getAge()> stateRules[4][0] && student.getAge() <stateRules[4][1] ){
            index = searchGroup(groups,stateRules[4][2],teachers);
            student.setGroup_num(index + 1);
            groups.get(index).addStudent(student);
        }
        if(student.getAge()> stateRules[5][0] && student.getAge() <stateRules[5][1]){
            index = searchGroup(groups,stateRules[5][2],teachers);
            student.setGroup_num(index + 1);
            groups.get(index).addStudent(student);
        }
    }
    
    //assign all students to groups
    public void assignForAll(){
  //      ArrayList<Group> groups = new ArrayList<Group>();
        for(Teacher tch: teachers){
            tch.setGroupNum(0);
        }
        groups.clear();
        for(Student s: students){
            assign(groups,s,teachers);
        }        
    }
    
    public void showAllGroup(){
        for(Group g : groups){
            System.out.println("Group Id : " + g.getGroupID() + "  GroupsizeLimit : "
            + g.getGroupSize() + "  Teacher's Name : " + g.getTeacher().getLastname()
                    + g.getTeacher().getFirstname());
            for(Student s: g.getList())
                System.out.println(s.getLastname() + " " + s.getFirstname());
            System.out.println();
        }
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
    
    //assign groups to classroom
    public ArrayList<Classroom> assignClassroom(Group group, ArrayList<Classroom> classrooms){
        //search a classroom that is not full
        if(classrooms.size()!=0){
        for(Classroom c : classrooms){
            if((group.getType()==c.getStudentType()) && !c.isFull()){
                c.addGroup(group);
                return classrooms;
            }
        }
        }
        //if all suitable classrooms are full, assign a new one
        Classroom classroom = new Classroom();
        classroom.setStudentType(group.getType());
        classroom.setClassId(classrooms.size());
        if(group.getType() == stateRules[0][2])
            classroom.setClassCapacity(stateRules[0][4]);
        if(group.getType() == stateRules[1][2])
            classroom.setClassCapacity(stateRules[1][4]);
        if(group.getType() == stateRules[2][2])
            classroom.setClassCapacity(stateRules[2][4]);
        if(group.getType() == stateRules[3][2])
            classroom.setClassCapacity(stateRules[3][4]);
        if(group.getType() == stateRules[4][2])
            classroom.setClassCapacity(stateRules[4][4]);
        if(group.getType() == stateRules[5][2])
            classroom.setClassCapacity(stateRules[5][4]);
       
       // classroom.setClassCapacity(0);
       // classroom.getGroups()
      //  System.out.println(classroom.getClassCapacity());
        classroom.getOnclass().add(group);
        classroom.setIsUsed(true);
        
        classrooms.add(classroom);
        return classrooms;
        
    }
    
    public void assignForAllClassroom(){
        classrooms.clear();
        for(Group g : groups){
           classrooms = assignClassroom(g,classrooms);
        }
        
    }
    
    public void showAllClassroom(){
        for(Classroom c : classrooms){
            System.out.println("Classroom_ID : " + c.getClassId()+" StudentAgeCatagory : " 
                    + c.getStudentType()+" ClassGroupLimited : "+ c.getClassCapacity());
            for(Group g : c.getOnclass()){
                System.out.println();
                System.out.println("Group ID : " + g.getGroupID());
                for(Student s : g.getList()){
                    System.out.println(s.getLastname() + " " + s.getFirstname());
                }
            }
            System.out.println();
        }
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }
    
    public void readImmuRulesFromCSV() {
        String immurule_dir = "immurules.csv";
        try (BufferedReader inLine = new BufferedReader(new FileReader(immurule_dir));) {
            String inputLine = null;
            while ((inputLine = inLine.readLine()) != null) {
                VaccineRule vr = new VaccineRule(inputLine);
                vaccineRules.add(vr);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }
    
    public void readImmuFromCSV() {
        String immu_dir = "immunizations.csv";
        try (BufferedReader inLine = new BufferedReader(new FileReader(immu_dir));) {
            String inputLine = null;
            while ((inputLine = inLine.readLine()) != null) {
                //System.out.println(inputLine);
                String[] format = inputLine.split(",");
                for(Student stu: students){
                    if(stu.getId().equals(format[0])){
                        Immunization immu = new Immunization(inputLine);
                        stu.setImmus(immu);
                    }
                }
               
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    public void writeCsv(){
        String student_dir = "students.csv";
        try{
        BufferedWriter out = new BufferedWriter(new FileWriter(student_dir));
        String total = "";
        for(int i= 0 ;i<students.size(); i++){
            Student stu = students.get(i);
            String comma = ",";
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
            
            String s = stu.getId() + comma+stu.getFirstname() +comma+stu.getLastname()+comma+fm.format(stu.getBirthday())+"\n";    
            total = total+s;
        }
        out.flush();
        out.write(total);
        out.close();
        }catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    public void writeTeacherCsv(){
        String teacher_dir = "teachers.csv";
        try{
        BufferedWriter out = new BufferedWriter(new FileWriter(teacher_dir));
        String total = "";
        for(int i= 0 ;i<teachers.size(); i++){
            Teacher tea = teachers.get(i);
            String comma = ",";
            String s = tea.getId() + comma+tea.getFirstname() +comma+tea.getLastname()+"\n";    
            total = total+s;
        }
        out.flush();
        out.write(total);
        out.close();
        }catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    public void writeImmuCsv(){
        String immu_dir = "immunizations.csv";
        try{
        BufferedWriter out = new BufferedWriter(new FileWriter(immu_dir));
        String total = "";
        for(int i= 0 ;i<students.size(); i++){
            Immunization immunization = students.get(i).getImmus();
            String comma = ",";
            int[] injected = new int[8];
            boolean[] boolInjected = immunization.getInjected();
            String strInjected = new String();
            for(int j = 0; j<8; j++){
                injected[j] = boolInjected[j]==true?1:0;
                if(j<7){
                    strInjected =strInjected + injected[j] + comma;
                }else{
                    strInjected +=injected[j];
                }
            }
            String immu = students.get(i).getId() + comma + strInjected+"\n";
                
            total = total+immu;
        }
        out.flush();
        out.write(total);
        out.close();
        }catch(Exception e){
            e.printStackTrace();
        }   
    }    
}