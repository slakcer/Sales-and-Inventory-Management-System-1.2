/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales.and.inventory.management.system;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Tapar, Emmanuel Christian, Ng, Juan Miguel
 */
public class generateReports {
    private static final String username = JDBC.username;
    private static final String password = JDBC.password;
    private static final String dataConn = JDBC.dataConn;
    public static String SystemDate = Main.SystemDate;
    public static String userloggedin = Main.userloggedin;
    public static int userID = Main.userID;
    private static final DecimalFormat decfor = new DecimalFormat("0.00");  
    public String imFile = "C:\\Users\\tapar\\OneDrive\\Documents\\NetBeansProjects\\Sales and Inventory Management System\\ReceiptLogoo.png"; 
    Connection sqlConn =null;
    PreparedStatement pst = null, pst2 = null, pst3 = null, pst4 = null,pst5 = null,pst6 = null,pst7 = null,pst8 = null;
    ResultSet rs=null, rs2=null,rs3=null, rs4=null, rs5=null,rs6=null, r7=null, rs8=null;
    JDBC db = new JDBC();

    public void generateInventoryReport(){
        try{
        db.Connect();
        sqlConn = DriverManager.getConnection(dataConn,username,password);
        int PrID=0, count=0, PrStock=0, PrUnitPrice=0, PrCost=0, PrTotalValue=0, GrandTotalValue=0;
        String PrName=null, CaDescription=null,BuName=null, BuEmail=null,BuTIN=null, BuAddress=null, OrTime=null, strOrID=null;
        String struserID = Integer.toString(userID);
        pst = sqlConn.prepareStatement("SELECT * FROM business_info"); 
        rs = pst.executeQuery();
            if(rs.next()){
            BuName = rs.getString("BuName");
            BuTIN = rs.getString("BuTIN");
            BuEmail = rs.getString("BuEmail");
            BuAddress = rs.getString("BuAddress");
            //needs phone number 
            }
        pst.close();
        pst = sqlConn.prepareStatement("SELECT P.PrName, C.CaDescription, P.PrCost, P.PrUnitPrice, P.PrStock FROM product P INNER JOIN product_category C WHERE P.CaID=C.CaID"); 
        rs = pst.executeQuery();
        LocalDate Date = LocalDate.now(); 
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatteddate = Date.format(dateformat);
        String FileName = String.format("C:\\Sales and Inventory Documents\\Inventory Reports\\Inventory Report-%s.pdf",formatteddate);
        float left = 30;
        float right = 30;
        float top = 60;
        float bottom = 0;
        Document InventoryReport = new Document(PageSize.A4, left, right, top, bottom);      
        // Creating an Image object 
        Image image = Image.getInstance(imFile);             
        image.scaleToFit(Utilities.millimetersToPoints(110),Utilities.millimetersToPoints(110));
        image.setAlignment(Image.MIDDLE);               
        InventoryReport.setMargins(25, 25, 25, 25);
        Font title = new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL,BaseColor.BLACK);
        Font strInventory = new Font(Font.FontFamily.HELVETICA,20,Font.NORMAL,BaseColor.BLACK);
        Font products = new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL,BaseColor.BLACK);
        Font details = new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL,BaseColor.BLACK);
        Font lineFont = new Font(Font.FontFamily.HELVETICA,18,Font.NORMAL,BaseColor.BLACK);
        Font newlineFont = new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL,BaseColor.BLACK);
        OutputStream output = new FileOutputStream(FileName);
        PdfWriter writer = PdfWriter.getInstance(InventoryReport, output); 
        Paragraph line = new Paragraph("------------------------------------------------------------------------------------------", lineFont);
        Paragraph newline = new Paragraph("\n",newlineFont);
        Paragraph BusinessName = new Paragraph(PrName,title);
        BusinessName.setAlignment(Element.ALIGN_CENTER);     
        Paragraph BusinessEmail = new Paragraph(BuEmail,title);
        BusinessEmail.setAlignment(Element.ALIGN_CENTER);
        Paragraph BusinessAddress = new Paragraph(BuAddress+"\nDAN R. PEL - Prop.",title);
        BusinessAddress.setAlignment(Element.ALIGN_CENTER);
        Paragraph PaInventoryReport = new Paragraph("INVENTORY REPORT",strInventory);
        PaInventoryReport.setAlignment(Element.ALIGN_CENTER);
        Paragraph UserDetails = new Paragraph("Requested by: "+userloggedin,title);
        UserDetails.setAlignment(Element.ALIGN_LEFT);
        Paragraph PaDate = new Paragraph("Date: "+formatteddate,title);
        PaDate.setAlignment(Element.ALIGN_LEFT);
        Paragraph devs = new Paragraph("System Developer:\nTapar, Emmanuel Christian\nNg, Juan Miguel",newlineFont);
        devs.setAlignment(Element.ALIGN_CENTER);
        //Creation of Report
        InventoryReport.open(); 
        InventoryReport.add(new Chunk(""));
        float [] columnwidth = {6f,4f,3f,2f,2f,2f};
        PdfPTable Inventory = new PdfPTable(6);
        Inventory.setWidthPercentage(100);
        Inventory.setWidths(columnwidth);
        PdfPCell ProdName = new PdfPCell(new Phrase("Product Name",details));
        PdfPCell ProdCategory = new PdfPCell(new Phrase("Category",details));
        PdfPCell ProdCost = new PdfPCell(new Phrase("Product Cost",details));
        PdfPCell ProdUnitPrice = new PdfPCell(new Phrase("Unit Price",details));
        PdfPCell ProdStock = new PdfPCell(new Phrase("Stock",details));
        PdfPCell ProdTotalValue = new PdfPCell(new Phrase("Total Value",details));
        ProdName.setBorder(Rectangle.BOX);
        ProdName.setBackgroundColor(BaseColor.LIGHT_GRAY);
        Inventory.addCell(ProdName).setHorizontalAlignment(Element.ALIGN_CENTER);
        ProdCategory.setBorder(Rectangle.BOX);
        ProdCategory.setBackgroundColor(BaseColor.LIGHT_GRAY);
        Inventory.addCell(ProdCategory).setHorizontalAlignment(Element.ALIGN_CENTER);
        ProdCost.setBorder(Rectangle.BOX);
        ProdCost.setBackgroundColor(BaseColor.LIGHT_GRAY);
        Inventory.addCell(ProdCost).setHorizontalAlignment(Element.ALIGN_CENTER);
        ProdUnitPrice.setBorder(Rectangle.BOX);
        ProdUnitPrice.setBackgroundColor(BaseColor.LIGHT_GRAY);
        Inventory.addCell(ProdUnitPrice).setHorizontalAlignment(Element.ALIGN_CENTER);
        ProdStock.setBorder(Rectangle.BOX);
        ProdStock.setBackgroundColor(BaseColor.LIGHT_GRAY);
        Inventory.addCell(ProdStock).setHorizontalAlignment(Element.ALIGN_CENTER);
        ProdTotalValue.setBorder(Rectangle.BOX);
        ProdTotalValue.setBackgroundColor(BaseColor.LIGHT_GRAY);
        Inventory.addCell(ProdTotalValue).setHorizontalAlignment(Element.ALIGN_CENTER);
        InventoryReport.add(image);
        InventoryReport.add(BusinessName); 
        InventoryReport.add(BusinessAddress);
        InventoryReport.add(BusinessEmail);  
        InventoryReport.add(PaInventoryReport);
        InventoryReport.add(newline);
        InventoryReport.add(UserDetails);
        InventoryReport.add(PaDate); 
        InventoryReport.add(newline);      
        while(rs.next()){
            PrTotalValue = 0;
            PrName = rs.getString("PrName");
            PdfPCell PrNameCell = new PdfPCell(new Phrase(PrName,products));
            PrNameCell.setBorder(Rectangle.BOX);                    
            Inventory.addCell(PrNameCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            CaDescription = rs.getString("CaDescription");
            PdfPCell CaDescriptionCell = new PdfPCell(new Phrase(CaDescription,products));
            CaDescriptionCell.setBorder(Rectangle.BOX);                    
            Inventory.addCell(CaDescriptionCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            PrCost = rs.getInt("PrCost");
            String strPrCost = Integer.toString(PrCost);
            PdfPCell PrCostCell = new PdfPCell(new Phrase("P"+strPrCost,products));
            PrCostCell.setBorder(Rectangle.BOX);                    
            Inventory.addCell(PrCostCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            PrUnitPrice = rs.getInt("PrUnitPrice");
            String strPrUnitPrice = Integer.toString(PrUnitPrice);
            PdfPCell PrUnitPriceCell = new PdfPCell(new Phrase("P"+strPrUnitPrice,products));
            PrUnitPriceCell.setBorder(Rectangle.BOX);                    
            Inventory.addCell(PrUnitPriceCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            PrStock = rs.getInt("PrStock");
            String strPrStock = Integer.toString(PrStock);
            PdfPCell PrStockCell = new PdfPCell(new Phrase(strPrStock,products));
            PrStockCell.setBorder(Rectangle.BOX);                    
            Inventory.addCell(PrStockCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            PrTotalValue = PrUnitPrice * PrStock;
            String strPrTotalValue= Integer.toString(PrTotalValue);
            PdfPCell PrTotalValueCell = new PdfPCell(new Phrase("P"+strPrTotalValue,products));
            PrTotalValueCell.setBorder(Rectangle.BOX);                    
            Inventory.addCell(PrTotalValueCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            GrandTotalValue = GrandTotalValue + PrTotalValue;
            }     
        Paragraph PaGrandTotal = new Paragraph("Grand Total Product Value: P"+GrandTotalValue,title);
        PaDate.setAlignment(Element.ALIGN_LEFT);
        InventoryReport.add((Element)Inventory);        
        InventoryReport.add(line);
        InventoryReport.add(newline);
        InventoryReport.add(PaGrandTotal);
        InventoryReport.add(newline);
        InventoryReport.add(newline);
        InventoryReport.add(devs);
        InventoryReport.newPage();       
        InventoryReport.close();
        writer.close(); 
        
        }

        catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        
    } 
    public void generateSalesReport(String Range, String Date1, String Date2){
        try{
        db.Connect();
        sqlConn = DriverManager.getConnection(dataConn,username,password);
        int SalesGrossIncome=0, SalesTotalLoss=0, SalesTotalCost=0, SalesTotalPenalties=0, OrTotalAmount=0,OrCostTotal=0,OrID=0, GrandGrossIncome=0, GrandRentalGrossIncome=0, GrandPenalties=0,GrandCost=0,GrandLosses=0;
        String PrName=null, CaDescription=null,BuName=null, BuEmail=null,BuTIN=null, BuAddress=null, OrTime=null, strOrID=null, SalesDate=null, strDate=null, FileName;
        float SalesNetIncome=0, OrNetIncome=0, GrandNet=0;
        
        String struserID = Integer.toString(userID);
        pst = sqlConn.prepareStatement("SELECT * FROM business_info"); 
        rs = pst.executeQuery();
            if(rs.next()){
            BuName = rs.getString("BuName");
            BuTIN = rs.getString("BuTIN");
            BuEmail = rs.getString("BuEmail");
            BuAddress = rs.getString("BuAddress");
            //needs phone number 
            }
        pst.close();
        //      
        LocalDate Date = LocalDate.now();
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter month = DateTimeFormatter.ofPattern("MMMM");
        DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
        String formatteddate = Date.format(dateformat);
        String formatmonth = Date.format(month);
        String formatyear = Date.format(year);
        strDate = formatteddate;
        String daterange = Date1+" to "+Date2;
        if(Date2.equals("")){
        FileName = String.format("C:\\Sales and Inventory Documents\\Sales Reports\\Sales Report-%s.pdf",formatteddate);    
        }
        else if(Range.equals("Monthly")){
        FileName = String.format("C:\\Sales and Inventory Documents\\Sales Reports\\Sales Report for %s.pdf",formatmonth);    
        }
        else if(Range.equals("Annually")){
        FileName = String.format("C:\\Sales and Inventory Documents\\Sales Reports\\Sales Report for %s.pdf",formatyear);    
        }
        else{
        FileName = String.format("C:\\Sales and Inventory Documents\\Sales Reports\\Sales Report for %s.pdf",daterange);      
        }       
        float left = 30;
        float right = 30;
        float top = 60;
        float bottom = 0;
        Document SalesReport = new Document(PageSize.A4, left, right, top, bottom);
        // Creating an Image object 
        Image image = Image.getInstance(imFile);             
        image.scaleToFit(Utilities.millimetersToPoints(110),Utilities.millimetersToPoints(110));
        image.setAlignment(Image.MIDDLE);               
        SalesReport.setMargins(25, 25, 25, 25);
        //Setting of Paragraphs, Fonts, Contents
        Font title = new Font(Font.FontFamily.HELVETICA,12,Font.NORMAL,BaseColor.BLACK);
        Font strInventory = new Font(Font.FontFamily.HELVETICA,20,Font.NORMAL,BaseColor.BLACK);
        Font products = new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL,BaseColor.BLACK);
        Font details = new Font(Font.FontFamily.HELVETICA,10,Font.NORMAL,BaseColor.BLACK);
        Font lineFont = new Font(Font.FontFamily.HELVETICA,18,Font.NORMAL,BaseColor.BLACK);
        Font newlineFont = new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL,BaseColor.BLACK);
        OutputStream output = new FileOutputStream(FileName);
        PdfWriter writer = PdfWriter.getInstance(SalesReport, output); 
        Paragraph line = new Paragraph("------------------------------------------------------------------------------------------", lineFont);
        Paragraph newline = new Paragraph("\n",newlineFont);
        Paragraph BusinessName = new Paragraph(BuName,title);
        BusinessName.setAlignment(Element.ALIGN_CENTER);
        Paragraph BusinessEmail = new Paragraph(BuEmail,title);
        BusinessEmail.setAlignment(Element.ALIGN_CENTER);
        Paragraph BusinessAddress = new Paragraph(BuAddress+"\nDAN R. PEL - Prop.",title);
        BusinessAddress.setAlignment(Element.ALIGN_CENTER);
        Paragraph PaInventoryReport = new Paragraph("SALES REPORT",strInventory);
        PaInventoryReport.setAlignment(Element.ALIGN_CENTER);
        Paragraph UserDetails = new Paragraph("Requested by: "+userloggedin,title);
        UserDetails.setAlignment(Element.ALIGN_LEFT);
        Paragraph PaDate = new Paragraph("Date Generated: "+formatteddate,title);
        PaDate.setAlignment(Element.ALIGN_LEFT);
        Paragraph PaSalesDate = new Paragraph("Sales Date: "+strDate,title);
        PaDate.setAlignment(Element.ALIGN_LEFT);
        Paragraph PaSalesRange = new Paragraph("Sales Date: "+daterange,title);
        PaDate.setAlignment(Element.ALIGN_LEFT);
        Paragraph Retail = new Paragraph("RETAIL SALES",title);
        Retail.setAlignment(Element.ALIGN_LEFT);
        Paragraph Rental = new Paragraph("RENTAL SALES",title);
        Rental.setAlignment(Element.ALIGN_LEFT);
        Paragraph Penalties = new Paragraph("PENALTIES",title);
        Penalties.setAlignment(Element.ALIGN_LEFT);
        Paragraph Losses = new Paragraph("LOSSES",title);
        Losses.setAlignment(Element.ALIGN_LEFT);
        Paragraph TotalSales = new Paragraph("TOTAL SALES",title);
        TotalSales.setAlignment(Element.ALIGN_LEFT);
        Paragraph devs = new Paragraph("System Developer:\nTapar, Emmanuel Christian\nNg, Juan Miguel",newlineFont);
        devs.setAlignment(Element.ALIGN_CENTER);
        Paragraph disclaimer = new Paragraph("*Note: Rental Gross Income not Included for computation of Net Income*\n*Note: Doesn't include computation of current day sales for custom, monthly, and annual range.*\n*Note: Taxes are not deducted on the Total Net Income*",newlineFont);
        disclaimer.setAlignment(Element.ALIGN_LEFT);
        //Creation of Report
        SalesReport.open(); 
        SalesReport.add(new Chunk(""));
        //Retail Sales Table
        float [] columnwidth = {4f,3f,3f,2f,2f};
        PdfPTable RetailSales = new PdfPTable(5);
        RetailSales.setWidthPercentage(100);
        RetailSales.setWidths(columnwidth);
        PdfPCell TransID = new PdfPCell(new Phrase("Transaction ID",details));
        PdfPCell GrossSales = new PdfPCell(new Phrase("Gross Income",details));
        PdfPCell TotalCost = new PdfPCell(new Phrase("Product Total Cost",details));     
        PdfPCell NetIncome = new PdfPCell(new Phrase("Net Income",details));
        PdfPCell TransDate = new PdfPCell(new Phrase("Transaction Date",details));
        TransID.setBorder(Rectangle.BOX);
        TransID.setBackgroundColor(BaseColor.LIGHT_GRAY);
        RetailSales.addCell(TransID).setHorizontalAlignment(Element.ALIGN_CENTER);
        GrossSales.setBorder(Rectangle.BOX);
        GrossSales.setBackgroundColor(BaseColor.LIGHT_GRAY);
        RetailSales.addCell(GrossSales).setHorizontalAlignment(Element.ALIGN_CENTER);
        TotalCost.setBorder(Rectangle.BOX);
        TotalCost.setBackgroundColor(BaseColor.LIGHT_GRAY);
        RetailSales.addCell(TotalCost).setHorizontalAlignment(Element.ALIGN_CENTER);
        NetIncome.setBorder(Rectangle.BOX);
        NetIncome.setBackgroundColor(BaseColor.LIGHT_GRAY);
        RetailSales.addCell(NetIncome).setHorizontalAlignment(Element.ALIGN_CENTER);
        TransDate.setBorder(Rectangle.BOX);
        TransDate.setBackgroundColor(BaseColor.LIGHT_GRAY);
        RetailSales.addCell(TransDate).setHorizontalAlignment(Element.ALIGN_CENTER);
        //Rental Sales Table
        float [] columnwidth2 = {3f,2f,2f,3f};
        PdfPTable RentalSales = new PdfPTable(4);
        RentalSales.setWidthPercentage(100);
        RentalSales.setWidths(columnwidth2);
        PdfPCell OfrNumberCell = new PdfPCell(new Phrase("Official Receipt Number",details));
        TransID.setBorder(Rectangle.BOX);
        TransID.setBackgroundColor(BaseColor.LIGHT_GRAY);
        RentalSales.addCell(TransID).setHorizontalAlignment(Element.ALIGN_CENTER);
        OfrNumberCell.setBorder(Rectangle.BOX);
        OfrNumberCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        RentalSales.addCell(OfrNumberCell).setHorizontalAlignment(Element.ALIGN_CENTER);
        GrossSales.setBorder(Rectangle.BOX);
        GrossSales.setBackgroundColor(BaseColor.LIGHT_GRAY);
        RentalSales.addCell(GrossSales).setHorizontalAlignment(Element.ALIGN_CENTER);
        TransDate.setBorder(Rectangle.BOX);
        TransDate.setBackgroundColor(BaseColor.LIGHT_GRAY);
        RentalSales.addCell(TransDate).setHorizontalAlignment(Element.ALIGN_CENTER);
        //Penalties Table
        float [] columnwidth3 = {3f,6f,3f,3f};
        PdfPTable PenaltyTable = new PdfPTable(4);
        PenaltyTable.setWidthPercentage(100);
        PenaltyTable.setWidths(columnwidth3);
        PdfPCell PenID = new PdfPCell(new Phrase("Penalty ID",details));
        PdfPCell PenDescription = new PdfPCell(new Phrase("Penalty Description",details));
        PdfPCell PenAmount = new PdfPCell(new Phrase("Penalty Amount",details));
        PenID.setBorder(Rectangle.BOX);
        PenID.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PenaltyTable.addCell(PenID).setHorizontalAlignment(Element.ALIGN_CENTER);
        PenDescription.setBorder(Rectangle.BOX);
        PenDescription.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PenaltyTable.addCell(PenDescription).setHorizontalAlignment(Element.ALIGN_CENTER);
        PenAmount.setBorder(Rectangle.BOX);
        PenAmount.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PenaltyTable.addCell(PenAmount).setHorizontalAlignment(Element.ALIGN_CENTER);
        TransDate.setBorder(Rectangle.BOX);
        TransDate.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PenaltyTable.addCell(TransDate).setHorizontalAlignment(Element.ALIGN_CENTER);
        //Losses Table
        float [] columnwidth4 = {3f,6f,3f,3f,3f};
        PdfPTable LossTable = new PdfPTable(5);
        LossTable.setWidthPercentage(100);
        LossTable.setWidths(columnwidth4);
        PdfPCell LoID = new PdfPCell(new Phrase("Loss ID",details));
        PdfPCell PrNamee = new PdfPCell(new Phrase("Product Name",details));
        PdfPCell LoQuantity = new PdfPCell(new Phrase("Loss Quantity",details));
        PdfPCell LoValue = new PdfPCell(new Phrase("Loss Value",details));
        LoID.setBorder(Rectangle.BOX);
        LoID.setBackgroundColor(BaseColor.LIGHT_GRAY);
        LossTable.addCell(LoID).setHorizontalAlignment(Element.ALIGN_CENTER);
        PrNamee.setBorder(Rectangle.BOX);
        PrNamee.setBackgroundColor(BaseColor.LIGHT_GRAY);
        LossTable.addCell(PrNamee).setHorizontalAlignment(Element.ALIGN_CENTER);
        LoQuantity.setBorder(Rectangle.BOX);
        LoQuantity.setBackgroundColor(BaseColor.LIGHT_GRAY);
        LossTable.addCell(LoQuantity).setHorizontalAlignment(Element.ALIGN_CENTER);
        LoValue.setBorder(Rectangle.BOX);
        LoValue.setBackgroundColor(BaseColor.LIGHT_GRAY);
        LossTable.addCell(LoValue).setHorizontalAlignment(Element.ALIGN_CENTER);
        TransDate.setBorder(Rectangle.BOX);
        TransDate.setBackgroundColor(BaseColor.LIGHT_GRAY);
        LossTable.addCell(TransDate).setHorizontalAlignment(Element.ALIGN_CENTER);
        //Total Table
        float [] columnwidth5 = {3f,4f,6f,3f,3f,3f,3f};
        PdfPTable TotalTable = new PdfPTable(7);
        TotalTable.setWidthPercentage(100);
        TotalTable.setWidths(columnwidth5);
        PdfPCell TotDate = new PdfPCell(new Phrase("Sales Date",details));
        PdfPCell TotGrossIncome = new PdfPCell(new Phrase("Gross Income",details));
        PdfPCell TotRentGrossIncome = new PdfPCell(new Phrase("Rental Gross Income",details));
        PdfPCell TotPenalties = new PdfPCell(new Phrase("Penalties",details));
        PdfPCell TotCost = new PdfPCell(new Phrase("Cost",details));
        PdfPCell TotLoss = new PdfPCell(new Phrase("Losses",details));
        PdfPCell TotNetIncome = new PdfPCell(new Phrase("Net Income",details));
        TotDate.setBorder(Rectangle.BOX);
        TotDate.setBackgroundColor(BaseColor.LIGHT_GRAY);
        TotalTable.addCell(TotDate).setHorizontalAlignment(Element.ALIGN_CENTER);
        TotGrossIncome.setBorder(Rectangle.BOX);
        TotGrossIncome.setBackgroundColor(BaseColor.LIGHT_GRAY);
        TotalTable.addCell(TotGrossIncome).setHorizontalAlignment(Element.ALIGN_CENTER);
        TotRentGrossIncome.setBorder(Rectangle.BOX);
        TotRentGrossIncome.setBackgroundColor(BaseColor.LIGHT_GRAY);
        TotalTable.addCell(TotRentGrossIncome).setHorizontalAlignment(Element.ALIGN_CENTER);
        TotPenalties.setBorder(Rectangle.BOX);
        TotPenalties.setBackgroundColor(BaseColor.LIGHT_GRAY);
        TotalTable.addCell(TotPenalties).setHorizontalAlignment(Element.ALIGN_CENTER);
        TotCost.setBorder(Rectangle.BOX);
        TotCost.setBackgroundColor(BaseColor.LIGHT_GRAY);
        TotalTable.addCell(TotCost).setHorizontalAlignment(Element.ALIGN_CENTER);
        TotLoss.setBorder(Rectangle.BOX);
        TotLoss.setBackgroundColor(BaseColor.LIGHT_GRAY);
        TotalTable.addCell(TotLoss).setHorizontalAlignment(Element.ALIGN_CENTER);
        TotNetIncome.setBorder(Rectangle.BOX);
        TotNetIncome.setBackgroundColor(BaseColor.LIGHT_GRAY);
        TotalTable.addCell(TotNetIncome).setHorizontalAlignment(Element.ALIGN_CENTER);
        
        //If Today
        if(strDate.equals(Date1)){
        //Getting Transactions
        pst = sqlConn.prepareStatement("SELECT * FROM orders WHERE OrDate = '"+strDate+"'"); 
        rs = pst.executeQuery();
        while(rs.next()){
            OrNetIncome = 0;
            OrID = rs.getInt("OrID");
            strOrID = Integer.toString(OrID);
            PdfPCell OrIDCell = new PdfPCell(new Phrase(strOrID,products));
            OrIDCell.setBorder(Rectangle.BOX);                    
            RetailSales.addCell(OrIDCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            OrTotalAmount = rs.getInt("OrTotalAmount");
            String strOrTotalAmount = Integer.toString(OrTotalAmount);
            PdfPCell PrNameCell = new PdfPCell(new Phrase("P"+strOrTotalAmount,products));
            PrNameCell.setBorder(Rectangle.BOX);                    
            RetailSales.addCell(PrNameCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            OrCostTotal = rs.getInt("OrCostTotal");
            String strOrCostTotal = Integer.toString(OrCostTotal);
            PdfPCell CaDescriptionCell = new PdfPCell(new Phrase("P"+strOrCostTotal,products));
            CaDescriptionCell.setBorder(Rectangle.BOX);                    
            RetailSales.addCell(CaDescriptionCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            OrNetIncome = OrTotalAmount - OrCostTotal;
            String strOrNetIncome = decfor.format(OrNetIncome);
            PdfPCell PrStockCell = new PdfPCell(new Phrase("P"+strOrNetIncome,products));
            PrStockCell.setBorder(Rectangle.BOX);                    
            RetailSales.addCell(PrStockCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            Date date = rs.getDate("OrDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDatee = dateFormat.format(date);
            PdfPCell TrDate = new PdfPCell(new Phrase(strDatee,products));
            TrDate.setBorder(Rectangle.BOX);                    
            RetailSales.addCell(TrDate).setHorizontalAlignment(Element.ALIGN_CENTER);
            GrandGrossIncome = GrandGrossIncome + OrTotalAmount;
            GrandCost = GrandCost + OrCostTotal;
            }          
        pst.close();
        //Getting Rental Sales through Official Receipts
        pst = sqlConn.prepareStatement("SELECT * FROM official_receipt WHERE OfrDate = '"+strDate+"'"); 
        rs = pst.executeQuery();
        while(rs.next()){
            int SalesID = rs.getInt("OfrID");
            String strSalesID = Integer.toString(SalesID);
            PdfPCell OrIDCell = new PdfPCell(new Phrase(strSalesID,products));
            OrIDCell.setBorder(Rectangle.BOX);                    
            RentalSales.addCell(OrIDCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int OfrNumber = rs.getInt("OfrNumber");
            String strOfrNumber = "00"+Integer.toString(OfrNumber);
            PdfPCell PrNameCell = new PdfPCell(new Phrase(strOfrNumber,products));
            PrNameCell.setBorder(Rectangle.BOX);                    
            RentalSales.addCell(PrNameCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int OfrTotal = rs.getInt("OfrTotalAmountDue");
            String strOfrTotal = Integer.toString(OfrTotal);
            PdfPCell PrCostCell = new PdfPCell(new Phrase("P"+strOfrTotal,products));
            PrCostCell.setBorder(Rectangle.BOX);                    
            RentalSales.addCell(PrCostCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            Date date = rs.getDate("OfrDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDatee = dateFormat.format(date);
            PdfPCell TrDate = new PdfPCell(new Phrase(strDatee,products));
            TrDate.setBorder(Rectangle.BOX);                    
            RentalSales.addCell(TrDate).setHorizontalAlignment(Element.ALIGN_CENTER);
            GrandRentalGrossIncome = GrandRentalGrossIncome + OfrTotal; 
            }  
        pst.close();
        //Getting Penalties
        pst = sqlConn.prepareStatement("SELECT * FROM penalties WHERE PenalDate = '"+strDate+"'"); 
        rs = pst.executeQuery();
        while(rs.next()){
            int PenalID = rs.getInt("PenalID");
            String strPenalID = Integer.toString(PenalID);
            PdfPCell OrIDCell = new PdfPCell(new Phrase(strPenalID,products));
            OrIDCell.setBorder(Rectangle.BOX);                    
            PenaltyTable.addCell(OrIDCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            String PenalDescription = rs.getString("PenalDescription");
            PdfPCell PrNameCell = new PdfPCell(new Phrase(PenalDescription,products));
            PrNameCell.setBorder(Rectangle.BOX);                    
            PenaltyTable.addCell(PrNameCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int PenalAmount = rs.getInt("PenalAmount");
            String strPenalAmount = Integer.toString(PenalAmount);
            PdfPCell PrCostCell = new PdfPCell(new Phrase("P"+strPenalAmount,products));
            PrCostCell.setBorder(Rectangle.BOX);                    
            PenaltyTable.addCell(PrCostCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            Date date = rs.getDate("PenalDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDatee = dateFormat.format(date);
            PdfPCell TrDate = new PdfPCell(new Phrase(strDatee,products));
            TrDate.setBorder(Rectangle.BOX);                    
            PenaltyTable.addCell(TrDate).setHorizontalAlignment(Element.ALIGN_CENTER);
            GrandPenalties = GrandPenalties + PenalAmount;
            }  
        pst.close();
        //Getting Losses
        pst = sqlConn.prepareStatement("SELECT * FROM losses L INNER JOIN product P WHERE L.LossDate = '"+strDate+"' AND L.PrID = P.PrID" ); 
        rs = pst.executeQuery();
        while(rs.next()){
            int LossID = rs.getInt("LossID");
            String strLossID = Integer.toString(LossID);
            PdfPCell OrIDCell = new PdfPCell(new Phrase(strLossID,products));
            OrIDCell.setBorder(Rectangle.BOX);                    
            LossTable.addCell(OrIDCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            String ProdName = rs.getString("PrName");
            PdfPCell PrNameCell = new PdfPCell(new Phrase(ProdName,products));
            PrNameCell.setBorder(Rectangle.BOX);                    
            LossTable.addCell(PrNameCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int LossQuantity = rs.getInt("LossQuantity");
            String strLossQuantity = Integer.toString(LossQuantity);
            PdfPCell PrCostCell = new PdfPCell(new Phrase(strLossQuantity,products));
            PrCostCell.setBorder(Rectangle.BOX);                    
            LossTable.addCell(PrCostCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int LossValue = rs.getInt("LossTotalCost");
            String strLossValue = Integer.toString(LossValue);
            PdfPCell CaDescriptionCell = new PdfPCell(new Phrase("P"+strLossValue,products));
            CaDescriptionCell.setBorder(Rectangle.BOX);                    
            LossTable.addCell(CaDescriptionCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            Date date = rs.getDate("LossDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDatee = dateFormat.format(date);
            PdfPCell TrDate = new PdfPCell(new Phrase(strDatee,products));
            TrDate.setBorder(Rectangle.BOX);                    
            LossTable.addCell(TrDate).setHorizontalAlignment(Element.ALIGN_CENTER);
            GrandLosses = GrandLosses + LossValue;         
            }  
        pst.close();
        //Getting Total Sales
        pst = sqlConn.prepareStatement("SELECT * FROM sales WHERE SalesDate = '"+strDate+"'" ); 
        rs = pst.executeQuery();
        while(rs.next()){
            Date date = rs.getDate("SalesDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDatee = dateFormat.format(date);
            PdfPCell TrDate = new PdfPCell(new Phrase(strDatee,products));
            TrDate.setBorder(Rectangle.BOX);                    
            RetailSales.addCell(TrDate).setHorizontalAlignment(Element.ALIGN_CENTER);
            int TotalGrossIncome = rs.getInt("SalesGrossIncome");
            String strTotalGrossIncome = Integer.toString(TotalGrossIncome);
            PdfPCell PrNameCell = new PdfPCell(new Phrase("P"+strTotalGrossIncome,products));
            PrNameCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(PrNameCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int TotalRentalGrossIncome = rs.getInt("SalesTotalRentalGross"); 
            String strTotalRentalGrossIncome = Integer.toString(TotalRentalGrossIncome);
            PdfPCell TotalRentalGrossIncomeCell = new PdfPCell(new Phrase("P"+strTotalRentalGrossIncome,products));
            TotalRentalGrossIncomeCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(TotalRentalGrossIncomeCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int TotalPenalties = rs.getInt("SalesTotalPenalties");
            String strTotalPenalties = Integer.toString(TotalPenalties);
            PdfPCell PrCostCell = new PdfPCell(new Phrase("P"+strTotalPenalties,products));
            PrCostCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(PrCostCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int TotalCostt = rs.getInt("SalesTotalCost");
            String strTotalCostt = Integer.toString(TotalCostt);
            PdfPCell strTotalCosttCell = new PdfPCell(new Phrase("P"+strTotalCostt,products));
            strTotalCosttCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(strTotalCosttCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int TotalLosses = rs.getInt("SalesTotalLoss");
            String strTotalLosses = Integer.toString(TotalLosses);
            PdfPCell strTotalLossesCell = new PdfPCell(new Phrase("P"+strTotalLosses,products));
            strTotalLossesCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(strTotalLossesCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            float TotalNet = rs.getFloat("SalesNetIncome");
            String strTotalNet = decfor.format(TotalNet);
            PdfPCell strTotalNetCell = new PdfPCell(new Phrase("P"+strTotalNet,products));
            strTotalNetCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(strTotalNetCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            }  
        pst.close(); 
        GrandNet = (GrandGrossIncome+GrandPenalties)-(GrandLosses+GrandCost);
        }
        //If Date has Range (Custom, Monthly, Annually)
        else{
        pst = sqlConn.prepareStatement("SELECT * FROM orders WHERE OrDate BETWEEN '"+Date1+"' AND '"+Date2+"'"); 
        rs = pst.executeQuery();
        while(rs.next()){
            OrNetIncome = 0;
            OrID = rs.getInt("OrID");
            strOrID = Integer.toString(OrID);
            PdfPCell OrIDCell = new PdfPCell(new Phrase(strOrID,products));
            OrIDCell.setBorder(Rectangle.BOX);                    
            RetailSales.addCell(OrIDCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            OrTotalAmount = rs.getInt("OrTotalAmount");
            String strOrTotalAmount = Integer.toString(OrTotalAmount);
            PdfPCell PrNameCell = new PdfPCell(new Phrase("P"+strOrTotalAmount,products));
            PrNameCell.setBorder(Rectangle.BOX);                    
            RetailSales.addCell(PrNameCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            OrCostTotal = rs.getInt("OrCostTotal");
            String strOrCostTotal = Integer.toString(OrCostTotal);
            PdfPCell CaDescriptionCell = new PdfPCell(new Phrase("P"+strOrCostTotal,products));
            CaDescriptionCell.setBorder(Rectangle.BOX);                    
            RetailSales.addCell(CaDescriptionCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            OrNetIncome = OrTotalAmount - OrCostTotal;
            String strOrNetIncome = decfor.format(OrNetIncome);
            PdfPCell PrStockCell = new PdfPCell(new Phrase("P"+strOrNetIncome,products));
            PrStockCell.setBorder(Rectangle.BOX);                    
            RetailSales.addCell(PrStockCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            Date date = rs.getDate("OrDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDatee = dateFormat.format(date);
            PdfPCell TrDate = new PdfPCell(new Phrase(strDatee,products));
            TrDate.setBorder(Rectangle.BOX);                    
            RetailSales.addCell(TrDate).setHorizontalAlignment(Element.ALIGN_CENTER);
            
            }  
        pst.close();
        //Getting Rental Sales through Official Receipts
        pst = sqlConn.prepareStatement("SELECT * FROM official_receipt WHERE OfrDate BETWEEN '"+Date1+"' AND '"+Date2+"'"); 
        rs = pst.executeQuery();
        while(rs.next()){
            int SalesID = rs.getInt("OfrID");
            String strSalesID = Integer.toString(SalesID);
            PdfPCell OrIDCell = new PdfPCell(new Phrase(strSalesID,products));
            OrIDCell.setBorder(Rectangle.BOX);                    
            RentalSales.addCell(OrIDCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int OfrNumber = rs.getInt("OfrNumber");
            String strOfrNumber = "00"+Integer.toString(OfrNumber);
            PdfPCell PrNameCell = new PdfPCell(new Phrase(strOfrNumber,products));
            PrNameCell.setBorder(Rectangle.BOX);                    
            RentalSales.addCell(PrNameCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int OfrTotal = rs.getInt("OfrTotalAmountDue");
            String strOfrTotal = Integer.toString(OfrTotal);
            PdfPCell PrCostCell = new PdfPCell(new Phrase("P"+strOfrTotal,products));
            PrCostCell.setBorder(Rectangle.BOX);                    
            RentalSales.addCell(PrCostCell).setHorizontalAlignment(Element.ALIGN_CENTER); 
            Date date = rs.getDate("OfrDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDatee = dateFormat.format(date);
            PdfPCell TrDate = new PdfPCell(new Phrase(strDatee,products));
            TrDate.setBorder(Rectangle.BOX);                    
            RentalSales.addCell(TrDate).setHorizontalAlignment(Element.ALIGN_CENTER);
            }  
        pst.close();
        //Getting Penalties
        pst = sqlConn.prepareStatement("SELECT * FROM penalties WHERE PenalDate BETWEEN '"+Date1+"' AND '"+Date2+"'"); 
        rs = pst.executeQuery();
        while(rs.next()){
            int PenalID = rs.getInt("PenalID");
            String strPenalID = Integer.toString(PenalID);
            PdfPCell OrIDCell = new PdfPCell(new Phrase(strPenalID,products));
            OrIDCell.setBorder(Rectangle.BOX);                    
            PenaltyTable.addCell(OrIDCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            String PenalDescription = rs.getString("PenalDescription");
            PdfPCell PrNameCell = new PdfPCell(new Phrase(PenalDescription,products));
            PrNameCell.setBorder(Rectangle.BOX);                    
            PenaltyTable.addCell(PrNameCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int PenalAmount = rs.getInt("PenalAmount");
            String strPenalAmount = Integer.toString(PenalAmount);
            PdfPCell PrCostCell = new PdfPCell(new Phrase("P"+strPenalAmount,products));
            PrCostCell.setBorder(Rectangle.BOX);                    
            PenaltyTable.addCell(PrCostCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            Date date = rs.getDate("PenalDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDatee = dateFormat.format(date);
            PdfPCell TrDate = new PdfPCell(new Phrase(strDatee,products));
            TrDate.setBorder(Rectangle.BOX);                    
            PenaltyTable.addCell(TrDate).setHorizontalAlignment(Element.ALIGN_CENTER);
            }  
        pst.close();
        //Getting Losses
        pst = sqlConn.prepareStatement("SELECT * FROM losses L INNER JOIN product P WHERE (L.LossDate BETWEEN '"+Date1+"' AND '"+Date2+"') AND L.PrID = P.PrID" ); 
        rs = pst.executeQuery();
        while(rs.next()){
            int LossID = rs.getInt("LossID");
            String strLossID = Integer.toString(LossID);
            PdfPCell OrIDCell = new PdfPCell(new Phrase(strLossID,products));
            OrIDCell.setBorder(Rectangle.BOX);                    
            LossTable.addCell(OrIDCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            String ProdName = rs.getString("PrName");
            PdfPCell PrNameCell = new PdfPCell(new Phrase(ProdName,products));
            PrNameCell.setBorder(Rectangle.BOX);                    
            LossTable.addCell(PrNameCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int LossQuantity = rs.getInt("LossQuantity");
            String strLossQuantity = Integer.toString(LossQuantity);
            PdfPCell PrCostCell = new PdfPCell(new Phrase(strLossQuantity,products));
            PrCostCell.setBorder(Rectangle.BOX);                    
            LossTable.addCell(PrCostCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int LossValue = rs.getInt("LossTotalCost");
            String strLossValue = Integer.toString(LossValue);
            PdfPCell CaDescriptionCell = new PdfPCell(new Phrase("P"+strLossValue,products));
            CaDescriptionCell.setBorder(Rectangle.BOX);                    
            LossTable.addCell(CaDescriptionCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            Date date = rs.getDate("LossDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDatee = dateFormat.format(date);
            PdfPCell TrDate = new PdfPCell(new Phrase(strDatee,products));
            TrDate.setBorder(Rectangle.BOX);                    
            LossTable.addCell(TrDate).setHorizontalAlignment(Element.ALIGN_CENTER);
               
            }  
        pst.close();
        //Getting Total Sales
        pst = sqlConn.prepareStatement("SELECT * FROM sales WHERE SalesDate BETWEEN '"+Date1+"' AND '"+Date2+"'"); 
        rs = pst.executeQuery();
        while(rs.next()){
            Date date = rs.getDate("SalesDate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
            String strDatee = dateFormat.format(date);
            PdfPCell TrDate = new PdfPCell(new Phrase(strDatee,products));
            TrDate.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(TrDate).setHorizontalAlignment(Element.ALIGN_CENTER);
            int TotalGrossIncome = rs.getInt("SalesGrossIncome");
            String strTotalGrossIncome = Integer.toString(TotalGrossIncome);
            PdfPCell PrNameCell = new PdfPCell(new Phrase("P"+strTotalGrossIncome,products));
            PrNameCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(PrNameCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int TotalRentalGrossIncome = rs.getInt("SalesTotalRentalGross"); 
            String strTotalRentalGrossIncome = Integer.toString(TotalRentalGrossIncome);
            PdfPCell TotalRentalGrossIncomeCell = new PdfPCell(new Phrase("P"+strTotalRentalGrossIncome,products));
            TotalRentalGrossIncomeCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(TotalRentalGrossIncomeCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int TotalPenalties = rs.getInt("SalesTotalPenalties");
            String strTotalPenalties = Integer.toString(TotalPenalties);
            PdfPCell PrCostCell = new PdfPCell(new Phrase("P"+strTotalPenalties,products));
            PrCostCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(PrCostCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int TotalCostt = rs.getInt("SalesTotalCost");
            String strTotalCostt = Integer.toString(TotalCostt);
            PdfPCell strTotalCosttCell = new PdfPCell(new Phrase("P"+strTotalCostt,products));
            strTotalCosttCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(strTotalCosttCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            int TotalLosses = rs.getInt("SalesTotalLoss");
            String strTotalLosses = Integer.toString(TotalLosses);
            PdfPCell strTotalLossesCell = new PdfPCell(new Phrase("P"+strTotalLosses,products));
            strTotalLossesCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(strTotalLossesCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            float TotalNet = rs.getFloat("SalesNetIncome");
            String strTotalNet = decfor.format(TotalNet);
            PdfPCell strTotalNetCell = new PdfPCell(new Phrase("P"+strTotalNet,products));
            strTotalNetCell.setBorder(Rectangle.BOX);                    
            TotalTable.addCell(strTotalNetCell).setHorizontalAlignment(Element.ALIGN_CENTER);
            }  
        pst.close();    
        pst = sqlConn.prepareStatement("SELECT SUM(SalesGrossIncome), SUM(SalesTotalRentalGross), SUM(SalesTotalPenalties), SUM(SalesTotalCost), SUM(SalesTotalLoss), SUM(SalesNetIncome) FROM sales WHERE SalesDate BETWEEN '"+Date1+"' AND '"+Date2+"'"); 
        rs = pst.executeQuery();
        if(rs.next()){
            GrandGrossIncome = rs.getInt(1);
            GrandRentalGrossIncome = rs.getInt(2); 
            GrandPenalties = rs.getInt(3);
            GrandCost = rs.getInt(4);
            GrandLosses = rs.getInt(5);
            GrandNet = rs.getFloat(6);
            }  
        pst.close();  
        }
        float [] columnwidth6 = {8f,2f};
        PdfPTable Totals = new PdfPTable(2);
        Totals.setWidthPercentage(100);
        Totals.setWidths(columnwidth6);
        PdfPCell GrandGrossCell = new PdfPCell(new Phrase("Grand Total Gross Income",products));
        GrandGrossCell.setBorder(Rectangle.NO_BORDER);
        Totals.addCell(GrandGrossCell).setHorizontalAlignment(Element.ALIGN_LEFT);
        String strGrandGrossIncome = Integer.toString(GrandGrossIncome);
        PdfPCell GrandGrossIncomeCell = new PdfPCell(new Phrase("P"+strGrandGrossIncome,products));
        GrandGrossIncomeCell.setBorder(Rectangle.NO_BORDER);                    
        Totals.addCell(GrandGrossIncomeCell).setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell GrandRentalGrossIncomee = new PdfPCell(new Phrase("Grand Total Rental Gross Income",products));
        GrandRentalGrossIncomee.setBorder(Rectangle.NO_BORDER);
        Totals.addCell(GrandRentalGrossIncomee).setHorizontalAlignment(Element.ALIGN_LEFT);
        String strGrandRentalGrossIncome= Integer.toString(GrandRentalGrossIncome);
        PdfPCell GrandRentalGrossIncomeCell = new PdfPCell(new Phrase("P"+strGrandRentalGrossIncome,products));
        GrandRentalGrossIncomeCell.setBorder(Rectangle.NO_BORDER);                    
        Totals.addCell(GrandRentalGrossIncomeCell).setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell GrandPenaltiess = new PdfPCell(new Phrase("Grand Total Penalties",products));
        GrandPenaltiess.setBorder(Rectangle.NO_BORDER);
        Totals.addCell(GrandPenaltiess).setHorizontalAlignment(Element.ALIGN_LEFT);
        String strGrandPenalties= Integer.toString(GrandPenalties);
        PdfPCell GrandPenaltiesCell = new PdfPCell(new Phrase("P"+strGrandPenalties,products));
        GrandPenaltiesCell.setBorder(Rectangle.NO_BORDER);                    
        Totals.addCell(GrandPenaltiesCell).setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell GrandCostt = new PdfPCell(new Phrase("Grand Total Cost",products));
        GrandCostt.setBorder(Rectangle.NO_BORDER);
        Totals.addCell(GrandCostt).setHorizontalAlignment(Element.ALIGN_LEFT);
        String strGrandCost = Integer.toString(GrandCost);
        PdfPCell GrandCostCell = new PdfPCell(new Phrase("P"+strGrandCost,products));
        GrandCostCell.setBorder(Rectangle.NO_BORDER);                    
        Totals.addCell(GrandCostCell).setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell GrandLossess = new PdfPCell(new Phrase("Grand Total Losses",products));
        GrandLossess.setBorder(Rectangle.NO_BORDER);
        Totals.addCell(GrandLossess).setHorizontalAlignment(Element.ALIGN_LEFT);
        String strGrandLosses = Integer.toString(GrandLosses);
        PdfPCell GrandLossesCell = new PdfPCell(new Phrase("P"+strGrandLosses,products));
        GrandLossesCell.setBorder(Rectangle.NO_BORDER);                    
        Totals.addCell(GrandLossesCell).setHorizontalAlignment(Element.ALIGN_RIGHT);
        PdfPCell GrandNett = new PdfPCell(new Phrase("Grand Total Net Income",products));
        GrandNett.setBorder(Rectangle.NO_BORDER);
        Totals.addCell(GrandNett).setHorizontalAlignment(Element.ALIGN_LEFT);
        String strGrandNet = Float.toString(GrandNet);
        PdfPCell GrandNetCell = new PdfPCell(new Phrase("P"+strGrandNet,products));
        GrandNetCell.setBorder(Rectangle.NO_BORDER);                    
        Totals.addCell(GrandNetCell).setHorizontalAlignment(Element.ALIGN_RIGHT);
        //Insertion of Elements in PDF
        SalesReport.add(image);
        SalesReport.add(BusinessName); 
        SalesReport.add(BusinessAddress);
        SalesReport.add(BusinessEmail); 
        SalesReport.add(PaInventoryReport);
        SalesReport.add(newline);
        SalesReport.add(UserDetails);
        SalesReport.add(PaDate); 
        if(Date2.equals("")){
        SalesReport.add(PaSalesDate);    
        }
        else{
        SalesReport.add(PaSalesRange);    
        }    
        SalesReport.add(line);
        SalesReport.add(Retail);
        SalesReport.add(newline);
        SalesReport.add((Element)RetailSales);        
        SalesReport.add(line);
        SalesReport.add(Rental);
        SalesReport.add(newline);
        SalesReport.add((Element)RentalSales);        
        SalesReport.add(line);
        SalesReport.add(Penalties);
        SalesReport.add(newline);
        SalesReport.add((Element)PenaltyTable);        
        SalesReport.add(line);
        SalesReport.add(Losses);
        SalesReport.add(newline);
        SalesReport.add((Element)LossTable);        
        SalesReport.add(line);
        SalesReport.add(TotalSales);
        SalesReport.add(newline);
        SalesReport.add((Element)TotalTable);
        SalesReport.add(line);
        SalesReport.add(newline);
        SalesReport.add((Element)Totals);
        SalesReport.add(disclaimer);
        SalesReport.add(line);
        SalesReport.add(newline);
        SalesReport.add(newline);
        SalesReport.add(newline);
        SalesReport.add(devs);
        SalesReport.newPage();       
        SalesReport.close();
        writer.close(); 
        }
        catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        }
       
    }

