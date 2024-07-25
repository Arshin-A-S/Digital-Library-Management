/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.arshin.digitallibrarymanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author arshi
 */
public class ModifyMember extends JFrame {
    /**
     * Creates new form ModifyMember
     */
    private Connection con;
    private int memberID;
    public ModifyMember(AdminMenu am) {
        initComponents();
        databaseConnect();

        setFieldsEditable(false);
        confirmButton.setVisible(false);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFieldsEditable(false);
                confirmButton.setVisible(false);
                try{
                    memberID=Integer.parseInt(memberIdTextField.getText());
                } catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null, "Enter only numbers for Member Id", "Member Id Error", JOptionPane.ERROR_MESSAGE);
                }
                setDetails();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nameTextField.getText().isEmpty()){
                    modifyMember();
                }else{
                    JOptionPane.showMessageDialog(null, "Enter member ID and click search first!", "Member Id Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ModifyMember(am).setVisible(true);
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                am.setVisible(true);
            }
        });

        this.setContentPane(this.modifyMembersPanel);
        this.setSize(1000,750);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void databaseConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DigitalLibrary", "root", "1234");
        } catch(Exception SqlConnE){
            SqlConnE.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Connection Error: "+SqlConnE.getMessage(), "Database Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFieldsEditable(boolean editable){
        nameTextField.setEditable(editable);
        phoneTextField.setEditable(editable);
        joinDateTextField.setEditable(editable);
    }

    private void setDetails(){
        try{
            String available;
            String getQuery = "select member_name, phone_no, join_date from members where member_id=?";
            PreparedStatement getStatement = con.prepareStatement(getQuery);
            getStatement.setInt(1, memberID);
            ResultSet memberDetails = getStatement.executeQuery();
            if(memberDetails.next()){
                nameTextField.setText(memberDetails.getString("member_name"));
                phoneTextField.setText(memberDetails.getString("phone_no"));
                joinDateTextField.setText(String.valueOf(memberDetails.getDate("join_date")));
            } else{
                JOptionPane.showMessageDialog(null, "Unknown member ID! Enter correct book id", "Member Not Found", JOptionPane.ERROR_MESSAGE);
            }
        } catch(SQLException SqlQueryE){
            SqlQueryE.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Query Error!", "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modifyMember(){
        setFieldsEditable(true);
        confirmButton.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean updated = updateDatabase();
                if(updated){
                    JOptionPane.showMessageDialog(null, "Member Details updated successfully!", "Member Details Update", JOptionPane.INFORMATION_MESSAGE);
                    setFieldsEditable(false);
                    confirmButton.setVisible(false);
                    setDetails();
                } else{
                    JOptionPane.showMessageDialog(null, "Failed updating the member details!", "member Details Update", JOptionPane.ERROR_MESSAGE);
                    setFieldsEditable(false);
                    confirmButton.setVisible(false);
                }
            }
        });

    }

    private boolean updateDatabase(){
        try{
            String userName = nameTextField.getText().strip();
            String phoneNo = phoneTextField.getText().strip();
            String joinDate = joinDateTextField.getText().strip();
            //Preparing book details
            String updateQuery = "update members set member_name=?, phone_no=?, join_date=?  where member_id=?";
            PreparedStatement updateStatement = con.prepareStatement(updateQuery);
            updateStatement.setString(1, userName);
            updateStatement.setString(2, phoneNo);
            updateStatement.setString(3, joinDate);
            updateStatement.setInt(4, memberID);
            //Executing updates
            int memberDetailsUpdated = updateStatement.executeUpdate();
            if(memberDetailsUpdated > 0){
                return true;
            } else{
                return false;
            }
        } catch(SQLException SqlQueryE){
            SqlQueryE.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Query Error!", "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modifyMembersPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        memberIdTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        phoneTextField = new javax.swing.JTextField();
        joinDateTextField = new javax.swing.JTextField();
        modifyButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 750));
        setSize(new java.awt.Dimension(1000, 750));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Modify Member Details");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Enter the member ID : ");

        searchButton.setText("SEARCH");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Name : ");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Date Joined : ");

        closeButton.setText("Close");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Phone No. : ");


        modifyButton.setText("Modify");

        cancelButton.setText("Cancel");

        confirmButton.setText("Confirm");

        javax.swing.GroupLayout modifyMembersPanelLayout = new javax.swing.GroupLayout(modifyMembersPanel);
        modifyMembersPanel.setLayout(modifyMembersPanelLayout);
        modifyMembersPanelLayout.setHorizontalGroup(
            modifyMembersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(modifyMembersPanelLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addGroup(modifyMembersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modifyMembersPanelLayout.createSequentialGroup()
                        .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(confirmButton)
                        .addGap(132, 132, 132)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyMembersPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(memberIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(searchButton))
                    .addGroup(modifyMembersPanelLayout.createSequentialGroup()
                        .addGroup(modifyMembersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(modifyMembersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(phoneTextField)
                            .addComponent(nameTextField)
                            .addComponent(joinDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        modifyMembersPanelLayout.setVerticalGroup(
            modifyMembersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyMembersPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(modifyMembersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memberIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addGap(76, 76, 76)
                .addGroup(modifyMembersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(modifyMembersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(modifyMembersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(joinDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(modifyMembersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifyMembersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(155, 155, 155))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(modifyMembersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(modifyMembersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField joinDateTextField;
    private javax.swing.JTextField memberIdTextField;
    private javax.swing.JButton modifyButton;
    private javax.swing.JPanel modifyMembersPanel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
