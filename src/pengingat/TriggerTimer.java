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
    
    private String time, judul;
    private boolean isEnabled;
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
    }
    public TriggerTimer(String time, String judul, boolean isEnabled) {
        this.time = time;
        this.judul = judul;
        this.isEnabled = isEnabled;
        update(time, judul, isEnabled);
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

    public boolean isIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
    
    private void doAction(){
        if(isEnabled) {
            if(tt != null) {
                doCancel();
            }
            tt = new TimerTask() {
                @Override
                public void run() {
                    if(getCurrentTimeStamp().equals(time)){
    //                    Notification myNotification = new Notification(judul, deskripsi, "dialog-information"); // create the notification object
    //                    myNotification.show();
                        System.out.println(judul);
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
    public void update(String time, String judul, boolean isEnabled){
        this.time = time;
        this.judul = judul;
        if(isEnabled){
            doAction();
        }
        else{
            doCancel();
        }
        
    }
    
}
