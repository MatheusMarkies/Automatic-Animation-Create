/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recognizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Matheus Markies
 */
public class DataTreatment {

    static String ResultPath = "C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\ScriptData\\FirstRecognizer\\";
    
    public DataTreatment() throws FileNotFoundException, IOException {
        
    System.out.println("Data Treatment");
    
    File ResultFolder = new File(ResultPath);
    File[] AllFolders = ResultFolder.listFiles();
    
    for(int i=0;i<AllFolders.length;i++){
    File[] AllFiles = AllFolders[i].listFiles();
    
    if(AllFiles.length ==0)
    break;
    
    for(int u=0;u<AllFiles.length;u++){
        
        ArrayList<String> StartDATA = new ArrayList<>();
        ArrayList<String> CloseDATA = new ArrayList<>();
        
        ArrayList<String> Content = new ArrayList<>();
        ArrayList<String> ContentAnalyze = new ArrayList<>();
        
        String[] SplitContent;
        
        Content = TXTUtilities.ReadTXT.readTXTFile(AllFiles[u]);
        
        for(int o=0;o<Content.size();o++){
        int Control = 0;
        for(int r =0;r<ContentAnalyze.size();r++){
            if(Content.get(o).equals(ContentAnalyze.get(r))){
               Control ++;
            }
        }
        if(Control == 0){
        SplitContent = Content.get(o).split(":");
        ContentAnalyze.add(Content.get(o));
        
        if(SplitContent.length > 0){
        try{
        if(SplitContent[0].equals("Start")){
        StartDATA.add(SplitContent[1]);
        }else{
        CloseDATA.add(SplitContent[1]);
        }
        }catch(Exception e){
                
       }
        }else{
        //break;
        }
        
        }
        }
        
        //System.out.println("StartDATA: "+StartDATA);
        
        for(int y = 0;y<StartDATA.size();y++){
        
            for(int f = 0;f<StartDATA.size();f++){
                if(StartDATA.get(y).equals(StartDATA.get(f))){
                StartDATA.remove(f);
                break;
                }
            }
            
        }
        System.out.println("Result A: "+StartDATA);
        for(int y = 0;y<CloseDATA.size();y++){
        
            for(int f = 0;f<CloseDATA.size();f++){
                if(CloseDATA.get(y).equals(CloseDATA.get(f))){
                CloseDATA.remove(f);
                break;
                }
            }
            
        }
        System.out.println("Result B: "+CloseDATA);
        
        ArrayList<String> WriteNewFile = new ArrayList<>();
        
        for(int r = 0;r<StartDATA.size();r++){
        WriteNewFile.add("Start:"+StartDATA.get(r));
        }
        for(int r = 0;r<CloseDATA.size();r++){
        WriteNewFile.add("Close:"+CloseDATA.get(r));
        }
        
        File NewTxt = AllFiles[u];
        AllFiles[u].delete();
        String[] WordsList = new String[WriteNewFile.size()];
        for(int g=0;g<WriteNewFile.size();g++){
        WordsList[g] = WriteNewFile.get(g);
        }
         System.out.println("Words "+WriteNewFile);
        TXTUtilities.WriteTXT.Write_Array_TXT_File(NewTxt, WordsList);
        
    }//AllFiles U
    
    }//AllFolder I
    System.out.println("Tratamento acabou...");
    new ProcessingInformation();
    }//Method
    
}
