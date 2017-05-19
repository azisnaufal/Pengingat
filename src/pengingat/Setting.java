/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengingat;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author azisn
 */
public class Setting extends javax.swing.JDialog {

    /**
     * Creates new form Setting
     */
    Connection koneksi;
    public Setting(java.awt.Frame parent, boolean modal, Connection koneksii) {
        super(parent, modal);
        initComponents();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpeg", "jpg", "png");
        jFileChooser1.addChoosableFileFilter(filter);
        koneksi = koneksii;
        showData();
    }
    public void showData(){
        try{
            Statement stmt = koneksi.createStatement();
            String query = "SELECT * From tb_setting";
            ResultSet rs = stmt.executeQuery(query);
            int no = 1;
            while (rs.next()){
                jsSilenceAfter.setValue(rs.getInt("silence_after"));
                jsSnoozeLength.setValue(rs.getInt("snooze_length"));
                jsPomodoroDuration.setValue(rs.getInt("pomo_length"));
                jsPomodoroBreakDuration.setValue(rs.getInt("pomo_break"));
//                jslVolume.setValue(rs.getInt("volume"));
//                lblVolume.setText(rs.getInt("volume")+"%");
                cbxStartWeekOn.setSelectedItem(rs.getString("start_week_on"));
                no++;
            }
        } catch(SQLException ex){
            ex.printStackTrace();
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbxStartWeekOn = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jsSilenceAfter = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jsSnoozeLength = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jsPomodoroDuration = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jsPomodoroBreakDuration = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Setting");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/preferences-other.png"))); // NOI18N
        jLabel1.setText("Setting");

        jLabel2.setText("Silence After");

        jLabel3.setText("Snooze Length");

        jLabel5.setText("Start Week On");

        cbxStartWeekOn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" }));

        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Apply");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jsSilenceAfter.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jsSilenceAfter.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jsSilenceAfterMouseWheelMoved(evt);
            }
        });

        jLabel7.setText("Minute");

        jsSnoozeLength.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jsSnoozeLength.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jsSnoozeLengthMouseWheelMoved(evt);
            }
        });

        jLabel8.setText("Minute");

        jLabel6.setText("Pomodoro Duration");

        jsPomodoroDuration.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jsPomodoroDuration.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jsPomodoroDurationMouseWheelMoved(evt);
            }
        });

        jLabel9.setText("Minute");

        jLabel10.setText("Break Duration");

        jsPomodoroBreakDuration.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jsPomodoroBreakDuration.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jsPomodoroBreakDurationMouseWheelMoved(evt);
            }
        });

        jLabel11.setText("Minute");

        jLabel12.setText("Change Lockscreen Wallpaper");

        jButton4.setText("Browse");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cbxStartWeekOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(28, 28, 28)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jsSilenceAfter, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                                    .addComponent(jsSnoozeLength))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel10))
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jsPomodoroDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jsPomodoroBreakDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jsSilenceAfter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsSnoozeLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxStartWeekOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jsPomodoroDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jsPomodoroBreakDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int silence_after = Integer.parseInt(jsSilenceAfter.getValue()+"");
        int snooze_length = Integer.parseInt(jsSnoozeLength.getValue()+"");
        //        int volume = Integer.parseInt(jslVolume.getValue()+"");
        String StartWeekOn = cbxStartWeekOn.getSelectedItem().toString();
        int pomodoro_duration = Integer.parseInt(jsPomodoroDuration.getValue()+"");
        int break_duration = Integer.parseInt(jsPomodoroBreakDuration.getValue()+"");
        
        if (snooze_length < 0 || silence_after < 0 || pomodoro_duration < 0 ||  break_duration < 0){
            JOptionPane.showMessageDialog(null, "Invalid Snooze Length or Silence After Value");
        }
        else {
            try{
                Statement stmt = koneksi.createStatement();
                String query = "UPDATE `tb_setting` SET "
                        + "`silence_after` = "+silence_after+", "
                        + "`snooze_length` = "+snooze_length+", "
                        + "`start_week_on` = '"+StartWeekOn+"', "
                        + "`pomo_length` = '"+pomodoro_duration+"',"
                        + "`pomo_break` = '"+break_duration+"' WHERE `id_setting` = 1";
                System.out.println(query);
                int berhasil = stmt.executeUpdate(query);
                if (berhasil == 1){
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Data gagal diubah");
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada query");
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jsSilenceAfterMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jsSilenceAfterMouseWheelMoved
        // TODO add your handling code here:
        int notches = evt.getWheelRotation();
        if (notches < 0) {
            jsSilenceAfter.setValue(Integer.parseInt(jsSilenceAfter.getValue().toString()) + 1);
        }
        else if (notches > 0) {
            jsSilenceAfter.setValue(Integer.parseInt(jsSilenceAfter.getValue().toString()) - 1);
        }
    }//GEN-LAST:event_jsSilenceAfterMouseWheelMoved

    private void jsSnoozeLengthMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jsSnoozeLengthMouseWheelMoved
        // TODO add your handling code here:
        int notches = evt.getWheelRotation();
        if (notches < 0) {
            jsSnoozeLength.setValue(Integer.parseInt(jsSnoozeLength.getValue().toString()) + 1);
        }
        else if (notches > 0) {
            jsSnoozeLength.setValue(Integer.parseInt(jsSnoozeLength.getValue().toString()) - 1);
        }
    }//GEN-LAST:event_jsSnoozeLengthMouseWheelMoved

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int silence_after = Integer.parseInt(jsSilenceAfter.getValue()+"");
        int snooze_length = Integer.parseInt(jsSnoozeLength.getValue()+"");
        //        int volume = Integer.parseInt(jslVolume.getValue()+"");
        String StartWeekOn = cbxStartWeekOn.getSelectedItem().toString();
        int pomodoro_duration = Integer.parseInt(jsPomodoroDuration.getValue()+"");
        int break_duration = Integer.parseInt(jsPomodoroBreakDuration.getValue()+"");
        
        if (snooze_length < 0 || silence_after < 0 || pomodoro_duration < 0 ||  break_duration < 0){
            JOptionPane.showMessageDialog(null, "Invalid Snooze Length or Silence After Value");
        }
        else {
            try{
                Statement stmt = koneksi.createStatement();
                String query = "UPDATE `tb_setting` SET "
                        + "`silence_after` = "+silence_after+", "
                        + "`snooze_length` = "+snooze_length+", "
                        + "`start_week_on` = '"+StartWeekOn+"', "
                        + "`pomo_length` = '"+pomodoro_duration+"',"
                        + "`pomo_break` = '"+break_duration+"' WHERE `id_setting` = 1";
                System.out.println(query);
                int berhasil = stmt.executeUpdate(query);
                if (berhasil == 1){
                    JOptionPane.showMessageDialog(null, "Data berhasil diubah");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Data gagal diubah");
                }
            }
            catch(SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan pada query");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jsPomodoroDurationMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jsPomodoroDurationMouseWheelMoved
        // TODO add your handling code here:
        int notches = evt.getWheelRotation();
        if (notches < 0) {
            jsPomodoroDuration.setValue(Integer.parseInt(jsPomodoroDuration.getValue().toString()) + 1);
        }
        else if (notches > 0) {
            jsPomodoroDuration.setValue(Integer.parseInt(jsPomodoroDuration.getValue().toString()) - 1);
        }
    }//GEN-LAST:event_jsPomodoroDurationMouseWheelMoved

    private void jsPomodoroBreakDurationMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jsPomodoroBreakDurationMouseWheelMoved
        // TODO add your handling code here:
        int notches = evt.getWheelRotation();
        if (notches < 0) {
            jsPomodoroBreakDuration.setValue(Integer.parseInt(jsPomodoroBreakDuration.getValue().toString()) + 1);
        }
        else if (notches > 0) {
            jsPomodoroBreakDuration.setValue(Integer.parseInt(jsPomodoroBreakDuration.getValue().toString()) - 1);
        }
    }//GEN-LAST:event_jsPomodoroBreakDurationMouseWheelMoved

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (jFileChooser1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = new File(jFileChooser1.getSelectedFile()+"");
            //System.out.println(selectedFile.getName());
            PenyimpananData pd = new PenyimpananData(koneksi);
            pd.gantiBg(jFileChooser1.getCurrentDirectory()+selectedFile.getName()+"");
        }
        else
        {
            System.out.println("No Selection ");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Setting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxStartWeekOn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner jsPomodoroBreakDuration;
    private javax.swing.JSpinner jsPomodoroDuration;
    private javax.swing.JSpinner jsSilenceAfter;
    private javax.swing.JSpinner jsSnoozeLength;
    // End of variables declaration//GEN-END:variables
}
