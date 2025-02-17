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
public class SearchBooks extends JFrame {

    /**
     * Creates new form SearchBooks
     */
    private Connection con;
    private int bookId;
    public SearchBooks(AdminMenu am) {
        initComponents();
        databaseConnect();
        setBorrowLabelsVisible(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBorrowLabelsVisible(false);
                try{
                    try{
                        bookId = Integer.parseInt(bookIdTextField.getText().strip());
                    } catch(NumberFormatException nfe){
                        JOptionPane.showMessageDialog(null, "Enter only correct numbers for Book ID!", "Incorrect Book ID", JOptionPane.ERROR_MESSAGE);
                    }

                    //Preparing book details
                    String getBookDetailsQuery = "select * from books where book_id=?";
                    String getBookBorrowDetailsQuery = "select * from borrowed_details where book_id=?";
                    PreparedStatement getBookDetailsStatement = con.prepareStatement(getBookDetailsQuery);
                    getBookDetailsStatement.setInt(1, bookId);

                    PreparedStatement getBookBorrowDetailsStatement = con.prepareStatement(getBookBorrowDetailsQuery);
                    getBookBorrowDetailsStatement.setInt(1, bookId);
                    //Executing insertion
                    ResultSet book = getBookDetailsStatement.executeQuery();
                    if(book.next()){
                        bookNameLabel.setText(book.getString("book_name"));
                        authorNameLabel.setText(book.getString("author_name"));
                        String availability = book.getString("availability");
                        availabilityLabel.setText(availability);
                        if(availability.equals("borrowed")){
                            ResultSet borrowedBook = getBookDetailsStatement.executeQuery();
                            setBorrowLabelsVisible(true);
                            dateBorrowedLabel.setText(String.valueOf(borrowedBook.getDate("borrowed_date")));
                            returnDateLabel.setText(String.valueOf(borrowedBook.getDate("return_date")));
                            displayUserLabel.setText(String.valueOf(borrowedBook.getInt("user_id")));
                        }
                    } else{
                        JOptionPane.showMessageDialog(SearchBooks.this, "Enter the correct book ID!", "Incorrect Book ID", JOptionPane.ERROR_MESSAGE);
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
                am.setVisible(true);
            }
        });
        this.setContentPane(this.searchBookPanel);
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

    private void setBorrowLabelsVisible(boolean visible){
        dateBorrowedLabel.setVisible(visible);
        DateBorrowedLabel.setVisible(visible);
        returnDateLabel.setVisible(visible);
        ReturnDateLabel.setVisible(visible);
        displayUserLabel.setVisible(visible);
        UserLabel.setVisible(visible);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchBookPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bookIdTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        bookNameLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        authorNameLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        availabilityLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        DateBorrowedLabel = new javax.swing.JLabel();
        dateBorrowedLabel = new javax.swing.JLabel();
        ReturnDateLabel = new javax.swing.JLabel();
        returnDateLabel = new javax.swing.JLabel();
        UserLabel = new javax.swing.JLabel();
        displayUserLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 750));
        setSize(new java.awt.Dimension(1000, 750));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Search Books");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Enter the Book ID :");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Book Name :");

        bookNameLabel.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        bookNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Author : ");

        authorNameLabel.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        authorNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Availability : ");

        availabilityLabel.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        availabilityLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        searchButton.setText("SEARCH");

        closeButton.setText("Close");


        DateBorrowedLabel.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        DateBorrowedLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        DateBorrowedLabel.setText("Date Borrowed : ");

        dateBorrowedLabel.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        dateBorrowedLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        ReturnDateLabel.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        ReturnDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ReturnDateLabel.setText("Return Date : ");

        returnDateLabel.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        returnDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        UserLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        UserLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        UserLabel.setText("User : ");

        displayUserLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout searchBookPanelLayout = new javax.swing.GroupLayout(searchBookPanel);
        searchBookPanel.setLayout(searchBookPanelLayout);
        searchBookPanelLayout.setHorizontalGroup(
            searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchBookPanelLayout.createSequentialGroup()
                .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchBookPanelLayout.createSequentialGroup()
                        .addGap(288, 288, 288)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchBookPanelLayout.createSequentialGroup()
                        .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchBookPanelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(searchBookPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(UserLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ReturnDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DateBorrowedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchBookPanelLayout.createSequentialGroup()
                                .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(searchBookPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(bookIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(searchBookPanelLayout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(bookNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(authorNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(availabilityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dateBorrowedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(returnDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))))
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchBookPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(closeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(displayUserLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        searchBookPanelLayout.setVerticalGroup(
            searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchBookPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85)
                .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(authorNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(availabilityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DateBorrowedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateBorrowedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ReturnDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(returnDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(searchBookPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UserLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayUserLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchBookPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchBookPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DateBorrowedLabel;
    private javax.swing.JLabel ReturnDateLabel;
    private javax.swing.JLabel UserLabel;
    private javax.swing.JLabel authorNameLabel;
    private javax.swing.JLabel availabilityLabel;
    private javax.swing.JTextField bookIdTextField;
    private javax.swing.JLabel bookNameLabel;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel dateBorrowedLabel;
    private javax.swing.JLabel displayUserLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel returnDateLabel;
    private javax.swing.JPanel searchBookPanel;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
