/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pengingat;
import pengingat.pomodoro.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import pengingat.pomodoro.*;
/**
 *
 * @author azisn
 */
public class frmMain extends javax.swing.JFrame {
    
    /**
     * Creates new form frmMain
     */

    DefaultTableModel dtm;
    SettingPanel sp ;
    PenyimpananData pd;
    private java.util.List<TriggerTimer> trig = new ArrayList<>();
    Connection koneksi;
    int minute,second;
    Thread t;
    public static SystemTray systemTray;
    public static Image trayIconn;
    public static TrayIcon trayIcon ;
    public static PopupMenu trayPopupMenu ;
    String icon, username, host, password, port, db;
    private void Systray(){
        //checking for support
                if(!SystemTray.isSupported()){
                    System.out.println("System tray is not supported !!! ");
                    return ;
                }
                //get the systemTray of the system
                systemTray = SystemTray.getSystemTray();
                
                //get default toolkit
                //Toolkit toolkit = Toolkit.getDefaultToolkit();
                //get image
                //Toolkit.getDefaultToolkit().getImage("src/resources/busylogo.jpg");new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/edit-find-replace.png"))
                trayIconn = Toolkit.getDefaultToolkit().getImage(icon);
                
                //popupmenu
                trayPopupMenu = new PopupMenu();
                MenuItem close = new MenuItem("Exit");
                close.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                trayPopupMenu.add(close);
                //1t menuitem for popupmenu
                MenuItem action = new MenuItem("Restore");
                action.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        pack();
                        setLocationByPlatform(true);
                        setLocationRelativeTo(null);
                        setVisible(true);
                    }
                });
                trayPopupMenu.add(action);
                
                //2nd menuitem of popupmenu
               
                //setting tray icon
                trayIcon= new TrayIcon(trayIconn, "Pengingat", trayPopupMenu);
                //adjust to default size as per system recommendation
                trayIcon.setImageAutoSize(true);
                trayIcon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        if (e.getButton() == MouseEvent.BUTTON1 && isVisible() == false) {
                            pack();
                            setLocationByPlatform(true);
                            setLocationRelativeTo(null);
                            setVisible(true);
                            if (pd.isPomodoroRunning() == 1){
                                btnStartPomodoro.setVisible(false);
                                jSeparator1.setVisible(false);
                                lblPomoStat.setText("Your Pomodoro's is running!");
                            }
                        }
                    }
                });
                try{
                    systemTray.add(trayIcon);
                }
                catch(AWTException awtException){
                    awtException.printStackTrace();
                }
    }
    public frmMain(boolean isDialog) {
        initComponents();
        sp = new SettingPanel();
        icon = sp.getValue("Icon");
        host = sp.getValue("host");
        username = sp.getValue("username");
        password = sp.getValue("password");
        db = sp.getValue("db");
        Systray();
        koneksi = DatabaseConnection.getKoneksi(host,port,username,"",db);
        pd = new PenyimpananData(koneksi);
        pd.setSettingData();
        if (pd.isPomodoroRunning() == 1){
            btnStartPomodoro.setVisible(false);
            jSeparator1.setVisible(false);
            lblPomoStat.setText("Your Pomodoro's is running!");
        }
        if (!isDialog){
            showData();
        }
    }
    public void showNotif(String caption, String message){
        trayIcon.displayMessage(caption, message, TrayIcon.MessageType.INFO);
    }
    public void showData() {
        if (!trig.isEmpty()){
            int i  = 0;
            for (i = 0;i<trig.size();i++){
                trig.get(i).doCancel();
            }
        }
        String[] kolom = { "Enabled", "Alarm Name", "Time", "Repeat", "Days", "Music" };
        dtm = new DefaultTableModel(null, kolom){
            @Override
            public Class getColumnClass(int column) {
                switch(column){
                    case 0 : return Boolean.class;
                    case 3 : return Boolean.class;
                    default : return String.class;
                }
            }
        };
        try
        {
            java.sql.Statement stmt = koneksi.createStatement();
            String query = "SELECT    \n" +
                    "`tb_alarm`.`enabled`   \n" +
                    " , `tb_alarm`.`alarm_name`    \n" +
                    " ,`tb_alarm`.`time`        \n" +
                    " , `tb_alarm`.`repeat`    \n" +
                    " , `tb_alarm`.`days`    \n" +
                    " , `tb_music`.`filename`\n" +
                    " , CONCAT (`tb_music`.`filedir`,\"/\",`tb_music`.`filename`) AS filedirname\n" +
                    "  FROM    `db_pengingat`.`tb_alarm`    INNER JOIN `db_pengingat`.`tb_music` ON (`tb_alarm`.`id_music` = `tb_music`.`id_music`)";
            ResultSet rs = stmt.executeQuery(query);
            int no = 0;
            while (rs.next()) {
                boolean enabled;
                if (rs.getInt("enabled") == 1) {
                    enabled = true;
                }
                else
                    enabled = false;
                String days;
                boolean repeat;
                if (rs.getInt("repeat") == 1) {
                    repeat = true;
                    days = rs.getString("days");
                }
                else {
                    repeat = false;
                    days = "-";
                }
                String alarm_name = rs.getString("alarm_name");
                String filename = rs.getString("filedirname");
                String time = rs.getString("time");
                dtm.addRow(new Object[] { Boolean.valueOf(enabled), alarm_name, time, Boolean.valueOf(repeat), days, filename });
                TriggerTimer trigg = new TriggerTimer(time, alarm_name, days, filename, enabled, repeat,koneksi,this);
                trig.add(trigg);
                no++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tbDataPengingat.setModel(dtm);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbDataPengingat = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        jToolBar1 = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnStartPomodoro = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnSetting = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnExit = new javax.swing.JButton();
        lblPomoStat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pengingat");
        setLocation(new java.awt.Point(0, 0));

        tbDataPengingat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDataPengingat.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbDataPengingat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataPengingatMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbDataPengingatMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbDataPengingat);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/document-new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.setFocusable(false);
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNew);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/edit-find-replace.png"))); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEdit);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/user-trash-full.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDelete);

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/view-refresh.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.setFocusable(false);
        btnRefresh.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRefresh.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        jToolBar1.add(btnRefresh);
        jToolBar1.add(jSeparator1);

        btnStartPomodoro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/pomodoro.png"))); // NOI18N
        btnStartPomodoro.setText("Start Pomodoro");
        btnStartPomodoro.setFocusable(false);
        btnStartPomodoro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStartPomodoro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnStartPomodoro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartPomodoroActionPerformed(evt);
            }
        });
        jToolBar1.add(btnStartPomodoro);
        jToolBar1.add(jSeparator2);

        btnSetting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/preferences-other.png"))); // NOI18N
        btnSetting.setText("Setting");
        btnSetting.setFocusable(false);
        btnSetting.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSetting.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSettingActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSetting);
        jToolBar1.add(jSeparator3);

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pengingat.icon/application-exit.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.setFocusable(false);
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jToolBar1.add(btnExit);

        lblPomoStat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(lblPomoStat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPomoStat, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    int baris = -1;
    private void tbDataPengingatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataPengingatMouseClicked
        // TODO add your handling code here:
        baris = tbDataPengingat.getSelectedRow();
        
    }//GEN-LAST:event_tbDataPengingatMouseClicked
    
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        NewAlarm d = new NewAlarm(this, true, "Tambah", "", koneksi);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
        dtm.getDataVector().removeAllElements();
        showData();
    }//GEN-LAST:event_btnNewActionPerformed
    
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if(baris == -1){
            JOptionPane.showMessageDialog(this, "You haven't clicked the row!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            String alarm_namee = tbDataPengingat.getValueAt(baris, 1).toString();
            NewAlarm frame = new NewAlarm(this, true, "Edit", alarm_namee, koneksi);
            frame.setLocationRelativeTo(this);
            frame.setVisible(true);
            dtm.getDataVector().removeAllElements();
            showData();
        }
        
//        boolean enabled = Boolean.parseBoolean(tbDataPengingat.getValueAt(baris, 0).toString());
//        String time = tbDataPengingat.getValueAt(baris, 2).toString();
//        boolean repeat = Boolean.parseBoolean(tbDataPengingat.getValueAt(baris, 4).toString());
//        String dayss = tbDataPengingat.getValueAt(baris, 4).toString();
//        String filedirname = tbDataPengingat.getValueAt(baris, 5).toString();
//        trig.get(baris).update(time, alarm_namee, dayss, filedirname, enabled, repeat);
    }//GEN-LAST:event_btnEditActionPerformed
    
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        String alarm_namee = tbDataPengingat.getValueAt(baris, 1).toString();
        String time = tbDataPengingat.getValueAt(baris, 2).toString();
        String dayss = tbDataPengingat.getValueAt(baris, 4).toString();
        String filedirname = tbDataPengingat.getValueAt(baris, 5).toString();
        trig.get(baris).update(time, alarm_namee, dayss, filedirname, false, false);
        int id_alarm = pd.getIdAlarm(tbDataPengingat.getValueAt(baris, 1).toString());
        pd.deleteAlarm(id_alarm);
        dtm.getDataVector().removeAllElements();
        showData();
    }//GEN-LAST:event_btnDeleteActionPerformed
    
    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        dtm.getDataVector().removeAllElements();
        showData();
    }//GEN-LAST:event_btnRefreshActionPerformed
    
    private void btnSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSettingActionPerformed
        // TODO add your handling code here:
        Setting s = new Setting(this, true, koneksi);
        s.setLocationRelativeTo(this);
        s.setVisible(true);
        pd.setSettingData();
    }//GEN-LAST:event_btnSettingActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (this, "Are you sure?","Warning",dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnStartPomodoroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartPomodoroActionPerformed
        // TODO add your handling code here:
        frmPomodoro pomo = new frmPomodoro(this, true, pd.PomodoroDuration, pd.BreakDuration, koneksi, this);
        pomo.setLocationRelativeTo(this);
        pomo.setVisible(true);
    }//GEN-LAST:event_btnStartPomodoroActionPerformed

    private void tbDataPengingatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataPengingatMousePressed
        // TODO add your handling code here:
        JTable table = (JTable)evt.getSource();
        Point p = evt.getPoint();
        int row = tbDataPengingat.rowAtPoint(p);
        if(evt.getClickCount() == 2){
            JOptionPane.showMessageDialog(this, "Trying to edit this value? \n Click the edit button!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tbDataPengingatMousePressed
    
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
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
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
                frmMain frame = new frmMain(false);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    public javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSetting;
    private javax.swing.JButton btnStartPomodoro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblPomoStat;
    private javax.swing.JTable tbDataPengingat;
    // End of variables declaration//GEN-END:variables

}
