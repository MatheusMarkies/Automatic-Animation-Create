/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnimationCreate;

import java.util.logging.Level;
import java.util.logging.Logger;
import visualizer.Oscilloscope;
import static visualizer.Oscilloscope.*;

/**
 *
 * @author Matheus Markies
 */
public class TestRunnable extends Thread{
 
    public void run() {
        
        while (true) {            
            
            try{
                

                
            }catch(Exception e){
                System.err.println(e);
            }
            
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(TestRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
}
