/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengingat;

import java.io.FileInputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javazoom.jl.player.advanced.AdvancedPlayer;
/**
 *
 * @author azisn
 */
public class AlarmDialog extends javax.swing.JDialog {

    /**
     * Creates new form AlarmDialog
     */

    String filedir, fileextension,alarm_name;
    AdvancedPlayer player;
    AudioPlayer wavplayer;
    Thread t;
    Connection koneksi;
    PenyimpananData pd;
    boolean repeat;
    DefaultTableModel dtm;
    frmMain f;
    String alarm_time;
    public AlarmDialog(java.awt.Frame parent, boolean modal, String alarm_name, String filedir, String fileextension, Connection koneksi,boolean repeat, frmMain f, String alarm_time) {
        super(parent, modal);
        initComponents();
        this.alarm_time = alarm_time;
        this.f = f;
        this.dtm = dtm;
        this.repeat =repeat;
        this.alarm_name = alarm_name;
        this.koneksi = koneksi;
        pd = new PenyimpananData(koneksi);
        lblalarm_name.setText(alarm_name);
        this.filedir = filedir;
        setTitle(alarm_name);
        this.fileextension = fileextension;
        if (fileextension.equals(".wav")){
            wavplayer = new AudioPlayer(filedir);
            wavplayer.loop();
            wavplayer.play();
        }
        else{
            t = new Thread(new MyThread());
            t.start();
        }   
    }
    public class MyThread implements Runnable {
        public void run(){
            try{
                do{
                    FileInputStream fis = new FileInputStream(filedir);
                    player = new AdvancedPlayer(fis);
                    player.play();
                }while(true);
            }
            catch(Exception exc){
                exc.printStackTrace();
                System.out.println("Failed to play the file.");
            }
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

        lblalarm_name = new javax.swing.JLabel();
        btnSnooze = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("a");
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblalarm_name.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        lblalarm_name.setText("Alarm_name");
        lblalarm_name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnSnooze.setText("Snooze");
        btnSnooze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSnoozeActionPerformed(evt);
            }
        });

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnSnooze)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(61, 61, 61))
            .addGroup(layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(lblalarm_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(119, 119, 119))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(lblalarm_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSnooze)
                    .addComponent(btnClose))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        if (!repeat){
        pd.disableAlarm(pd.getIdAlarm(alarm_name));
        }
        if (fileextension.equals(".wav")){
            wavplayer.stop();
        }
        else{
            t.stop();
        }
        
        dispose();
        f.dtm.getDataVector().removeAllElements();
        f.showData();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (!repeat){
        pd.disableAlarm(pd.getIdAlarm(alarm_name));
        }
        if (fileextension.equals(".wav")){
            wavplayer.stop();
        }
        else{
            t.stop();
        }
        
        dispose();
        f.dtm.getDataVector().removeAllElements();
        f.showData();
        //terus alter enabled jadi 0;
    }//GEN-LAST:event_formWindowClosing

    private void btnSnoozeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSnoozeActionPerformed
        // TODO add your handling code here:
        //harus bisa nambah ... dari setting
        if (!repeat){
        //pd.disableAlarm(pd.getIdAlarm(alarm_name));
        }
        if (fileextension.equals(".wav")){
            wavplayer.stop();
        }
        else{
            t.stop();
        }
        try {
            pd.update_snooze_alarm(pd.getIdAlarm(alarm_name), alarm_time);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        dispose();
        f.dtm.getDataVector().removeAllElements();
        f.showData();
    }//GEN-LAST:event_btnSnoozeActionPerformed

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
            java.util.logging.Logger.getLogger(AlarmDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlarmDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlarmDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlarmDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
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
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSnooze;
    private javax.swing.JLabel lblalarm_name;
    // End of variables declaration//GEN-END:variables
}
