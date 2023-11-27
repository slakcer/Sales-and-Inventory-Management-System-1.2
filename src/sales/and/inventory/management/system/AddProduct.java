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
import javax.swing.JOptionPane;

/**
 *
 * @author juanm
 */
public class AddProduct extends javax.swing.JFrame {
    private static final String username = JDBC.username;
    private static final String password = JDBC.password;
    private static final String dataConn = JDBC.dataConn;

    Connection sqlConn =null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null, pst3 = null, pst4 = null;
    ResultSet rs=null, rs2=null,rs3=null, rs4=null;
    Color borderColor = new Color(13, 20, 57);
    /**
     * Creates new form AddProduct
     */
    public AddProduct() {
        initComponents();
        JDBC db = new JDBC();
        db.Connect();
        getCategory();
        getProvider();
        setBackground(new Color(0,0,0,0));
    }
    public final void getCategory(){
            try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT CaDescription FROM product_category");
            rs = pst.executeQuery();
            while (rs.next()) {
                String bnk = rs.getString("CaDescription");
                cboCategory.addItem(bnk);
            }
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(this, "Please contact your admin for assistance.");
        }
    }
    
    public final void getProvider(){
            try {
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("SELECT ProviderName FROM provider");
            rs = pst.executeQuery();
            while (rs.next()) {
                String bnk = rs.getString("ProviderName");
                cboProvider.addItem(bnk);
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
        txtProduct = new TextField("Product Name");
        txtBarcode = new TextField("Product Barcode (Optional)");
        cboCategory = new Combobox<>("Category");
        txtCost = new TextField("Product Cost");
        txtStock = new TextField("Stock");
        txtUnitPrice = new TextField("Product Unit Price");
        Cancel = new javax.swing.JButton();
        AddProduct = new javax.swing.JButton();
        panelBorder2 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        cboProvider = new Combobox<>("Provider");
        prodDescription = new TextField("Description");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        txtProduct.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        txtProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProductActionPerformed(evt);
            }
        });

        txtBarcode.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        txtBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcodeActionPerformed(evt);
            }
        });

        cboCategory.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        cboCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboCategoryMouseClicked(evt);
            }
        });

        txtCost.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        txtCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostActionPerformed(evt);
            }
        });

        txtStock.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });

        txtUnitPrice.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        txtUnitPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitPriceActionPerformed(evt);
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

        AddProduct.setBackground(new java.awt.Color(207, 124, 6));
        AddProduct.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        AddProduct.setForeground(new java.awt.Color(255, 255, 255));
        AddProduct.setText("Add Product");
        AddProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddProductMouseClicked(evt);
            }
        });
        AddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProductActionPerformed(evt);
            }
        });

        panelBorder2.setBackground(new java.awt.Color(20, 30, 86));
        panelBorder2.setForeground(new java.awt.Color(60, 63, 65));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Add Product");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(257, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(251, 251, 251))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        cboProvider.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        cboProvider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboProviderMouseClicked(evt);
            }
        });

        prodDescription.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        prodDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prodDescriptionActionPerformed(evt);
            }
        });

        panelBorder1.setBorder(new RoundedBorder(20, borderColor));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(Cancel)
                                .addGap(18, 18, 18)
                                .addComponent(AddProduct))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtProduct)
                                    .addComponent(cboCategory, javax.swing.GroupLayout.Alignment.TRAILING, 0, 328, Short.MAX_VALUE)
                                    .addComponent(txtStock)
                                    .addComponent(prodDescription))
                                .addGap(40, 40, 40)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUnitPrice)
                                    .addComponent(txtCost)
                                    .addComponent(txtBarcode, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboProvider, 0, 336, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBarcode, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCost, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUnitPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(cboProvider, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(prodDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddProduct)
                    .addComponent(Cancel))
                .addGap(30, 30, 30))
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

    private void txtProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProductActionPerformed

    private void AddProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddProductMouseClicked

        dispose();
    }//GEN-LAST:event_AddProductMouseClicked

    private void AddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProductActionPerformed
       //UPDATED LAST 20/02/2023 4:06AM
       addProduct();
       txtProduct.setText("");
       txtBarcode.setText("");
       txtCost.setText("");
       txtStock.setText("");
       txtUnitPrice.setText("");
       prodDescription.setText("");
       Main.updateInventory();
    
    }//GEN-LAST:event_AddProductActionPerformed

    private void CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelMouseClicked

        dispose();
    }//GEN-LAST:event_CancelMouseClicked

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelActionPerformed

    private void txtCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostActionPerformed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockActionPerformed

    private void cboCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboCategoryMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCategoryMouseClicked

    private void txtBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarcodeActionPerformed

    private void txtUnitPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnitPriceActionPerformed

    private void cboProviderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboProviderMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboProviderMouseClicked

    private void prodDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prodDescriptionActionPerformed
    public void addProduct(){
         try
        {                  
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst2 = sqlConn.prepareStatement("SELECT CaID, CaDescription FROM product_category WHERE CaDescription = ?");
            String cbocategory = (String)cboCategory.getSelectedItem();
            String Provider = (String)cboProvider.getSelectedItem();
            System.out.println(cbocategory);
            pst2.setString(1, cbocategory);
            rs2 = pst2.executeQuery();
            while(rs2.next()){ 
                String category = rs2.getString("CaDescription");
                int categoryID = rs2.getInt("CaID");  
                System.out.println(categoryID);
                if(category.equals(cbocategory)){                 
                    int CaID = rs2.getInt("CaID");
                    //ADDING PRODUCT 11/01/2023
                    pst = sqlConn.prepareStatement("INSERT into product(PrID, PrCost, PrName, PrUnitPrice, PrStock, CaID, PrBarcode, PrVAT, ProvID)values"
                    + "(?,?,?,?,?,?,?,?,?)"); 
                    String barcode = txtBarcode.getText();
                    String strbarcode=null;
                    int PrBarcode = 0; 
                    if(barcode.isEmpty()){
                    int min = 100000000;
                    int max = 1000000000; 
                    PrBarcode = (int)Math.floor(Math.random() * (max - min + 1) + min);
                    strbarcode = Integer.toString(PrBarcode);
                    }
                    else{
                    strbarcode = barcode;    
                    }
                    //GENERATION OF RANDOM BARCODE VALUE 11/01/2023                                     
                    int cost = Integer.parseInt(txtCost.getText());
                    //SELECTING LAST PRODUCT ID FROM A CATEGORY	13/01/2023
                    pst3 = sqlConn.prepareStatement("SELECT PrID, CaID FROM product WHERE PrID = (SELECT MAX(PrID) FROM product WHERE CaID = ?)");
                    pst3.setInt(1, categoryID);
                    rs3 = pst3.executeQuery(); 
                    if(rs3.next()){ 
                        int product = rs3.getInt("PrID");
                        if(product != 0){
                        int ProID = product + 1;
                        pst.setInt(1, ProID);
                        }
   
                    } 
                    else{
                        String strPrID = Integer.toString(CaID)+"0001";
                        int ProID = Integer.parseInt(strPrID); 
                        pst.setInt(1, ProID); 
                    }
                    pst.setInt(2, cost);
                    pst.setString(3, txtProduct.getText());
                    int unitprice = Integer.parseInt(txtUnitPrice.getText());
                    pst.setInt(4, unitprice); 
                    int stock = Integer.parseInt(txtStock.getText());
                    //COMPUTATION OF VAT AND VATABLE SALES 28/01/2023
                    double VATableSales = (double)Math.round((unitprice/1.12)*100)/100;
                    double VAT = (double)Math.round((VATableSales*0.12)*100)/100;
                    pst.setInt(5, stock);
                    pst.setInt(6, CaID); 
                    pst.setString(7, strbarcode);
                    pst.setDouble(8, VAT);
                    pst4 = sqlConn.prepareStatement("SELECT * FROM provider WHERE ProviderName = '"+Provider+"'");
                    rs4 = pst4.executeQuery(); 
                    if(rs4.next()){
                        int provID = rs4.getInt("ProvID"); 
                        pst.setInt(9, provID); 
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Contact admin for assistance!");
                    }
                    pst.executeUpdate();
                    pst.close();
                    JOptionPane.showMessageDialog(this, "Product Added!");
                    }
                else{
                    JOptionPane.showMessageDialog(this, "Please Add a Category First!");
                }
                }         
            sqlConn.close();    
        }
        catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Please input the right data type.");
        } 
        catch(HeadlessException | NumberFormatException ex){
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Please input details");
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddProduct;
    private javax.swing.JButton Cancel;
    private javax.swing.JComboBox<String> cboCategory;
    private javax.swing.JComboBox<String> cboProvider;
    private javax.swing.JLabel jLabel1;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JTextField prodDescription;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtCost;
    private javax.swing.JTextField txtProduct;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtUnitPrice;
    // End of variables declaration//GEN-END:variables
}
