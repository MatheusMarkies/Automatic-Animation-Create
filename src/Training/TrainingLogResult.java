/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Training;

import application.Main;
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

    public static void startTrainingLogResultForAritmetic() {

        File[] allFilesFromResults = new File(TrainingResultsFolder).listFiles();

        for (int i = 0; i < allFilesFromResults.length; i++) {

            File[] allFolderFilesTXT = allFilesFromResults[i].listFiles();

            for (int e = 0; e < allFolderFilesTXT.length; e++) {

                ArrayList<String> MainFileContent = new ArrayList<String>();
                ArrayList<String> TxtFileContent = new ArrayList<String>();

                File TxtFile = allFolderFilesTXT[e];
                File WordsFile = new File(WordsDataBaseDIR + "\\" + allFilesFromResults[i].getName() + ".txt");

                if (!WordsFile.exists()) {
                    TXTUtilities.CreateTXT.CreateTXTFile(WordsFile);
                }

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
                
                //if (MainFileContent == null || TxtFileContent == null)
                //break;
                
                if (MainFileContent.size() != 0) {
                    
                    if(TxtFileContent.size() != 0){
                    
                    if (MainFileContent.size() > TxtFileContent.size()) {

                        for (int r = 0; r < MainFileContent.size(); r++) {

                            int a = Integer.parseInt(MainFileContent.get(r));
                            
                            if(r < TxtFileContent.size()){
                                
                             int b = Integer.parseInt(TxtFileContent.get(r));
                             int c = (a + b) / 2;
                             NewFileContent.add(c + "");
                             
                            }else
                            NewFileContent.add(a + "");
                            
//                            if (MainFileContent.get(r) != null && TxtFileContent.get(r) != null) {
//                                int c = (a + b) / 2;
//                                NewFileContent.add(c + "");
//                            } else {
//                                if (MainFileContent.get(r) != null) {
//                                    NewFileContent.add(a + "");
//                                } else {
//                                    NewFileContent.add(b + "");
//                                }
//                            }
//                            //System.out.println("File COntent: "+c+" "+WordsFile.getName()); 
                       }

                    } else
                        for (int r = 0; r < TxtFileContent.size(); r++) { //for

                            int a = Integer.parseInt(TxtFileContent.get(r));
                            
                             if(r < MainFileContent.size()){
                                
                             int b = Integer.parseInt(MainFileContent.get(r));
                             int c = (a + b) / 2;
                             NewFileContent.add(c + "");
                             
                            }else
                            NewFileContent.add(a + "");

//                            if (MainFileContent.get(r) != null && TxtFileContent.get(r) != null) {
//                                int c = (a + b) / 2;
//                                NewFileContent.add(c + "");
//                            } else {
//                                if (MainFileContent.get(r) != null) {
//                                    NewFileContent.add(a + "");
//                                } else {
//                                    NewFileContent.add(b + "");
//                                }
//                            }


                            //System.out.println("File COntent: "+c+" "+WordsFile.getName());
                        }//for
                    
                    }else
                    for(int y = 0;y<MainFileContent.size();y++)
                    NewFileContent.add(MainFileContent.get(y));
                    
                }else if(TxtFileContent.size() != 0){
                 for(int y = 0;y<TxtFileContent.size();y++)
                NewFileContent.add(TxtFileContent.get(y));
                }
                
                if (NewFileContent == null || NewFileContent.size() == 0 || NewFileContent.size() == 0) {
                System.err.println("NewFileContentArray Error");
                    System.out.println("TxtFileContent "+TxtFileContent.size());
                    System.out.println("MainContent "+MainFileContent.size());
                //if(TxtFileContent.size() != 0){
                   
                //}
               break;
               } else {
                
                String[] NewFileContentArray = new String[NewFileContent.size()];
                System.out.println("NewFileContent " + NewFileContent);
                for (int t = 0; t < NewFileContent.size(); t++) {
                    NewFileContentArray[t] = NewFileContent.get(t);
                }

                    try {

                        if (NewFileContentArray.length > 0) {
                            System.out.println("Path: " + WordsFile);
                            System.out.println("NewFileContentArray: " + NewFileContentArray.length);
                            TXTUtilities.WriteTXT.Write_Array_TXT_File(WordsFile, NewFileContentArray);
                        } else {
                            System.out.println("NewFileContentArray: Null");
                        }

                    } catch (IOException ex) {
                        System.out.println("Erro na gravacao... " + WordsFile);
                    }
               // }

            }//For First

        }
        System.out.println("Acabou!!!");
       // Main.Training = false;
    }

}

}