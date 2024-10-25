/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sales.and.inventory.management.system;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author juanm
 */
public class DeleteToga extends javax.swing.JFrame {
    private static final String username = JDBC.username;
    private static final String password = JDBC.password;
    private static final String dataConn = JDBC.dataConn;

    Connection sqlConn =null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null, pst3 = null;
    ResultSet rs=null, rs2=null,rs3=null;
    /**
     * Creates new form DeleteToga
     */
    public DeleteToga() {
        initComponents();
        JDBC db = new JDBC();
        db.Connect();
        getToga();
        setBackground(new Color(0,0,0,0));
    }
    public final void getToga(){
            try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT TogaDescription FROM toga");
            rs = pst.executeQuery();
            while (rs.next()) {
                String Description = rs.getString("TogaDescription");
                DescriptionCombo.addItem(Description);
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
        jLabel4 = new javax.swing.JLabel();
        DescriptionCombo = new javax.swing.JComboBox<>();
        Cancel = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        panelBorder2 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelBorder1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Toga Description");

        DescriptionCombo.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DescriptionCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DescriptionComboMouseClicked(evt);
            }
        });
        DescriptionCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescriptionComboActionPerformed(evt);
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

        Delete.setBackground(new java.awt.Color(207, 124, 6));
        Delete.setFont(new java.awt.Font("Roboto", 0, 27)); // NOI18N
        Delete.setForeground(new java.awt.Color(255, 255, 255));
        Delete.setText("Delete");
        Delete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteMouseClicked(evt);
            }
        });
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        panelBorder2.setBackground(new java.awt.Color(20, 30, 86));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Delete Toga");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(jLabel1)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(Cancel)
                        .addGap(25, 25, 25)
                        .addComponent(Delete))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DescriptionCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(DescriptionCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
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

    private void DeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteMouseClicked

        dispose();
    }//GEN-LAST:event_DeleteMouseClicked

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        deleteToga();
        Main.updateToga();
    }//GEN-LAST:event_DeleteActionPerformed

    private void CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelMouseClicked
        dispose();
    }//GEN-LAST:event_CancelMouseClicked

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelActionPerformed

    private void DescriptionComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DescriptionComboMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DescriptionComboMouseClicked

    private void DescriptionComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescriptionComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DescriptionComboActionPerformed
    public void deleteToga(){
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            String Toga = (String) DescriptionCombo.getSelectedItem();
            try {
                sqlConn = DriverManager.getConnection(dataConn, username, password);
                pst = sqlConn.prepareStatement("DELETE FROM toga WHERE TogaDescription = ? " );
                pst.setString(1, Toga);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(rootPane, "Deleted!");
                sqlConn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);

            } 
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JButton Delete;
    private javax.swing.JComboBox<String> DescriptionCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    // End of variables declaration//GEN-END:variables
}
