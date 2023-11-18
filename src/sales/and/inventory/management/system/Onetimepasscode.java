/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sales.and.inventory.management.system;
import javax.swing.JOptionPane;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.Random;
/**
 *
 * @author juanm
 */
public class Onetimepasscode extends javax.swing.JFrame {

    public static final String ACCOUNT_SID = "ACe572053de6dd30a497b7a4177a5da5fb";
    public static final String AUTH_TOKEN = "47703770bbfe591ed6507952dd7c9928";

    ForgotPassword OTP = new ForgotPassword();
    int OTP2 = OTP.getOTP();
    
    public void setOTP2(int  number)
        {
            this.OTP2 = number;
        }
    
    
    String Newphone = OTP.getPhone();
    
    Random rand = new Random();
    /**
     * Creates new form Onetimepasscode
     */
    public Onetimepasscode() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Resetpass = new javax.swing.JButton();
        Resend = new javax.swing.JLabel();
        OTPentry = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(255, 0, 255));
        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setText("Enter one time passcode");

        Resetpass.setBackground(new java.awt.Color(0, 0, 51));
        Resetpass.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Resetpass.setForeground(new java.awt.Color(255, 255, 255));
        Resetpass.setText("Verify OTP");
        Resetpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetpassActionPerformed(evt);
            }
        });

        Resend.setBackground(new java.awt.Color(255, 0, 255));
        Resend.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Resend.setForeground(new java.awt.Color(51, 0, 204));
        Resend.setText("Resend OTP");
        Resend.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Resend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResendMouseClicked(evt);
            }
        });

        OTPentry.setFont(new java.awt.Font("Roboto", 0, 48)); // NOI18N
        OTPentry.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                OTPentryKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(Resend))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Resetpass, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                            .addComponent(OTPentry)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(jLabel2)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addComponent(OTPentry, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(Resetpass, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(Resend)
                .addContainerGap())
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

    private void ResetpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetpassActionPerformed
        
        if(Integer.parseInt(OTPentry.getText()) == OTP2)
        {
            ResetPassword show = new ResetPassword();
            show.setVisible(true);
            dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Incorrect OTP Please Try Again");                  
        }
         
        
       
        
    }//GEN-LAST:event_ResetpassActionPerformed

    private void ResendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResendMouseClicked
        
        Random rand = new Random();
        int number =  rand.nextInt(999999);
        setOTP2(number);
        
        String CallingNumber = "+63" + Newphone.substring(1);
        String Verification = "Your one time password is " + OTP2;
        
        
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
              new com.twilio.type.PhoneNumber(CallingNumber),
              new com.twilio.type.PhoneNumber("+12232176092"),
              Verification
                                
                                
            ).create();
            
            JOptionPane.showMessageDialog(null,"New password should have 10 characters or more. Please try again.");
    }//GEN-LAST:event_ResendMouseClicked

    private void OTPentryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OTPentryKeyTyped
        char a = evt.getKeyChar();
        
        if(!Character.isDigit(a))
        {
            evt.consume();
        }
        
        if(OTPentry.getText().length() >= 9)
        {
            evt.consume();
        }
    }//GEN-LAST:event_OTPentryKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField OTPentry;
    private javax.swing.JLabel Resend;
    private javax.swing.JButton Resetpass;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
