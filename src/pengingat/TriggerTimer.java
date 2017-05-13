/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pengingat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
//import static pengingatiseng.frmMain.getCurrentTimeStamp;

/**
 *
 * @author azisn
 * TriggerTimer() untuk pembuatan
 * Update() untuk Update
 * Cancel() check terlebih dahulu timer nya null atau gk
 * Action() jika Timernya sudah ada Cancel() lalu eksekusi
 */
public class TriggerTimer {
    
    private String time, judul, dayss, filedirMusic, fileextension;
    private boolean isEnabled, repeat;
    private TimerTask tt;
    Timer t = null;
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
    
    public static String getCurrentDayStamp(){
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String a = new SimpleDateFormat("EEEE", Locale.US).format(date.getTime());
        return a;
        //update(time, judul, isEnabled);
    }
    public TriggerTimer(String time, String judul,String dayss,String filedirMusic, boolean isEnabled,boolean repeat){
        this.time = time;
        this.judul = judul;
        this.dayss = dayss;
        this.filedirMusic = filedirMusic;
        this.isEnabled = isEnabled;
        this.repeat = repeat;
        this.fileextension = filedirMusic.substring(filedirMusic.lastIndexOf("."), filedirMusic.length());
        update(time, judul, dayss, filedirMusic, isEnabled, repeat);
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDayss() {
        return dayss;
    }

    public void setDayss(String dayss) {
        this.dayss = dayss;
    }

    public String getFiledirMusic() {
        return filedirMusic;
    }

    public void setFiledirMusic(String filedirMusic) {
        this.filedirMusic = filedirMusic;
    }

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
    
    
    private void doAction(){
        if(isIsEnabled()) {
            if(tt != null) {
                doCancel();
            }
            tt = new TimerTask() {
                @Override
                public void run() {
                    if(repeat){
                        if(dayss.contains(getCurrentDayStamp())){
                            if(getCurrentTimeStamp().equals(getTime())){
                                //                    Notification myNotification = new Notification(judul, deskripsi, "dialog-information"); // create the notification object
                                //                    myNotification.show();
                                AlarmDialog d = new AlarmDialog(null, true, judul, filedirMusic, fileextension);
                                d.setLocationRelativeTo(null);
                                d.setVisible(true);
                                System.out.println(judul);
                                t.cancel(); // buat keluar dari looping yang menyebalkan ini.
                            }
                        }
                    }
                    if(getCurrentTimeStamp().equals(getTime())){
    //                    Notification myNotification = new Notification(judul, deskripsi, "dialog-information"); // create the notification object
    //                    myNotification.show();
                        //new AlarmDialog(null, true, judul, filedirMusic, fileextension).setVisible(true);
                        System.out.println(judul);
                        AlarmDialog d = new AlarmDialog(null, true, judul, filedirMusic, fileextension);
                                d.setLocationRelativeTo(null);
                                d.setVisible(true);
                        t.cancel(); // buat keluar dari looping yang menyebalkan ini.
                    }
                }
            };
            t = new Timer();
            t.scheduleAtFixedRate(tt, 0,1000);
        } else {
            tt = null;
            t = null;
        }
        
    }
    public void doCancel(){
        if(t != null) {
            t.cancel();
        }
    }                  
    public void update(String time, String judul,String dayss,String filedirMusic, boolean isEnabled,boolean repeat){
        this.setTime(time);
        this.setJudul(judul);
        this.setDayss(dayss);
        this.setFiledirMusic(filedirMusic);
        
        if(isEnabled){
            doAction();
        }
        else{
            doCancel();
        }
        
    }
    
}
