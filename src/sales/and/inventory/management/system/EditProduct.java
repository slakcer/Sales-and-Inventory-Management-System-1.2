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
public class EditProduct extends javax.swing.JFrame {
    private static final String username = JDBC.username;
    private static final String password = JDBC.password;
    private static final String dataConn = JDBC.dataConn;

    Connection sqlConn =null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null, pst3 = null;
    ResultSet rs=null, rs2=null,rs3=null;
    /**
     * Creates new form EditProduct
     */
    public EditProduct() {
        initComponents();
        JDBC db = new JDBC();
        db.Connect();
        getCategory();
        getProduct();
        setBackground(new Color(0,0,0,0));
    }
    public final void getCategory(){
            try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT CaDescription FROM product_category");
            rs = pst.executeQuery();
            while (rs.next()) {
                String bnk = rs.getString("CaDescription");
                CategoryName.addItem(bnk);
            }
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Please contact your admin for assistance.");
        }
    }
    public final void getProduct(){
            try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT PrName FROM product");
            rs = pst.executeQuery();
            while (rs.next()) {
                String bnk = rs.getString("PrName");
                ProductName.addItem(bnk);
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
        jLabel6 = new javax.swing.JLabel();
        ProductName = new javax.swing.JComboBox<>();
        txtBarcode = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CategoryName = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtCost = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Stock = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtUnitPrice = new javax.swing.JTextField();
        Cancel = new javax.swing.JButton();
        SaveChanges = new javax.swing.JButton();
        panelBorder2 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelBorder1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Product Name");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("Product Barcode");

        ProductName.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        ProductName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProductNameMouseClicked(evt);
            }
        });

        txtBarcode.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcodeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("Category");

        CategoryName.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        CategoryName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CategoryNameMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("Product Cost");

        txtCost.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("Stock");

        Stock.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("Product Unit Price");

        txtUnitPrice.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtUnitPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitPriceActionPerformed(evt);
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

        SaveChanges.setBackground(new java.awt.Color(207, 124, 6));
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

        panelBorder2.setBackground(new java.awt.Color(20, 30, 86));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Edit Product");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(Cancel)
                        .addGap(18, 18, 18)
                        .addComponent(SaveChanges))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(Stock, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(txtBarcode, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                                    .addComponent(txtUnitPrice)))
                            .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(CategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtCost))))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(13, 13, 13)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Stock))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(13, 13, 13)
                        .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
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

    private void CategoryNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CategoryNameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CategoryNameMouseClicked

    private void SaveChangesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveChangesMouseClicked

        dispose();
    }//GEN-LAST:event_SaveChangesMouseClicked

    private void SaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveChangesActionPerformed
        try {
            String Category = (String) CategoryName.getSelectedItem();
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT * FROM product_category WHERE CaDescription = ?");
            pst.setString(1, Category);
            rs = pst.executeQuery();
            while (rs.next()) {
                String ID = rs.getString("CaID");
                int unitprice = Integer.parseInt(txtUnitPrice.getText());
                double VATableSales = (double)Math.round((unitprice/1.12)*100)/100;
                double VAT = (double)Math.round((VATableSales*0.12)*100)/100;
                int p = JOptionPane.showConfirmDialog(null, "Do you really want to update this product's details?", "Update", JOptionPane.YES_NO_OPTION);
                if (p == 0) {
                String Product = (String) ProductName.getSelectedItem();
            try {
                sqlConn = DriverManager.getConnection(dataConn, username, password);
                pst = sqlConn.prepareStatement("UPDATE product SET CaID = ?, PrCost = ?, PrUnitPrice = ?, PrVAT = ?, PrStock = ? , PrBarcode = ? WHERE PrName = ?");
                pst.setString(1, ID);
                pst.setInt(2, Integer.parseInt(txtCost.getText()));
                pst.setInt(3, Integer.parseInt(txtUnitPrice.getText()));
                pst.setDouble(4, VAT);
                pst.setInt(5, Integer.parseInt(Stock.getText())); 
                String name = (String)ProductName.getSelectedItem();
                String bar = txtBarcode.getText();
                String code = null;
                pst2 = sqlConn.prepareStatement("SELECT PrBarcode FROM product WHERE PrName = '"+name+"'");
                rs2 = pst2.executeQuery();
                if(rs2.next())
                {
                    code = rs2.getString("PrBarcode");
                }
                pst2.close();
                if(!bar.isEmpty()){
                pst.setString(6, bar);    
                }
                else{
                pst.setString(6, code);       
                }
                pst.setString(7, Product);
                pst.executeUpdate();
                pst.execute();
                JOptionPane.showMessageDialog(rootPane, "Updated!");
                sqlConn.close();
                Main.updateInventory();
                Main.RentalandSellInstances();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);
            } 
        }
            }
            
             
        }
        catch (NumberFormatException e) {
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Please complete details!");
        } 
        catch (Exception e) {
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Please contact your admin for assistance.");
        }  
        dispose(); 
        
    }//GEN-LAST:event_SaveChangesActionPerformed

    private void CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelMouseClicked

        dispose();
    }//GEN-LAST:event_CancelMouseClicked

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelActionPerformed

    private void txtCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostActionPerformed

    private void StockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StockActionPerformed

    private void txtBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarcodeActionPerformed

    private void ProductNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProductNameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductNameMouseClicked

    private void txtUnitPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnitPriceActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JComboBox<String> CategoryName;
    private javax.swing.JComboBox<String> ProductName;
    private javax.swing.JButton SaveChanges;
    private javax.swing.JTextField Stock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
