/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Training;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Markies
 */
public class TrainingLogResult {
    
static String TrainingResultsFolder = "C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\WordsDataBase\\Training\\TrainingResults";
static String WordsDataBaseDIR = "C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\WordsDataBase";
    
public static void startTrainingLogResultForAritmetic(){
 
 File[] allFilesFromResults = new File(TrainingResultsFolder).listFiles();
 
 for(int i=0;i<allFilesFromResults.length;i++){
 
 File[] allFolderFilesTXT = allFilesFromResults[i].listFiles();
 
 for(int e=0;e<allFolderFilesTXT.length;e++){
 
ArrayList<String> MainFileContent = new ArrayList<String>();
ArrayList<String> TxtFileContent = new ArrayList<String>();

 File TxtFile = allFolderFilesTXT[e];
 File WordsFile = new File(WordsDataBaseDIR+"\\"+allFilesFromResults[i].getName()+".txt");
 
 if(!WordsFile.exists())
 TXTUtilities.CreateTXT.CreateTXTFile(WordsFile);
 

     try {
         MainFileContent = TXTUtilities.ReadTXT.readTXTFile(TxtFile);
     } catch (FileNotFoundException ex) {
     break;
     }
     try {
         TxtFileContent = TXTUtilities.ReadTXT.readTXTFile(WordsFile);
     } catch (FileNotFoundException ex) {
      break;
     }

 
 ArrayList<String> NewFileContent = new ArrayList<String>();
 if(MainFileContent.size() > 0){
 if(MainFileContent.size() < TxtFileContent.size()){
     
     for(int r=0;r<MainFileContent.size();r++){
        
         int a = Integer.parseInt(MainFileContent.get(r));
         int b = Integer.parseInt(TxtFileContent.get(r));
         if(MainFileContent.get(r) != null && TxtFileContent.get(r) != null){
         int c = (a+b)/2;
         NewFileContent.add(c+"");
         }else{
         if(MainFileContent.get(r) != null){
         NewFileContent.add(a+"");
         }else{
         NewFileContent.add(b+"");
         }
         }
        //System.out.println("File COntent: "+c+" "+WordsFile.getName()); 
     }
     
 }else{
  for(int r=0;r<TxtFileContent.size();r++){
  
   int a = Integer.parseInt(MainFileContent.get(r));
   int b = Integer.parseInt(TxtFileContent.get(r));
   
   if(MainFileContent.get(r) != null && TxtFileContent.get(r) != null){
   int c = (a+b)/2;
   NewFileContent.add(c+""); 
   }else{
   if(MainFileContent.get(r) != null){
         NewFileContent.add(a+"");
         }else{
         NewFileContent.add(b+"");
         }   
   }
   
      //System.out.println("File COntent: "+c+" "+WordsFile.getName());
  }
 }//Else
 }
 String[] NewFileContentArray = new String[NewFileContent.size()];
     System.out.println("NewFileContent "+ NewFileContent);
 for(int t = 0;t<NewFileContent.size();t++){
 NewFileContentArray[t] = NewFileContent.get(t);
 }
 
 if(NewFileContentArray == null || NewFileContent.size() == 0 || NewFileContentArray.length == 0){
 System.err.println("NewFileContentArray Error"); 
 break;
 }    
 
     try {
         
         if(NewFileContentArray.length > 0){
         System.out.println("NewFileContentArray: "+NewFileContentArray.length);
             System.out.println("Path: "+WordsFile);
         TXTUtilities.WriteTXT.Write_Array_TXT_File(WordsFile, NewFileContentArray);
     }else{
     System.out.println("NewFileContentArray: Null ");
     }
         
         } catch (IOException ex) {
         System.out.println("Erro na gravacao... " +WordsFile);
     }
 
 }//For First
     
 }
     
 }
    
}
