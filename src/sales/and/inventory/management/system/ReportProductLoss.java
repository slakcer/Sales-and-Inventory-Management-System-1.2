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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;


/**
 *
 * @author juanm
 */
public class ReportProductLoss extends javax.swing.JFrame {
    private static final String username = JDBC.username;
    private static final String password = JDBC.password;
    private static final String dataConn = JDBC.dataConn;

    Connection sqlConn =null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null, pst3 = null;
    ResultSet rs=null, rs2=null,rs3=null;
  
    /**
     * Creates new form EditOrder
     */
    public ReportProductLoss() {
        initComponents();
        JDBC db = new JDBC();
        db.Connect();
        getDescription();
        setBackground(new Color(0,0,0,0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
        public void getDescription()
    {
        try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT PrName FROM product");
            rs = pst.executeQuery();
            while (rs.next()) {
                String Description = rs.getString("PrName");
                cboProduct.addItem(Description);
            }
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Please contact your admin for assistance.");
        }
    }
        public void ReportLoss()
            {
            try{
                String Product = (String)cboProduct.getSelectedItem();
                int PrID = 0, Price=0, PrStock=0;
                pst = sqlConn.prepareStatement("SELECT PrID, PrUnitPrice, PrStock FROM product WHERE PrName = '"+Product+"'");
                rs = pst.executeQuery();
                if (rs.next()) {
                PrID = rs.getInt("PrID");
                Price = rs.getInt("PrUnitPrice");
                PrStock = rs.getInt("PrStock");
                }
                pst.close();
                pst = sqlConn.prepareStatement("INSERT INTO losses (PrID, LossQuantity, LossTotalCost, LossDate) values (?,?,?,?)");  
                pst.setInt(1, PrID);
                String strQuantity = txtQuantity.getText();
                int Quantity = Integer.parseInt(strQuantity);
                int LossCost = Quantity * Price;
                pst.setInt(2, Quantity);
                pst.setInt(3, LossCost);
                LocalDate Date = LocalDate.now();
                DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formatteddate = Date.format(dateformat);
                pst.setString(4, formatteddate);
                pst.executeUpdate();
                pst.close();
                pst = sqlConn.prepareStatement("UPDATE product SET PrStock = ? WHERE PrName = '"+Product+"'");
                pst.setInt(1, PrStock-Quantity);
                pst.executeUpdate();
                pst.close();
                JOptionPane.showMessageDialog(this, "Successfully reported!");
            }
            catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Please input quantity!.");    
            }
            catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please contact your admin for assistance.");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new swing.PanelBorder();
        jLabel4 = new javax.swing.JLabel();
        cboProduct = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        Cancel = new javax.swing.JButton();
        btnReportLoss = new javax.swing.JButton();
        panelBorder2 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(102, 255, 204));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Product ID");

        cboProduct.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cboProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboProductMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("Quantity");

        txtQuantity.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuantityKeyTyped(evt);
            }
        });

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

        btnReportLoss.setBackground(new java.awt.Color(0, 0, 51));
        btnReportLoss.setFont(new java.awt.Font("Roboto", 0, 27)); // NOI18N
        btnReportLoss.setForeground(new java.awt.Color(255, 255, 255));
        btnReportLoss.setText("Report");
        btnReportLoss.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportLoss.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportLossMouseClicked(evt);
            }
        });
        btnReportLoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportLossActionPerformed(evt);
            }
        });

        panelBorder2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Report Loss");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jLabel1)
                .addContainerGap(170, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(Cancel)
                        .addGap(18, 18, 18)
                        .addComponent(btnReportLoss))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtQuantity)
                        .addComponent(jLabel6)
                        .addComponent(cboProduct, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(cboProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReportLoss, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
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

    
    private void btnReportLossMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportLossMouseClicked
        dispose();
    }//GEN-LAST:event_btnReportLossMouseClicked

    private void btnReportLossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportLossActionPerformed
        ReportLoss();
        Main.updateInventory();
        Main.RentalandSellInstances();
        txtQuantity.setText("");
    }//GEN-LAST:event_btnReportLossActionPerformed

    private void CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelMouseClicked
       dispose();
    }//GEN-LAST:event_CancelMouseClicked

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed
            if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                ReportLoss();
                Main.updateInventory();
            } 
    }//GEN-LAST:event_txtQuantityKeyPressed

    private void cboProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboProductMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboProductMouseClicked

    private void txtQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyTyped
        char a = evt.getKeyChar();       
        if(!Character.isDigit(a))
        {
            evt.consume();
        }
    }//GEN-LAST:event_txtQuantityKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JButton btnReportLoss;
    private static javax.swing.JComboBox<String> cboProduct;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
