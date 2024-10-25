/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sales.and.inventory.management.system;

import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author juanm
 */
public class Checkout extends javax.swing.JFrame {
    private static final String username = JDBC.username;
    private static final String password = JDBC.password;
    private static final String dataConn = JDBC.dataConn;
    public static int payment = 0;
    public static String ModeofPayment = null;
    public static int userlevel = Signin.userlevel;
    generateReports r = new generateReports();
    Connection sqlConn =null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null, pst3 = null;
    ResultSet rs=null, rs2=null,rs3=null;
    static generateReceipt g = new generateReceipt();
    /**
     * Creates new form AddProduct
     */
    public Checkout() {
        initComponents();
        JDBC db = new JDBC();
        db.Connect();
        setBackground(new Color(0,0,0,0));
    }
    public static int getPayment(int pay){
            try{
                String strpayment = txtPaymentAmount.getText();
                payment = Integer.parseInt(strpayment);
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(Main.frame, "Please input payment amount");
            }           
            pay = payment;
            return pay;
    }
    public static String getModeofPayment(String MoP){
            try{
            String mop = (String)cboMoP.getSelectedItem();
            ModeofPayment = mop;  
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(Main.frame, "Please contact your admin for assistance!");
            }
            MoP = ModeofPayment;

            return MoP;
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
        jLabel4 = new javax.swing.JLabel();
        cboMoP = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtPaymentAmount = new javax.swing.JTextField();
        Cancel = new javax.swing.JButton();
        Checkout = new javax.swing.JButton();
        panelBorder2 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelBorder1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Mode of Payment");

        cboMoP.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cboMoP.setModel(new javax.swing.DefaultComboBoxModel<>(new String []{"","Cash","Gcash"}));
        cboMoP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboMoPMouseClicked(evt);
            }
        });
        cboMoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMoPActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("Payment Amount");

        txtPaymentAmount.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtPaymentAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaymentAmountActionPerformed(evt);
            }
        });

        Cancel.setBackground(new java.awt.Color(204, 204, 204));
        Cancel.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
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

        Checkout.setBackground(new java.awt.Color(207, 124, 6));
        Checkout.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        Checkout.setForeground(new java.awt.Color(255, 255, 255));
        Checkout.setText("Checkout");
        Checkout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Checkout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CheckoutMouseClicked(evt);
            }
        });
        Checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckoutActionPerformed(evt);
            }
        });

        panelBorder2.setBackground(new java.awt.Color(20, 30, 86));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Checkout");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPaymentAmount)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboMoP, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(Cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Checkout)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMoP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(143, 143, 143))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelActionPerformed

    private void CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelMouseClicked
        dispose();
    }//GEN-LAST:event_CancelMouseClicked

    private void CheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckoutActionPerformed
      getModeofPayment(ModeofPayment);  
      if(ModeofPayment == "Gcash"){
        int a = JOptionPane.showConfirmDialog(null, "Confirm payment amount?", "Payment Confirmation", JOptionPane.YES_NO_OPTION);
        if(a == 0){
          dispose();
          GcashReference show = new GcashReference();
          show.setVisible(true);
          }        
      }
      else{
        int a = JOptionPane.showConfirmDialog(null, "Confirm payment amount?", "Payment Confirmation", JOptionPane.YES_NO_OPTION);
        if(a == 0){
            getPayment(payment);           
            switch(userlevel){
            case 1:
                CashierPOS.MakeRetailTransaction();
                break;
            case 2:
                Main.MakeRetailTransaction(); 
                break;
            default:
                JOptionPane.showMessageDialog(rootPane, "Please contact your admin for assistance!");
        }
            g.generateTapeReceipt();
            Main.updateInventory();
            Main.getTransactions();
            dispose();
          }        
      }
    }//GEN-LAST:event_CheckoutActionPerformed

    private void CheckoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CheckoutMouseClicked

    }//GEN-LAST:event_CheckoutMouseClicked

    private void cboMoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMoPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMoPActionPerformed

    private void cboMoPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboMoPMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMoPMouseClicked

    private void txtPaymentAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaymentAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaymentAmountActionPerformed
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private static javax.swing.JButton Checkout;
    private static javax.swing.JComboBox<String> cboMoP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private static javax.swing.JTextField txtPaymentAmount;
    // End of variables declaration//GEN-END:variables
}
