/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sales.and.inventory.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author juanm
 */
public class JDBC {
    public static final String username= "root";
    public static final String password= "nopass";
    public static final String dataConn ="jdbc:mysql://localhost:3306/dbsales";   
        public void Connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");        
        }
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SalesAndInventoryManagementSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            System.out.println("Database not connected");
        } 
    }
}
