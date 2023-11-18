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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Tapar, Emmanuel Christian, Ng, Juan Miguel
 */
public class generateReceipt {
    private static final String username = JDBC.username;
    private static final String password = JDBC.password;
    private static final String dataConn = JDBC.dataConn;
    public static String SystemDate = Main.SystemDate;
    public static String userloggedin = Signin.userloggedin;
    public static int userID = Signin.userID;
    Connection sqlConn =null;
    PreparedStatement pst = null, pst2 = null, pst3 = null, pst4 = null,pst5 = null,pst6 = null,pst7 = null,pst8 = null;
    ResultSet rs=null, rs2=null,rs3=null, rs4=null, rs5=null,rs6=null, r7=null, rs8=null;
    JDBC db = new JDBC();

    public void generateTapeReceipt(){
        try{
        db.Connect();
        sqlConn = DriverManager.getConnection(dataConn,username,password);
        int OrNumber=0, count=0, quantity=0, price=0, total=0, totalamountdue=0, Change=0,payment=0, OrID=0;
        String BuName=null, BuTIN=null, BuEmail=null, BuAddress=null, strOrDate = null,PrName=null, OrTime=null, strOrID=null, OrGcash=null, BuPhone=null;
        Date OrDate=null;
        float VAT=0, VATableSales=0;
        String struserID = Integer.toString(userID);
        pst = sqlConn.prepareStatement("SELECT * FROM business_info"); 
        rs = pst.executeQuery();
            if(rs.next()){
            BuName = rs.getString("BuName");
            BuTIN = rs.getString("BuTIN");
            BuEmail = rs.getString("BuEmail");
            BuAddress = rs.getString("BuAddress");
            BuPhone = rs.getString("BuPhoneNumber");
            }
        pst.close();
        pst = sqlConn.prepareStatement("SELECT OrID, OrNumber, OrLessVAT, OrNet_of_VAT, OrChange, OrDate, OrTime, OrGcashReference FROM orders WHERE OrNumber = (SELECT MAX(OrNumber) FROM order_details)"); 
        rs = pst.executeQuery();
            if(rs.next()){
            OrNumber = rs.getInt("OrNumber");
            OrID = rs.getInt("OrID");
            strOrID = Integer.toString(OrID);
            Change = rs.getInt("OrChange");
            VAT = rs.getFloat("OrLessVAT");
            VATableSales = rs.getFloat("OrNet_of_VAT");
            OrDate = rs.getDate("OrDate");            
            OrTime = rs.getString("OrTime");
            OrGcash = rs.getString("OrGcashReference");
            }
        pst.close();
        pst = sqlConn.prepareStatement("SELECT PayAmount FROM payment P INNER JOIN orders O WHERE P.PayID = O.PayID AND O.OrNumber = '"+OrNumber+"'"); 
        rs = pst.executeQuery();
            if(rs.next()){
            //OrNumber = rs.getInt(1);
            payment = rs.getInt("PayAmount");
            }
        pst.close();
        pst = sqlConn.prepareStatement("SELECT COUNT(OrNumber) FROM order_details WHERE OrNumber = '"+OrNumber+"'"); 
        rs = pst.executeQuery();
            if(rs.next()){
            count = rs.getInt(1);
            }
        pst.close();
        pst = sqlConn.prepareStatement("SELECT * FROM order_details D INNER JOIN product P WHERE D.OrNumber = '"+OrNumber+"' AND D.PrID = P.PrID"); 
        rs = pst.executeQuery();
        LocalDate Date = LocalDate.now(); 
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy");
        String formatteddate = Date.format(dateformat);
        String FileName = String.format("C:\\Sales and Inventory Documents\\Tape Receipts\\%s%d.pdf",formatteddate , OrNumber);
        Rectangle page = new Rectangle(Utilities.millimetersToPoints(80), Utilities.millimetersToPoints(150));
        Document receipt = new Document();
        String imFile = "C:\\Users\\tapar\\OneDrive\\Documents\\NetBeansProjects\\Sales and Inventory Management System\\ReceiptLogoo.png"; 
        // Creating an Image object 
        Image image = Image.getInstance(imFile);             
        image.scaleToFit(Utilities.millimetersToPoints(55),Utilities.millimetersToPoints(55));
        image.setAlignment(Image.MIDDLE);               
        receipt.setMargins(10, 10, 10, 10);
        receipt.setPageSize(page);
        Font title = new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL,BaseColor.BLACK);
        Font products = new Font(Font.FontFamily.HELVETICA,7,Font.NORMAL,BaseColor.BLACK);
        Font details = new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL,BaseColor.BLACK);
        Font lineFont = new Font(Font.FontFamily.HELVETICA,8,Font.NORMAL,BaseColor.BLACK);
        OutputStream output = new FileOutputStream(FileName);
        PdfWriter writer = PdfWriter.getInstance(receipt, output); 
        Paragraph line = new Paragraph("-----------------------------------------------------------------------------", lineFont);
        Paragraph newline = new Paragraph("\n",lineFont);
        Paragraph BusinessName = new Paragraph(BuName,title);
        BusinessName.setAlignment(Element.ALIGN_CENTER);
        Paragraph BusinessTIN = new Paragraph("VAT REG TIN: "+BuTIN,title);      
        BusinessTIN.setAlignment(Element.ALIGN_CENTER);
        Paragraph BusinessEmail = new Paragraph(BuEmail,title);
        BusinessEmail.setAlignment(Element.ALIGN_CENTER);
        Paragraph BusinessPhone = new Paragraph(BuPhone,title);
        BusinessPhone.setAlignment(Element.ALIGN_CENTER);
        Paragraph BusinessAddress = new Paragraph(BuAddress+"\nDAN R. PEL - Prop.",title);
        BusinessAddress.setAlignment(Element.ALIGN_CENTER);
        Paragraph OfficialReceipt = new Paragraph("Official Receipt # "+strOrID,title);
        OfficialReceipt.setAlignment(Element.ALIGN_CENTER);
        Paragraph GcashReference = new Paragraph("Gcash Reference # "+OrGcash,title);
        GcashReference.setAlignment(Element.ALIGN_CENTER);
        Paragraph UserDetails = new Paragraph("User ID: "+struserID+"\n"+"User Name: "+userloggedin,title);
        UserDetails.setAlignment(Element.ALIGN_CENTER);
        Paragraph DateTime = new Paragraph("Date: "+OrDate+" Time: "+OrTime,title);
        DateTime.setAlignment(Element.ALIGN_CENTER);
        Paragraph Feedback = new Paragraph("Tell us about your visit! Share your experience\nand approach any of our staff!",title);
        Feedback.setAlignment(Element.ALIGN_CENTER);
        Paragraph content = new Paragraph("Thank you come again!\nThis serves as your OFFICIAL RECEIPT\nValid for 5 years after the specified date",title);
        content.setAlignment(Element.ALIGN_CENTER);
        Paragraph devs = new Paragraph("POS System Developer:\nTapar, Emmanuel Christian\nNg, Juan Miguel",title);
        devs.setAlignment(Element.ALIGN_CENTER);
        //Creation of Receipt
        receipt.open(); 
        receipt.add(new Chunk(""));
        float [] columnwidth = {1.5f,6f,2f,2f};
        PdfPTable Orders = new PdfPTable(4);
        Orders.setWidthPercentage(100);
        Orders.setWidths(columnwidth);
        float [] columnwidth2 = {8f,2f};
        PdfPTable Totals = new PdfPTable(2);
        Totals.setWidthPercentage(100);
        Totals.setWidths(columnwidth2);
        PdfPCell qty = new PdfPCell(new Phrase("QTY",details));
        PdfPCell item = new PdfPCell(new Phrase("ITEM",details));
        PdfPCell pricee = new PdfPCell(new Phrase("PRICE",details));
        PdfPCell totaal = new PdfPCell(new Phrase("TOTAL",details));
        qty.setBorder(Rectangle.NO_BORDER);
        Orders.addCell(qty);
        item.setBorder(Rectangle.NO_BORDER);
        Orders.addCell(item);
        pricee.setBorder(Rectangle.NO_BORDER);
        Orders.addCell(pricee);
        totaal.setBorder(Rectangle.NO_BORDER);
        Orders.addCell(totaal);
        receipt.add(image);
        receipt.add(BusinessName); 
        receipt.add(BusinessAddress);
        receipt.add(BusinessEmail); 
        receipt.add(BusinessPhone); 
        receipt.add(BusinessTIN); 
        receipt.add(UserDetails); 
        receipt.add(OfficialReceipt);
        receipt.add(GcashReference);
        receipt.add(DateTime); 
        receipt.add(line);      
        while(rs.next()){ 
            quantity = rs.getInt("OrDetQuantity");
            String strquantity = Integer.toString(quantity);
            PdfPCell QuantityCell = new PdfPCell(new Phrase(strquantity,products));
            QuantityCell.setBorder(Rectangle.NO_BORDER);                    
            Orders.addCell(QuantityCell).setHorizontalAlignment(Element.ALIGN_LEFT);
            PrName = rs.getString("PrName");
            PdfPCell ProductCell = new PdfPCell(new Phrase(PrName,products));
            ProductCell.setBorder(Rectangle.NO_BORDER);                    
            Orders.addCell(ProductCell).setHorizontalAlignment(Element.ALIGN_LEFT);
            price = rs.getInt("PrUnitPrice");
            String strprice = Integer.toString(price);
            PdfPCell PriceCell = new PdfPCell(new Phrase(strprice,products));
            PriceCell.setBorder(Rectangle.NO_BORDER);                    
            Orders.addCell(PriceCell).setHorizontalAlignment(Element.ALIGN_LEFT);
            total = rs.getInt("OrDetPriceTotal");
            String strtotal = Integer.toString(total);
            PdfPCell TotalCell = new PdfPCell(new Phrase(strtotal,products));
            TotalCell.setBorder(Rectangle.NO_BORDER);                    
            Orders.addCell(TotalCell).setHorizontalAlignment(Element.ALIGN_LEFT); 
            totalamountdue = totalamountdue + total;        
            }      
        String totalam = Integer.toString(totalamountdue);
        String strpay = Integer.toString(payment);
        String strchange = Integer.toString(Change);
        String vatablesales = Float.toString(VATableSales);
        String strtotalvat = Float.toString(VAT);               
        PdfPCell totalamount = new PdfPCell(new Phrase("Total Amount Due: ",products));
        PdfPCell pay = new PdfPCell(new Phrase("Payment: ",products));
        PdfPCell change = new PdfPCell(new Phrase("Change: ",products));
        PdfPCell vatsales = new PdfPCell(new Phrase("VATable Sales: ",products));
        PdfPCell vat = new PdfPCell(new Phrase("VAT (12%): ",products));
        PdfPCell vatexemptsales = new PdfPCell(new Phrase("VAT Exempt Sales: ",products));
        PdfPCell zeroratedsales = new PdfPCell(new Phrase("Zero Rated Sales: ",products));
        PdfPCell inttotalamount = new PdfPCell(new Phrase(totalam,products));
        PdfPCell intpay = new PdfPCell(new Phrase(strpay,products));
        PdfPCell intchange = new PdfPCell(new Phrase(strchange,products));
        PdfPCell intvatsales = new PdfPCell(new Phrase(vatablesales,products));
        PdfPCell intvat = new PdfPCell(new Phrase(strtotalvat,products));
        PdfPCell zero = new PdfPCell(new Phrase("0.00",products));
        totalamount.setBorder(Rectangle.NO_BORDER);
        pay.setBorder(Rectangle.NO_BORDER);
        change.setBorder(Rectangle.NO_BORDER);
        vatsales.setBorder(Rectangle.NO_BORDER);
        vat.setBorder(Rectangle.NO_BORDER);
        vatexemptsales.setBorder(Rectangle.NO_BORDER);
        zeroratedsales.setBorder(Rectangle.NO_BORDER);
        inttotalamount.setBorder(Rectangle.NO_BORDER);
        intpay.setBorder(Rectangle.NO_BORDER);
        intchange.setBorder(Rectangle.NO_BORDER);
        intvatsales.setBorder(Rectangle.NO_BORDER);
        intvat.setBorder(Rectangle.NO_BORDER);
        zero.setBorder(Rectangle.NO_BORDER);
        Totals.addCell(totalamount);
        Totals.addCell(inttotalamount);
        Totals.addCell(pay);
        Totals.addCell(intpay);
        Totals.addCell(change);
        Totals.addCell(intchange);
        Totals.addCell(vatsales);
        Totals.addCell(intvatsales);
        Totals.addCell(vat);
        Totals.addCell(intvat);
        Totals.addCell(vatexemptsales);
        Totals.addCell(zero);
        Totals.addCell(zeroratedsales);
        Totals.addCell(zero);
        receipt.add((Element)Orders); 
        receipt.add(line);
        receipt.add((Element)Totals); 
        receipt.add(Feedback);
        receipt.add(content);
        receipt.add(devs);
        receipt.newPage();       
        receipt.close();
        writer.close(); 
        }

        catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
        
    } 
    }

