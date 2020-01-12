/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Training;

import application.Main;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Markies
 */
public class MainTraining extends Thread{
 
static String AudioFolder = "C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\WordsDataBase\\Training\\AudioFiles";
static String TrainingResultsFolder = "C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\WordsDataBase\\Training\\TrainingResults";

public static int IndexFolders;
public static File[] AudioFilesFromFolder;
public static File[] allAudioFolders;

public static boolean ExcNextAudio = true;
public static int NextAudio;

boolean TrainingLogResultLock = false;

boolean NewItem = true;

public void run() {

File AudioFolderFile = new File(AudioFolder);
allAudioFolders = AudioFolderFile.listFiles();

while (Main.Training == true) {

if(IndexFolders < (allAudioFolders.length-1)){

 if(NewItem) 
 AudioFilesFromFolder = allAudioFolders[IndexFolders].listFiles();
 
 if(AudioFilesFromFolder.length == 0){
 IndexFolders ++;
 NewItem = true;
 }else{
 NewItem = false;
 }
 
}else{
//System.out.println("Training Stoped");
 if(!TrainingLogResultLock){
TrainingLogResult.startTrainingLogResultForAritmetic();
TrainingLogResultLock = true;
 }
}

if(ExcNextAudio){

    try{
    Main.playerExample.playSong(AudioFilesFromFolder[NextAudio]);
    System.out.println("Next Training: "+AudioFilesFromFolder[NextAudio].getName());
    }catch(Exception E){
    //System.err.println("Erro 33:");
   // System.err.println(E);
    }

if(NextAudio < (AudioFilesFromFolder.length-1)){
NextAudio ++;
}else{
IndexFolders ++;
NewItem = true;
NextAudio = 0;
}

ExcNextAudio = false;
}

    try {
        Thread.sleep(200);
    } catch (InterruptedException ex) {
        
    }

}//while

}//Run

        
    public static String getTrainingResultsFolder(){
        return TrainingResultsFolder;
    }

    
}
