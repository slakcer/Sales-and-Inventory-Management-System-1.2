 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sales.and.inventory.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
/**
 *
 * @author juanm
 */
public class Signin extends javax.swing.JFrame {    
    private static final String username = JDBC.username;
    private static final String password = JDBC.password;
    private static final String dataConn = JDBC.dataConn;
    public static String userloggedin = null;
    public static int userID = 0;
    public static int userlevel =0; 
        Connection sqlConn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        JDBC db = new JDBC();            
    /**
     * Creates new form Sign in
     */      
    public Signin() {
        db.Connect();  
        initComponents();   
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Password = new PasswordField("Password");
        Forgotpassword = new javax.swing.JLabel();
        Login = new Hover();
        Newacc = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new Hover();
        jLabel8 = new javax.swing.JLabel();
        UsernameText = new TextField("Username");
        jLabel1 = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(11, 30, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 0, 255));
        jLabel2.setFont(new java.awt.Font("Bahnschrift", 0, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(11, 30, 51));
        jLabel2.setText("Sign in");

        Password.setBackground(new java.awt.Color(255, 255, 255));
        Password.setActionCommand("<Not Set>");
        Password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PasswordKeyPressed(evt);
            }
        });

        Forgotpassword.setBackground(new java.awt.Color(240, 240, 240));
        Forgotpassword.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Forgotpassword.setForeground(new java.awt.Color(0, 83, 122));
        Forgotpassword.setText("Forgot password?");
        Forgotpassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Forgotpassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ForgotpasswordMouseClicked(evt);
            }
        });

        Login.setBackground(new java.awt.Color(1, 60, 88));
        Login.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Login.setForeground(new java.awt.Color(255, 255, 255));
        Login.setText("Sign in");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });

        Newacc.setBackground(new java.awt.Color(240, 240, 240));
        Newacc.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Newacc.setForeground(new java.awt.Color(0, 83, 122));
        Newacc.setText("Sign up");
        Newacc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Newacc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NewaccMouseClicked(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(245, 162, 1));
        jLabel5.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(11, 30, 51));
        jLabel5.setText("DanPel Elegance");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sign-in logo.png"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(240, 240, 240));
        jButton1.setForeground(new java.awt.Color(240, 240, 240));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exit.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 0, 255));
        jLabel8.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Don't have an account?");

        UsernameText.setBackground(new java.awt.Color(255, 255, 255));
        UsernameText.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        UsernameText.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                UsernameTextFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                UsernameTextFocusLost(evt);
            }
        });
        UsernameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 112, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addGap(110, 110, 110))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(20, 20, 20))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(UsernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(220, 220, 220)
                                .addComponent(Forgotpassword))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(6, 6, 6)
                                .addComponent(Newacc)))
                        .addGap(35, 35, 35))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(UsernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Forgotpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(Login, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel8))
                    .addComponent(Newacc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5)))
                .addGap(41, 41, 41))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gifs/Sign in.gif"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 97, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void NewaccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NewaccMouseClicked
        Registration show = new Registration();
        show.setVisible(true);
        dispose();
    }//GEN-LAST:event_NewaccMouseClicked

    private void ForgotpasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ForgotpasswordMouseClicked
        ForgotPassword show = new ForgotPassword();
        show.setVisible(true);
        dispose();
    }//GEN-LAST:event_ForgotpasswordMouseClicked

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
                getUser(userloggedin);
                getUserID(userID);
                getUserLevel(userlevel);
                Login();
    }//GEN-LAST:event_LoginActionPerformed

    private void PasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PasswordKeyPressed
              if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                getUser(userloggedin);
                getUserID(userID);
                getUserLevel(userlevel);
                Login();           
            } 
    }//GEN-LAST:event_PasswordKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void UsernameTextFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameTextFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameTextFocusGained

    private void UsernameTextFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_UsernameTextFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameTextFocusLost

    private void UsernameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameTextActionPerformed
    public void Login(){
                try {                                        
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                String user = UsernameText.getText();
                String password = Password.getText();
                pst = sqlConn.prepareStatement("select * from user where Uname = '"+user+"' and Upass = '"+password+"'");               
                rs = pst.executeQuery();               
                    if(rs.next())
                    {
                        int Ulevel = rs.getInt("ULevel");
                        if(Ulevel == 2){
                        Main show = new Main();
                        show.setVisible(true);
                        dispose();    
                        }
                        else if(Ulevel == 1){
                        CashierPOS show = new CashierPOS();
                        show.setVisible(true);
                        dispose(); 
                        }
                    }           
                    else
                    {
                        JOptionPane.showMessageDialog(this,"Username or Password is incorrect");
                        UsernameText.setText("");
                        Password.setText("");
                    }
                    pst.close();            
                }       
            catch (SQLException ex) {
                Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public String getUser(String user){
                try {
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                user = UsernameText.getText();
                pst = sqlConn.prepareStatement("select * from user where Uname = '"+user+"'");
                rs = pst.executeQuery();
                    if(rs.next())
                    {
                    String userFname = rs.getString("UFname")+" ";
                    String userLname = rs.getString("ULname");
                    userloggedin = userFname.concat(userLname);
                    }
                user = userloggedin;
                }
            catch (SQLException ex) {
                Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
            }
            return user;
    }
        public int getUserID(int ID){
                try {
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                String user = UsernameText.getText();
                pst = sqlConn.prepareStatement("select * from user where Uname = '"+user+"'");
                rs = pst.executeQuery();
                    if(rs.next())
                    {
                    userID = rs.getInt("UID");
                    }

                ID = userID;

                }
            catch (SQLException ex) {
                Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
            }
            return ID;
    }
        public int getUserLevel(int level){
                try {
                sqlConn = DriverManager.getConnection(dataConn,username,password);
                String user = UsernameText.getText();
                pst = sqlConn.prepareStatement("select * from user where Uname = '"+user+"'");
                rs = pst.executeQuery();
                    if(rs.next())
                    {
                    userlevel = rs.getInt("ULevel");
                    }

                level = userlevel;

                }
            catch (SQLException ex) {
                Logger.getLogger(Signin.class.getName()).log(Level.SEVERE, null, ex);
            }
            return level;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Signin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Forgotpassword;
    private javax.swing.JButton Login;
    private javax.swing.JLabel Newacc;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTextField UsernameText;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
