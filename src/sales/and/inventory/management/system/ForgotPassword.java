/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sales.and.inventory.management.system;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Random;
import java.awt.Color;
/**
 *
 * @author juanm
 */
public class ForgotPassword extends javax.swing.JFrame {
    
        private static String Phone;
        public String getPhone()
        {
            return this.Phone;
        }
        
        public void setPhone(String number)
        {
            this.Phone = number;
        }
        static Random rand = new Random();
        private static int OTP =  rand.nextInt(999999);
        
        public final int getOTP()
        {
            return this.OTP;
        }
        
        public void setOTP(int OTP2)
        {
            this.OTP = OTP2;
        }
            
        public static final String ACCOUNT_SID = "ACe572053de6dd30a497b7a4177a5da5fb";
        public static final String AUTH_TOKEN = "47703770bbfe591ed6507952dd7c9928"; 
        private static final String username = JDBC.username;
        private static final String password = JDBC.password;
        private static final String dataConn = JDBC.dataConn;
        JDBC db = new JDBC();
        Connection sqlConn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        
        
    public ForgotPassword() {      
        initComponents();
        db.Connect();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        Login = new javax.swing.JLabel();
        Phonenumber = new TextField("Phone number");
        jLabel2 = new javax.swing.JLabel();
        OTPbutton = new Hover();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(11, 30, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setBackground(new java.awt.Color(255, 0, 255));
        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Remembered your password?");

        Login.setBackground(new java.awt.Color(255, 0, 255));
        Login.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Login.setForeground(new java.awt.Color(0, 83, 122));
        Login.setText("Login");
        Login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginMouseClicked(evt);
            }
        });

        Phonenumber.setBackground(new java.awt.Color(255, 255, 255));
        Phonenumber.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Phonenumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PhonenumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PhonenumberFocusLost(evt);
            }
        });
        Phonenumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhonenumberActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 0, 255));
        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(11, 30, 51));
        jLabel2.setText("Forgot Password");

        OTPbutton.setBackground(new java.awt.Color(1, 60, 88));
        OTPbutton.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        OTPbutton.setForeground(new java.awt.Color(255, 255, 255));
        OTPbutton.setText("Send One Time Passcode via SMS");
        OTPbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OTPbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(Phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 52, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OTPbutton)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Login)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Phonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(OTPbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(Login))
                .addGap(69, 69, 69))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gifs/Forgot.gif"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginMouseClicked
        Signin show = new Signin();
        show.setVisible(true);
        dispose();
    }//GEN-LAST:event_LoginMouseClicked

    private void PhonenumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PhonenumberFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_PhonenumberFocusGained

    private void PhonenumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PhonenumberFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_PhonenumberFocusLost

    private void PhonenumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhonenumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PhonenumberActionPerformed

    private void OTPbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OTPbuttonActionPerformed
                try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            sqlConn = DriverManager.getConnection(dataConn,username,password);

            String number = Phonenumber.getText();

            pst = sqlConn.prepareStatement("select * from user where UPhoneNumber = '"+number+"'");

            rs = pst.executeQuery();

            if(rs.next())
            {
                String CallingNumber = "+63" + number.substring(1);
                String Verification = "Your one time password is " + OTP;

                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(CallingNumber),
                    new com.twilio.type.PhoneNumber("+12232176092"),
                    Verification

                ).create();

                setPhone(number);
                Onetimepasscode show = new Onetimepasscode();
                show.setVisible(true);

            }

            else
            {
                JOptionPane.showMessageDialog(this,"Please enter a valid phone number");
                Phonenumber.setText("");

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ForgotPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_OTPbuttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Login;
    private javax.swing.JButton OTPbutton;
    private javax.swing.JTextField Phonenumber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
