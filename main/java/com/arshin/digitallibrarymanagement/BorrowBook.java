/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.arshin.digitallibrarymanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import javax.swing.*;

/**
 *
 * @author Arshin A S
 */
public class BorrowBook extends javax.swing.JFrame {
    /**
     * Creates new form BorrowBook
     */
    private Connection con;
    private int bookId;
    public BorrowBook(UserMenu um, int userId) {
        initComponents();
        databaseConnect();

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate currentDate = LocalDate.now();
                try{
                    try{
                        bookId = Integer.parseInt(bookIdTextField.getText().strip());
                    } catch(NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null, "Enter only correct numbers for Book ID!", "Incorrect Book ID", JOptionPane.ERROR_MESSAGE);
                    }

                    String returnDate = returnDateTextField.getText();

                    //Preparing book details
                    String updateBooksQuery = "update books set availability=? where book_id=?";
                    String updateBorrowDetailsQuery="update borrow_details set user_id=?, borrow_date=?, return_date=? where book_id=?";
                    String getAvailabilityQuery = "select availability from books where book_id=?";
                    //Get availability statement
                    PreparedStatement getAvailabilityStatement = con.prepareStatement(getAvailabilityQuery);
                    getAvailabilityStatement.setInt(1, bookId);
                    //Update books availability statement
                    PreparedStatement updateBooksStatement = con.prepareStatement(updateBooksQuery);
                    updateBooksStatement.setString(1, "borrowed");
                    updateBooksStatement.setInt(2, bookId);
                    //Update borrow details statement
                    PreparedStatement updateBorrowDetailsStatement = con.prepareStatement(updateBorrowDetailsQuery);
                    updateBorrowDetailsStatement.setInt(1, userId);
                    updateBorrowDetailsStatement.setDate(2, Date.valueOf(currentDate));
                    updateBorrowDetailsStatement.setDate(3, Date.valueOf(returnDate));
                    updateBorrowDetailsStatement.setInt(4, bookId);
                    //Executing insertion
                    ResultSet availabilityResult = getAvailabilityStatement.executeQuery();
                    if(availabilityResult.next()){
                        String availability = availabilityResult.getString("availability");
                        if(availability.equals("available")){
                            int booksUpdtaed = updateBooksStatement.executeUpdate();
                            int borrowDetailsUpdated = updateBorrowDetailsStatement.executeUpdate();
                            if(booksUpdtaed > 0 && borrowDetailsUpdated > 0){
                                JOptionPane.showMessageDialog(BorrowBook.this, "Book borrowed successfully!", "Borrow Book", JOptionPane.INFORMATION_MESSAGE);
                            } else{
                                JOptionPane.showMessageDialog(BorrowBook.this, "Failed to borrow the book!", "Borrow Book Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else{
                            JOptionPane.showMessageDialog(BorrowBook.this, "This book is not available to borrow right now!", "Book Unavailable", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else{
                        JOptionPane.showMessageDialog(BorrowBook.this, "Enter the correct book ID!", "Incorrect Book ID", JOptionPane.ERROR_MESSAGE);
                    }
                } catch(SQLException SqlQueryE){
                    SqlQueryE.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Database Query Error!", "Database Query Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                um.setVisible(true);
            }
        });
        this.setContentPane(this.borrowBooksPanel);
        this.setVisible(true);
        this.setSize(1000,750);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        borrowBooksPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bookIdTextField = new javax.swing.JTextField();
        borrowButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        returnDateTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Borrow Books");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Enter the Book ID : ");

        borrowButton.setText("Borrow");

        closeButton.setText("Close");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Return Date : ");

        returnDateTextField.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        returnDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnDateTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout borrowBooksPanelLayout = new javax.swing.GroupLayout(borrowBooksPanel);
        borrowBooksPanel.setLayout(borrowBooksPanelLayout);
        borrowBooksPanelLayout.setHorizontalGroup(
            borrowBooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, borrowBooksPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
            .addGroup(borrowBooksPanelLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(borrowBooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(borrowBooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bookIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(returnDateTextField))
                .addGap(63, 63, 63)
                .addComponent(borrowButton)
                .addContainerGap(340, Short.MAX_VALUE))
        );
        borrowBooksPanelLayout.setVerticalGroup(
            borrowBooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowBooksPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                .addGroup(borrowBooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(borrowButton))
                .addGap(65, 65, 65)
                .addGroup(borrowBooksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(200, 200, 200)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(borrowBooksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(borrowBooksPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void returnDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnDateTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bookIdTextField;
    private javax.swing.JPanel borrowBooksPanel;
    private javax.swing.JButton borrowButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField returnDateTextField;
    // End of variables declaration//GEN-END:variables
}
