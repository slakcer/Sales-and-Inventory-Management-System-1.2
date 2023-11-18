/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sales.and.inventory.management.system;

import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author juanm
 */
public class GenerateSalesReport extends javax.swing.JFrame {
    private static final String username = JDBC.username;
    private static final String password = JDBC.password;
    private static final String dataConn = JDBC.dataConn;
    generateReports r = new generateReports();
    Connection sqlConn =null;
    PreparedStatement pst = null;
    PreparedStatement pst2 = null, pst3 = null;
    ResultSet rs=null, rs2=null,rs3=null;
    /**
     * Creates new form AddProduct
     */
    public GenerateSalesReport() {
        initComponents();
        JDBC db = new JDBC();
        LocalDate date = LocalDate.now();
        LocalDate yesterday = date.minusDays(1);
        Date Yesterday = convertToDateUsingDate(yesterday);
        db.Connect();
        DateChooser1.setMaxSelectableDate(Yesterday);
        DateChooser2.setMaxSelectableDate(Yesterday);
        JTextFieldDateEditor editor = (JTextFieldDateEditor) DateChooser1.getDateEditor();
        editor.setEditable(false);
        JTextFieldDateEditor editor2 = (JTextFieldDateEditor) DateChooser2.getDateEditor();
        editor2.setEditable(false);
        setBackground(new Color(0,0,0,0));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public static Date convertToDateUsingDate(LocalDate date) {
        return java.sql.Date.valueOf(date);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new swing.PanelBorder();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboRange = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        DateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        DateChooser2 = new com.toedter.calendar.JDateChooser();
        Cancel = new javax.swing.JButton();
        GenerateSalesReport = new javax.swing.JButton();
        panelBorder2 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        panelBorder1.setBackground(new java.awt.Color(102, 255, 204));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 51));
        jLabel4.setText("Range");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("(Select Date if custom date is selected)");

        cboRange.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cboRange.setModel(new javax.swing.DefaultComboBoxModel<>(new String []{"","Custom","Today","Monthly","Annually"}));
        cboRange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboRangeMouseClicked(evt);
            }
        });
        cboRange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboRangeActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 51));
        jLabel5.setText("From");

        DateChooser1.setDateFormatString("dd/MM/yyyy");
        DateChooser1.setMaxSelectableDate(new java.util.Date(253370739708000L));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 51));
        jLabel3.setText("To");

        DateChooser2.setDateFormatString("dd/MM/yyyy");

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

        GenerateSalesReport.setBackground(new java.awt.Color(0, 0, 51));
        GenerateSalesReport.setFont(new java.awt.Font("Roboto", 0, 27)); // NOI18N
        GenerateSalesReport.setForeground(new java.awt.Color(255, 255, 255));
        GenerateSalesReport.setText("Generate");
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

        panelBorder2.setBackground(new java.awt.Color(0, 0, 51));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Generate Sales Report");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(159, 159, 159)
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
                .addGap(23, 23, 23)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelBorder2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5)
                                    .addComponent(DateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                                    .addComponent(cboRange, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Cancel)
                                .addGap(18, 18, 18)
                                .addComponent(GenerateSalesReport)))
                        .addGap(43, 43, 43))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboRange, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addComponent(DateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GenerateSalesReport, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
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

    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CancelActionPerformed

    private void CancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelMouseClicked
        dispose();
    }//GEN-LAST:event_CancelMouseClicked

    private void GenerateSalesReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateSalesReportActionPerformed
        String Range = (String)cboRange.getSelectedItem();
        if(Range.equals("Custom")){
            Date date1 = DateChooser1.getDate();
            Date date2 = DateChooser2.getDate();
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String format1 = dateformat.format(date1);
            String format2 = dateformat.format(date2);
            r.generateSalesReport(Range,format1,format2);   
            JOptionPane.showMessageDialog(this,"Sales Report Generated");
            dispose();
        }
        else if(Range.equals("Today")){
            LocalDate date = LocalDate.now();
            DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String format1 = dateformat.format(date);
            r.generateSalesReport(Range,format1,"");  
            JOptionPane.showMessageDialog(this,"Sales Report Generated");
            dispose();
        }
        else if(Range.equals("Monthly")){
            LocalDate date = LocalDate.now();
            LocalDate firstday = date.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate lastday = date.with(TemporalAdjusters.lastDayOfMonth());
            DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String format1 = dateformat.format(firstday);
            String format2 = dateformat.format(lastday);
            r.generateSalesReport(Range,format1,format2);
            JOptionPane.showMessageDialog(this,"Sales Report Generated");
            dispose();
        }
        else if(Range.equals("Annually")){
            LocalDate date = LocalDate.now();
            LocalDate firstday = date.with(TemporalAdjusters.firstDayOfYear());
            LocalDate lastday = date.with(TemporalAdjusters.lastDayOfYear());
            DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String format1 = dateformat.format(firstday);
            String format2 = dateformat.format(lastday);
            r.generateSalesReport(Range,format1,format2);   
            JOptionPane.showMessageDialog(this,"Sales Report Generated");
            dispose();
        }
        else if(Range.equals("")){
            JOptionPane.showMessageDialog(this,"Please Select Date Range");
        }
        cboRange.setSelectedIndex(0);
        DateChooser1.setCalendar(null);
        DateChooser2.setCalendar(null);
        

    }//GEN-LAST:event_GenerateSalesReportActionPerformed

    private void GenerateSalesReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenerateSalesReportMouseClicked

    }//GEN-LAST:event_GenerateSalesReportMouseClicked

    private void cboRangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboRangeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cboRangeMouseClicked

    private void cboRangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboRangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboRangeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private com.toedter.calendar.JDateChooser DateChooser1;
    private com.toedter.calendar.JDateChooser DateChooser2;
    private javax.swing.JButton GenerateSalesReport;
    private javax.swing.JComboBox<String> cboRange;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    // End of variables declaration//GEN-END:variables
}
