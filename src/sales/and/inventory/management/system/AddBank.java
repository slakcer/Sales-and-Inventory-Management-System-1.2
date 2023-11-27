/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sales.and.inventory.management.system;


import java.awt.Color;
import java.awt.HeadlessException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author juanm
 */
public class AddBank extends javax.swing.JFrame {
        private static final String username = JDBC.username;
        private static final String password = JDBC.password;
        private static final String dataConn = JDBC.dataConn;
        JDBC db = new JDBC();
        Connection sqlConn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
    /**
     * Creates new form AddBank
     */
    public AddBank() {
        initComponents();
        db.Connect();    
        setBackground(new Color(0,0,0,0));
    }
    
    Color borderColor = new Color(13, 20, 57);
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SaveChanges = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        BankName = new TextField("Bank Name");
        panelBorder1 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(638, 383));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        SaveChanges.setBackground(new java.awt.Color(207, 124, 6));
        SaveChanges.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
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

        Cancel.setBackground(new java.awt.Color(207, 124, 6));
        Cancel.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        Cancel.setForeground(new java.awt.Color(255, 255, 255));
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

        BankName.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        BankName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BankNameActionPerformed(evt);
            }
        });

        panelBorder1.setBackground(new java.awt.Color(20, 30, 86));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add Bank");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel1)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel1.setBorder(new RoundedBorder(20, borderColor));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Cancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SaveChanges))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(BankName, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(BankName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel)
                    .addComponent(SaveChanges))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BankNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BankNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BankNameActionPerformed

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelActionPerformed

    private void CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelMouseClicked
        dispose();
    }//GEN-LAST:event_CancelMouseClicked

    private void SaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveChangesActionPerformed
        AddBank();
        BankName.setText("");
        Main.updateBanks();
    }//GEN-LAST:event_SaveChangesActionPerformed

    private void SaveChangesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveChangesMouseClicked
        dispose();
    }//GEN-LAST:event_SaveChangesMouseClicked
    public void AddBank(){
        try
         {      
            sqlConn = DriverManager.getConnection(dataConn,username, password);

            pst = sqlConn.prepareStatement("INSERT into bank(BaName)values"
                    + "(?)");

                    pst.setString(1, BankName.getText());
                    pst.executeUpdate();
                    pst.close(); 
                    JOptionPane.showMessageDialog(this, "Bank has been added successfuly!");
                    sqlConn.close();       
        } 
        catch (SQLException ex) {
            if (ex.getSQLState().startsWith("23")) {
            JOptionPane.showMessageDialog(this, "Duplicate entry, bank is already added!");
            }
        } 
        catch(HeadlessException | NumberFormatException ex){
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Please input details");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BankName;
    private javax.swing.JButton Cancel;
    private javax.swing.JButton SaveChanges;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
