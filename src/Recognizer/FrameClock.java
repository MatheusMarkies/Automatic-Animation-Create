/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recognizer;

import static Recognizer.Comparator.passComparatorSymmetric;
import static Recognizer.Comparator.passComparatorWithTolerance;
import static Recognizer.Comparator.passComparatorWithTolerancePlus;
import static AnimationCreate.CreateKeyFrameData.VideoTime;
import application.Main;
import static application.PlayerExample.streamPlayer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static visualizer.Oscilloscope.getFile;

/**
 *
 * @author Matheus Markies
 */
public class FrameClock extends Thread{

    public static int CurrentFrame;
    static int WhileTimer = 0;
    boolean Exec;
    
    static String state_;
    static boolean ChangeState;
    
    public void run() {
    
        int TotalFrames = VideoTime * 60;
        CurrentFrame = 0;
        
        while (true) {
            
            //if(WhileTimer > 16){
            //CurrentFrame += 1;
            //}
            
            if(!streamPlayer.isPlaying() && WhileTimer > 0 && Main.Training == false){
                try {
                    if(Exec == false){
                    //passComparatorSymmetric(getFile(),1);
                    //passComparatorWithTolerance(getFile(),60);
                    passComparatorWithTolerancePlus(getFile(),100,3);
                    Exec = true;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FrameClock.class.getName()).log(Level.SEVERE, null, ex);
                }
                WhileTimer = 0;
            }else{

            }
            
            if(!streamPlayer.isPlaying() && WhileTimer > 0 && Main.Training == true){
            Training.MainTraining.ExcNextAudio = true;
            }else{
            
            }
            
            if(ChangeState){
            System.out.println(state_);
            ChangeState = false;
            }
            
            try {
                Thread.sleep(1000);
                if(streamPlayer.isPlaying()){
                WhileTimer += 1;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(FrameClock.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    public static int getCurretFrame (){
    return CurrentFrame;
    }
    public static int getTimer(){
    return WhileTimer;
    }
    public static String setState(){
    ChangeState = true;
    return state_;
    }
    
}
