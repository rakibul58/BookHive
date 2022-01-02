/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryManagementPakage;

import com.mysql.jdbc.PreparedStatement;
//import com.sun.imageio.plugins.png.RowFilter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
//import java.util.regex.*;
import javax.swing.RowFilter;

/**
 *
 * @author rakibul
 */
public class TeacherIssueBooks extends javax.swing.JFrame {

    /**
     * Creates new form Book
     */
    public TeacherIssueBooks() {
        initComponents();
        connect();
       
        Book_Load();
    
    }
    
    
    Connection con ;
    PreparedStatement pst , update , issue ;
    int id;
    String bookId , teachId ;
    
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/library","root","");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LibrarianLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LibrarianLogin.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        BookTable = new javax.swing.JTable();
        backBtn = new javax.swing.JButton();
        IssueBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("C059", 1, 18)); // NOI18N
        jLabel1.setText("IIUC Library");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 23, 132, 29));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel2.setText("Search");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 71, -1, -1));

        searchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtKeyReleased(evt);
            }
        });
        getContentPane().add(searchTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 70, 480, -1));

        BookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Teacher ID", "Book ID", "Request Date"
            }
        ));
        BookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BookTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(BookTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 99, 540, 247));

        backBtn.setIcon(new javax.swing.ImageIcon("/home/rakibul/Documents/LibraryManagement/backIcon.png")); // NOI18N
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        getContentPane().add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 30, 63, -1));

        IssueBtn.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        IssueBtn.setText("Issue");
        IssueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IssueBtnActionPerformed(evt);
            }
        });
        getContentPane().add(IssueBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, 130, -1));

        setSize(new java.awt.Dimension(577, 445));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        new MainUserInterface().setVisible(true);
        this.setVisible(false);
      
    }//GEN-LAST:event_backBtnActionPerformed

    private void BookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BookTableMouseClicked
        // TODO add your handling code here:
     int cpys;
     DefaultTableModel d=  (DefaultTableModel)BookTable.getModel();
     int selectedindex = BookTable.getSelectedRow();
     if(BookTable.getRowSorter()!= null){
         selectedindex = BookTable.getRowSorter().convertRowIndexToModel(selectedindex);
     }
     id = Integer.parseInt(d.getValueAt(selectedindex, 0).toString());
     teachId = d.getValueAt(selectedindex, 1).toString();
     bookId = d.getValueAt(selectedindex, 2).toString();
     
    
     
     
    }//GEN-LAST:event_BookTableMouseClicked

    private void searchTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtKeyReleased
        // TODO add your handling code here:
        DefaultTableModel table = (DefaultTableModel) BookTable.getModel();
        String search = searchTxt.getText();
        TableRowSorter<DefaultTableModel>tr = new TableRowSorter<DefaultTableModel>(table);
        BookTable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
    }//GEN-LAST:event_searchTxtKeyReleased

    private void IssueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IssueBtnActionPerformed
        try {
            // TODO add your handling code here:
            issue = (PreparedStatement) con.prepareStatement("insert into issueBook (member, memberID, BookID, iDate, rDate) values (?,?,?,?,?)");
            issue.setString(1, "Teacher");
            issue.setString(2, teachId);
            issue.setString(3, bookId);
            issue.setString(4, LocalDate.now().toString());
            issue.setString(5, LocalDate.now().plusMonths(1).toString());
            int k = issue.executeUpdate();
            if(k == 1){
                JOptionPane.showMessageDialog(this, "Book Issued");
                pst = (PreparedStatement) con.prepareStatement("delete from TeachersRequest where id=?");
                pst.setInt(1, id);
                int l = pst.executeUpdate();
                if(l == 1){
                RemoveCopy();
                Book_Load();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherIssueBooks.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_IssueBtnActionPerformed

    public void Book_Load(){
        
        try {
            pst = (PreparedStatement) con.prepareStatement("SELECT * FROM TeachersRequest");
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rss = rs.getMetaData();
            DefaultTableModel df = (DefaultTableModel) BookTable.getModel();
            df.setRowCount(0);
            while (rs.next()) {   
                
                String id = String.valueOf(rs.getInt("id"));
                String teaID = rs.getString("teacherID");
                String bookID = rs.getString("bookID");
                String reqDate = rs.getString("date");
             
                
                
                String tbData[] = {id,teaID,bookID,reqDate};
                df.addRow(tbData);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Category.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
      public void RemoveCopy(){
        
     
        try {
            update = (PreparedStatement) con.prepareStatement("update StudentBooks set copies = copies - 1 where id=?");
            update.setString(1, bookId);
            int l = update.executeUpdate();
            if(l == 1){
                System.out.println("updated");
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TeacherIssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeacherIssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeacherIssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeacherIssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TeacherIssueBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable BookTable;
    private javax.swing.JButton IssueBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchTxt;
    // End of variables declaration//GEN-END:variables
}