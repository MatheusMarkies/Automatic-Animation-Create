/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recognizer;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Matheus Markies
 */
public class ProcessingInformation {

    static String ResultPath = "C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\ScriptData\\FirstRecognizer\\";
    
    public ProcessingInformation() {
    getMoreRepeatedWords();
    }  
    
    public void getMoreRepeatedWords(){
        
       File ResultPath_ = new File(ResultPath);
       File[] ResultPathAllFiles = ResultPath_.listFiles();
       
       ArrayList<String> FileNames = new ArrayList<>();
       
       for(int i=0;i<ResultPathAllFiles.length;i++){
           
           File[] AllFiles = ResultPathAllFiles[i].listFiles();
           for(int t=0;t<AllFiles.length;t++)
           FileNames.add(AllFiles[t].getName());
               
       }
       System.out.println("FileNames: "+FileNames);
       
       ArrayList<String> RepeatedWords = new ArrayList<>();
       ArrayList<Integer> RepeatedNumber = new ArrayList<>();
       
       String TempWord = "";
       int Repeated = 0;
       int MostRepeated = 0;
       int Lenght = FileNames.size();
       
       while (RepeatedWords.size() < Lenght) {            

       for(int i =0;i<FileNames.size();i++){
       
       if(i == 0){
       TempWord = FileNames.get(i);
       }

       for(int g =0;g<FileNames.size();g++){
         if(FileNames.get(i).equals(FileNames.get(g))){
          Repeated ++;   
         }  
       }
       if(Repeated > MostRepeated){
       TempWord = FileNames.get(i);
       MostRepeated = Repeated;
       Repeated = 0;
       }
    }

   RepeatedWords.add(TempWord);
   RepeatedNumber.add(MostRepeated);
   FileNames.remove(TempWord);
   
   MostRepeated = 0;
   Repeated = 0;
       
   }//While
       
   System.out.println("RepeatedWords "+RepeatedWords);
       
  }//Method

}//CLASS
