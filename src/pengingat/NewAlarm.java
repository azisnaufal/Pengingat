/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pengingat;

import java.awt.Frame;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hakaze
 */
public class NewAlarm extends javax.swing.JDialog {
    
    /**
     * Creates new form NewAlarm
     */
    PenyimpananData pd ;
    String action;
    Connection koneksi;
    cbxDaysSaturday cdmSaturday = new cbxDaysSaturday();
    cbxDaysSundays cdmSundays = new cbxDaysSundays();
    cbxDaysMonday cdmMonday = new cbxDaysMonday();
    String oldAlarm_name;
    public NewAlarm(java.awt.Frame parent, boolean modal, String act, String alarm_name, Connection koneksii) {
        super(parent, modal);
        initComponents();
        oldAlarm_name = alarm_name;
        koneksi = koneksii;
        pd = new PenyimpananData(koneksi);
        action = act;
        if (act.equals("Edit")){
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/edit-find-replace.png"))); // NOI18N
            jLabel1.setText("Edit Alarm");
            setTitle("Edit Existing Alarm");
        }
        else {
            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/document-new.png"))); // NOI18N
            jLabel1.setText("Create Alarm");
        }
        jFileChooser1.addChoosableFileFilter(new FileNameExtensionFilter("MP3 Files", "mp3"));
        jFileChooser1.addChoosableFileFilter(new FileNameExtensionFilter("WAV files", "wav"));
        pd.setSettingData();
        cbxEnabled.setVisible(false);
        jpDays.setVisible(false);
        lblDays.setVisible(false);
        loadMusic();
        setJPane();
        if (act == "Edit"){
            showData(alarm_name);
        }
    }
    private void loadMusic(){
        CBMusic.setModel(pd.setMusictoCbx());
    }
    private void showData(String alarm_name){
        cbxEnabled.setVisible(true);
        try {
            Statement stmt = koneksi.createStatement();
            String query = "	SELECT\n" +
                    "`tb_alarm`.`enabled`, `tb_alarm`.`alarm_name`,`tb_alarm`.`time`, `tb_alarm`.`repeat`, `tb_alarm`.`days`, `tb_music`.`filename`FROM`db_pengingat`.`tb_alarm` INNER JOIN `db_pengingat`.`tb_music` ON (`tb_alarm`.`id_music` = `tb_music`.`id_music`) WHERE alarm_name = \""+alarm_name+"\";";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getInt("enabled") == 1){
                    cbxEnabled.setSelected(true);
                }
                else{
                    cbxEnabled.setSelected(false);
                }
                TBAlarmName.setText(rs.getString("alarm_name"));
                String dbTime = rs.getString("time");
                Date date = new SimpleDateFormat("HH:mm:ss").parse(dbTime);
                Time.setValue(date);
                pd.days = rs.getString("days");
                
                if(rs.getInt("repeat") == 1){
                    setJPane();
                    chkRepeat.setSelected(true);
                    jpDays.setVisible(true);
                    lblDays.setVisible(true);
                    
                }
                else{
                    chkRepeat.setSelected(false);
                    jpDays.setVisible(false);
                    lblDays.setVisible(false);
                }
                
                if (rs.getString("filename") == "Alarm02.wav"){
                    CBMusic.setSelectedItem("Default");
                }
                else {
                    CBMusic.setSelectedItem(rs.getString("filename"));
                }
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan di query");
        } catch (ParseException ex) {
            Logger.getLogger(NewAlarm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setJPane(){
        if (pd.StartWeekOn.equals("Saturday")){
            cdmSaturday.DayChecker(pd.days);
            jpDays.add(cdmSaturday, "yes");
            //System.out.println(pd.days);
        }
        else if (pd.StartWeekOn.equals("Sunday")){
            cdmSundays.DayChecker(pd.days);
            jpDays.add(cdmSundays, "yes");
            //System.out.println(pd.days);
        }
        else if (pd.StartWeekOn.equals("Monday")){
            cdmMonday.DayChecker(pd.days);
            jpDays.add(cdmMonday, "yes");
            //System.out.println(pd.days);
        }
        else {
            System.out.println("Failed to fetch setting \"Start Week On\", switch to fallback mode!");
            jpDays.add(cdmSundays, "yes");
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

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TBAlarmName = new javax.swing.JTextField();
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        Time = new javax.swing.JSpinner(sm);
        chkRepeat = new javax.swing.JCheckBox();
        CBMusic = new javax.swing.JComboBox<>();
        BBrowse = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblDays = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BCancel = new javax.swing.JButton();
        BOK = new javax.swing.JButton();
        jpDays = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        cbxEnabled = new javax.swing.JCheckBox();

        jFileChooser1.setAcceptAllFileFilterUsed(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create New Alarm");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/document-new.png"))); // NOI18N
        jLabel1.setText("Create Alarm");

        jLabel2.setText("Alarm Name");

        JSpinner.DateEditor de = new JSpinner.DateEditor(Time, "HH:mm:ss");
        Time.setEditor(de);

        chkRepeat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRepeatActionPerformed(evt);
            }
        });

        CBMusic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default" }));

        BBrowse.setText("Browse");
        BBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBrowseActionPerformed(evt);
            }
        });

        jLabel3.setText("Time");

        jLabel4.setText("Repeat");

        lblDays.setText("Days");

        jLabel6.setText("Alarm Music");

        BCancel.setText("Cancel");
        BCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelActionPerformed(evt);
            }
        });

        BOK.setText("Ok");
        BOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOKActionPerformed(evt);
            }
        });

        jpDays.setMinimumSize(new java.awt.Dimension(469, 0));
        jpDays.setLayout(new java.awt.CardLayout());

        cbxEnabled.setText("Enabled");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxEnabled)
                                .addGap(21, 21, 21))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(lblDays))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkRepeat)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(TBAlarmName, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(Time, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(CBMusic, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(BBrowse)))
                                        .addGap(0, 99, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jpDays, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BOK)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(BCancel)))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(cbxEnabled))
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(TBAlarmName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CBMusic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BBrowse)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(Time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkRepeat)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDays, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpDays, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BCancel)
                            .addComponent(BOK))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void BBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBrowseActionPerformed
        // TODO add your handling code here:
        // jfilechooser : filter audio format only
        // default ringtone : windows alarm
        // after jfilechooser ok button pressed,
        // then save dir address to db,
        // save file name to db,
        // save file extension to db
        // then cbxmusic is load file name and file extension from the db
        //
        if (jFileChooser1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = new File(jFileChooser1.getSelectedFile()+"");
            //System.out.println(selectedFile.getName());
            pd.SimpanFileKeDb(jFileChooser1.getCurrentDirectory()+"", selectedFile.getName()+"", CBMusic);
            
        }
        else
        {
            System.out.println("No Selection ");
        }
    }//GEN-LAST:event_BBrowseActionPerformed
    int chkrepeat = 0;
    private void chkRepeatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRepeatActionPerformed
        // TODO add your handling code here:
        if (chkrepeat == 0){
            jpDays.setVisible(true);
            lblDays.setVisible(true);
            chkrepeat = 1;
        }
        else {
            jpDays.setVisible(false);
            lblDays.setVisible(false);
            chkrepeat = 0;
        }
    }//GEN-LAST:event_chkRepeatActionPerformed
    
    private void BOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOKActionPerformed
        // TODO add your handling code here:
        //save to db
        String alarm_name = TBAlarmName.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(Time.getModel().getValue()).toString();
        //get chkrepeat int value
        //if (chkrepeat == 1){
        //get pd.days;
        //}
        System.out.println(CBMusic.getSelectedItem().toString());
        if (action.equals("Tambah")){
            if (pd.StartWeekOn.equals("Saturday")){
                pd.SaveAlarm(alarm_name, time, chkRepeat.isSelected(), pd.getIdMusic(CBMusic.getSelectedItem().toString()), cdmSaturday.day);
            }
            else if (pd.StartWeekOn.equals("Sunday")){
                pd.SaveAlarm(alarm_name, time, chkRepeat.isSelected(), pd.getIdMusic(CBMusic.getSelectedItem().toString()), cdmSundays.day);
            }
            else if (pd.StartWeekOn.equals("Monday")){
                pd.SaveAlarm(alarm_name, time, chkRepeat.isSelected(), pd.getIdMusic(CBMusic.getSelectedItem().toString()), cdmMonday.day);
            }
        }
        else if (action.equals("Edit")){
            if (pd.StartWeekOn.equals("Saturday")){
                pd.UpdateAlarm(pd.getIdAlarm(oldAlarm_name), cbxEnabled.isSelected(), alarm_name, time, chkRepeat.isSelected(), pd.getIdMusic(CBMusic.getSelectedItem().toString()), cdmSaturday.day);
                
            }
            else if (pd.StartWeekOn.equals("Sunday")){
                pd.UpdateAlarm(pd.getIdAlarm(oldAlarm_name), cbxEnabled.isSelected(), alarm_name, time, chkRepeat.isSelected(), pd.getIdMusic(CBMusic.getSelectedItem().toString()), cdmSundays.day);
            }
            else if (pd.StartWeekOn.equals("Monday")){
                pd.UpdateAlarm(pd.getIdAlarm(oldAlarm_name), cbxEnabled.isSelected(), alarm_name, time, chkRepeat.isSelected(), pd.getIdMusic(CBMusic.getSelectedItem().toString()), cdmMonday.day);
            }
            
        }
        this.dispose();
    }//GEN-LAST:event_BOKActionPerformed
    
    private void BCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_BCancelActionPerformed

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
            java.util.logging.Logger.getLogger(NewAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBrowse;
    private javax.swing.JButton BCancel;
    private javax.swing.JButton BOK;
    private javax.swing.JComboBox<String> CBMusic;
    private javax.swing.JTextField TBAlarmName;
    private javax.swing.JSpinner Time;
    private javax.swing.JCheckBox cbxEnabled;
    private javax.swing.JCheckBox chkRepeat;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jpDays;
    private javax.swing.JLabel lblDays;
    // End of variables declaration//GEN-END:variables
}
