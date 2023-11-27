/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sales.and.inventory.management.system;

import java.awt.geom.*;
import com.raven.chart.ModelChart;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.UIResource;
import javax.swing.table.TableCellRenderer;




/**
 *
 * @author juanm
 */


public class Main extends javax.swing.JFrame {
    
    private static final String username = JDBC.username;
    private static final String password = JDBC.password;
    private static final String dataConn = JDBC.dataConn;
    public static String userloggedin = Signin.userloggedin;
    public static int userID = Signin.userID;
    public static String SystemDate = null;
    public static String Gcash = GcashReference.Gcash;
    public static String MoP = Checkout.ModeofPayment;
    public static int Payment = Checkout.payment;
    public static JFrame frame;
    static Connection sqlConn =null;
    static PreparedStatement pst = null, pst2 = null, pst3 = null, pst4 = null,pst5 = null,pst6 = null,pst7 = null,pst8 = null;
    static ResultSet rs=null, rs2=null,rs3=null, rs4=null, rs5=null,rs6=null, r7=null, rs8=null;
    JDBC db = new JDBC();
    generateReceipt g = new generateReceipt();
    generateReports r = new generateReports();

    


    

    /**
     * Creates new form Main
     */
   public Main() {
        initComponents();
        init();
        setBackground(new Color(0,0,0,0));
        getDate(SystemDate);
        showDate(SystemDate);       
        showTime();     
        db.Connect();
        updateInventory();
        updateCategory();
        updateProvider();
        updateSchools();
        updateBanks(); 
        getUserInfo();
        getTransactions();
        updateInvoice();
        updateToga();
        updateBusinessInfo();
        updateAccountInfo();
        getSales();
        RentalandSellInstances();
        generateInvoiceFolder();
        generateOfficialReceiptFolder();
        generateInventoryReportFolder();
        generateSalesReportFolder();
        generateSalesandInventoryDocumentsFolder();
        generateTapeReceiptFolder();      
        
        try {        
            ShowYear(); 
        } catch (Exception e) {
            e.printStackTrace();
        }   
        chart.addLegend("Gross Income", new Color(245, 189, 135));
        chart.addLegend("Expense", new Color(135, 189, 245));
        chart.addLegend("Profit", new Color(189, 135, 245));
        
    }
   

        public static void RentalandSellInstances(){
            try{               
            String plural = null;
            LocalDate date = LocalDate.now();
            LocalDate firstday = date.with(TemporalAdjusters.firstDayOfYear());
            LocalDate lastday = date.with(TemporalAdjusters.lastDayOfYear());
            LocalDate firstdaymonth = date.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate lastdaymonth = date.with(TemporalAdjusters.lastDayOfMonth());
            DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter month = DateTimeFormatter.ofPattern("MMMM");
            DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
            String first = dateformat.format(firstday);
            String last = dateformat.format(lastday);
            String Year = year.format(date);
            String Month1 = dateformat.format(firstdaymonth);
            String Month2 = dateformat.format(lastdaymonth);
            String Month = month.format(date);
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT S.ScName, COUNT(I.ScID) AS MAXCOUNT FROM invoice I INNER JOIN school_client S WHERE I.ScID=S.ScID AND InDate BETWEEN '"+first+"' AND '"+last+"' GROUP BY S.ScName\n" +
            "HAVING COUNT(I.ScID)=(SELECT MAX(mycount) AS M  FROM (SELECT ScID, COUNT(ScID) mycount  FROM invoice GROUP BY ScID)AS T);"); 
            rs = pst.executeQuery();
                if(rs.next()){
                    String most = rs.getString("ScName");
                    String count = rs.getString("MAXCOUNT");
                    AnnualRent.setText(Year);
                    MostSchoolRents.setText(most);
                    int intcount = Integer.parseInt(count);
                    if(intcount>1){
                        plural = " Rentals";
                    }
                    else{
                        plural = " Rental";
                    }
                    MostSchoolRentsCount.setText(count+plural);
                }
                else{
                    AnnualRent.setText(Year);
                    MostSchoolRents.setText("No products sold yet");
                    MostSchoolRentsCount.setText("");
                }
            pst.close();
            pst = sqlConn.prepareStatement("SELECT S.ScName, I.ScID, COUNT(I.ScID) AS MINCOUNT FROM invoice I INNER JOIN school_client S WHERE I.ScID=S.ScID AND InDate BETWEEN '"+first+"' AND '"+last+"' GROUP BY S.ScName\n" +
            "HAVING COUNT(I.ScID)=(SELECT MIN(mycount) AS M  FROM (SELECT ScID, COUNT(ScID) mycount  FROM invoice GROUP BY ScID)AS T);"); 
            rs = pst.executeQuery();
                if(rs.next()){
                    String min = rs.getString("ScName");
                    String count = rs.getString("MINCOUNT");                   
                    AnnualRent.setText(Year);
                    LeastSchoolRents.setText(min);
                    int intcount = Integer.parseInt(count);
                    if(intcount>1){
                        plural = " Rentals";
                    }
                    else{
                        plural = " Rental";
                    }
                    LeastSchoolRentsCount.setText(count+plural);
                }
                else{
                    AnnualRent.setText(Year);
                    LeastSchoolRents.setText("No products sold yet");
                    LeastSchoolRentsCount.setText("");
                }
            pst.close();
            pst = sqlConn.prepareStatement("SELECT P.PrName, T.PrID, T.SUM FROM (SELECT PrID, SUM(OrDetQuantity) AS SUM FROM order_details D INNER JOIN orders O WHERE O.OrNumber=D.OrNumber AND O.OrDate BETWEEN '"+Month1+"' AND '"+Month2+"' GROUP BY PrID) AS T INNER JOIN product P WHERE P.PrID=T.PrID AND T.SUM=(SELECT MAX(S) AS M FROM (SELECT PrID, SUM(OrDetQuantity) AS S FROM order_details D INNER JOIN orders O WHERE O.OrNumber=D.OrNumber AND O.OrDate BETWEEN '"+Month1+"' AND '"+Month2+"' GROUP BY PrID) AS W)");
            rs = pst.executeQuery();
                if(rs.next()){
                    String most = rs.getString("P.PrName");
                    String count = rs.getString("T.SUM");
                    MonthSold.setText(Month);
                    MostProductSold.setText(most);
                    int intcount = Integer.parseInt(count);
                    if(intcount>1){
                        plural = " Items";
                    }
                    else{
                        plural = " Item";
                    }
                    MostProductSoldCount.setText(count+plural);
                }
                else{
                    MonthSold.setText(Month);
                    MostProductSold.setText("No products sold yet");
                    MostProductSoldCount.setText("");
                }
            pst.close();
            pst = sqlConn.prepareStatement("SELECT P.PrName, T.PrID, T.SUM FROM (SELECT PrID, SUM(OrDetQuantity) AS SUM FROM order_details D INNER JOIN orders O WHERE O.OrNumber=D.OrNumber AND O.OrDate BETWEEN '"+Month1+"' AND '"+Month2+"' GROUP BY PrID) AS T INNER JOIN product P WHERE P.PrID=T.PrID AND T.SUM=(SELECT MIN(S) AS M FROM (SELECT PrID, SUM(OrDetQuantity) AS S FROM order_details D INNER JOIN orders O WHERE O.OrNumber=D.OrNumber AND O.OrDate BETWEEN '"+Month1+"' AND '"+Month2+"' GROUP BY PrID) AS W)");
            rs = pst.executeQuery();
                if(rs.next()){
                    String min = rs.getString("P.PrName");
                    String count = rs.getString("T.SUM");
                    MonthSold.setText(Month);
                    LeastProductSold.setText(min);
                    int intcount = Integer.parseInt(count);
                    if(intcount>1){
                        plural = " Items";
                    }
                    else{
                        plural = " Item";
                    }
                    LeastProductSoldCount.setText(count+plural);
                }
                else{
                    MonthSold.setText(Month);
                    LeastProductSold.setText("No products sold yet");
                    LeastProductSoldCount.setText("");
                }
            pst.close();
            pst = sqlConn.prepareStatement("SELECT PrName, PrStock FROM product WHERE PrStock = (SELECT MAX(PrStock) FROM product)");           
            rs = pst.executeQuery();
                if(rs.next()){
                    String min = rs.getString("PrName");
                    String count = rs.getString("PrStock");
                    MostProductStock.setText(min);
                    MostProductCount.setText(count);
                    int intcount = Integer.parseInt(count);
                    if(intcount>1){
                        plural = " Items";
                    }
                    else{
                        plural = " Item";
                    }
                    MostProductCount.setText(count+plural);
                }
                else{
                    MostProductStock.setText("No stock");
                    MostProductCount.setText("");
                }
            pst.close();
            pst = sqlConn.prepareStatement("SELECT PrName, PrStock FROM product WHERE PrStock = (SELECT MIN(PrStock) FROM product)");
            rs = pst.executeQuery();
                if(rs.next()){
                    String min = rs.getString("PrName");
                    String count = rs.getString("PrStock");
                    LeastProductStock.setText(min);
                    LeastProductCount.setText(count);
                    int intcount = Integer.parseInt(count);
                    if(intcount>1){
                        plural = " Items";
                    }
                    else{
                        plural = " Item";
                    }
                    LeastProductCount.setText(count+plural);
                }
                else{
                    LeastProductStock.setText("No stock");
                    LeastProductCount.setText("");
                }
            pst.close();
            }          
            catch(Exception e){           
            }
        }
   
        private void init(){
            BankTable.fixTable(jScrollPane6);
            BankTable.fixTable(jScrollPane1);
            BankTable.fixTable(jScrollPane9);
            BankTable.fixTable(jScrollPane11);
            BankTable.fixTable(jScrollPane7);
            BankTable.fixTable(jScrollPane10);
            BankTable.fixTable(jScrollPane4);
            BankTable.fixTable(jScrollPane3);
            BankTable.fixTable(jScrollPane2);
        }
        public final void getUserInfo(){
            User.setText(userloggedin);
        }
        public final void showDate(String Date)
        {
            Datetxt.setText(Date);
        }
        public final void changePage(String page)
        {
            Page.setText(page);
        }
        public String getDate(String date)
        {
            java.util.Date d = new java.util.Date(); 
            SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
            date = s.format(d);
            SystemDate = date;
            return date;
        }
        
        public final void showTime()
        {
            new Timer (0, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    Date d = new Date();
                    SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss");
                    String tim = s.format(d);
                    Timetxt.setText(tim);
                }
            }) .start();
        }
    
        public static final void updateInventory()
    {
         try
        { 
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT * from product P INNER JOIN product_category C, provider R WHERE P.CaID = C.CaID AND P.ProvID = R.ProvID");
            
            rs =pst.executeQuery();
            ResultSetMetaData StData = rs.getMetaData();          
            int q = StData.getColumnCount();           
            DefaultTableModel Inventory = (DefaultTableModel)InventoryTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER );
            InventoryTable.setDefaultRenderer(Object.class, centerRenderer);
            Inventory.setRowCount(0);      
            while(rs.next()){              
                Vector columnData = new Vector();            
                for (int i = 1; i <= q; i++)
                {                   
                    columnData.add(rs.getString("P.PrID"));
                    columnData.add(rs.getString("P.PrBarcode"));
                    columnData.add(rs.getString("P.PrName"));
                    columnData.add(rs.getString("C.CaDescription"));
                    columnData.add(rs.getString("P.PrCost"));
                    columnData.add(rs.getString("P.PrUnitPrice"));
                    columnData.add(rs.getString("P.PrStock"));   
                    columnData.add(rs.getString("R.ProviderName"));
                    columnData.add(rs.getString("P.PrDateReceived"));
                }
                    Inventory.addRow(columnData);                               
            }
         
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }        
    }
        public static final void updateCategory()
    {
         try
        { 
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT * from product_category");        
            rs =pst.executeQuery();
            ResultSetMetaData StData = rs.getMetaData();
            
            int q = StData.getColumnCount();           
            DefaultTableModel Category = (DefaultTableModel)CategoryTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER );
            CategoryTable.setDefaultRenderer(Object.class, centerRenderer);
            Category.setRowCount(0);           
            while(rs.next()){              
                Vector columnData = new Vector();            
                for (int i = 1; i <= q; i++)
                {                   
                    columnData.add(rs.getString("CaID"));
                    columnData.add(rs.getString("CaDescription"));
                }
                    Category.addRow(columnData);                              
            }
         
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
        
    }
        
        public static final void updateProvider()
    {
         try
        { 
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT * from provider");        
            rs =pst.executeQuery();
            ResultSetMetaData StData = rs.getMetaData();
            
            int q = StData.getColumnCount();           
            DefaultTableModel Provider = (DefaultTableModel)ProviderTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER );
            ProviderTable.setDefaultRenderer(Object.class, centerRenderer);
            Provider.setRowCount(0);           
            while(rs.next()){              
                Vector columnData = new Vector();            
                for (int i = 1; i <= q; i++)
                {                   
                    columnData.add(rs.getString("ProvID"));
                    columnData.add(rs.getString("ProviderName"));
                    columnData.add(rs.getString("ProviderAddress"));
                }
                    Provider.addRow(columnData);                              
            }
         
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
        
    }
        private static final DecimalFormat decfor = new DecimalFormat("0.00"); 
                public final void  getSales(){
         try
         { 
            
            String dat, strTotalVAT, strNetIncome, date, SalesSystemDate, Today;
            java.util.Date d = new java.util.Date(); 
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            Today = s.format(d);
            SalesSystemDate = Today;
            ArrayList list = new ArrayList();
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            
            double TotalVAT = 0, NetIncome;
            int TotalCost=0, TotalAmount=0, TotalPenalties=0, TotalLosses=0, TotalRental=0, count=0;
            pst = sqlConn.prepareStatement("SELECT OrDate FROM orders GROUP BY OrDate");
            pst7 = sqlConn.prepareStatement("INSERT into sales(SalesGrossIncome, SalesTotalLoss, SalesTotalVAT, SalesTotalPenalties, SalesNetIncome, SalesDate, SalesTotalCost, SalesTotalRentalGross)values(?,?,?,?,?,?,?,?)");  
            rs =pst.executeQuery();
            while(rs.next()){
                dat = rs.getString("OrDate");
                pst8 = sqlConn.prepareStatement("SELECT COUNT(SalesDate) FROM sales");
                rs8 = pst8.executeQuery();
                if(rs8.next()){
                count = rs8.getInt(1);    
                }               
                pst8.close();
                pst8 = sqlConn.prepareStatement("SELECT SalesDate FROM sales");
                rs8 = pst8.executeQuery();
                while(rs8.next()){
                    date = rs8.getString("SalesDate");                   
                    for(int i = 0; i<=count;i++){                       
                    list.add(date);                      
                    }
                }
                if(!list.contains(dat)&&!dat.equals(SalesSystemDate)){
                pst2 = sqlConn.prepareStatement("SELECT SUM(OrLessVAT) FROM orders WHERE Ordate = '"+dat+"'");        
                rs2 =pst2.executeQuery();
                if(rs2.next()){
                    TotalVAT = rs2.getDouble(1);               
                } 
                pst2.close();
                pst2 = sqlConn.prepareStatement("SELECT SUM(OfrTotalAmountDue) FROM official_receipt WHERE OfrDate = '"+dat+"'");        
                rs2 =pst2.executeQuery();
                if(rs2.next()){
                    TotalRental = rs2.getInt(1);               
                } 
                pst2.close();
                pst3 = sqlConn.prepareStatement("SELECT SUM(OrCostTotal) FROM orders WHERE Ordate = '"+dat+"'");        
                rs3 = pst3.executeQuery();
                if(rs3.next()){
                    TotalCost = rs3.getInt(1);               
                }
                pst4 = sqlConn.prepareStatement("SELECT SUM(OrTotalAmount) FROM orders WHERE Ordate = '"+dat+"'");        
                rs4 =pst4.executeQuery();
                if(rs4.next()){
                    TotalAmount = rs4.getInt(1);               
                }
                pst5 = sqlConn.prepareStatement("SELECT SUM(PenalAmount) FROM penalties WHERE PenalDate = '"+dat+"'");        
                rs5 =pst5.executeQuery();
                if(rs5.next()){
                    TotalPenalties = rs5.getInt(1);               
                }
                pst6 = sqlConn.prepareStatement("SELECT SUM(LossTotalCost) FROM losses WHERE LossDate = '"+dat+"'");        
                rs6 =pst6.executeQuery();
                if(rs6.next()){
                    TotalLosses = rs6.getInt(1);               
                }
                NetIncome = (TotalAmount+TotalPenalties) - (TotalCost+TotalLosses);
                strTotalVAT = decfor.format(TotalVAT);
                strNetIncome = decfor.format(NetIncome);
                pst7.setInt(1, TotalAmount);
                pst7.setInt(2, TotalLosses);
                pst7.setString(3, strTotalVAT);
                pst7.setInt(4, TotalPenalties);
                pst7.setString(5, strNetIncome);
                pst7.setString(6, dat);
                pst7.setInt(7, TotalCost); 
                pst7.setInt(8, TotalRental);
                pst7.executeUpdate();    
                }
                
            }          
        
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }     
        }
        public static final void updateSchools()
    {
         try
        { 
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT * FROM school_client");        
            rs =pst.executeQuery();
            ResultSetMetaData StData = rs.getMetaData();
            
            int q = StData.getColumnCount();           
            DefaultTableModel School = (DefaultTableModel)SchoolTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER );
            SchoolTable.setDefaultRenderer(Object.class, centerRenderer);
            School.setRowCount(0);
            BigInteger bigPhone;
            BigInteger bigTIN;
            while(rs.next()){              
                Vector columnData = new Vector();            
                for (int i = 1; i <= q; i++)
                {                   
                    String Phone = rs.getString("ScPhoneNumber"); 
                    String TIN = rs.getString("ScTIN");
                    bigPhone = new BigInteger(Phone);
                    bigTIN = new BigInteger(TIN);
                    columnData.add(rs.getString("ScID"));
                    columnData.add(rs.getString("ScName"));
                    columnData.add(rs.getString("ScContactName"));
                    columnData.add(Phone);
                    columnData.add(rs.getString("ScAddress"));
                    columnData.add(rs.getString("ScEmail"));
                    columnData.add(TIN);
                
                }
                    School.addRow(columnData); 
                    
            }
         
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    }  
        public static final void updateBanks()
    {
         try
        { 
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT * FROM bank");        
            rs =pst.executeQuery();
            ResultSetMetaData StData = rs.getMetaData();
            
            int q = StData.getColumnCount();           
            DefaultTableModel Bank = (DefaultTableModel)BankTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER );
            BankTable.setDefaultRenderer(Object.class, centerRenderer);
            Bank.setRowCount(0);
            while(rs.next()){              
                Vector columnData = new Vector();            
                for (int i = 1; i <= q; i++)
                {                   

                    columnData.add(rs.getString("BaID"));
                    columnData.add(rs.getString("BaName"));                
                }
                    Bank.addRow(columnData);                 
            }
         
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
        
    }
        public static final void getTransactions()
    {
         try
        { 

            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT O.OrID, P.PayAmount, O.OrNumber, O.OrGCashReference, O.OrTotalAmount, O.OrDate FROM orders O INNER JOIN payment P WHERE O.OrID = P.OrID;");        
            rs = pst.executeQuery();
            ResultSetMetaData StData = rs.getMetaData();
            
            int q = StData.getColumnCount();
            
            DefaultTableModel Order = (DefaultTableModel)OrderTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER );
            OrderTable.setDefaultRenderer(Object.class, centerRenderer);
            Order.setRowCount(0);           
            while(rs.next()){              
                Vector columnData = new Vector();            
                for (int i = 1; i <= q; i++)
                {                   
                    columnData.add(rs.getString("OrID"));                  
                    columnData.add(rs.getString("OrNumber"));
                    columnData.add(rs.getString("OrGCashReference"));                 
                    columnData.add(rs.getString("OrTotalAmount"));
                    columnData.add(rs.getString("PayAmount"));
                    columnData.add(rs.getString("OrDate"));           
                }
                    Order.addRow(columnData);                              
            }       
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }       
    }  
    public static final void updateInvoice()
    {
         try
        { 
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT * from invoice I INNER JOIN school_client S WHERE I.ScID = S.ScID");
            
            rs =pst.executeQuery();
            ResultSetMetaData StData = rs.getMetaData();          
            int q = StData.getColumnCount();           
            DefaultTableModel Invoice = (DefaultTableModel)InvoiceTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER );
            InvoiceTable.setDefaultRenderer(Object.class, centerRenderer);
            Invoice.setRowCount(0);      
            while(rs.next()){              
                Vector columnData = new Vector();            
                for (int i = 1; i <= q; i++)
                {                   
                    columnData.add(rs.getString("I.InID"));
                    columnData.add(rs.getString("S.ScName"));
                    columnData.add(rs.getString("I.InAmount"));
                    columnData.add(rs.getString("I.InDate"));
                    columnData.add(rs.getString("I.InStatus"));
                }
                    Invoice.addRow(columnData);                
                
            }
         
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
        
    }
    public static final void updateToga()
    {
         try
        { 
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT * from toga T INNER JOIN school_client S WHERE T.ScID = S.ScID");
            
            rs =pst.executeQuery();
            ResultSetMetaData StData = rs.getMetaData();          
            int q = StData.getColumnCount();           
            DefaultTableModel Toga = (DefaultTableModel)TogaTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER );
            TogaTable.setDefaultRenderer(Object.class, centerRenderer);
            Toga.setRowCount(0);      
            while(rs.next()){              
                Vector columnData = new Vector();            
                for (int i = 1; i <= q; i++)
                {                   
                    columnData.add(rs.getString("S.ScName"));
                    columnData.add(rs.getString("T.TogaDescription"));
                    columnData.add(rs.getString("T.TogaStock"));   
                }
                    Toga.addRow(columnData);                
                
            }
         
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
        
    }
    public final void updateBusinessInfo()
    {
         try
        { 
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT * FROM business_info");        
            rs =pst.executeQuery();
          
            while(rs.next()){              
                BusinessName.setText(rs.getString("BuName"));
                BusinessTIN.setText(rs.getString("BuTIN"));
                BusinessAddress.setText(rs.getString("BuAddress"));
                BusinessContact.setText(rs.getString("BuEmail"));
                BusinessPhone.setText(rs.getString("BuPhoneNumber"));                
            }
         
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    }
    public final void updateAccountInfo()
    {
         try
        { 
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT * FROM user where UID = ?");
            pst.setInt(1, userID);
            rs =pst.executeQuery();
          
            while(rs.next()){              
                Firstname.setText(rs.getString("UFname"));
                Lastname.setText(rs.getString("ULname"));
                Username.setText(rs.getString("Uname"));
                EmailAddress.setText(rs.getString("UEmail"));
                PhoneNumber.setText(rs.getString("UPhoneNumber"));
                    
            }
         
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    } 
    public class jGradientPanel extends JPanel {
        
    private int cornerRadius;

    public jGradientPanel(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false);  // Make the panel transparent so that the background gradient shows through
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Call super.paintComponent(g) first

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        
        Shape roundedRect = new RoundRectangle2D.Double(0, 0, width, height, cornerRadius, cornerRadius);
        g2d.setClip(roundedRect);

        Color startColor = new Color(0x0B1E33);  // RGB code #0B1E33
        Color endColor = new Color(0xF5A201);   // RGB code #F5A201
        GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, width, 0, endColor);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, width, height);
        }
    }
    public class CustomHeaderRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            label.setHorizontalAlignment(JLabel.CENTER );
            // Customize the font for the header
            label.setFont(label.getFont().deriveFont(Font.BOLD, 14)); // Change the font size and style as needed
            return label;
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

        jTextField1 = new javax.swing.JTextField();
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        panelBorder1 = new swing.PanelBorder();
        panelBorder9 = new swing.PanelBorder();
        jLabel37 = new javax.swing.JLabel();
        Page = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        Datetxt = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        Timetxt = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        User = new javax.swing.JLabel();
        panelBorder2 = new swing.PanelBorder();
        Sidepanel = new javax.swing.JPanel();
        Brands = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Category = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Inventory = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        POS = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        School = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        Transaction = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        SalesReport = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Business = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Account = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        Signout = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Dashboard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelBorder3 = new swing.PanelBorder();
        MainPanel = new javax.swing.JLayeredPane();
        DashboardP = new javax.swing.JPanel();
        panelBorder4 = new swing.PanelBorder();
        LeastProductSold = new javax.swing.JLabel();
        LeastProductSoldCount = new javax.swing.JLabel();
        MostSold = new javax.swing.JLabel();
        LeastSold = new javax.swing.JLabel();
        MonthSold = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        MostProductSoldCount = new javax.swing.JLabel();
        MostProductSold = new javax.swing.JLabel();
        panelBorder5 = new swing.PanelBorder();
        jPanel1 = new javax.swing.JPanel();
        HighStock = new javax.swing.JLabel();
        MostProductStock = new javax.swing.JLabel();
        MostProductCount = new javax.swing.JLabel();
        LowStock = new javax.swing.JLabel();
        LeastProductStock = new javax.swing.JLabel();
        LeastProductCount = new javax.swing.JLabel();
        DailyInventory = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        panelBorder6 = new swing.PanelBorder();
        jPanel3 = new javax.swing.JPanel();
        MostSchoolRents = new javax.swing.JLabel();
        MostSchoolRentsCount = new javax.swing.JLabel();
        LeastSchoolRents = new javax.swing.JLabel();
        LeastSchoolRentsCount = new javax.swing.JLabel();
        MostRent = new javax.swing.JLabel();
        LeastRent = new javax.swing.JLabel();
        AnnualRent = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        BrandsP = new javax.swing.JPanel();
        CategoryP1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        BankTable = new swing.Table();
        AddBank = new javax.swing.JButton();
        DeleteBank = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        AccountP = new javax.swing.JPanel();
        EmailAddress = new TextField(" ");
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        Username = new TextField(" ");
        SaveChanges2 = new javax.swing.JButton();
        Firstname = new TextField(" ");
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        Lastname = new TextField(" ");
        jLabel58 = new javax.swing.JLabel();
        PhoneNumber = new TextField(" ");
        ChangePassword1 = new javax.swing.JButton();
        TINEdit1 = new javax.swing.JLabel();
        TINEdit2 = new javax.swing.JLabel();
        TINEdit3 = new javax.swing.JLabel();
        TINEdit4 = new javax.swing.JLabel();
        TINEdit5 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        POSP = new javax.swing.JPanel();
        ResetOrder = new javax.swing.JButton();
        DeleteOrder = new javax.swing.JButton();
        EditOrder = new javax.swing.JButton();
        panelBorder17 = new swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        POSTable = new swing.Table();
        txtBarcode = new TextField("Barcode");
        jScrollPane5 = new javax.swing.JScrollPane();
        ReceiptPreview = new javax.swing.JTextArea();
        txtTotalAmount = new TextField("Total Amount");
        txtChange = new TextField("Change");
        DeleteOrder1 = new javax.swing.JButton();
        btnCheckout = new javax.swing.JButton();
        CategoryP = new javax.swing.JPanel();
        AddCategory = new javax.swing.JButton();
        DeleteCategory = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        CategoryTable = new swing.Table();
        AddProvider = new javax.swing.JButton();
        DeleteProvider = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        ProviderTable = new swing.Table();
        BusinessP = new javax.swing.JPanel();
        BusinessName = new TextField(" ");
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        BusinessTIN = new TextField(" ");
        BusinessContact = new TextField(" ");
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        BusinessAddress = new TextField(" ");
        SaveChanges = new javax.swing.JButton();
        NameEdit = new javax.swing.JLabel();
        TINEdit = new javax.swing.JLabel();
        AddressEdit = new javax.swing.JLabel();
        NumberEdit = new javax.swing.JLabel();
        BusinessPhone = new TextField(" ");
        jLabel73 = new javax.swing.JLabel();
        NumberEdit1 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        TogaInventoryP = new javax.swing.JPanel();
        AddProduct1 = new javax.swing.JButton();
        DeleteProduct1 = new javax.swing.JButton();
        TogaButton1 = new javax.swing.JButton();
        AddTogaStock = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        TogaTable = new swing.Table();
        jLabel27 = new javax.swing.JLabel();
        OrdersP = new javax.swing.JPanel();
        ViewContracts = new javax.swing.JButton();
        Search1 = new javax.swing.JButton();
        panelBorder25 = new swing.PanelBorder();
        jScrollPane10 = new javax.swing.JScrollPane();
        OrderTable = new swing.Table();
        SchoolP = new javax.swing.JPanel();
        AddSchool = new javax.swing.JButton();
        CreateContract = new javax.swing.JButton();
        DeleteSchool = new javax.swing.JButton();
        EditSchool = new javax.swing.JButton();
        panelBorder27 = new swing.PanelBorder();
        jScrollPane4 = new javax.swing.JScrollPane();
        SchoolTable = new swing.Table();
        InventoryP = new javax.swing.JPanel();
        Search = new javax.swing.JButton();
        panelBorder29 = new swing.PanelBorder();
        jScrollPane3 = new javax.swing.JScrollPane();
        InventoryTable = new swing.Table();
        AddProduct = new javax.swing.JButton();
        EditProduct = new javax.swing.JButton();
        DeleteProduct = new javax.swing.JButton();
        TogaButton = new javax.swing.JButton();
        GenerateBarcode3 = new javax.swing.JButton();
        GenerateInventoryReport1 = new javax.swing.JButton();
        AddStock = new javax.swing.JButton();
        SalesReportP = new javax.swing.JPanel();
        ComboYear = new Combobox<>(" ");
        jLabel38 = new javax.swing.JLabel();
        ComboMonth = new Combobox<>(" ");
        jLabel42 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        GenerateSalesReport = new javax.swing.JButton();
        ReportLoss = new javax.swing.JButton();
        panelBorder32 = new swing.PanelBorder();
        chart = new com.raven.chart.Chart();
        jLabel62 = new javax.swing.JLabel();
        pieChart = new sales.and.inventory.management.system.PieChart();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        ContractsP = new javax.swing.JPanel();
        ViewOrders = new javax.swing.JButton();
        ViewOrders1 = new javax.swing.JButton();
        DeleteProduct4 = new javax.swing.JButton();
        Search2 = new javax.swing.JButton();
        panelBorder33 = new swing.PanelBorder();
        jScrollPane2 = new javax.swing.JScrollPane();
        InvoiceTable = new swing.Table();
        jLabel31 = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        jMenuItem1.setText("jMenuItem1");

        jScrollPane8.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        panelBorder9.setBackground(new java.awt.Color(20, 30, 86));
        panelBorder9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sign-in logo.png"))); // NOI18N
        panelBorder9.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        Page.setFont(new java.awt.Font("Bahnschrift", 0, 30)); // NOI18N
        Page.setForeground(new java.awt.Color(255, 255, 255));
        Page.setText("Dashboard");
        panelBorder9.add(Page, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 550, 64));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/Calendar2.png"))); // NOI18N
        panelBorder9.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 10, -1, -1));

        Datetxt.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Datetxt.setForeground(new java.awt.Color(255, 255, 255));
        Datetxt.setText("Date");
        panelBorder9.add(Datetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, -1, 40));

        jLabel70.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/user.png"))); // NOI18N
        panelBorder9.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 10, -1, -1));

        Timetxt.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Timetxt.setForeground(new java.awt.Color(255, 255, 255));
        Timetxt.setText("Time");
        panelBorder9.add(Timetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, 70, 40));

        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/clock.png"))); // NOI18N
        panelBorder9.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, -1, -1));

        User.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        User.setForeground(new java.awt.Color(255, 255, 255));
        User.setText("user");
        panelBorder9.add(User, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 150, 40));

        panelBorder2.setBackground(new java.awt.Color(13, 20, 57));

        Sidepanel.setBackground(new java.awt.Color(13, 20, 57));

        Brands.setBackground(new java.awt.Color(13, 20, 57));
        Brands.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Brands.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BrandsMouseClicked(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/Brand.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("          Banks");

        javax.swing.GroupLayout BrandsLayout = new javax.swing.GroupLayout(Brands);
        Brands.setLayout(BrandsLayout);
        BrandsLayout.setHorizontalGroup(
            BrandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BrandsLayout.createSequentialGroup()
                .addGroup(BrandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BrandsLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel3))
                    .addGroup(BrandsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BrandsLayout.setVerticalGroup(
            BrandsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BrandsLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Category.setBackground(new java.awt.Color(13, 20, 57));
        Category.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Category.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CategoryMouseClicked(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/category.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("          Category");

        javax.swing.GroupLayout CategoryLayout = new javax.swing.GroupLayout(Category);
        Category.setLayout(CategoryLayout);
        CategoryLayout.setHorizontalGroup(
            CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CategoryLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CategoryLayout.setVerticalGroup(
            CategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CategoryLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(5, 5, 5))
        );

        Inventory.setBackground(new java.awt.Color(13, 20, 57));
        Inventory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InventoryMouseClicked(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/Inventory.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("          Inventory");

        javax.swing.GroupLayout InventoryLayout = new javax.swing.GroupLayout(Inventory);
        Inventory.setLayout(InventoryLayout);
        InventoryLayout.setHorizontalGroup(
            InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        InventoryLayout.setVerticalGroup(
            InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        POS.setBackground(new java.awt.Color(13, 20, 57));
        POS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        POS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                POSMouseClicked(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/Point of sale.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("        Point of Sale");

        javax.swing.GroupLayout POSLayout = new javax.swing.GroupLayout(POS);
        POS.setLayout(POSLayout);
        POSLayout.setHorizontalGroup(
            POSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(POSLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        POSLayout.setVerticalGroup(
            POSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(POSLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(5, 5, 5))
        );

        School.setBackground(new java.awt.Color(13, 20, 57));
        School.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        School.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SchoolMouseClicked(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/School.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("           Schools");

        javax.swing.GroupLayout SchoolLayout = new javax.swing.GroupLayout(School);
        School.setLayout(SchoolLayout);
        SchoolLayout.setHorizontalGroup(
            SchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SchoolLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(SchoolLayout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );
        SchoolLayout.setVerticalGroup(
            SchoolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SchoolLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Transaction.setBackground(new java.awt.Color(13, 20, 57));
        Transaction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Transaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TransactionMouseClicked(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/Transactions.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("       Transactions");

        javax.swing.GroupLayout TransactionLayout = new javax.swing.GroupLayout(Transaction);
        Transaction.setLayout(TransactionLayout);
        TransactionLayout.setHorizontalGroup(
            TransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransactionLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TransactionLayout.setVerticalGroup(
            TransactionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransactionLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        SalesReport.setBackground(new java.awt.Color(13, 20, 57));
        SalesReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SalesReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SalesReportMouseClicked(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/Sales Report.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("        Sales Report");

        javax.swing.GroupLayout SalesReportLayout = new javax.swing.GroupLayout(SalesReport);
        SalesReport.setLayout(SalesReportLayout);
        SalesReportLayout.setHorizontalGroup(
            SalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalesReportLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SalesReportLayout.setVerticalGroup(
            SalesReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalesReportLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16))
        );

        Business.setBackground(new java.awt.Color(13, 20, 57));
        Business.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Business.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BusinessMouseClicked(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/Business.png"))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("          Business");

        javax.swing.GroupLayout BusinessLayout = new javax.swing.GroupLayout(Business);
        Business.setLayout(BusinessLayout);
        BusinessLayout.setHorizontalGroup(
            BusinessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BusinessLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(BusinessLayout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        BusinessLayout.setVerticalGroup(
            BusinessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BusinessLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Account.setBackground(new java.awt.Color(13, 20, 57));
        Account.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AccountMouseClicked(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/Account.png"))); // NOI18N

        jLabel20.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("           Account");

        javax.swing.GroupLayout AccountLayout = new javax.swing.GroupLayout(Account);
        Account.setLayout(AccountLayout);
        AccountLayout.setHorizontalGroup(
            AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccountLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AccountLayout.setVerticalGroup(
            AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Signout.setBackground(new java.awt.Color(13, 20, 57));
        Signout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Signout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignoutMouseClicked(evt);
            }
        });

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/Signout.png"))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("            Sign Out");

        javax.swing.GroupLayout SignoutLayout = new javax.swing.GroupLayout(Signout);
        Signout.setLayout(SignoutLayout);
        SignoutLayout.setHorizontalGroup(
            SignoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignoutLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SignoutLayout.setVerticalGroup(
            SignoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SignoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Dashboard.setBackground(new java.awt.Color(13, 20, 57));
        Dashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashboardMouseClicked(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/dashboard.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("        Dashboard");

        javax.swing.GroupLayout DashboardLayout = new javax.swing.GroupLayout(Dashboard);
        Dashboard.setLayout(DashboardLayout);
        DashboardLayout.setHorizontalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DashboardLayout.setVerticalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout SidepanelLayout = new javax.swing.GroupLayout(Sidepanel);
        Sidepanel.setLayout(SidepanelLayout);
        SidepanelLayout.setHorizontalGroup(
            SidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Category, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Inventory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(POS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(School, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Transaction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(SalesReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Business, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Account, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Signout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(SidepanelLayout.createSequentialGroup()
                .addComponent(Dashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(SidepanelLayout.createSequentialGroup()
                .addComponent(Brands, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        SidepanelLayout.setVerticalGroup(
            SidepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidepanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Brands, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Inventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(POS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(School, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Transaction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SalesReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(Business, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Account, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Signout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Sidepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addComponent(Sidepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        MainPanel.setBackground(new java.awt.Color(0, 204, 204));
        MainPanel.setLayout(new java.awt.CardLayout());

        DashboardP.setBackground(new java.awt.Color(255, 255, 255));

        panelBorder4.setBackground(new java.awt.Color(248, 165, 47));
        panelBorder4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LeastProductSold.setBackground(new java.awt.Color(0, 0, 0));
        LeastProductSold.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        LeastProductSold.setForeground(new java.awt.Color(0, 0, 0));
        LeastProductSold.setText("Shorts");
        panelBorder4.add(LeastProductSold, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, -1, -1));

        LeastProductSoldCount.setBackground(new java.awt.Color(0, 0, 0));
        LeastProductSoldCount.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        LeastProductSoldCount.setForeground(new java.awt.Color(0, 0, 0));
        LeastProductSoldCount.setText("3 Items");
        panelBorder4.add(LeastProductSoldCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, -1));

        MostSold.setBackground(new java.awt.Color(0, 0, 0));
        MostSold.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        MostSold.setForeground(new java.awt.Color(0, 0, 0));
        MostSold.setText("Topmost product sold for the month");
        panelBorder4.add(MostSold, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        LeastSold.setBackground(new java.awt.Color(0, 0, 0));
        LeastSold.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        LeastSold.setForeground(new java.awt.Color(0, 0, 0));
        LeastSold.setText("Least product sold for the month");
        panelBorder4.add(LeastSold, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        MonthSold.setBackground(new java.awt.Color(0, 0, 0));
        MonthSold.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        MonthSold.setForeground(new java.awt.Color(0, 0, 0));
        MonthSold.setText("December");
        panelBorder4.add(MonthSold, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jPanel2.setBackground(new java.awt.Color(248, 165, 47));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/products.png"))); // NOI18N
        jPanel2.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 60, 100));

        jLabel30.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Top Product Sold");
        jPanel2.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 200, 27));

        MostProductSoldCount.setBackground(new java.awt.Color(0, 0, 0));
        MostProductSoldCount.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        MostProductSoldCount.setForeground(new java.awt.Color(0, 0, 0));
        MostProductSoldCount.setText("16 Items");
        jPanel2.add(MostProductSoldCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        MostProductSold.setBackground(new java.awt.Color(0, 0, 0));
        MostProductSold.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        MostProductSold.setForeground(new java.awt.Color(0, 0, 0));
        MostProductSold.setText("Sando");
        jPanel2.add(MostProductSold, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        panelBorder4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 460));

        panelBorder5.setBackground(new java.awt.Color(254, 200, 44));
        panelBorder5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(254, 200, 44));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HighStock.setBackground(new java.awt.Color(0, 0, 0));
        HighStock.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        HighStock.setForeground(new java.awt.Color(0, 0, 0));
        HighStock.setText("Highest Product Stock");
        jPanel1.add(HighStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        MostProductStock.setBackground(new java.awt.Color(0, 0, 0));
        MostProductStock.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        MostProductStock.setForeground(new java.awt.Color(0, 0, 0));
        MostProductStock.setText("Mongol Pencil");
        jPanel1.add(MostProductStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        MostProductCount.setBackground(new java.awt.Color(0, 0, 0));
        MostProductCount.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        MostProductCount.setForeground(new java.awt.Color(0, 0, 0));
        MostProductCount.setText("50 Items");
        jPanel1.add(MostProductCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        LowStock.setBackground(new java.awt.Color(0, 0, 0));
        LowStock.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        LowStock.setForeground(new java.awt.Color(0, 0, 0));
        LowStock.setText("Lowest Product Stock");
        jPanel1.add(LowStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        LeastProductStock.setBackground(new java.awt.Color(0, 0, 0));
        LeastProductStock.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        LeastProductStock.setForeground(new java.awt.Color(0, 0, 0));
        LeastProductStock.setText("Uniqlo Tshirt XL");
        jPanel1.add(LeastProductStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        LeastProductCount.setBackground(new java.awt.Color(0, 0, 0));
        LeastProductCount.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        LeastProductCount.setForeground(new java.awt.Color(0, 0, 0));
        LeastProductCount.setText("5 Items");
        jPanel1.add(LeastProductCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, -1));

        DailyInventory.setBackground(new java.awt.Color(0, 0, 0));
        DailyInventory.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        DailyInventory.setForeground(new java.awt.Color(0, 0, 0));
        DailyInventory.setText("Today");
        jPanel1.add(DailyInventory, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 104, -1, 50));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/orders.png"))); // NOI18N
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 60, -1));

        jLabel39.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Inventory");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 27));

        panelBorder5.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 340, 540));

        panelBorder6.setBackground(new java.awt.Color(253, 201, 159));
        panelBorder6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(253, 201, 159));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MostSchoolRents.setBackground(new java.awt.Color(0, 0, 0));
        MostSchoolRents.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        MostSchoolRents.setForeground(new java.awt.Color(0, 0, 0));
        MostSchoolRents.setText("PUP");
        jPanel3.add(MostSchoolRents, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        MostSchoolRentsCount.setBackground(new java.awt.Color(0, 0, 0));
        MostSchoolRentsCount.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        MostSchoolRentsCount.setForeground(new java.awt.Color(0, 0, 0));
        MostSchoolRentsCount.setText("12 Rentals");
        jPanel3.add(MostSchoolRentsCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        LeastSchoolRents.setBackground(new java.awt.Color(0, 0, 0));
        LeastSchoolRents.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        LeastSchoolRents.setForeground(new java.awt.Color(0, 0, 0));
        LeastSchoolRents.setText("UE");
        jPanel3.add(LeastSchoolRents, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        LeastSchoolRentsCount.setBackground(new java.awt.Color(0, 0, 0));
        LeastSchoolRentsCount.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        LeastSchoolRentsCount.setForeground(new java.awt.Color(0, 0, 0));
        LeastSchoolRentsCount.setText("1 Rental");
        jPanel3.add(LeastSchoolRentsCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, -1));

        MostRent.setBackground(new java.awt.Color(0, 0, 0));
        MostRent.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        MostRent.setForeground(new java.awt.Color(0, 0, 0));
        MostRent.setText("The current topmost rental client ");
        jPanel3.add(MostRent, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 178, -1, -1));

        LeastRent.setBackground(new java.awt.Color(0, 0, 0));
        LeastRent.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        LeastRent.setForeground(new java.awt.Color(0, 0, 0));
        LeastRent.setText("The current least rental client ");
        jPanel3.add(LeastRent, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, -1, -1));

        AnnualRent.setBackground(new java.awt.Color(0, 0, 0));
        AnnualRent.setFont(new java.awt.Font("Bahnschrift", 0, 36)); // NOI18N
        AnnualRent.setForeground(new java.awt.Color(0, 0, 0));
        AnnualRent.setText("2023");
        jPanel3.add(AnnualRent, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/toga.png"))); // NOI18N
        jPanel3.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, 83));

        jLabel41.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Rentals");
        jPanel3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        panelBorder6.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, -1, -1));

        javax.swing.GroupLayout DashboardPLayout = new javax.swing.GroupLayout(DashboardP);
        DashboardP.setLayout(DashboardPLayout);
        DashboardPLayout.setHorizontalGroup(
            DashboardPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardPLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder6, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        DashboardPLayout.setVerticalGroup(
            DashboardPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashboardPLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(DashboardPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBorder4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder6, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(147, Short.MAX_VALUE))
        );

        MainPanel.add(DashboardP, "card2");

        BrandsP.setBackground(new java.awt.Color(255, 255, 255));

        CategoryP1.setBackground(new java.awt.Color(255, 255, 255));

        BankTable.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        BankTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        BankTable.setForeground(new java.awt.Color(0, 0, 0));
        BankTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bank ID", "Bank Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        BankTable.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jScrollPane6.setViewportView(BankTable);

        AddBank.setBackground(new java.awt.Color(207, 124, 6));
        AddBank.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        AddBank.setForeground(new java.awt.Color(255, 255, 255));
        AddBank.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/plus.png"))); // NOI18N
        AddBank.setText("Add Bank");
        AddBank.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddBank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddBankMouseClicked(evt);
            }
        });
        AddBank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBankActionPerformed(evt);
            }
        });

        DeleteBank.setBackground(new java.awt.Color(207, 124, 6));
        DeleteBank.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        DeleteBank.setForeground(new java.awt.Color(255, 255, 255));
        DeleteBank.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/bin.png"))); // NOI18N
        DeleteBank.setText("Delete Bank");
        DeleteBank.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteBank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteBankMouseClicked(evt);
            }
        });
        DeleteBank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteBankActionPerformed(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/bank.gif"))); // NOI18N

        javax.swing.GroupLayout CategoryP1Layout = new javax.swing.GroupLayout(CategoryP1);
        CategoryP1.setLayout(CategoryP1Layout);
        CategoryP1Layout.setHorizontalGroup(
            CategoryP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CategoryP1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CategoryP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CategoryP1Layout.createSequentialGroup()
                        .addComponent(AddBank)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteBank))
                    .addGroup(CategoryP1Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CategoryP1Layout.setVerticalGroup(
            CategoryP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CategoryP1Layout.createSequentialGroup()
                .addGroup(CategoryP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddBank, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteBank, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(CategoryP1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CategoryP1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel23))
                    .addGroup(CategoryP1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BrandsPLayout = new javax.swing.GroupLayout(BrandsP);
        BrandsP.setLayout(BrandsPLayout);
        BrandsPLayout.setHorizontalGroup(
            BrandsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CategoryP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        BrandsPLayout.setVerticalGroup(
            BrandsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CategoryP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MainPanel.add(BrandsP, "card3");

        AccountP.setBackground(new java.awt.Color(255, 255, 255));

        EmailAddress.setEditable(false);
        EmailAddress.setBackground(new java.awt.Color(255, 255, 255));
        EmailAddress.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        EmailAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                EmailAddressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                EmailAddressFocusLost(evt);
            }
        });
        EmailAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailAddressActionPerformed(evt);
            }
        });

        jLabel52.setBackground(new java.awt.Color(255, 0, 255));
        jLabel52.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 0, 51));
        jLabel52.setText("Username");

        jLabel53.setBackground(new java.awt.Color(255, 0, 255));
        jLabel53.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 0, 51));
        jLabel53.setText("Email Address");

        Username.setEditable(false);
        Username.setBackground(new java.awt.Color(255, 255, 255));
        Username.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                UsernameFocusLost(evt);
            }
        });
        Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameActionPerformed(evt);
            }
        });

        SaveChanges2.setBackground(new java.awt.Color(207, 124, 6));
        SaveChanges2.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        SaveChanges2.setForeground(new java.awt.Color(255, 255, 255));
        SaveChanges2.setText("Save Changes");
        SaveChanges2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SaveChanges2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SaveChanges2MouseClicked(evt);
            }
        });
        SaveChanges2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveChanges2ActionPerformed(evt);
            }
        });

        Firstname.setEditable(false);
        Firstname.setBackground(new java.awt.Color(255, 255, 255));
        Firstname.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Firstname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                FirstnameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                FirstnameFocusLost(evt);
            }
        });
        Firstname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FirstnameActionPerformed(evt);
            }
        });

        jLabel56.setBackground(new java.awt.Color(255, 0, 255));
        jLabel56.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 0, 51));
        jLabel56.setText("First name");

        jLabel57.setBackground(new java.awt.Color(255, 0, 255));
        jLabel57.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 51));
        jLabel57.setText("Last name");

        Lastname.setEditable(false);
        Lastname.setBackground(new java.awt.Color(255, 255, 255));
        Lastname.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        Lastname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                LastnameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                LastnameFocusLost(evt);
            }
        });
        Lastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LastnameActionPerformed(evt);
            }
        });

        jLabel58.setBackground(new java.awt.Color(255, 0, 255));
        jLabel58.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 51));
        jLabel58.setText("Phone Number");

        PhoneNumber.setEditable(false);
        PhoneNumber.setBackground(new java.awt.Color(255, 255, 255));
        PhoneNumber.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        PhoneNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PhoneNumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PhoneNumberFocusLost(evt);
            }
        });
        PhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneNumberActionPerformed(evt);
            }
        });

        ChangePassword1.setBackground(new java.awt.Color(207, 124, 6));
        ChangePassword1.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        ChangePassword1.setForeground(new java.awt.Color(255, 255, 255));
        ChangePassword1.setText("Change Password");
        ChangePassword1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ChangePassword1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChangePassword1MouseClicked(evt);
            }
        });
        ChangePassword1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangePassword1ActionPerformed(evt);
            }
        });

        TINEdit1.setBackground(new java.awt.Color(255, 0, 255));
        TINEdit1.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        TINEdit1.setForeground(new java.awt.Color(51, 0, 204));
        TINEdit1.setText("Edit");
        TINEdit1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TINEdit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TINEdit1MouseClicked(evt);
            }
        });

        TINEdit2.setBackground(new java.awt.Color(255, 0, 255));
        TINEdit2.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        TINEdit2.setForeground(new java.awt.Color(51, 0, 204));
        TINEdit2.setText("Edit");
        TINEdit2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TINEdit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TINEdit2MouseClicked(evt);
            }
        });

        TINEdit3.setBackground(new java.awt.Color(255, 0, 255));
        TINEdit3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        TINEdit3.setForeground(new java.awt.Color(51, 0, 204));
        TINEdit3.setText("Edit");
        TINEdit3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TINEdit3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TINEdit3MouseClicked(evt);
            }
        });

        TINEdit4.setBackground(new java.awt.Color(255, 0, 255));
        TINEdit4.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        TINEdit4.setForeground(new java.awt.Color(51, 0, 204));
        TINEdit4.setText("Edit");
        TINEdit4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TINEdit4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TINEdit4MouseClicked(evt);
            }
        });

        TINEdit5.setBackground(new java.awt.Color(255, 0, 255));
        TINEdit5.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        TINEdit5.setForeground(new java.awt.Color(51, 0, 204));
        TINEdit5.setText("Edit");
        TINEdit5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TINEdit5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TINEdit5MouseClicked(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/account.png"))); // NOI18N

        javax.swing.GroupLayout AccountPLayout = new javax.swing.GroupLayout(AccountP);
        AccountP.setLayout(AccountPLayout);
        AccountPLayout.setHorizontalGroup(
            AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccountPLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AccountPLayout.createSequentialGroup()
                        .addGroup(AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(EmailAddress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                            .addComponent(Username, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lastname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Firstname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PhoneNumber))
                        .addGap(30, 30, 30)
                        .addGroup(AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TINEdit1)
                            .addComponent(TINEdit2)
                            .addComponent(TINEdit3)
                            .addComponent(TINEdit4)
                            .addComponent(TINEdit5)))
                    .addGroup(AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(SaveChanges2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ChangePassword1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addGap(96, 96, 96))
        );
        AccountPLayout.setVerticalGroup(
            AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccountPLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel56)
                .addGap(18, 18, 18)
                .addGroup(AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AccountPLayout.createSequentialGroup()
                        .addGroup(AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Firstname, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TINEdit1))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel57)
                        .addGap(18, 18, 18)
                        .addGroup(AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TINEdit2))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel52)
                        .addGap(18, 18, 18)
                        .addGroup(AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TINEdit3))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel53)
                        .addGap(18, 18, 18)
                        .addGroup(AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EmailAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TINEdit4))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel58)
                        .addGap(18, 18, 18)
                        .addGroup(AccountPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TINEdit5))
                        .addGap(18, 18, 18)
                        .addComponent(SaveChanges2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(ChangePassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel24))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        MainPanel.add(AccountP, "card12");

        POSP.setBackground(new java.awt.Color(255, 255, 255));
        POSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                POSPKeyPressed(evt);
            }
        });
        POSP.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ResetOrder.setBackground(new java.awt.Color(207, 124, 6));
        ResetOrder.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        ResetOrder.setForeground(new java.awt.Color(255, 255, 255));
        ResetOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/reset.png"))); // NOI18N
        ResetOrder.setText("Reset ");
        ResetOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ResetOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResetOrderMouseClicked(evt);
            }
        });
        ResetOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetOrderActionPerformed(evt);
            }
        });
        POSP.add(ResetOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 420, 150, 50));

        DeleteOrder.setBackground(new java.awt.Color(207, 124, 6));
        DeleteOrder.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        DeleteOrder.setForeground(new java.awt.Color(255, 255, 255));
        DeleteOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/bin.png"))); // NOI18N
        DeleteOrder.setText("Delete ");
        DeleteOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteOrderMouseClicked(evt);
            }
        });
        DeleteOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteOrderActionPerformed(evt);
            }
        });
        POSP.add(DeleteOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 520, 150, 50));

        EditOrder.setBackground(new java.awt.Color(207, 124, 6));
        EditOrder.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        EditOrder.setForeground(new java.awt.Color(255, 255, 255));
        EditOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/pencil.png"))); // NOI18N
        EditOrder.setText("Edit");
        EditOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EditOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditOrderMouseClicked(evt);
            }
        });
        EditOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditOrderActionPerformed(evt);
            }
        });
        POSP.add(EditOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 320, 150, 50));

        panelBorder17.setBackground(new java.awt.Color(255, 255, 255));

        POSTable.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        POSTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Quantity", "Unit Price", "Total Price"
            }
        ));
        POSTable.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        POSTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        POSTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        POSTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        POSTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        POSTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        jScrollPane1.setViewportView(POSTable);

        javax.swing.GroupLayout panelBorder17Layout = new javax.swing.GroupLayout(panelBorder17);
        panelBorder17.setLayout(panelBorder17Layout);
        panelBorder17Layout.setHorizontalGroup(
            panelBorder17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelBorder17Layout.setVerticalGroup(
            panelBorder17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder17Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        POSP.add(panelBorder17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, -20, 657, 780));

        txtBarcode.setBackground(new java.awt.Color(255, 255, 255));
        txtBarcode.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        txtBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarcodeActionPerformed(evt);
            }
        });
        txtBarcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBarcodeKeyPressed(evt);
            }
        });
        POSP.add(txtBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 320, 200, 50));

        ReceiptPreview.setColumns(20);
        ReceiptPreview.setRows(5);
        jScrollPane5.setViewportView(ReceiptPreview);

        POSP.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 0, 410, 300));

        txtTotalAmount.setEditable(false);
        txtTotalAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalAmount.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        POSP.add(txtTotalAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 420, 200, 50));

        txtChange.setEditable(false);
        txtChange.setBackground(new java.awt.Color(255, 255, 255));
        txtChange.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        txtChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChangeActionPerformed(evt);
            }
        });
        POSP.add(txtChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 520, 200, 50));

        DeleteOrder1.setBackground(new java.awt.Color(207, 124, 6));
        DeleteOrder1.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        DeleteOrder1.setForeground(new java.awt.Color(255, 255, 255));
        DeleteOrder1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/search.png"))); // NOI18N
        DeleteOrder1.setText("Check Price");
        DeleteOrder1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteOrder1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteOrder1MouseClicked(evt);
            }
        });
        DeleteOrder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteOrder1ActionPerformed(evt);
            }
        });
        POSP.add(DeleteOrder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 620, 200, 50));

        btnCheckout.setBackground(new java.awt.Color(207, 124, 6));
        btnCheckout.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        btnCheckout.setForeground(new java.awt.Color(255, 255, 255));
        btnCheckout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/checkout.png"))); // NOI18N
        btnCheckout.setText("Checkout");
        btnCheckout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCheckout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCheckoutMouseClicked(evt);
            }
        });
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });
        POSP.add(btnCheckout, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 620, 150, 50));

        MainPanel.add(POSP, "card6");

        CategoryP.setBackground(new java.awt.Color(255, 255, 255));

        AddCategory.setBackground(new java.awt.Color(207, 124, 6));
        AddCategory.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        AddCategory.setForeground(new java.awt.Color(255, 255, 255));
        AddCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/plus.png"))); // NOI18N
        AddCategory.setText("Add Category");
        AddCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddCategoryMouseClicked(evt);
            }
        });
        AddCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCategoryActionPerformed(evt);
            }
        });

        DeleteCategory.setBackground(new java.awt.Color(207, 124, 6));
        DeleteCategory.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        DeleteCategory.setForeground(new java.awt.Color(255, 255, 255));
        DeleteCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/bin.png"))); // NOI18N
        DeleteCategory.setText("Delete Category");
        DeleteCategory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteCategoryMouseClicked(evt);
            }
        });
        DeleteCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteCategoryActionPerformed(evt);
            }
        });

        CategoryTable.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        CategoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category ID", "Category Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CategoryTable.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        CategoryTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane9.setViewportView(CategoryTable);
        if (CategoryTable.getColumnModel().getColumnCount() > 0) {
            CategoryTable.getColumnModel().getColumn(0).setResizable(false);
            CategoryTable.getColumnModel().getColumn(1).setResizable(false);
        }

        AddProvider.setBackground(new java.awt.Color(207, 124, 6));
        AddProvider.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        AddProvider.setForeground(new java.awt.Color(255, 255, 255));
        AddProvider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/plus.png"))); // NOI18N
        AddProvider.setText("Add Provider");
        AddProvider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddProvider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddProviderMouseClicked(evt);
            }
        });
        AddProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProviderActionPerformed(evt);
            }
        });

        DeleteProvider.setBackground(new java.awt.Color(207, 124, 6));
        DeleteProvider.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        DeleteProvider.setForeground(new java.awt.Color(255, 255, 255));
        DeleteProvider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/bin.png"))); // NOI18N
        DeleteProvider.setText("Delete Provider");
        DeleteProvider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteProvider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteProviderMouseClicked(evt);
            }
        });
        DeleteProvider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteProviderActionPerformed(evt);
            }
        });

        ProviderTable.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        ProviderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Provider ID", "Name", "Address"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ProviderTable.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        ProviderTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(ProviderTable);
        if (ProviderTable.getColumnModel().getColumnCount() > 0) {
            ProviderTable.getColumnModel().getColumn(0).setResizable(false);
            ProviderTable.getColumnModel().getColumn(1).setResizable(false);
            ProviderTable.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout CategoryPLayout = new javax.swing.GroupLayout(CategoryP);
        CategoryP.setLayout(CategoryPLayout);
        CategoryPLayout.setHorizontalGroup(
            CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CategoryPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CategoryPLayout.createSequentialGroup()
                        .addComponent(AddCategory)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteCategory))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CategoryPLayout.createSequentialGroup()
                        .addComponent(AddProvider)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteProvider)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        CategoryPLayout.setVerticalGroup(
            CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CategoryPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddProvider, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteProvider, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CategoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        MainPanel.add(CategoryP, "card4");

        BusinessP.setBackground(new java.awt.Color(255, 255, 255));
        BusinessP.setPreferredSize(new java.awt.Dimension(1088, 715));

        BusinessName.setEditable(false);
        BusinessName.setBackground(new java.awt.Color(255, 255, 255));
        BusinessName.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        BusinessName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BusinessNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BusinessNameFocusLost(evt);
            }
        });
        BusinessName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusinessNameActionPerformed(evt);
            }
        });

        jLabel48.setBackground(new java.awt.Color(255, 0, 255));
        jLabel48.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 0, 51));
        jLabel48.setText("Business Name");

        jLabel49.setBackground(new java.awt.Color(255, 0, 255));
        jLabel49.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 51));
        jLabel49.setText("Business TIN");

        BusinessTIN.setEditable(false);
        BusinessTIN.setBackground(new java.awt.Color(255, 255, 255));
        BusinessTIN.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        BusinessTIN.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BusinessTINFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BusinessTINFocusLost(evt);
            }
        });
        BusinessTIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusinessTINActionPerformed(evt);
            }
        });

        BusinessContact.setEditable(false);
        BusinessContact.setBackground(new java.awt.Color(255, 255, 255));
        BusinessContact.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        BusinessContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BusinessContactFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BusinessContactFocusLost(evt);
            }
        });
        BusinessContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusinessContactActionPerformed(evt);
            }
        });

        jLabel50.setBackground(new java.awt.Color(255, 0, 255));
        jLabel50.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 0, 51));
        jLabel50.setText("Business Address");

        jLabel51.setBackground(new java.awt.Color(255, 0, 255));
        jLabel51.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 51));
        jLabel51.setText("Business Email");

        BusinessAddress.setEditable(false);
        BusinessAddress.setBackground(new java.awt.Color(255, 255, 255));
        BusinessAddress.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        BusinessAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BusinessAddressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BusinessAddressFocusLost(evt);
            }
        });
        BusinessAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusinessAddressActionPerformed(evt);
            }
        });

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

        NameEdit.setBackground(new java.awt.Color(255, 0, 255));
        NameEdit.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        NameEdit.setForeground(new java.awt.Color(51, 0, 204));
        NameEdit.setText("Edit");
        NameEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        NameEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NameEditMouseClicked(evt);
            }
        });

        TINEdit.setBackground(new java.awt.Color(255, 0, 255));
        TINEdit.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        TINEdit.setForeground(new java.awt.Color(51, 0, 204));
        TINEdit.setText("Edit");
        TINEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TINEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TINEditMouseClicked(evt);
            }
        });

        AddressEdit.setBackground(new java.awt.Color(255, 0, 255));
        AddressEdit.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        AddressEdit.setForeground(new java.awt.Color(51, 0, 204));
        AddressEdit.setText("Edit");
        AddressEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddressEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddressEditMouseClicked(evt);
            }
        });

        NumberEdit.setBackground(new java.awt.Color(255, 0, 255));
        NumberEdit.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        NumberEdit.setForeground(new java.awt.Color(51, 0, 204));
        NumberEdit.setText("Edit");
        NumberEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        NumberEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NumberEditMouseClicked(evt);
            }
        });

        BusinessPhone.setEditable(false);
        BusinessPhone.setBackground(new java.awt.Color(255, 255, 255));
        BusinessPhone.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        BusinessPhone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                BusinessPhoneFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                BusinessPhoneFocusLost(evt);
            }
        });
        BusinessPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BusinessPhoneActionPerformed(evt);
            }
        });

        jLabel73.setBackground(new java.awt.Color(255, 0, 255));
        jLabel73.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 0, 51));
        jLabel73.setText("Business Phone Number");

        NumberEdit1.setBackground(new java.awt.Color(255, 0, 255));
        NumberEdit1.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        NumberEdit1.setForeground(new java.awt.Color(51, 0, 204));
        NumberEdit1.setText("Edit");
        NumberEdit1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        NumberEdit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NumberEdit1MouseClicked(evt);
            }
        });

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/business.png"))); // NOI18N

        javax.swing.GroupLayout BusinessPLayout = new javax.swing.GroupLayout(BusinessP);
        BusinessP.setLayout(BusinessPLayout);
        BusinessPLayout.setHorizontalGroup(
            BusinessPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BusinessPLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(BusinessPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BusinessPLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(BusinessPLayout.createSequentialGroup()
                        .addGroup(BusinessPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SaveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BusinessPLayout.createSequentialGroup()
                                .addComponent(BusinessName, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NameEdit))
                            .addComponent(jLabel49)
                            .addGroup(BusinessPLayout.createSequentialGroup()
                                .addComponent(BusinessTIN, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TINEdit))
                            .addComponent(jLabel50)
                            .addGroup(BusinessPLayout.createSequentialGroup()
                                .addComponent(BusinessAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(AddressEdit))
                            .addComponent(jLabel51)
                            .addGroup(BusinessPLayout.createSequentialGroup()
                                .addComponent(BusinessContact, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NumberEdit))
                            .addComponent(jLabel73)
                            .addGroup(BusinessPLayout.createSequentialGroup()
                                .addComponent(BusinessPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(NumberEdit1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                        .addComponent(jLabel26)
                        .addGap(90, 90, 90))))
        );
        BusinessPLayout.setVerticalGroup(
            BusinessPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BusinessPLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(BusinessPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26)
                    .addGroup(BusinessPLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(18, 18, 18)
                        .addGroup(BusinessPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BusinessName, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BusinessPLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(NameEdit)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel49)
                        .addGap(18, 18, 18)
                        .addGroup(BusinessPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BusinessTIN, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BusinessPLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(TINEdit)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel50)
                        .addGap(18, 18, 18)
                        .addGroup(BusinessPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BusinessAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BusinessPLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(AddressEdit)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel51)
                        .addGap(18, 18, 18)
                        .addGroup(BusinessPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BusinessContact, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BusinessPLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(NumberEdit)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel73)
                        .addGap(12, 12, 12)
                        .addGroup(BusinessPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BusinessPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BusinessPLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(NumberEdit1)))
                        .addGap(91, 91, 91)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(SaveChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91))
        );

        MainPanel.add(BusinessP, "card11");

        TogaInventoryP.setBackground(new java.awt.Color(255, 255, 255));

        AddProduct1.setBackground(new java.awt.Color(207, 124, 6));
        AddProduct1.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        AddProduct1.setForeground(new java.awt.Color(255, 255, 255));
        AddProduct1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/plus.png"))); // NOI18N
        AddProduct1.setText("Add Toga");
        AddProduct1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddProduct1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddProduct1MouseClicked(evt);
            }
        });
        AddProduct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProduct1ActionPerformed(evt);
            }
        });

        DeleteProduct1.setBackground(new java.awt.Color(207, 124, 6));
        DeleteProduct1.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        DeleteProduct1.setForeground(new java.awt.Color(255, 255, 255));
        DeleteProduct1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/bin.png"))); // NOI18N
        DeleteProduct1.setText("Delete Toga");
        DeleteProduct1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteProduct1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteProduct1MouseClicked(evt);
            }
        });
        DeleteProduct1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteProduct1ActionPerformed(evt);
            }
        });

        TogaButton1.setBackground(new java.awt.Color(207, 124, 6));
        TogaButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/icons/InventoryButton.png"))); // NOI18N
        TogaButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TogaButton1MouseClicked(evt);
            }
        });
        TogaButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TogaButton1ActionPerformed(evt);
            }
        });

        AddTogaStock.setBackground(new java.awt.Color(207, 124, 6));
        AddTogaStock.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        AddTogaStock.setForeground(new java.awt.Color(255, 255, 255));
        AddTogaStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/plus.png"))); // NOI18N
        AddTogaStock.setText("Add Stock");
        AddTogaStock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddTogaStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddTogaStockMouseClicked(evt);
            }
        });
        AddTogaStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTogaStockActionPerformed(evt);
            }
        });

        TogaTable.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        TogaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "School Name", "Toga Description", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TogaTable.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jScrollPane7.setViewportView(TogaTable);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/toga.gif"))); // NOI18N

        javax.swing.GroupLayout TogaInventoryPLayout = new javax.swing.GroupLayout(TogaInventoryP);
        TogaInventoryP.setLayout(TogaInventoryPLayout);
        TogaInventoryPLayout.setHorizontalGroup(
            TogaInventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TogaInventoryPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TogaInventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TogaInventoryPLayout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 53, Short.MAX_VALUE))
                    .addGroup(TogaInventoryPLayout.createSequentialGroup()
                        .addComponent(AddProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DeleteProduct1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddTogaStock, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TogaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        TogaInventoryPLayout.setVerticalGroup(
            TogaInventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TogaInventoryPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TogaInventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(TogaInventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(AddProduct1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AddTogaStock, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(DeleteProduct1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TogaButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(TogaInventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TogaInventoryPLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TogaInventoryPLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        MainPanel.add(TogaInventoryP, "card13");

        OrdersP.setBackground(new java.awt.Color(255, 255, 255));

        ViewContracts.setBackground(new java.awt.Color(207, 124, 6));
        ViewContracts.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        ViewContracts.setForeground(new java.awt.Color(255, 255, 255));
        ViewContracts.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/contract2.png"))); // NOI18N
        ViewContracts.setText("Invoices");
        ViewContracts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ViewContracts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewContractsMouseClicked(evt);
            }
        });
        ViewContracts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewContractsActionPerformed(evt);
            }
        });

        Search1.setBackground(new java.awt.Color(255, 255, 255));
        Search1.setFont(new java.awt.Font("Roboto", 0, 27)); // NOI18N
        Search1.setForeground(new java.awt.Color(255, 255, 255));
        Search1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/search.png"))); // NOI18N
        Search1.setBorder(null);
        Search1.setBorderPainted(false);
        Search1.setContentAreaFilled(false);
        Search1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Search1.setFocusPainted(false);
        Search1.setFocusable(false);
        Search1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Search1MouseClicked(evt);
            }
        });
        Search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search1ActionPerformed(evt);
            }
        });

        panelBorder25.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelBorder25Layout = new javax.swing.GroupLayout(panelBorder25);
        panelBorder25.setLayout(panelBorder25Layout);
        panelBorder25Layout.setHorizontalGroup(
            panelBorder25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1181, Short.MAX_VALUE)
        );
        panelBorder25Layout.setVerticalGroup(
            panelBorder25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        OrderTable.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        OrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Order Number", "Gcash Reference", "Gross Amount", "Payment", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        OrderTable.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jScrollPane10.setViewportView(OrderTable);
        if (OrderTable.getColumnModel().getColumnCount() > 0) {
            OrderTable.getColumnModel().getColumn(0).setResizable(false);
            OrderTable.getColumnModel().getColumn(1).setResizable(false);
            OrderTable.getColumnModel().getColumn(2).setResizable(false);
            OrderTable.getColumnModel().getColumn(3).setResizable(false);
            OrderTable.getColumnModel().getColumn(4).setResizable(false);
            OrderTable.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout OrdersPLayout = new javax.swing.GroupLayout(OrdersP);
        OrdersP.setLayout(OrdersPLayout);
        OrdersPLayout.setHorizontalGroup(
            OrdersPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrdersPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OrdersPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(OrdersPLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(OrdersPLayout.createSequentialGroup()
                        .addComponent(ViewContracts)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Search1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        OrdersPLayout.setVerticalGroup(
            OrdersPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrdersPLayout.createSequentialGroup()
                .addGroup(OrdersPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ViewContracts, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(panelBorder25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
        );

        MainPanel.add(OrdersP, "card8");

        SchoolP.setBackground(new java.awt.Color(255, 255, 255));

        AddSchool.setBackground(new java.awt.Color(207, 124, 6));
        AddSchool.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        AddSchool.setForeground(new java.awt.Color(255, 255, 255));
        AddSchool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/plus.png"))); // NOI18N
        AddSchool.setText("Add School");
        AddSchool.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddSchool.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddSchoolMouseClicked(evt);
            }
        });
        AddSchool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSchoolActionPerformed(evt);
            }
        });

        CreateContract.setBackground(new java.awt.Color(207, 124, 6));
        CreateContract.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        CreateContract.setForeground(new java.awt.Color(255, 255, 255));
        CreateContract.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/pencil.png"))); // NOI18N
        CreateContract.setText("Create Invoice");
        CreateContract.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CreateContract.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CreateContractMouseClicked(evt);
            }
        });
        CreateContract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateContractActionPerformed(evt);
            }
        });

        DeleteSchool.setBackground(new java.awt.Color(207, 124, 6));
        DeleteSchool.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        DeleteSchool.setForeground(new java.awt.Color(255, 255, 255));
        DeleteSchool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/bin.png"))); // NOI18N
        DeleteSchool.setText("Delete School");
        DeleteSchool.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteSchool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteSchoolActionPerformed(evt);
            }
        });

        EditSchool.setBackground(new java.awt.Color(207, 124, 6));
        EditSchool.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        EditSchool.setForeground(new java.awt.Color(255, 255, 255));
        EditSchool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/pencil.png"))); // NOI18N
        EditSchool.setText("Edit School");
        EditSchool.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EditSchool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditSchoolActionPerformed(evt);
            }
        });

        panelBorder27.setBackground(new java.awt.Color(255, 255, 255));

        SchoolTable.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        SchoolTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "School ID", "School Name", "Contact Person", "Contact Number", "School Email", "School Address", "TIN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SchoolTable.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jScrollPane4.setViewportView(SchoolTable);
        if (SchoolTable.getColumnModel().getColumnCount() > 0) {
            SchoolTable.getColumnModel().getColumn(0).setResizable(false);
            SchoolTable.getColumnModel().getColumn(1).setResizable(false);
            SchoolTable.getColumnModel().getColumn(2).setResizable(false);
            SchoolTable.getColumnModel().getColumn(3).setResizable(false);
            SchoolTable.getColumnModel().getColumn(4).setResizable(false);
            SchoolTable.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout panelBorder27Layout = new javax.swing.GroupLayout(panelBorder27);
        panelBorder27.setLayout(panelBorder27Layout);
        panelBorder27Layout.setHorizontalGroup(
            panelBorder27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder27Layout.setVerticalGroup(
            panelBorder27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout SchoolPLayout = new javax.swing.GroupLayout(SchoolP);
        SchoolP.setLayout(SchoolPLayout);
        SchoolPLayout.setHorizontalGroup(
            SchoolPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SchoolPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SchoolPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SchoolPLayout.createSequentialGroup()
                        .addComponent(AddSchool)
                        .addGap(18, 18, 18)
                        .addComponent(EditSchool)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteSchool)
                        .addGap(18, 18, 18)
                        .addComponent(CreateContract)
                        .addContainerGap(319, Short.MAX_VALUE))
                    .addComponent(panelBorder27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        SchoolPLayout.setVerticalGroup(
            SchoolPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SchoolPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SchoolPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EditSchool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddSchool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(SchoolPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(DeleteSchool, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CreateContract, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(panelBorder27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(175, 175, 175))
        );

        MainPanel.add(SchoolP, "card7");

        InventoryP.setBackground(new java.awt.Color(255, 255, 255));

        Search.setBackground(new java.awt.Color(255, 255, 255));
        Search.setFont(new java.awt.Font("Bahnschrift", 0, 20)); // NOI18N
        Search.setForeground(new java.awt.Color(255, 255, 255));
        Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/search.png"))); // NOI18N
        Search.setBorder(null);
        Search.setBorderPainted(false);
        Search.setContentAreaFilled(false);
        Search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Search.setFocusPainted(false);
        Search.setFocusable(false);
        Search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchMouseClicked(evt);
            }
        });
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        panelBorder29.setBackground(new java.awt.Color(255, 255, 255));

        InventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Barcode", "Product Name", "Category", "Cost", "Unit Price", "Stock", "Provider", "Date Received"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        InventoryTable.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        InventoryTable.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        jScrollPane3.setViewportView(InventoryTable);
        if (InventoryTable.getColumnModel().getColumnCount() > 0) {
            InventoryTable.getColumnModel().getColumn(0).setResizable(false);
            InventoryTable.getColumnModel().getColumn(1).setResizable(false);
            InventoryTable.getColumnModel().getColumn(2).setResizable(false);
            InventoryTable.getColumnModel().getColumn(3).setResizable(false);
            InventoryTable.getColumnModel().getColumn(4).setResizable(false);
            InventoryTable.getColumnModel().getColumn(5).setResizable(false);
            InventoryTable.getColumnModel().getColumn(6).setResizable(false);
            InventoryTable.getColumnModel().getColumn(7).setResizable(false);
            InventoryTable.getColumnModel().getColumn(8).setResizable(false);
        }
        InventoryTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        InventoryTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        InventoryTable.getColumnModel().getColumn(2).setPreferredWidth(225);
        InventoryTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        InventoryTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        InventoryTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        InventoryTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        InventoryTable.getColumnModel().getColumn(7).setPreferredWidth(200);
        InventoryTable.getColumnModel().getColumn(8).setPreferredWidth(150);

        AddProduct.setBackground(new java.awt.Color(207, 124, 6));
        AddProduct.setFont(new java.awt.Font("Bahnschrift", 0, 20)); // NOI18N
        AddProduct.setForeground(new java.awt.Color(255, 255, 255));
        AddProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/plus.png"))); // NOI18N
        AddProduct.setText("Add New Product");
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

        EditProduct.setBackground(new java.awt.Color(207, 124, 6));
        EditProduct.setFont(new java.awt.Font("Bahnschrift", 0, 20)); // NOI18N
        EditProduct.setForeground(new java.awt.Color(255, 255, 255));
        EditProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/pencil.png"))); // NOI18N
        EditProduct.setText("Edit Product");
        EditProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EditProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditProductMouseClicked(evt);
            }
        });
        EditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditProductActionPerformed(evt);
            }
        });

        DeleteProduct.setBackground(new java.awt.Color(207, 124, 6));
        DeleteProduct.setFont(new java.awt.Font("Bahnschrift", 0, 20)); // NOI18N
        DeleteProduct.setForeground(new java.awt.Color(255, 255, 255));
        DeleteProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/bin.png"))); // NOI18N
        DeleteProduct.setText("Delete Product");
        DeleteProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteProductMouseClicked(evt);
            }
        });
        DeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteProductActionPerformed(evt);
            }
        });

        TogaButton.setBackground(new java.awt.Color(207, 124, 6));
        TogaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/icons/togabutton.png"))); // NOI18N
        TogaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TogaButtonMouseClicked(evt);
            }
        });
        TogaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TogaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder29Layout = new javax.swing.GroupLayout(panelBorder29);
        panelBorder29.setLayout(panelBorder29Layout);
        panelBorder29Layout.setHorizontalGroup(
            panelBorder29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(panelBorder29Layout.createSequentialGroup()
                        .addComponent(AddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(EditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TogaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder29Layout.setVerticalGroup(
            panelBorder29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder29Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelBorder29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(AddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TogaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GenerateBarcode3.setBackground(new java.awt.Color(207, 124, 6));
        GenerateBarcode3.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        GenerateBarcode3.setForeground(new java.awt.Color(255, 255, 255));
        GenerateBarcode3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/plus.png"))); // NOI18N
        GenerateBarcode3.setText("Generate Barcode");
        GenerateBarcode3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GenerateBarcode3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GenerateBarcode3MouseClicked(evt);
            }
        });
        GenerateBarcode3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateBarcode3ActionPerformed(evt);
            }
        });

        GenerateInventoryReport1.setBackground(new java.awt.Color(207, 124, 6));
        GenerateInventoryReport1.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        GenerateInventoryReport1.setForeground(new java.awt.Color(255, 255, 255));
        GenerateInventoryReport1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/order2.png"))); // NOI18N
        GenerateInventoryReport1.setText("Generate Report");
        GenerateInventoryReport1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GenerateInventoryReport1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GenerateInventoryReport1MouseClicked(evt);
            }
        });
        GenerateInventoryReport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateInventoryReport1ActionPerformed(evt);
            }
        });

        AddStock.setBackground(new java.awt.Color(207, 124, 6));
        AddStock.setFont(new java.awt.Font("Bahnschrift", 0, 20)); // NOI18N
        AddStock.setForeground(new java.awt.Color(255, 255, 255));
        AddStock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/plus.png"))); // NOI18N
        AddStock.setText("Add Stock");
        AddStock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddStockMouseClicked(evt);
            }
        });
        AddStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddStockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InventoryPLayout = new javax.swing.GroupLayout(InventoryP);
        InventoryP.setLayout(InventoryPLayout);
        InventoryPLayout.setHorizontalGroup(
            InventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(InventoryPLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(GenerateInventoryReport1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(GenerateBarcode3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AddStock, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 407, Short.MAX_VALUE)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        InventoryPLayout.setVerticalGroup(
            InventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryPLayout.createSequentialGroup()
                .addGroup(InventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InventoryPLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(InventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddStock, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(GenerateBarcode3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InventoryPLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(InventoryPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(GenerateInventoryReport1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Search, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(163, 163, 163))
        );

        MainPanel.add(InventoryP, "card5");

        SalesReportP.setBackground(new java.awt.Color(255, 255, 255));

        ComboYear.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        ComboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboYearActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Year:");

        ComboMonth.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        ComboMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String []{" ","January","February","March","April","May","June","July","August","September","October","November","December"}));
        ComboMonth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboMonthMouseClicked(evt);
            }
        });
        ComboMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboMonthActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Month:");

        jLabel45.setFont(new java.awt.Font("Bahnschrift", 0, 27)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Toga Rental Pie Chart");

        GenerateSalesReport.setBackground(new java.awt.Color(207, 124, 6));
        GenerateSalesReport.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        GenerateSalesReport.setForeground(new java.awt.Color(255, 255, 255));
        GenerateSalesReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/checkout.png"))); // NOI18N
        GenerateSalesReport.setText("Generate Report");
        GenerateSalesReport.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GenerateSalesReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                GenerateSalesReportMouseClicked(evt);
            }
        });
        GenerateSalesReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateSalesReportActionPerformed(evt);
            }
        });

        ReportLoss.setBackground(new java.awt.Color(207, 124, 6));
        ReportLoss.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        ReportLoss.setForeground(new java.awt.Color(255, 255, 255));
        ReportLoss.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/pencil.png"))); // NOI18N
        ReportLoss.setText("Report Loss");
        ReportLoss.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ReportLoss.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReportLossMouseClicked(evt);
            }
        });
        ReportLoss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportLossActionPerformed(evt);
            }
        });

        panelBorder32.setBackground(new java.awt.Color(255, 255, 255));

        chart.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N

        jLabel62.setFont(new java.awt.Font("Bahnschrift", 0, 27)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Retail Sales Bar Graph");

        javax.swing.GroupLayout panelBorder32Layout = new javax.swing.GroupLayout(panelBorder32);
        panelBorder32.setLayout(panelBorder32Layout);
        panelBorder32Layout.setHorizontalGroup(
            panelBorder32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder32Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel62)
                .addGap(156, 156, 156))
        );
        panelBorder32Layout.setVerticalGroup(
            panelBorder32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder32Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel62)
                .addGap(5, 5, 5)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pieChart.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sales report.png"))); // NOI18N

        javax.swing.GroupLayout SalesReportPLayout = new javax.swing.GroupLayout(SalesReportP);
        SalesReportP.setLayout(SalesReportPLayout);
        SalesReportPLayout.setHorizontalGroup(
            SalesReportPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalesReportPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SalesReportPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SalesReportPLayout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel45)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(SalesReportPLayout.createSequentialGroup()
                        .addGroup(SalesReportPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pieChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(SalesReportPLayout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(42, 42, 42)
                                .addComponent(ComboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel42)
                                .addGap(173, 173, 173))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SalesReportPLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(ComboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SalesReportPLayout.createSequentialGroup()
                                .addComponent(GenerateSalesReport)
                                .addGap(18, 18, 18)
                                .addComponent(ReportLoss, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(SalesReportPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(SalesReportPLayout.createSequentialGroup()
                        .addComponent(panelBorder32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(SalesReportPLayout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29)
                        .addGap(26, 26, 26))))
        );
        SalesReportPLayout.setVerticalGroup(
            SalesReportPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SalesReportPLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(SalesReportPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SalesReportPLayout.createSequentialGroup()
                        .addGroup(SalesReportPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(GenerateSalesReport, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ReportLoss, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addGap(18, 18, 18)
                        .addGroup(SalesReportPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(SalesReportPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SalesReportPLayout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(5, 5, 5)
                        .addComponent(pieChart, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBorder32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        MainPanel.add(SalesReportP, "card10");

        ContractsP.setBackground(new java.awt.Color(255, 255, 255));

        ViewOrders.setBackground(new java.awt.Color(207, 124, 6));
        ViewOrders.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        ViewOrders.setForeground(new java.awt.Color(255, 255, 255));
        ViewOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/order2.png"))); // NOI18N
        ViewOrders.setText("Transactions");
        ViewOrders.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ViewOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewOrdersMouseClicked(evt);
            }
        });
        ViewOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewOrdersActionPerformed(evt);
            }
        });

        ViewOrders1.setBackground(new java.awt.Color(207, 124, 6));
        ViewOrders1.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        ViewOrders1.setForeground(new java.awt.Color(255, 255, 255));
        ViewOrders1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/pencil.png"))); // NOI18N
        ViewOrders1.setText("Generate Payment Record");
        ViewOrders1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ViewOrders1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewOrders1MouseClicked(evt);
            }
        });
        ViewOrders1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewOrders1ActionPerformed(evt);
            }
        });

        DeleteProduct4.setBackground(new java.awt.Color(207, 124, 6));
        DeleteProduct4.setFont(new java.awt.Font("Bahnschrift", 0, 24)); // NOI18N
        DeleteProduct4.setForeground(new java.awt.Color(255, 255, 255));
        DeleteProduct4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/bin.png"))); // NOI18N
        DeleteProduct4.setText("Report Penalties");
        DeleteProduct4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteProduct4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DeleteProduct4MouseClicked(evt);
            }
        });
        DeleteProduct4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteProduct4ActionPerformed(evt);
            }
        });

        Search2.setBackground(new java.awt.Color(255, 255, 255));
        Search2.setFont(new java.awt.Font("Roboto", 0, 27)); // NOI18N
        Search2.setForeground(new java.awt.Color(255, 255, 255));
        Search2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sales/and/inventory/management/system/icons/search.png"))); // NOI18N
        Search2.setBorder(null);
        Search2.setBorderPainted(false);
        Search2.setContentAreaFilled(false);
        Search2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Search2.setFocusPainted(false);
        Search2.setFocusable(false);
        Search2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search2ActionPerformed(evt);
            }
        });

        panelBorder33.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelBorder33Layout = new javax.swing.GroupLayout(panelBorder33);
        panelBorder33.setLayout(panelBorder33Layout);
        panelBorder33Layout.setHorizontalGroup(
            panelBorder33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBorder33Layout.setVerticalGroup(
            panelBorder33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        InvoiceTable.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        InvoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice ID", "School", "Gross Amount", "Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        InvoiceTable.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jScrollPane2.setViewportView(InvoiceTable);
        if (InvoiceTable.getColumnModel().getColumnCount() > 0) {
            InvoiceTable.getColumnModel().getColumn(0).setResizable(false);
            InvoiceTable.getColumnModel().getColumn(1).setResizable(false);
            InvoiceTable.getColumnModel().getColumn(2).setResizable(false);
            InvoiceTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/invoice.png"))); // NOI18N

        javax.swing.GroupLayout ContractsPLayout = new javax.swing.GroupLayout(ContractsP);
        ContractsP.setLayout(ContractsPLayout);
        ContractsPLayout.setHorizontalGroup(
            ContractsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContractsPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ContractsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(panelBorder33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ContractsPLayout.createSequentialGroup()
                        .addComponent(ViewOrders)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ViewOrders1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DeleteProduct4, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(ContractsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContractsPLayout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContractsPLayout.createSequentialGroup()
                        .addComponent(Search2)
                        .addGap(18, 18, 18))))
        );
        ContractsPLayout.setVerticalGroup(
            ContractsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContractsPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ContractsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Search2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ContractsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ViewOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ViewOrders1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DeleteProduct4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ContractsPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ContractsPLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ContractsPLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(163, 163, 163))
        );

        MainPanel.add(ContractsP, "card9");

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelBorder9, javax.swing.GroupLayout.PREFERRED_SIZE, 1181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, 716, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void SignoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignoutMouseClicked
        int a = JOptionPane.showConfirmDialog(null, "Do you want to sign out?", "Signing out", JOptionPane.YES_NO_OPTION);
        
        if(a == 0)
        {
        Signin show = new Signin();
        show.setVisible(true);
        dispose();
        }
    }//GEN-LAST:event_SignoutMouseClicked

    private void DashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DashboardMouseClicked
        changePage("Dashboard");
        MainPanel.removeAll();
        MainPanel.add(DashboardP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_DashboardMouseClicked

    private void BrandsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BrandsMouseClicked
 
        changePage("Banks");
        MainPanel.removeAll();
        MainPanel.add(BrandsP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_BrandsMouseClicked

    private void CategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CategoryMouseClicked
        changePage("Product Categories");
        
        MainPanel.removeAll();
        MainPanel.add(CategoryP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_CategoryMouseClicked

    private void InventoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InventoryMouseClicked
        changePage("Retail Inventory");
        MainPanel.removeAll();
        MainPanel.add(InventoryP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_InventoryMouseClicked

    
    private void POSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_POSMouseClicked

        changePage("Point of Sale");
        MainPanel.removeAll();
        MainPanel.add(POSP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_POSMouseClicked

    private void SchoolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SchoolMouseClicked
        changePage("School Clients");
        
        MainPanel.removeAll();
        MainPanel.add(SchoolP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_SchoolMouseClicked

    private void TransactionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TransactionMouseClicked
        changePage("Transactions");
        
        MainPanel.removeAll();
        MainPanel.add(OrdersP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_TransactionMouseClicked

    private void SalesReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalesReportMouseClicked
        changePage("Sales Report");
        
        MainPanel.removeAll();
        MainPanel.add(SalesReportP);
        MainPanel.repaint();
        MainPanel.revalidate();
        
        
        
        
    }//GEN-LAST:event_SalesReportMouseClicked

    private void BusinessMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BusinessMouseClicked

        changePage("Business Information");
        MainPanel.removeAll();
        MainPanel.add(BusinessP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_BusinessMouseClicked

    private void AccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AccountMouseClicked
        changePage("Account Information");
        
        MainPanel.removeAll();
        MainPanel.add(AccountP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_AccountMouseClicked

    private void AddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddCategoryActionPerformed

    private void AddCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddCategoryMouseClicked
        AddCategory show = new AddCategory();
        show.setVisible(true);       
    }//GEN-LAST:event_AddCategoryMouseClicked

    private void AddProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddProductMouseClicked
        AddProduct show = new AddProduct();
        show.setVisible(true);
    }//GEN-LAST:event_AddProductMouseClicked

    private void AddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddProductActionPerformed

    private void ResetOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResetOrderMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ResetOrderMouseClicked

    private void ResetOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetOrderActionPerformed
        resetOrder();
    }//GEN-LAST:event_ResetOrderActionPerformed

    private void AddSchoolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddSchoolMouseClicked
        AddSchool show = new AddSchool();
        show.setVisible(true);
    }//GEN-LAST:event_AddSchoolMouseClicked

    private void AddSchoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSchoolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddSchoolActionPerformed

    private void CreateContractMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreateContractMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_CreateContractMouseClicked

    private void CreateContractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateContractActionPerformed
        AddContract show = new AddContract();
        show.setVisible(true);
    }//GEN-LAST:event_CreateContractActionPerformed

    private void ViewContractsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewContractsMouseClicked
        changePage("Invoices");
        MainPanel.removeAll();
        MainPanel.add(ContractsP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_ViewContractsMouseClicked

    private void ViewContractsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewContractsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewContractsActionPerformed

    private void ViewOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewOrdersMouseClicked
        MainPanel.removeAll();
        MainPanel.add(OrdersP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_ViewOrdersMouseClicked

    private void ViewOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewOrdersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewOrdersActionPerformed

    private void BusinessNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BusinessNameFocusGained

    }//GEN-LAST:event_BusinessNameFocusGained

    private void BusinessNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BusinessNameFocusLost

    }//GEN-LAST:event_BusinessNameFocusLost

    private void BusinessNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusinessNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessNameActionPerformed

    private void BusinessTINFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BusinessTINFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessTINFocusGained

    private void BusinessTINFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BusinessTINFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessTINFocusLost

    private void BusinessTINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusinessTINActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessTINActionPerformed

    private void BusinessAddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BusinessAddressFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessAddressFocusGained

    private void BusinessAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BusinessAddressFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessAddressFocusLost

    private void BusinessAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusinessAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessAddressActionPerformed

    
    private void SaveChangesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveChangesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SaveChangesMouseClicked

    private void SaveChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveChangesActionPerformed
         int p = JOptionPane.showConfirmDialog(null, "Do you really want to update your business details?", "Update", JOptionPane.YES_NO_OPTION);
         if (p == 0) {
            try {
                sqlConn = DriverManager.getConnection(dataConn, username, password);
                pst = sqlConn.prepareStatement("UPDATE business_info SET BuName = ?, BuTIN = ?, BuAddress = ?, BuEmail = ? WHERE BuID = 20232001");
                pst.setString(1, BusinessName.getText());
                pst.setString(2, BusinessTIN.getText());
                pst.setString(3, BusinessAddress.getText());
                pst.setString(4, BusinessContact.getText());
                pst.executeUpdate();
                pst.execute();
                JOptionPane.showMessageDialog(rootPane, "Updated!");
                sqlConn.close();
                BusinessName.setEditable(false);
                BusinessTIN.setEditable(false);
                BusinessAddress.setEditable(false);
                BusinessContact.setEditable(false);
                BusinessPhone.setEditable(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);

            } 
        }
    }//GEN-LAST:event_SaveChangesActionPerformed

    private void EmailAddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailAddressFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailAddressFocusGained

    private void EmailAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EmailAddressFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailAddressFocusLost

    private void EmailAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailAddressActionPerformed

    private void UsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameFocusGained

    private void UsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameFocusLost

    private void UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameActionPerformed

    private void SaveChanges2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SaveChanges2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SaveChanges2MouseClicked

    private void SaveChanges2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveChanges2ActionPerformed
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to update your account details?", "Update", JOptionPane.YES_NO_OPTION);
         if (p == 0) {
            try {
                sqlConn = DriverManager.getConnection(dataConn, username, password);
                pst = sqlConn.prepareStatement("UPDATE user SET UFname = ?, ULname = ?, Uname = ?, UEmail = ?, UPhoneNumber = ? WHERE UID = ?");
                pst.setString(1, Firstname.getText());
                pst.setString(2, Lastname.getText());
                pst.setString(3, Username.getText());
                pst.setString(4, EmailAddress.getText());
                pst.setString(5, PhoneNumber.getText());
                pst.setInt(6, userID);
                pst.executeUpdate();
                pst.execute();


                JOptionPane.showMessageDialog(rootPane, "Updated!");
                sqlConn.close();
                Firstname.setEditable(false);
                Lastname.setEditable(false);
                Username.setEditable(false);
                EmailAddress.setEditable(false);
                PhoneNumber.setEditable(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, e);

            } 
        }
    }//GEN-LAST:event_SaveChanges2ActionPerformed

    private void FirstnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FirstnameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_FirstnameFocusGained

    private void FirstnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FirstnameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_FirstnameFocusLost

    private void FirstnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FirstnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FirstnameActionPerformed

    private void LastnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LastnameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_LastnameFocusGained

    private void LastnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_LastnameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_LastnameFocusLost

    private void LastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LastnameActionPerformed

    private void PhoneNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PhoneNumberFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneNumberFocusGained

    private void PhoneNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PhoneNumberFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneNumberFocusLost

    private void PhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhoneNumberActionPerformed

    private void EditProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditProductMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EditProductMouseClicked

    private void EditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditProductActionPerformed
        EditProduct show = new EditProduct();
        show.setVisible(true);
    }//GEN-LAST:event_EditProductActionPerformed

    private void DeleteProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteProductMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteProductMouseClicked

    private void DeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteProductActionPerformed
        DeleteProduct show = new DeleteProduct();
        show.setVisible(true);
    }//GEN-LAST:event_DeleteProductActionPerformed

    private void DeleteOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteOrderMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteOrderMouseClicked

    private void DeleteOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteOrderActionPerformed
        DeleteOrder show = new DeleteOrder();
        show.setVisible(true);
    }//GEN-LAST:event_DeleteOrderActionPerformed

    private void DeleteCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteCategoryMouseClicked
        DeleteCategory show = new DeleteCategory();
        show.setVisible(true);
    }//GEN-LAST:event_DeleteCategoryMouseClicked

    private void DeleteCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteCategoryActionPerformed
        DeleteCategory show = new DeleteCategory();
        show.setVisible(true);
    }//GEN-LAST:event_DeleteCategoryActionPerformed

    private void EditSchoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditSchoolActionPerformed
        EditSchool show = new EditSchool();
        show.setVisible(true);
    }//GEN-LAST:event_EditSchoolActionPerformed

    private void EditOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditOrderMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EditOrderMouseClicked

    private void EditOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditOrderActionPerformed
        EditOrder show = new EditOrder();
        show.setVisible(true);
    }//GEN-LAST:event_EditOrderActionPerformed

    private void DeleteSchoolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteSchoolActionPerformed
        DeleteSchool show = new DeleteSchool();
        show.setVisible(true);
    }//GEN-LAST:event_DeleteSchoolActionPerformed

    private void ChangePassword1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChangePassword1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ChangePassword1MouseClicked

    private void ChangePassword1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangePassword1ActionPerformed
        ForgotPassword show = new ForgotPassword();
        show.setVisible(true);
    }//GEN-LAST:event_ChangePassword1ActionPerformed

    private void NameEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NameEditMouseClicked
        BusinessName.setEditable(true);
        BusinessName.setBackground(Color.white);
    }//GEN-LAST:event_NameEditMouseClicked

    private void TINEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TINEditMouseClicked
        BusinessTIN.setEditable(true);
        BusinessTIN.setBackground(Color.white);
    }//GEN-LAST:event_TINEditMouseClicked

    private void AddressEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddressEditMouseClicked
        BusinessAddress.setEditable(true);
        BusinessAddress.setBackground(Color.white);
    }//GEN-LAST:event_AddressEditMouseClicked

    private void NumberEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NumberEditMouseClicked
        BusinessContact.setEditable(true);
        BusinessContact.setBackground(Color.white);
    }//GEN-LAST:event_NumberEditMouseClicked

    private void TINEdit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TINEdit1MouseClicked
        Firstname.setEditable(true);
        Firstname.setBackground(Color.white);
    }//GEN-LAST:event_TINEdit1MouseClicked

    private void TINEdit2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TINEdit2MouseClicked
        Lastname.setEditable(true);
        Lastname.setBackground(Color.white);
    }//GEN-LAST:event_TINEdit2MouseClicked

    private void TINEdit3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TINEdit3MouseClicked
        Username.setEditable(true);
        Username.setBackground(Color.white);
    }//GEN-LAST:event_TINEdit3MouseClicked

    private void TINEdit4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TINEdit4MouseClicked
        EmailAddress.setEditable(true);
        EmailAddress.setBackground(Color.white);
    }//GEN-LAST:event_TINEdit4MouseClicked

    private void TINEdit5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TINEdit5MouseClicked
        PhoneNumber.setEditable(true);
        PhoneNumber.setBackground(Color.white);
    }//GEN-LAST:event_TINEdit5MouseClicked

    private void ViewOrders1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewOrders1MouseClicked
                
    }//GEN-LAST:event_ViewOrders1MouseClicked

    private void ViewOrders1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewOrders1ActionPerformed
        GenerateOfficialReceipt show = new GenerateOfficialReceipt();
        show.setVisible(true);
    }//GEN-LAST:event_ViewOrders1ActionPerformed

    private void TogaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TogaButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TogaButtonActionPerformed

    private void TogaButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TogaButtonMouseClicked
        changePage("Toga Inventory");
        MainPanel.removeAll();
        MainPanel.add(TogaInventoryP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_TogaButtonMouseClicked

    private void TogaButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TogaButton1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TogaButton1MouseClicked

    private void TogaButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TogaButton1ActionPerformed
        changePage("Retail Inventory");
        MainPanel.removeAll();
        MainPanel.add(InventoryP);
        MainPanel.repaint();
        MainPanel.revalidate();
    }//GEN-LAST:event_TogaButton1ActionPerformed

    private void AddProduct1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddProduct1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AddProduct1MouseClicked

    private void AddProduct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProduct1ActionPerformed
        AddToga show = new AddToga();
        show.setVisible(true);
    }//GEN-LAST:event_AddProduct1ActionPerformed

    private void GenerateSalesReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenerateSalesReportMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_GenerateSalesReportMouseClicked

    private void GenerateSalesReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateSalesReportActionPerformed
        GenerateSalesReport show = new GenerateSalesReport();
        show.setVisible(true);
        generateSalesReportFolder();
    }//GEN-LAST:event_GenerateSalesReportActionPerformed

    private void POSPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_POSPKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){        
            AddProductsToOrder();
        } 
    }//GEN-LAST:event_POSPKeyPressed

    private void ReportLossMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReportLossMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ReportLossMouseClicked

    private void ReportLossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportLossActionPerformed
        ReportProductLoss show = new ReportProductLoss();
        show.setVisible(true);
    }//GEN-LAST:event_ReportLossActionPerformed

    private void BusinessContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusinessContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessContactActionPerformed

    private void BusinessContactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BusinessContactFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessContactFocusLost

    private void BusinessContactFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BusinessContactFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessContactFocusGained

    private void BusinessPhoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BusinessPhoneFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessPhoneFocusGained

    private void BusinessPhoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_BusinessPhoneFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessPhoneFocusLost

    private void BusinessPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BusinessPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BusinessPhoneActionPerformed

    private void NumberEdit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NumberEdit1MouseClicked
        BusinessPhone.setEditable(true);
        BusinessPhone.setBackground(Color.white);
    }//GEN-LAST:event_NumberEdit1MouseClicked

    private void SearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SearchMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchMouseClicked

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        SearchInventory show = new SearchInventory();
        show.setVisible(true);
    }//GEN-LAST:event_SearchActionPerformed

    private void Search1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Search1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Search1MouseClicked

    private void Search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search1ActionPerformed


        SearchTransaction show = new SearchTransaction();
        show.setVisible(true);
    }//GEN-LAST:event_Search1ActionPerformed

    private void Search2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search2ActionPerformed
        SearchInvoice show = new SearchInvoice();
        show.setVisible(true);
    }//GEN-LAST:event_Search2ActionPerformed

    private void DeleteProduct4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteProduct4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteProduct4MouseClicked

    private void DeleteProduct4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteProduct4ActionPerformed
        ReportRentalPenalty show = new ReportRentalPenalty();
        show.setVisible(true);
    }//GEN-LAST:event_DeleteProduct4ActionPerformed

    private void ComboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboYearActionPerformed

        if(ComboYear.getSelectedIndex() > -1)
        {
            int year = Integer.valueOf(ComboYear.getSelectedItem().toString());
            try
            {       
                getJanuary(year);
                getFebruary(year);
                getMarch(year);
                getApril(year);
                getMay(year);
                getJune(year);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_ComboYearActionPerformed

    private void ComboMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboMonthActionPerformed
        if(ComboMonth.getSelectedIndex() >= 0 )
        {
            int year = Integer.valueOf(ComboYear.getSelectedItem().toString());
            int month = 0;
            String strmonth = (String)ComboMonth.getSelectedItem();
            switch(strmonth){
                case "January":
                    month = 1;
                    break;
                case "February":
                    month = 2;
                    break;
                case "March":
                    month = 3;
                    break;
                case "April":
                    month = 4;
                    break;
                case "May":
                    month = 5;
                    break;
                case "June":
                    month = 6;
                    break;
                case "July":
                    month = 7;
                    break;
                case "August":
                    month = 8;
                    break;
                case "September":
                    month = 9;
                    break;
                case "October":
                    month = 10;
                    break;
                case "November":
                    month = 11;
                    break;
                case "December":
                    month = 12;
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Please contact admin for assistance.");
            }
            ShowData(year,month);
        }
    }//GEN-LAST:event_ComboMonthActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void ComboMonthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboMonthMouseClicked
        
    }//GEN-LAST:event_ComboMonthMouseClicked

    private void txtBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBarcodeActionPerformed

    private void txtBarcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBarcodeKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            AddProductsToOrder();
        }
    }//GEN-LAST:event_txtBarcodeKeyPressed

    private void DeleteOrder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteOrder1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteOrder1MouseClicked

    private void DeleteOrder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteOrder1ActionPerformed
        SearchPOS show = new SearchPOS();
        show.setVisible(true);
    }//GEN-LAST:event_DeleteOrder1ActionPerformed

    private void btnCheckoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCheckoutMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCheckoutMouseClicked

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        Checkout show = new Checkout();
        show.setVisible(true);
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void DeleteProduct1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteProduct1ActionPerformed
        DeleteToga show = new DeleteToga();
        show.setVisible(true);
    }//GEN-LAST:event_DeleteProduct1ActionPerformed

    private void DeleteProduct1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteProduct1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteProduct1MouseClicked

    private void AddStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddStockMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AddStockMouseClicked

    private void AddStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddStockActionPerformed
        AddStock show = new AddStock();
        show.setVisible(true);
    }//GEN-LAST:event_AddStockActionPerformed

    private void AddTogaStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddTogaStockMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AddTogaStockMouseClicked

    private void AddTogaStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTogaStockActionPerformed
        AddTogaStock show = new AddTogaStock();
        show.setVisible(true);
    }//GEN-LAST:event_AddTogaStockActionPerformed

    private void txtChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChangeActionPerformed

    private void DeleteBankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteBankActionPerformed
        DeleteBank show = new DeleteBank();
        show.setVisible(true);
    }//GEN-LAST:event_DeleteBankActionPerformed

    private void DeleteBankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteBankMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteBankMouseClicked

    private void AddBankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBankActionPerformed
        AddBank show = new AddBank();
        show.setVisible(true);
    }//GEN-LAST:event_AddBankActionPerformed

    private void AddBankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddBankMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AddBankMouseClicked

    private void GenerateBarcode3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenerateBarcode3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_GenerateBarcode3MouseClicked

    private void GenerateBarcode3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateBarcode3ActionPerformed
        GenerateBarcode();
    }//GEN-LAST:event_GenerateBarcode3ActionPerformed

    private void GenerateInventoryReport1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenerateInventoryReport1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_GenerateInventoryReport1MouseClicked

    private void GenerateInventoryReport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateInventoryReport1ActionPerformed
        r.generateInventoryReport();
        JOptionPane.showMessageDialog(frame, "Inventory Report Generated!");
    }//GEN-LAST:event_GenerateInventoryReport1ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void AddProviderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddProviderMouseClicked
        AddProvider show = new AddProvider();
        show.setVisible(true); 
    }//GEN-LAST:event_AddProviderMouseClicked

    private void AddProviderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProviderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddProviderActionPerformed

    private void DeleteProviderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeleteProviderMouseClicked
        DeleteProvider show = new DeleteProvider();
        show.setVisible(true);
    }//GEN-LAST:event_DeleteProviderMouseClicked

    private void DeleteProviderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteProviderActionPerformed
        DeleteProvider show = new DeleteProvider();
        show.setVisible(true);
    }//GEN-LAST:event_DeleteProviderActionPerformed
        public static void generateOfficialReceiptFolder(){
            try
        {   
            String directory = "C:\\Sales and Inventory Documents\\Official Receipt\\";
            File Folder = new File(directory);           
            //If folder successfully created
           if(!Folder.exists()){
                Folder.mkdir();
                }             
        } 
        catch(HeadlessException | NumberFormatException ex){
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frame, "Please input details");
        }
    }    
    
        public static void generateInvoiceFolder(){
            try
        {   
            String directory = "C:\\Sales and Inventory Documents\\Invoices\\";
            File Folder = new File(directory);           
            //If folder successfully created
           if(!Folder.exists()){
                Folder.mkdir();
                }             
        } 
        catch(HeadlessException | NumberFormatException ex){
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frame, "Please input details");
        }
    }
    
        public static void generateTapeReceiptFolder(){
            try
        {   
            String directory = "C:\\Sales and Inventory Documents\\Tape Receipts\\";
            File Folder = new File(directory);           
            //If folder successfully created
           if(!Folder.exists()){
                Folder.mkdir();
                }             
        } 
        catch(HeadlessException | NumberFormatException ex){
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frame, "Please input details");
        }
    }
        public static void generateInventoryReportFolder(){
            try
        {   
            String directory = "C:\\Sales and Inventory Documents\\Inventory Reports\\";
            File Folder = new File(directory);           
            //If folder successfully created
           if(!Folder.exists()){
                Folder.mkdir();
                }             
        } 
        catch(HeadlessException | NumberFormatException ex){
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frame, "Please input details");
        }
    }
        public static void generateSalesReportFolder(){
            try
        {   
            String directory = "C:\\Sales and Inventory Documents\\Sales Reports\\";
            File Folder = new File(directory);           
            //If folder successfully created
           if(!Folder.exists()){
                Folder.mkdir();
                }             
        } 
        catch(HeadlessException | NumberFormatException ex){
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frame, "Please input details");
        }
    }
       public static void generateSalesandInventoryDocumentsFolder(){
            try
        {   
            String directory = "C:\\Sales and Inventory Documents\\";
            File Folder = new File(directory);           
            //If folder successfully created
           if(!Folder.exists()){
                Folder.mkdir();
                }             
        } 
        catch(HeadlessException | NumberFormatException ex){
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(frame, "Please input details");
        }
    }
    
    
    public void GenerateBarcode(){
        try
        {   
            sqlConn = DriverManager.getConnection(dataConn,username,password);
            pst = sqlConn.prepareStatement("SELECT PrID, PrName, PrBarcode FROM product");           
            rs = pst.executeQuery();
            String directory = "C:\\Sales and Inventory Documents\\Product Barcodes\\";
            File Folder = new File(directory);
            
            //If folder successfully created
            if(Folder.exists()){
                //write to png file
                while(rs.next()){ 
                int barcode = rs.getInt("PrBarcode");
                String strbarcode = Integer.toString(barcode);
                BarcodeCreateImage(rs.getString("PrName")+".png", strbarcode,directory); 
                } 
                JOptionPane.showMessageDialog(this, "Barcode generated successfully!");
            }
            else if(!Folder.exists()){
                Folder.mkdir();
                while(rs.next()){ 
                int barcode = rs.getInt("PrBarcode");
                String strbarcode = Integer.toString(barcode);
                BarcodeCreateImage(rs.getString("PrName")+".png", strbarcode,directory); 
                } 
                JOptionPane.showMessageDialog(this, "Barcode generated successfully!");
            }    
            else{
                JOptionPane.showMessageDialog(this, "Generation of Barcode unsucessful, contact admin for assistance.");
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Please input the right data type.");
        } 
        catch(HeadlessException | NumberFormatException ex){
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Please input details");
        }
    }
    public void resetOrder(){
        DefaultTableModel ordertable = (DefaultTableModel)POSTable.getModel();
        ordertable.setRowCount(0); 
        txtTotalAmount.setText("");
        txtChange.setText("");  
        ReceiptPreview.setText("");  
    }
    public static void AddQuantity (int Quantity, String product){
            DefaultTableModel ordertable = (DefaultTableModel)POSTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            POSTable.setDefaultRenderer(Object.class, centerRenderer);
            int rowcount = ordertable.getRowCount(); 
            ArrayList list = new ArrayList();
            for(int i = 0; i<rowcount;i++){                       
                list.add(ordertable.getValueAt(i, 0).toString());                      
            }
            for(int j = 0; j<=rowcount;j++){ 
                String duplicate = ordertable.getValueAt(j, 0).toString(); 
                if(list.contains(product)&&product.equals(duplicate)){
                    String strQuantity = ordertable.getValueAt(j, 2).toString();
                    strQuantity = Integer.toString(Quantity);
                    String strUnitPrice = ordertable.getValueAt(j, 3).toString();
                    int UnitPrice = Integer.parseInt(strUnitPrice);
                    int TotalAmount = Quantity * UnitPrice;
                    String strTotalAmount = Integer.toString(TotalAmount);
                    ordertable.setValueAt(strQuantity, j, 2);
                    ordertable.setValueAt(strTotalAmount, j, 4); 
                    JOptionPane.showMessageDialog(frame, "Quantity set!"); 
                    break;
                }
                else if(list.contains(product)&&!product.equals(duplicate)){
                    continue;
                }
                else{
                 JFrame frame = new JFrame();
                 JOptionPane.showMessageDialog(frame, "Product is not in order");   
                 break;
                }             
            }
            getTotalAmount(rowcount);  
    }
    public static void DeleteOrder(String product){
        try{
            DefaultTableModel ordertable = (DefaultTableModel)POSTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            POSTable.setDefaultRenderer(Object.class, centerRenderer);
            int rowcount = ordertable.getRowCount(); 
            ArrayList list = new ArrayList();
            for(int i = 0; i<rowcount;i++){                       
                list.add(ordertable.getValueAt(i, 0).toString());                      
            }
                for(int j = 0; j<=rowcount;j++){ 
                    String duplicate = ordertable.getValueAt(j, 0).toString(); 
                    if(list.contains(product)&&product.equals(duplicate)){
                        ordertable.removeRow(j);
                        JOptionPane.showMessageDialog(frame, "Product deleted!");                  
                    }
                    else if(list.contains(product)&&!product.equals(duplicate)){
                        continue;
                    }
                rowcount = ordertable.getRowCount(); 
                if(rowcount==0)
                    txtTotalAmount.setText("");
                else
                    getTotalAmount(rowcount);    
                }
        }
        catch(Exception e){
            
        }
    }
    
    public static void DeleteItem(String product){
            DefaultTableModel ordertable = (DefaultTableModel)InvoiceTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            InvoiceTable.setDefaultRenderer(Object.class, centerRenderer);
            int rowcount = ordertable.getRowCount(); 
            ArrayList list = new ArrayList();
            for(int i = 0; i<rowcount;i++){                       
                list.add(ordertable.getValueAt(i, 0).toString());                      
            }
            for(int j = 0; j<rowcount;j++){ 
                String duplicate = ordertable.getValueAt(j, 0).toString(); 
                if(list.contains(product)&&product.equals(duplicate)){
                    ordertable.removeRow(j); 
                    JOptionPane.showMessageDialog(frame, "Item deleted!");                  
                }
                else if(list.contains(product)&&!product.equals(duplicate)){
                    continue;
                }
                else{
                 JFrame frame = new JFrame();
                 JOptionPane.showMessageDialog(frame, "Toga is not in order");   
                }
            rowcount = ordertable.getRowCount(); 
            if(rowcount==0)
                txtTotalAmount.setText("");
            else
                getTotalAmount(rowcount);    
            }
    }
    public void AddProductsToOrder(){
            String Barcode = txtBarcode.getText(); 
            try
            { 
            sqlConn = DriverManager.getConnection(dataConn,username,password);    
            pst = sqlConn.prepareStatement("SELECT * FROM product WHERE PrBarcode = ?");       
            pst.setString(1, Barcode);
            rs = pst.executeQuery();           
            DefaultTableModel ordertable = (DefaultTableModel)POSTable.getModel();
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( JLabel.CENTER );
            POSTable.setDefaultRenderer(Object.class, centerRenderer);
            int rowcount = ordertable.getRowCount();         
            if(rs.next()){
                if(rowcount==0){                      
                        String PrInfo[] = {rs.getString("PrID"),rs.getString("PrName"),"1",rs.getString("PrUnitPrice"),rs.getString("PrUnitPrice")};
                        ordertable.addRow(PrInfo);                                            
                }
                else{
                    ArrayList list = new ArrayList();
                    String product = rs.getString("PrID");
                    for(int i = 0; i<rowcount;i++){                       
                        list.add(ordertable.getValueAt(i, 0).toString());                      
                     }
                    for(int j = 0; j<rowcount;j++){                          
                            String duplicate = ordertable.getValueAt(j, 0).toString(); 
                            if(list.contains(product)&&product.equals(duplicate)){
                                String strQuantity = ordertable.getValueAt(j, 2).toString();
                                int Quantity = Integer.parseInt(strQuantity) + 1;
                                strQuantity = Integer.toString(Quantity);
                                String strUnitPrice = ordertable.getValueAt(j, 3).toString();
                                int UnitPrice = Integer.parseInt(strUnitPrice);
                                int TotalAmount = Quantity * UnitPrice;
                                String strTotalAmount = Integer.toString(TotalAmount);
                                ordertable.setValueAt(strQuantity, j, 2);
                                ordertable.setValueAt(strTotalAmount, j, 4);                               
                            }                               
                       }
                  if(!list.contains(product)){
                                String PrInfo[] = {rs.getString("PrID"),rs.getString("PrName"),"1",rs.getString("PrUnitPrice"),rs.getString("PrUnitPrice")};
                                ordertable.addRow(PrInfo); 
                    }
              }            
            }
            else{
            JOptionPane.showMessageDialog(this, "Product doesn't exist");  
            txtBarcode.setText("");  
            }
            rowcount = ordertable.getRowCount();
            getTotalAmount(rowcount);           
            txtBarcode.setText("");            
            }catch (SQLException ex) {
                java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Please input the right data type.");
            } 
            catch(HeadlessException | NumberFormatException ex){
                java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Please input details");
            } 
    }   
    public static void getTotalAmount(int rowcount){
            int TotalAmount = 0;
            if(rowcount==0){
                TotalAmount = Integer.parseInt(POSTable.getValueAt(0,4).toString());
            }
            else{
                for(int i = 0; i<rowcount; i++){
                TotalAmount = TotalAmount + Integer.parseInt(POSTable.getValueAt(i,4).toString()); 
                }
            }
            txtTotalAmount.setText(Integer.toString(TotalAmount));
         
    }
    
    public void BarcodeCreateImage(String image_name, String barcodevalue, String directory){      
        try{
		Code128Bean code128 = new Code128Bean();
		code128.setHeight(15f);
		code128.setModuleWidth(0.3);
		code128.setQuietZone(10);
		code128.doQuietZone(true);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
		code128.generateBarcode(canvas, barcodevalue);
		canvas.finish();		
                //folder creation
                 FileOutputStream fos = new FileOutputStream(directory+image_name);
                 fos.write(baos.toByteArray());
                 fos.flush();
                 fos.close();
        }
        catch(Exception e){
          java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
           JOptionPane.showMessageDialog(this, "Contact admin for assistance.");
        }
    }
    public static void MakeRetailTransaction(){
           DefaultTableModel ordertable = (DefaultTableModel)POSTable.getModel();
           MAKE:
            try{
                int TotalAmount = 0;
                String strTotalAmount = null;
                strTotalAmount = txtTotalAmount.getText();
                if(strTotalAmount!=null){           
                TotalAmount = Integer.parseInt(strTotalAmount);
                String strPayment = null;
                int payment1 = Checkout.payment;
                strPayment = Integer.toString(payment1);  
                String mode = Checkout.ModeofPayment;
                String Reference = GcashReference.Gcash;
                    if(strPayment!=null){
                    int Payment = Integer.parseInt(strPayment);
                        if(Payment>0 && Payment>=TotalAmount){
                            pst = sqlConn.prepareStatement("SELECT PrId, PrStock FROM product");                                                                                     
                            ArrayList list = new ArrayList();
                            for(int i = 0; i<ordertable.getRowCount();i++){                       
                            list.add(ordertable.getValueAt(i, 0).toString());                      
                            }
                            rs = pst.executeQuery();                                                   
                            while(rs.next()){
                                for(int j = 0; j<ordertable.getRowCount();j++){   
                                String product = rs.getString("PrID");
                                String duplicate = ordertable.getValueAt(j, 0).toString(); 
                                    if(list.contains(product)&&product.equals(duplicate)){
                                    int Stock = rs.getInt("PrStock");        
                                    String strQuantity = ordertable.getValueAt(j, 2).toString();
                                    int Quantity = Integer.parseInt(strQuantity);
                                        if((Quantity<Stock&&Stock-Quantity!=0)||Stock-Quantity==0){
                                        pst = sqlConn.prepareStatement("UPDATE product SET PrStock = ? WHERE PrID = ?"); 
                                        pst.setInt(1, Stock-Quantity);
                                        pst.setString(2, product);
                                        pst.executeUpdate();                                  
                                        }  
                                        else if(Stock-Quantity<0||Stock==0){
                                        JOptionPane.showMessageDialog(frame, "Insufficient Stock, please restock.");    
                                        ordertable.setRowCount(0); 
                                        txtTotalAmount.setText("");
                                        break MAKE;                                 
                                        } 
                                    }
                                }
                            }
                                pst.close();
                                LocalDate Date = LocalDate.now();
                                LocalTime Time = LocalTime.now();
                                DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm:ss");
                                DateTimeFormatter OrIDformat = DateTimeFormatter.ofPattern("yyyy");
                                String formattedtime = Time.format(timeformat);
                                String formatteddate = Date.format(dateformat);
                                int OrderID = 0;
                                int OrNumber = 0;
                                pst = sqlConn.prepareStatement("INSERT into orders(OrID, UID, OrNumber, OrType, PayID,OrTotalAmount,OrChange,OrDate,OrTime,OrLessVAT,OrNet_of_VAT, OrCostTotal, OrGCashReference) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
                                pst2 = sqlConn.prepareStatement("SELECT OrID FROM orders WHERE OrID = (SELECT MAX(OrID) FROM orders)");
                                rs2 = pst2.executeQuery(); 
                                if(rs2.next()){ 
                                    int Order = rs2.getInt("OrID");
                                    if(Order != 0){
                                    OrderID = Order + 1;
                                    pst.setInt(1, OrderID);
                                    }
   
                                } 
                                else{
                                    String strOrID = Date.format(OrIDformat)+"0001";
                                    OrderID = Integer.parseInt(strOrID); 
                                    pst.setInt(1, OrderID); 
                                } 
                                pst2.close();
                                pst.setInt(2,userID);
                                pst2 = sqlConn.prepareStatement("SELECT OrNumber FROM orders WHERE OrNumber = (SELECT MAX(OrNumber) FROM orders)");
                                rs2 = pst2.executeQuery();
                                if(rs2.next()){ 
                                    int Ordernum = rs2.getInt("OrNumber");
                                    if(Ordernum != 0){
                                    OrNumber = Ordernum + 1;
                                    pst.setInt(3, OrNumber);
                                    }
   
                                } 
                                else{
                                    String strOrNum = "11000001";
                                    OrNumber = Integer.parseInt(strOrNum); 
                                    pst.setInt(3, OrNumber); 
                                }
                                pst2.close();
                                pst2 = sqlConn.prepareStatement("INSERT into payment(OrID, PayDescription, PayAmount, PayDate, PayCheckNum) values (?,?,?,?,?)");
                                pst2.setInt(1, OrderID);
                                if(mode == "Gcash"){
                                pst2.setString(2, mode); 
                                pst.setString(13, Reference);
                                }
                                else{
                                pst2.setString(2, mode);  
                                pst.setString(13, "N/A");
                                }                               
                                pst2.setInt(3, Payment);
                                pst2.setString(4, formatteddate);
                                pst2.setInt(5,0);
                                pst2.executeUpdate();
                                pst2.close();
                                pst2 = sqlConn.prepareStatement("SELECT PayID FROM payment WHERE OrID = '"+OrderID+"'");
                                rs2 = pst2.executeQuery();
                                if(rs2.next()){ 
                                    int PayID = rs2.getInt("PayID");
                                    pst.setInt(5, PayID);
                                }                            
                                String OrType = "Retail";
                                pst.setString(4, OrType);
                                pst.setInt(6, TotalAmount);
                                int Change =  Payment - TotalAmount ;
                                pst.setInt(7, Change);
                                pst.setString(8, formatteddate);
                                pst.setString(9, formattedtime);
                                double VATableSales = (double)Math.round((TotalAmount/1.12)*100)/100;
                                double VAT = (double)Math.round((VATableSales*0.12)*100)/100;
                                pst.setDouble(10, VAT);
                                pst.setDouble(11, VATableSales);  
                                pst2.close();
                                pst3 = sqlConn.prepareStatement("INSERT into order_details(OrNumber, PrID, OrDetQuantity, OrDetCostTotal, OrDetPriceTotal) values (?,?,?,?,?)");
                                pst2 = sqlConn.prepareStatement("SELECT PrID, PrCost, PrUnitPrice FROM product");
                                pst3.setInt(1,OrNumber);
                                rs2 = pst2.executeQuery();
                                while(rs2.next()){                                    
                                    for(int k = 0; k<ordertable.getRowCount();k++){
                                    String product = rs2.getString("PrID");
                                    int Cost = rs2.getInt("PrCost");
                                    int Price = rs2.getInt("PrUnitPrice");
                                    int PrID = Integer.parseInt(product);
                                    pst3.setInt(2, PrID);
                                    String duplicate = ordertable.getValueAt(k, 0).toString(); 
                                        if(list.contains(product)&&product.equals(duplicate)){
                                        String strQuantity = ordertable.getValueAt(k, 2).toString();
                                        int Quantity = Integer.parseInt(strQuantity);
                                        pst3.setInt(3, Quantity);
                                        int PrCostTotal = Cost * Quantity;
                                        int PriceTotal = Price * Quantity; 
                                        pst3.setInt(4, PrCostTotal);
                                        pst3.setInt(5, PriceTotal); 
                                        pst3.executeUpdate();
                                        }                                   
                                    }
                                    
                                }
                                
                                pst2.close();
                                pst3.close();
                                pst2 = sqlConn.prepareStatement("SELECT SUM(OrDetCostTotal) FROM order_details WHERE OrNumber ='"+OrNumber+"' ");
                                rs2 = pst2.executeQuery();
                                if(rs2.next()){
                                int OrderCostTotal = rs2.getInt(1);
                                pst.setInt(12, OrderCostTotal);   
                                }    
                                pst2.close();
                                pst.executeUpdate(); 
                                txtChange.setText(Integer.toString(Change)); 
                                ReceiptPreview();
                                RentalandSellInstances();
                                JOptionPane.showMessageDialog(frame, "Transaction Successful!");
                                ordertable.setRowCount(0); 
                                txtTotalAmount.setText("");
                                txtChange.setText("");  
                                ReceiptPreview.setText("");   

                        }
                        else{
                           JOptionPane.showMessageDialog(frame, "Insufficient Payment, please input correct payment amount");    
                        }
                    }
                    else{
                    JOptionPane.showMessageDialog(frame, "Please input payment amount");
                    }
                }
                else{
                JOptionPane.showMessageDialog(frame, "Please select products");  
                }    
            }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(frame, "Please select products or input payment!"); 
            }catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Please contact admin for assistance!");
        }     
    }
    
        public static void ReceiptPreview(){
            int TotalAmount = 0;
            String strTotalAmount = null;
            strTotalAmount = txtTotalAmount.getText();         
            String strPayment = null;
            int payment1 = Checkout.payment;
            strPayment = Integer.toString(payment1);  
            String mode = Checkout.ModeofPayment;
            String Reference = GcashReference.Gcash;
            LocalDate Date = LocalDate.now();
            LocalTime Time = LocalTime.now();
            DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter OrIDformat = DateTimeFormatter.ofPattern("yyyy");
            String formattedtime = Time.format(timeformat);
            String formatteddate = Date.format(dateformat);
            DefaultTableModel ordertable = (DefaultTableModel)POSTable.getModel();
            ReceiptPreview.setText(ReceiptPreview.getText() + "----------------------------------------------------------------------\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "                             Receipt Preview\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "----------------------------------------------------------------------\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "                             DanPel Elegance\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "                           DAN R. PEL - Prop.\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "                       VAT REG TIN: 123456789\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "                     User Name: "+userloggedin+"\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "               Date: " +formatteddate+"    Time: "+formattedtime+"\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "----------------------------------------------------------------------\n");
            int rowcount = ordertable.getRowCount();
            ReceiptPreview.setText(ReceiptPreview.getText() + "QTY        PRICE        TOTAL        ITEM\n");
            for(int j = 0; j<rowcount;j++){                          
                String Quantity = ordertable.getValueAt(j, 2).toString();
                String Product = ordertable.getValueAt(j, 1).toString();
                String Price = ordertable.getValueAt(j, 3).toString();
                int qty = Integer.parseInt(Quantity);
                int prc = Integer.parseInt(Price);
                int total = qty*prc;
                String strtotal = Integer.toString(total);
                ReceiptPreview.setText(ReceiptPreview.getText() +Quantity+"              "+Price+"            "+strtotal+"             "+Product+"\n");                                                          
                }
            ReceiptPreview.setText(ReceiptPreview.getText() + "----------------------------------------------------------------------\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "Total Amount Due: "+txtTotalAmount.getText()+"\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "Payment: "+strPayment+"\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "Change: "+txtChange.getText()+"\n\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "----------------------------------------------------------------------\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "           Tell us about your visit! Share your experience\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "                   and approach any of our staff!\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "                      Thank you come again!\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "                This serves as your OFFICIAL RECEIPT\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "             Valid for 5 years after the specified date\n");
            ReceiptPreview.setText(ReceiptPreview.getText() + "----------------------------------------------------------------------\n");

    }
    
    private void ShowYear() throws SQLException
    {
        pst = sqlConn.prepareStatement("SELECT DATE_FORMAT(InDate,'%Y') as YearNumber from invoice GROUP BY YearNumber");
        rs = pst.executeQuery();
        while(rs.next())
        {
            int year = rs.getInt("YearNumber");
            ComboYear.addItem(year + "");
        }
        
    }
    private void ShowMonth(int year) throws SQLException
    {
        pst = sqlConn.prepareStatement("SELECT DATE_FORMAT(InDate,'%M') as MonthText, DATE_FORMAT(InDate,'%m') as MonthNumber from invoice where DATE_FORMAT(InDate,'%Y') = ? GROUP BY MonthNumber ORDER BY MonthNumber ASC");
        pst.setInt(1,year);
        rs = pst.executeQuery();
        while(rs.next())
        {
            String monthText = rs.getString("MonthText");
            int month = rs.getInt("MonthNumber");
            ComboMonth.addItem(new Model_Month(month, monthText));
        }
    }
    
    private void ShowData(int year, int month)
    {
        SHOW:
        try {
            pieChart.clearData();
            pst = sqlConn.prepareStatement("Select ScName, SUM(InAmount) from invoice join school_client using (ScID) where InStatus = \"Paid\" and  DATE_FORMAT(InDate, '%Y') = ? and DATE_FORMAT(InDate, '%m') = ?  GROUP BY ScID ORDER BY ScID");
            pst.setInt(1,year);
            pst.setInt(2,month);
            rs = pst.executeQuery();
            int index = 0;
            if(!rs.isBeforeFirst()){
                JOptionPane.showMessageDialog(Main.frame, "No rental sales for this month.");
                break SHOW;
            }
            while(rs.next())
            {
                String ScName = rs.getString(1);
                double values = rs.getDouble(2);
                pieChart.addData(new ModelPieChart(ScName,values, getColor(index++)));
            }       
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    private Color getColor(int index)
    {
        Color[] color = new Color[]{new Color(255,102,102),new Color(58,55,227),new Color(206,215,33),new Color(29,184,85),new Color(94,217,214),new Color(43,45,250),new Color(200,169,86)};
        return color[index];
    }
    
    private void getJanuary(int year) throws SQLException
    {
        pst = sqlConn.prepareStatement("SELECT SUM(SalesGrossIncome) as Income, SUM(SalesTotalCost) as Cost, SUM(SalesNetIncome) as Profit from sales where DATE_FORMAT(SalesDate,'%Y') = ? AND DATE_FORMAT(SalesDate,'%m') = '01'");
        pst.setInt(1,year);
        rs = pst.executeQuery();
        while(rs.next())
        {
            int Income = rs.getInt("Income");
            int Cost = rs.getInt("Cost");
            int Profit = rs.getInt("Profit");
            chart.addData(new ModelChart("January", new double[]{Income, Cost, Profit}));

        }
        
        
    }
    
    private void getFebruary(int year) throws SQLException
    {
        pst = sqlConn.prepareStatement("SELECT SUM(SalesGrossIncome) as Income, SUM(SalesTotalCost) as Cost, SUM(SalesNetIncome) as Profit from sales where DATE_FORMAT(SalesDate,'%Y') = ? AND DATE_FORMAT(SalesDate,'%m') = '02'");
        pst.setInt(1,year);
        rs = pst.executeQuery();
        while(rs.next())
        {
            int Income = rs.getInt("Income");
            int Cost = rs.getInt("Cost");
            int Profit = rs.getInt("Profit");
            chart.addData(new ModelChart("February", new double[]{Income, Cost, Profit}));

        }
        
        
    }
    
    private void getMarch(int year) throws SQLException
    {
        pst = sqlConn.prepareStatement("SELECT SUM(SalesGrossIncome) as Income, SUM(SalesTotalCost) as Cost, SUM(SalesNetIncome) as Profit from sales where DATE_FORMAT(SalesDate,'%Y') = ? AND DATE_FORMAT(SalesDate,'%m') = '03'");
        pst.setInt(1,year);
        rs = pst.executeQuery();
        while(rs.next())
        {
            int Income = rs.getInt("Income");
            int Cost = rs.getInt("Cost");
            int Profit = rs.getInt("Profit");
            chart.addData(new ModelChart("March", new double[]{Income, Cost, Profit}));

        }
        
        
    }
    
    private void getApril(int year) throws SQLException
    {
        pst = sqlConn.prepareStatement("SELECT SUM(SalesGrossIncome) as Income, SUM(SalesTotalCost) as Cost, SUM(SalesNetIncome) as Profit from sales where DATE_FORMAT(SalesDate,'%Y') = ? AND DATE_FORMAT(SalesDate,'%m') = '04'");
        pst.setInt(1,year);
        rs = pst.executeQuery();
        while(rs.next())
        {
            int Income = rs.getInt("Income");
            int Cost = rs.getInt("Cost");
            int Profit = rs.getInt("Profit");
            chart.addData(new ModelChart("April", new double[]{Income, Cost, Profit}));

        }
        
        
    }
    
    private void getMay(int year) throws SQLException
    {
        pst = sqlConn.prepareStatement("SELECT SUM(SalesGrossIncome) as Income, SUM(SalesTotalCost) as Cost, SUM(SalesNetIncome) as Profit from sales where DATE_FORMAT(SalesDate,'%Y') = ? AND DATE_FORMAT(SalesDate,'%m') = '05'");
        pst.setInt(1,year);
        rs = pst.executeQuery();
        while(rs.next())
        {
            int Income = rs.getInt("Income");
            int Cost = rs.getInt("Cost");
            int Profit = rs.getInt("Profit");
            chart.addData(new ModelChart("May", new double[]{Income, Cost, Profit}));

        }
        
        
    }
    
    private void getJune(int year) throws SQLException
    {
        pst = sqlConn.prepareStatement("SELECT SUM(SalesGrossIncome) as Income, SUM(SalesTotalCost) as Cost, SUM(SalesNetIncome) as Profit from sales where DATE_FORMAT(SalesDate,'%Y') = ? AND DATE_FORMAT(SalesDate,'%m') = '06'");
        pst.setInt(1,year);
        rs = pst.executeQuery();
        while(rs.next())
        {
            int Income = rs.getInt("Income");
            int Cost = rs.getInt("Cost");
            int Profit = rs.getInt("Profit");
            chart.addData(new ModelChart("June", new double[]{Income, Cost, Profit}));

        }
        
        
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Account;
    private javax.swing.JPanel AccountP;
    private javax.swing.JButton AddBank;
    private javax.swing.JButton AddCategory;
    private javax.swing.JButton AddProduct;
    private javax.swing.JButton AddProduct1;
    private javax.swing.JButton AddProvider;
    private javax.swing.JButton AddSchool;
    private javax.swing.JButton AddStock;
    private javax.swing.JButton AddTogaStock;
    private javax.swing.JLabel AddressEdit;
    private static javax.swing.JLabel AnnualRent;
    private static swing.Table BankTable;
    private javax.swing.JPanel Brands;
    private javax.swing.JPanel BrandsP;
    private javax.swing.JPanel Business;
    private javax.swing.JTextField BusinessAddress;
    private javax.swing.JTextField BusinessContact;
    private javax.swing.JTextField BusinessName;
    private javax.swing.JPanel BusinessP;
    private javax.swing.JTextField BusinessPhone;
    private javax.swing.JTextField BusinessTIN;
    private javax.swing.JPanel Category;
    private javax.swing.JPanel CategoryP;
    private javax.swing.JPanel CategoryP1;
    private static swing.Table CategoryTable;
    private javax.swing.JButton ChangePassword1;
    private javax.swing.JComboBox<Object> ComboMonth;
    private javax.swing.JComboBox<String> ComboYear;
    private javax.swing.JPanel ContractsP;
    private javax.swing.JButton CreateContract;
    private javax.swing.JLabel DailyInventory;
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel DashboardP;
    private javax.swing.JLabel Datetxt;
    private javax.swing.JButton DeleteBank;
    private javax.swing.JButton DeleteCategory;
    private javax.swing.JButton DeleteOrder;
    private javax.swing.JButton DeleteOrder1;
    private javax.swing.JButton DeleteProduct;
    private javax.swing.JButton DeleteProduct1;
    private javax.swing.JButton DeleteProduct4;
    private javax.swing.JButton DeleteProvider;
    private javax.swing.JButton DeleteSchool;
    private javax.swing.JButton EditOrder;
    private javax.swing.JButton EditProduct;
    private javax.swing.JButton EditSchool;
    private javax.swing.JTextField EmailAddress;
    private javax.swing.JTextField Firstname;
    private javax.swing.JButton GenerateBarcode3;
    private javax.swing.JButton GenerateInventoryReport1;
    private javax.swing.JButton GenerateSalesReport;
    private javax.swing.JLabel HighStock;
    private javax.swing.JPanel Inventory;
    private javax.swing.JPanel InventoryP;
    private static swing.Table InventoryTable;
    private static swing.Table InvoiceTable;
    private javax.swing.JTextField Lastname;
    private static javax.swing.JLabel LeastProductCount;
    private static javax.swing.JLabel LeastProductSold;
    private static javax.swing.JLabel LeastProductSoldCount;
    private static javax.swing.JLabel LeastProductStock;
    private javax.swing.JLabel LeastRent;
    private static javax.swing.JLabel LeastSchoolRents;
    private static javax.swing.JLabel LeastSchoolRentsCount;
    private javax.swing.JLabel LeastSold;
    private javax.swing.JLabel LowStock;
    private javax.swing.JLayeredPane MainPanel;
    private static javax.swing.JLabel MonthSold;
    private static javax.swing.JLabel MostProductCount;
    private static javax.swing.JLabel MostProductSold;
    private static javax.swing.JLabel MostProductSoldCount;
    private static javax.swing.JLabel MostProductStock;
    private javax.swing.JLabel MostRent;
    private static javax.swing.JLabel MostSchoolRents;
    private static javax.swing.JLabel MostSchoolRentsCount;
    private javax.swing.JLabel MostSold;
    private javax.swing.JLabel NameEdit;
    private javax.swing.JLabel NumberEdit;
    private javax.swing.JLabel NumberEdit1;
    private static swing.Table OrderTable;
    private javax.swing.JPanel OrdersP;
    private javax.swing.JPanel POS;
    private javax.swing.JPanel POSP;
    private static swing.Table POSTable;
    private javax.swing.JLabel Page;
    private javax.swing.JTextField PhoneNumber;
    private static swing.Table ProviderTable;
    private static javax.swing.JTextArea ReceiptPreview;
    private javax.swing.JButton ReportLoss;
    private javax.swing.JButton ResetOrder;
    private javax.swing.JPanel SalesReport;
    private javax.swing.JPanel SalesReportP;
    private javax.swing.JButton SaveChanges;
    private javax.swing.JButton SaveChanges2;
    private javax.swing.JPanel School;
    private javax.swing.JPanel SchoolP;
    private static swing.Table SchoolTable;
    private javax.swing.JButton Search;
    private javax.swing.JButton Search1;
    private javax.swing.JButton Search2;
    private javax.swing.JPanel Sidepanel;
    private javax.swing.JPanel Signout;
    private javax.swing.JLabel TINEdit;
    private javax.swing.JLabel TINEdit1;
    private javax.swing.JLabel TINEdit2;
    private javax.swing.JLabel TINEdit3;
    private javax.swing.JLabel TINEdit4;
    private javax.swing.JLabel TINEdit5;
    private javax.swing.JLabel Timetxt;
    private javax.swing.JButton TogaButton;
    private javax.swing.JButton TogaButton1;
    private javax.swing.JPanel TogaInventoryP;
    private static swing.Table TogaTable;
    private javax.swing.JPanel Transaction;
    private javax.swing.JLabel User;
    private javax.swing.JTextField Username;
    private javax.swing.JButton ViewContracts;
    private javax.swing.JButton ViewOrders;
    private javax.swing.JButton ViewOrders1;
    private javax.swing.JButton btnCheckout;
    private com.raven.chart.Chart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder17;
    private swing.PanelBorder panelBorder2;
    private swing.PanelBorder panelBorder25;
    private swing.PanelBorder panelBorder27;
    private swing.PanelBorder panelBorder29;
    private swing.PanelBorder panelBorder3;
    private swing.PanelBorder panelBorder32;
    private swing.PanelBorder panelBorder33;
    private swing.PanelBorder panelBorder4;
    private swing.PanelBorder panelBorder5;
    private swing.PanelBorder panelBorder6;
    private swing.PanelBorder panelBorder9;
    private sales.and.inventory.management.system.PieChart pieChart;
    private javax.swing.JTextField txtBarcode;
    private static javax.swing.JTextField txtChange;
    private static javax.swing.JTextField txtTotalAmount;
    // End of variables declaration//GEN-END:variables
}
