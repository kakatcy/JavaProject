/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Business.Classroom;
import Business.DayCare;
import Business.Group;
import Business.Student;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jxw
 */
public class ArrangeClassroom extends javax.swing.JPanel {
    private JPanel userProcessContainer;
    private DayCare daycare;
    /**
     * Creates new form ClassroomArrange
     */
    public ArrangeClassroom(JPanel upc, DayCare dc) {
        initComponents();
        userProcessContainer = upc;
        daycare = dc;
        dc.assignForAll();
        dc.assignForAllClassroom();
        showStateRules();
        refreshGroupTbl();
        refreshClassroomTbl();
        
    }
    
    public void showStateRules(){
        int rowCount = stateRulesTbl.getRowCount();
        DefaultTableModel model = (DefaultTableModel)stateRulesTbl.getModel();
        for(int i = rowCount - 1; i >=0; i--) {
            model.removeRow(i);
        }
        
        for(int[] rule : daycare.getStateRules()) {
            Object row[] = new Object[4];
            row[0] = rule[0]+"-"+rule[1];
            
            row[1] = rule[2];
            row[2] = rule[3]+":1";
            row[3] = rule[4];
            model.addRow(row);
        }
    }
    
    public void refreshGroupTbl(){
        int rowCount = groupTbl.getRowCount();
        DefaultTableModel model = (DefaultTableModel)groupTbl.getModel();
        for(int i = rowCount - 1; i >=0; i--) {
            model.removeRow(i);
        }
        
        for(Group g: daycare.getGroups()) {
            Object row[] = new Object[4];
            row[0] = g.getGroupID();
            row[1] = g.getGroupSize();
            row[2] = g.getTeacher().getFirstname()+" "+g.getTeacher().getLastname();
            String stuNameList = new String();
            for(Student stu: g.getList()){
                stuNameList += stu.getFirstname()+" "+stu.getLastname()+",";
            }
            stuNameList = stuNameList.substring(0, stuNameList.length()-1);
            row[3] = stuNameList;
            model.addRow(row);
        }
    }
    
    public void refreshClassroomTbl(){
        int rowCount = classroomTbl.getRowCount();
        DefaultTableModel model = (DefaultTableModel)classroomTbl.getModel();
        for(int i = rowCount - 1; i >=0; i--) {
            model.removeRow(i);
        }
        
        for(Classroom cr: daycare.getClassrooms()) {
            Object row[] = new Object[2];
            row[0] = cr.getClassId();
            String groupList = new String();
            for(Group g: cr.getOnclass()){
                groupList += g.getGroupID()+",";
            }
            row[1] = groupList;
           
            model.addRow(row);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        groupTbl = new javax.swing.JTable();
        classroomLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        classroomTbl = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        stateRulesTbl = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(700, 500));

        groupLbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        groupLbl.setText("Group List");

        groupTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Group Id", "Group Size", "Teacher", "Students"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(groupTbl);
        if (groupTbl.getColumnModel().getColumnCount() > 0) {
            groupTbl.getColumnModel().getColumn(0).setPreferredWidth(30);
            groupTbl.getColumnModel().getColumn(1).setPreferredWidth(30);
            groupTbl.getColumnModel().getColumn(2).setPreferredWidth(60);
            groupTbl.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        classroomLbl.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        classroomLbl.setText("Classroom Arrangement");

        classroomTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Classroom", "Group"
            }
        ));
        jScrollPane2.setViewportView(classroomTbl);

        jLabel1.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jLabel1.setText("State Rules");

        stateRulesTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Age(Month)", "Group Size", "Ratio", "Max Groups/Room"
            }
        ));
        jScrollPane3.setViewportView(stateRulesTbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classroomLbl, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(30, 30, 30)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(groupLbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(groupLbl)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(classroomLbl)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel classroomLbl;
    private javax.swing.JTable classroomTbl;
    private javax.swing.JLabel groupLbl;
    private javax.swing.JTable groupTbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable stateRulesTbl;
    // End of variables declaration//GEN-END:variables
}