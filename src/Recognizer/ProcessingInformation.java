/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recognizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Markies
 */
public class ProcessingInformation {

    static String ResultPath = "C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\ScriptData\\FirstRecognizer\\";
    static String Result = "C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\ScriptData\\Result\\";
    
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
       //System.out.println("FileNames: "+FileNames);
       
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
   System.out.println("RepeatedNumber "+RepeatedNumber);
   
   int porc = (RepeatedNumber.get(0) * Comparator.getTolerance())/100;
   
   System.out.println("PROC: "+porc);
   
   ArrayList<String> TXT_Result = new ArrayList<>();
   for(int t = 0;t<RepeatedNumber.size();t++){
   if(RepeatedNumber.get(t) >= porc){
   TXT_Result.add(RepeatedWords.get(t));
   }
   }
   
   String[] TXT_ResultArray = new String[TXT_Result.size()];
   for(int i =0;i<TXT_Result.size();i++){
   TXT_ResultArray[i] = TXT_Result.get(i);
   }
   
   File file = new File(Result+"result.txt");
   
   TXTUtilities.CreateTXT.CreateTXTFile(file);
        try {
            TXTUtilities.WriteTXT.Write_Array_TXT_File(file, TXT_ResultArray);
        } catch (IOException ex) {
            Logger.getLogger(ProcessingInformation.class.getName()).log(Level.SEVERE, null, ex);
        }
   
  }//Method

}//CLASS
