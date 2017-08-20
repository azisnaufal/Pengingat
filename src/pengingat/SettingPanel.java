/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pengingat;

import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author oazisn
 */
public class SettingPanel {
    public Properties mypanel, myLanguage;
    private String strNamePanel;
    public SettingPanel(){
        
    }
    public String getValue(String nmPanel){
        try{
            mypanel = new Properties();
            mypanel.load(new FileInputStream("lib/setting.conf"));
            strNamePanel = mypanel.getProperty(nmPanel);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Setting File Not Found!", "Error", JOptionPane.INFORMATION_MESSAGE);
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return strNamePanel;
    }
}
