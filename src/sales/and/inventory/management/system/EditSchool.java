/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sales.and.inventory.management.system;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author juanm
 */
public class EditSchool extends javax.swing.JFrame {
    private static final String username = JDBC.username;
    private static final String password = JDBC.password;
    private static final String dataConn = JDBC.dataConn;

    Connection sqlConn =null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null, pst3 = null;
    ResultSet rs=null, rs2=null,rs3=null;
    /**
     * Creates new form EditSchool
     */
    public EditSchool() {
        initComponents();
        JDBC db = new JDBC();
        db.Connect();
        getSchool();
        setBackground(new Color(0,0,0,0));
    }
    
    public final void getSchool(){
            try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT ScName FROM school_client");
            rs = pst.executeQuery();
            while (rs.next()) {
                String bnk = rs.getString("ScName");
                SchoolName.addItem(bnk);
            }
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Please contact your admin for assistance.");
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

        panelBorder1 = new swing.PanelBorder();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        SchoolName = new javax.swing.JComboBox<>();
        TIN = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        ContactPerson = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ContactNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SchoolAddress = new javax.swing.JTextArea();
        Cancel = new javax.swing.JButton();
        SaveChanges = new javax.swing.JButton();
        panelBorder2 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(102, 255, 204));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("School Name");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("TIN");

        SchoolName.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        SchoolName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SchoolNameMouseClicked(evt);
            }
        });

        TIN.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        TIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TINActionPerformed(evt);
            }
        });
        TIN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TINKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Contact Person");

        ContactPerson.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        ContactPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactPersonActionPerformed(evt);
            }
        });
        ContactPerson.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ContactPersonKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("School Email");

        txtEmail.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 51));
        jLabel8.setText("Contact Number");

        ContactNumber.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        ContactNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactNumberActionPerformed(evt);
            }
        });
        ContactNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ContactNumberKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("School Address");

        SchoolAddress.setColumns(20);
        SchoolAddress.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        SchoolAddress.setRows(5);
        jScrollPane1.setViewportView(SchoolAddress);

        Cancel.setBackground(new java.awt.Color(204, 204, 204));
        Cancel.setFont(new java.awt.Font("Roboto", 0, 27)); // NOI18N
        Cancel.setForeground(new java.awt.Color(0, 0, 51));
        Cancel.setText("Cancel");
        Cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CancelMouseClicked(evt);
            }
        });
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        SaveChanges.setBackground(new java.awt.Color(0, 0, 51));
        SaveChanges.setFont(new java.awt.Font("Roboto", 0, 27)); // NOI18N
        SaveChanges.setForeground(new java.awt.Color(255, 255, 255));
        SaveChanges.setText("Save Changes");
        SaveChanges.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SaveChanges.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveChangesMouseClicked(evt);
            }
        });
        SaveChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveChangesActionPerformed(evt);
            }
        });

        panelBorder2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Edit School");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(268, 268, 268))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(SchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ContactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TIN, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addComponent(Cancel)
                                        .addGap(18, 18, 18)
                                        .addComponent(SaveChanges))
                                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7)
                                        .addComponent(txtEmail)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)))))))
                .addGap(32, 32, 32))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(13, 13, 13)
                        .addComponent(SchoolName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(ContactPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ContactNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(15, 15, 15)
                        .addComponent(TIN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SaveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SaveChangesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveChangesMouseClicked

        dispose();
    }//GEN-LAST:event_SaveChangesMouseClicked

    private void SaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveChangesActionPerformed
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to update this school's details?", "Update", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            String name = (String) SchoolName.getSelectedItem();
            EDIT:
            try {
                sqlConn = DriverManager.getConnection(dataConn, username, password);
                pst = sqlConn.prepareStatement("UPDATE school_client SET ScContactName = ?, ScPhoneNumber = ?, ScTIN = ?, ScEmail = ?, ScAddress = ? WHERE ScName = ?");
                    String Person = ContactPerson.getText();           
                    String Number = ContactNumber.getText();
                    String Address = SchoolAddress.getText();
                    String tin = TIN.getText();
                    String Email = txtEmail.getText();
                    int Personlength = Person.length();
                    int Numberlength = Number.length();
                    int Addresslength = Address.length();
                    int tinlength = tin.length();
                    int Emaillength = Email.length();
                        if(Personlength == 0 || Numberlength == 0 || Addresslength == 0 || tinlength == 0 || Emaillength == 0) {
                            JOptionPane.showMessageDialog(this, "Incomplete School Information, please try again.");
                            break EDIT;
                        }
                        if(tinlength != 14 ){
                            JOptionPane.showMessageDialog(this, "Invalid Tax Identification Number. Please try again.");
                            break EDIT;
                        }
                        if(Numberlength != 11||!Number.startsWith("09")){
                            JOptionPane.showMessageDialog(this, "Invalid Phone number. Please try again.");
                            break EDIT;
                        }
                        if(!Email.contains("@")&&!Email.contains(".com")){
                            JOptionPane.showMessageDialog(this, "Invalid Email Address. Please try again.");
                            break EDIT;
                        }
                pst.setString(1, ContactPerson.getText());
                pst.setString(2, Number);
                pst.setString(3, tin);
                pst.setString(4, txtEmail.getText());
                pst.setString(5, SchoolAddress.getText());
                pst.setString(6, name);
                pst.executeUpdate();
                pst.execute();
                JOptionPane.showMessageDialog(rootPane, "Updated!");
                Main.updateSchools();
                sqlConn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            } 
        }
    }//GEN-LAST:event_SaveChangesActionPerformed

    private void CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelMouseClicked
        dispose();
    }//GEN-LAST:event_CancelMouseClicked

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelActionPerformed

    private void TINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TINActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TINActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void ContactPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactPersonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ContactPersonActionPerformed

    private void ContactNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ContactNumberActionPerformed

    private void SchoolNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SchoolNameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SchoolNameMouseClicked

    private void ContactNumberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ContactNumberKeyTyped
            char c = evt.getKeyChar();
            if((!Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
                evt.consume();              
            }
            int limit = 10;
            int PhoneNumlength = ContactNumber.getText().length();
            if(PhoneNumlength > limit){
                evt.consume();
            }
    }//GEN-LAST:event_ContactNumberKeyTyped

    private void ContactPersonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ContactPersonKeyTyped
            char c = evt.getKeyChar();
            if((Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
                evt.consume();              
            }
    }//GEN-LAST:event_ContactPersonKeyTyped

    private void TINKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TINKeyTyped
            char c = evt.getKeyChar();
            if((!Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
                evt.consume();              
            }
            int limit = 13;
            int PhoneNumlength = TIN.getText().length();
            if(PhoneNumlength > limit){
                evt.consume();
            }
    }//GEN-LAST:event_TINKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JTextField ContactNumber;
    private javax.swing.JTextField ContactPerson;
    private javax.swing.JButton SaveChanges;
    private javax.swing.JTextArea SchoolAddress;
    private javax.swing.JComboBox<String> SchoolName;
    private javax.swing.JTextField TIN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables
}