/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Business.DayCare;
import Business.Student;
import Business.Teacher;
import java.awt.CardLayout;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jxw
 */
public class ManageTeacher extends javax.swing.JPanel {
    private JPanel userProcessContainer;
    private DayCare daycare;
    /**
     * Creates new form ManageTeacher
     */
    public ManageTeacher(JPanel upc, DayCare dc) {
        initComponents();
        userProcessContainer = upc;
        daycare = dc;
        refreshTable();
    }
    public void refreshTable() {
        int rowCount = teacherTable.getRowCount();
        DefaultTableModel model = (DefaultTableModel)teacherTable.getModel();
        for(int i = rowCount - 1; i >=0; i--) {
            model.removeRow(i);
        }
        
        for(Teacher t : daycare.getTeachers()) {
            Object row[] = new Object[3];
            row[0] = t.getFirstname();
            row[1] = t.getLastname();
            row[2] = t.getId();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        teacherTable = new javax.swing.JTable();
        addTchBtn = new javax.swing.JButton();
        deleteTchBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        teacherTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "ID"
            }
        ));
        jScrollPane1.setViewportView(teacherTable);

        addTchBtn.setText("Add Teacher");
        addTchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTchBtnActionPerformed(evt);
            }
        });

        deleteTchBtn.setText("Delete Teacher");
        deleteTchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTchBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jLabel1.setText("Teacher Information");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteTchBtn)
                        .addGap(18, 18, 18)
                        .addComponent(addTchBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addTchBtn)
                    .addComponent(deleteTchBtn))
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addTchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTchBtnActionPerformed
        AddTeacher as = new AddTeacher(userProcessContainer, daycare);
        userProcessContainer.add("AddTeacher", as);
        CardLayout layout = (CardLayout)userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_addTchBtnActionPerformed

    private void deleteTchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTchBtnActionPerformed
        int row = teacherTable.getSelectedRow();
        
        if(row<0) {
            JOptionPane.showMessageDialog(null, "Please select a row from the table first", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for(Teacher tch: daycare.getTeachers()){
            if(tch.getId() == teacherTable.getValueAt(row, 2) && tch.getFirstname() == teacherTable.getValueAt(row, 0)){
                daycare.deleteTeacher(tch);
                JOptionPane.showMessageDialog(null, "Teacher deleted successfully.");
                break;
            }    
        }
        refreshTable();
    }//GEN-LAST:event_deleteTchBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTchBtn;
    private javax.swing.JButton deleteTchBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable teacherTable;
    // End of variables declaration//GEN-END:variables
}
