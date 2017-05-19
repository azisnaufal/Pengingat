package pengingat.pomodoro.lockscreen;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class AltTabStopper implements Runnable
{
     private boolean working = true;
     private JFrame frame;
     public AltTabStopper(JFrame frame){
         this.frame = frame;
         
     }
     public void run()
     {
         
         try
         {
             Robot robot = new Robot();
             while (working)
             {
                  robot.keyRelease(KeyEvent.VK_ALT);
                  robot.keyRelease(KeyEvent.VK_TAB);
                  robot.keyRelease(KeyEvent.VK_WINDOWS);
                  robot.keyPress(KeyEvent.VK_WINDOWS);
                  robot.keyRelease(KeyEvent.VK_S);
                  robot.keyRelease(KeyEvent.VK_D);
                  robot.keyRelease(KeyEvent.VK_E);
                  robot.keyRelease(KeyEvent.VK_P);
                  robot.keyRelease(KeyEvent.VK_A);
                  robot.keyRelease(KeyEvent.VK_W);
                  frame.requestFocus();
                  try { 
                      Thread.sleep(10); 
                  } catch(InterruptedException ex) {
                  }
             }
         } catch (Exception e) { e.printStackTrace(); System.exit(-1); }
     }
}