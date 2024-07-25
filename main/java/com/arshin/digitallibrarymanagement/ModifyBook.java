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
 * @author Arshin A S
 */
public class ModifyBook extends javax.swing.JFrame {
    /**
     * Creates new form ModifyBook
     */
    private Connection con;
    private int bookID;

    public ModifyBook(AdminMenu am) {
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
                    bookID=Integer.parseInt(bookIdTextField.getText());
                } catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(null, "Enter only numbers in Book Id", "Book Id Error", JOptionPane.ERROR_MESSAGE);
                }
                setDetails();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!bookNameTextField.getText().isEmpty()){
                    modifyBook();
                }else{
                    JOptionPane.showMessageDialog(null, "Enter book ID and click search first!", "Book Id Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ModifyBook(am).setVisible(true);
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                am.setVisible(true);
            }
        });

        this.setContentPane(this.modifyBookPanel);
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
        bookNameTextField.setEditable(editable);
        authorTextField.setEditable(editable);

    }


    private void setDetails(){
        try{
            String getQuery = "select book_name, author_name, availability from books where book_id=?";
            PreparedStatement getStatement = con.prepareStatement(getQuery);
            getStatement.setInt(1, bookID);
            ResultSet bookDetails = getStatement.executeQuery();
            if(bookDetails.next()){
                bookNameTextField.setText(bookDetails.getString("book_name"));
                authorTextField.setText(bookDetails.getString("author_name"));
            } else{
                JOptionPane.showMessageDialog(null, "Unknown book id! Enter correct book id", "Book Id Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch(SQLException SqlQueryE){
            SqlQueryE.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Query Error!", "Database Query Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modifyBook(){
        setFieldsEditable(true);
        confirmButton.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean updated = updateDatabase();
                if(updated){
                    JOptionPane.showMessageDialog(null, "Book Details updated successfully!", "Book Details Update", JOptionPane.INFORMATION_MESSAGE);
                    setFieldsEditable(false);
                    confirmButton.setVisible(false);
                    setDetails();
                } else{
                    JOptionPane.showMessageDialog(null, "Failed updating the book details!", "Book Details Update", JOptionPane.ERROR_MESSAGE);
                    setFieldsEditable(false);
                    confirmButton.setVisible(false);
                }
            }
        });

    }

    private boolean updateDatabase(){
        try{
            String bookName = bookNameTextField.getText().strip();
            String authorName = authorTextField.getText().strip();

            //Preparing book details
            String updateQuery = "update books set book_name=?, author_name=? where book_id=?";
            PreparedStatement updateStatement = con.prepareStatement(updateQuery);
            updateStatement.setString(1, bookName);
            updateStatement.setString(2, authorName);
            updateStatement.setInt(3, bookID);

            //Executing updates
            int borrowDetailsUpdated;
            int bookDetailsUpdated = updateStatement.executeUpdate();
            if(bookDetailsUpdated > 0){
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

        modifyBookPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bookIdTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        bookNameTextField = new javax.swing.JTextField();
        authorTextField = new javax.swing.JTextField();
        modifyButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 750));
        setSize(new java.awt.Dimension(1000, 750));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Modify Book Details");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Enter the Book ID :");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Book Name :");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Author : ");

        searchButton.setText("SEARCH");

        closeButton.setText("Close");

        bookNameTextField.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        authorTextField.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        modifyButton.setText("Modify");

        cancelButton.setText("Cancel");

        confirmButton.setText("Confirm");

        javax.swing.GroupLayout modifyBookPanelLayout = new javax.swing.GroupLayout(modifyBookPanel);
        modifyBookPanel.setLayout(modifyBookPanelLayout);
        modifyBookPanelLayout.setHorizontalGroup(
            modifyBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyBookPanelLayout.createSequentialGroup()
                .addGroup(modifyBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modifyBookPanelLayout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyBookPanelLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(modifyBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(bookNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(modifyBookPanelLayout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addGroup(modifyBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modifyBookPanelLayout.createSequentialGroup()
                                .addComponent(bookIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(authorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(modifyBookPanelLayout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        modifyBookPanelLayout.setVerticalGroup(
            modifyBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyBookPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(modifyBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(modifyBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(modifyBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(authorTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addGroup(modifyBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(224, 224, 224))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(modifyBookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(modifyBookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField authorTextField;
    private javax.swing.JTextField bookIdTextField;
    private javax.swing.JTextField bookNameTextField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel modifyBookPanel;
    private javax.swing.JButton modifyButton;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
