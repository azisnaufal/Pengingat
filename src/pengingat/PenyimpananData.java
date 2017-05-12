package pengingat;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;




public class PenyimpananData
{
  public int SilenceAfter;
  public int SnoozeLength;
  public String StartWeekOn;
  
  public PenyimpananData() {}
  
  public String days = "";
  public Vector music = new Vector();
  Connection koneksi = DatabaseConnection.getKoneksi("localhost", "3306", "root", "", "db_pengingat");
  
  public void SimpanFileKeDb(String dir, String file, JComboBox jcbx) { String extension = file.substring(file.lastIndexOf("."), file.length());
    dir = dir.replace("\\", "\\\\");
    System.out.println(dir);
    try {
      Statement stmt = koneksi.createStatement();
      String query = "INSERT INTO tb_music (`filedir`,`filename`, `fileextension`) VALUE('" + dir + "','" + file + "','" + extension + "')";
      System.out.println(query);
      int berhasil = stmt.executeUpdate(query);
      if (berhasil == 1)
      {
        jcbx.removeAllItems();
        jcbx.setModel(setMusictoCbx());
        jcbx.setSelectedItem(file);
      }
      else {
        JOptionPane.showMessageDialog(null, "Data gagal dimasukkan");
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam database");
    }
  }
  
  public DefaultComboBoxModel setMusictoCbx() {
    try { Statement stmt = koneksi.createStatement();
      String query = "SELECT * From tb_music";
      ResultSet rs = stmt.executeQuery(query);
      int no = 1;
      while (rs.next()) {
        if (rs.getString("filename").equals("Alarm02.wav")) {
          music.add("Default");
        }
        else {
          music.add(rs.getString("filename"));
        }
        no++;
      }
      return new DefaultComboBoxModel(music);
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      return new DefaultComboBoxModel(music);
    }
  }
  
  public void setSettingData() {
    try {
      Statement stmt = koneksi.createStatement();
      String query = "SELECT * From tb_setting";
      ResultSet rs = stmt.executeQuery(query);
      int no = 1;
      while (rs.next()) {
        SilenceAfter = rs.getInt("silence_after");
        SnoozeLength = rs.getInt("snooze_length");
        StartWeekOn = rs.getString("start_week_on");
        no++;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
  
  public int getIdMusic(String music) 
  { 
    if (music.equals("Default")) 
    {
      return 1;
    }
    else {
    try
    {
      Statement stmt = koneksi.createStatement();
      String query = "SELECT tb_music.id_music FROM db_pengingat.tb_music WHERE tb_music.filename=\"" + music + "\"";
      ResultSet rs = stmt.executeQuery(query);
      System.out.println(query);
      int no = 1;
      
      return rs.getInt("id_music");
    }
    catch (SQLException ex) {
      ex.printStackTrace(); }
    return 0;
  }
}
public int getIdAlarm(String alarm_name) { 
    if (music.equals("Default")) {
      return 1;
    }
    else {
    try
    {
      Statement stmt = koneksi.createStatement();
      String query = "SELECT tb_music.id_music FROM db_pengingat.tb_music WHERE tb_music.filename=\"" + music + "\"";
      ResultSet rs = stmt.executeQuery(query);
      System.out.println(query);
      int no = 1;
      
      return rs.getInt("id_music");
    }
    catch (SQLException ex) {
      ex.printStackTrace(); }
    return 0;
  }
}

  public void SaveAlarm(String alarm_name, String time, boolean repeat, int id_music, String day)
  {
    try
    {
      Statement stmt = koneksi.createStatement();
      String query;
      if (repeat != true) {
        query = "INSERT INTO `db_pengingat`.`tb_alarm` (`enabled`, `alarm_name`, `time`, `repeat`, `id_music`) VALUES ('1', '" + alarm_name + "', '" + time + "', '0', '" + id_music + "');";
      }
      else {
        query = "INSERT INTO `db_pengingat`.`tb_alarm` (`enabled`, `alarm_name`, `time`, `repeat`, `days`, `id_music`) VALUES ('0', '" + alarm_name + "', '" + time + "', '1', '" + day + "', '" + id_music + "');";
      }
      System.out.println(query);
      int berhasil = stmt.executeUpdate(query);
      if (berhasil == 1)
      {
        JOptionPane.showMessageDialog(null, "Saved!");
      }
      else {
        JOptionPane.showMessageDialog(null, "Data gagal dimasukkan");
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam database");
    }
  }
  public void UpdateAlarm(String alarm_name, String time, boolean repeat, int id_music, String day)
  {
    try
    {
      // UPDATE `db_pengingat`.`tb_alarm` SET `enabled` = '0' , `alarm_name` = 'asuuuuuu' , `time` = '01:01:03' , `repeat` = '1' , `days` = 'Tuesday Wednesday' WHERE `id_alarm` = '17';
      Statement stmt = koneksi.createStatement();
      String query;
      if (repeat != true) {
        query = "UPDATE INTO `db_pengingat`.`tb_alarm` (`enabled`, `alarm_name`, `time`, `repeat`, `id_music`) VALUES ('1', '" + alarm_name + "', '" + time + "', '0', '" + id_music + "');";
      }
      else {
        query = "UPDATE `db_pengingat`.`tb_alarm` SET "
                + "`enabled` = '0' , "
                + "`alarm_name` = 'asuuuuuu' , "
                + "`time` = '01:01:03' , "
                + "`repeat` = '1' , "
                + "`days` = 'Tuesday Wednesday' , "
                + "`id_music` = '9'"
                + "WHERE `id_alarm` = '17';";
      }
      System.out.println(query);
      int berhasil = stmt.executeUpdate(query);
      if (berhasil == 1)
      {
        JOptionPane.showMessageDialog(null, "Saved!");
      }
      else {
        JOptionPane.showMessageDialog(null, "Data gagal dimasukkan");
      }
    }
    catch (SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Terjadi kesalahan dalam database");
    }
  }
  
}
