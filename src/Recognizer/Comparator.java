/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recognizer;

import static TXTUtilities.ReadTXT.readTXTFile;
import static TXTUtilities.TXTIA.GetTXTLineIndex.getTXTFileLineIndex;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Matheus Markies
 */
public class Comparator {
    
    static String WordsPath = "C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\WordsDataBase\\";
    
    static String ResultPath = "C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\ScriptData\\FirstRecognizer\\";
    static String ResultPathBackup = "C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\ScriptData\\FirstRecognizer\\";
    
    static String[] Words = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","x","y","z"};
    
    public static void passComparatorWithTolerance(File file,int Tolerance) throws FileNotFoundException, IOException{
        
    for(int i =0;i<Words.length;i++){
        
    int EqualsIndex = 0;
    ArrayList<String> SeqIn = new ArrayList<>();
    String SeqOut = "";
    
    ArrayList<String> Sequence = new ArrayList<String>();
        
    File WordsFile = new File(WordsPath+Words[i]+".txt");
        
    ArrayList<String> WordsFileSeq = new ArrayList<String>();

    ArrayList<String> ScriptContent = new ArrayList<String>();
    ScriptContent = readTXTFileWithOutJavaUtilities(file);

    ArrayList<String> ScriptSoundWave = new ArrayList<String>();
    ArrayList<String> ScriptTimer = new ArrayList<String>();
    
    for(int y = 0;y<ScriptContent.size();y++){
        
    String[] Temp = new String[1];
    Temp = ScriptContent.get(y).split(":");

    if(Temp.length>0){
    ScriptTimer.add(Temp[0]);
    ScriptSoundWave.add(Temp[1]);
    }
    
    }//Separator
    
    WordsFileSeq = readTXTFileWithOutJavaUtilities(WordsFile);
    
       // System.out.println("-------------------------------------------");
       // System.out.println("ScriptContent Size: "+ScriptContent.size());
       // System.out.println("WordsFileSeq Size: "+WordsFileSeq.size());
        //System.out.println("ScriptSoundWave Size: "+ScriptSoundWave.size());
       // System.out.println("");
    int SymmetricIn = 0;
    for(int y = 0;y<ScriptSoundWave.size();y++){
    for(int u=0;u<WordsFileSeq.size();u++){
        
     //System.out.println("WordsFileSeq "+WordsFileSeq.get(u));
     //System.out.println("ScriptSoundWave "+ScriptSoundWave.get(y));
        
     int Prop = (Integer.parseInt(WordsFileSeq.get(u))*Tolerance)/100;
     int tempNumber = Integer.parseInt(WordsFileSeq.get(u)) - Prop;
     
     SymmetricIn = (WordsFileSeq.size() * Tolerance)/100;
     
     boolean AddEquals = false;
     
    for(int h = 0;h< Prop;h++){
     
    if(Integer.parseInt(ScriptSoundWave.get(y)) == (tempNumber+h)){
        
    AddEquals = true;
    SeqIn.add(ScriptTimer.get(y));
    //System.err.println(SeqIn);
    //System.out.println("Very Good: "+(tempNumber+h));
    break;
    
    }
    }//FOR
        
    if(AddEquals){
    EqualsIndex += 1;
    }
    
    }
    
    if(EqualsIndex < SymmetricIn){
    EqualsIndex = 0;
    SeqIn = new ArrayList<>();
    SeqOut ="";
    
    }else{
        
    //System.out.println("EqualsIndex "+EqualsIndex);
    //System.out.println("SymmetricIn "+SymmetricIn);
    //System.out.println("");
        
    SeqOut = ScriptTimer.get(y);
    
    for(int g=0;g<SeqIn.size();g++){
     Sequence.add("Start:"+SeqIn.get(g));   
    }
    
    //if(SeqOut==SeqIn){
    //Sequence.add("Start:"+SeqIn);
    //Sequence.add("Close:"+(SeqOut));   
    //}
    
    SeqIn = new ArrayList<>();
    SeqOut ="";
    EqualsIndex = 0;
    }
    }
    
    if(Sequence.size() > 0){
    File ResultFile = new File(ResultPath+"0\\"+Words[i]+".txt");
    
    ArrayList<String> DataTemp = new ArrayList<>();
    
    if(ResultFile.exists()){
    DataTemp = readTXTFileWithOutJavaUtilities(ResultFile);
    for(int y=0;y<Sequence.size();y++){
    DataTemp.add(Sequence.get(y));
    }
    }else{
    DataTemp = Sequence;
    }
    
    for(int r =0;r<DataTemp.size();r++){
        int Control = 0;
        for(int k=0;k<DataTemp.size();k++){
        
            if(DataTemp.get(r) == DataTemp.get(k)){
             Control += 1;   
            if(Control > 1){
            DataTemp.remove(k);
            }
            }
            
        }
    }
    
    TXTUtilities.CreateTXT.CreateTXTFile(ResultFile);

    String[] SequenceARRAY = new String[DataTemp.size()];  

    for(int y =0;y<SequenceARRAY.length;y++){
    SequenceARRAY[y] = DataTemp.get(y);
    }
    
    TXTUtilities.WriteTXT.Write_Array_TXT_File(ResultFile, SequenceARRAY);
    Sequence.clear();
    }
     System.out.println("Letter: "+Words[i]);
    }//Main For
        
    }
    public static void passComparatorSymmetric(File file,int SymmetricIn) throws FileNotFoundException, IOException{
      
    //System.out.println("asd");
        
    for(int i =0;i<Words.length;i++){
        
    int EqualsIndex = 0;
    String SeqIn = "";
    String SeqOut = "";
    
    ArrayList<String> Sequence = new ArrayList<String>();
        
    File WordsFile = new File(WordsPath+Words[i]+".txt");
        
    ArrayList<String> WordsFileSeq = new ArrayList<String>();

    ArrayList<String> ScriptContent = new ArrayList<String>();
    ScriptContent = readTXTFileWithOutJavaUtilities(file);

    ArrayList<String> ScriptSoundWave = new ArrayList<String>();
    ArrayList<String> ScriptTimer = new ArrayList<String>();
    
    for(int y = 0;y<ScriptContent.size();y++){
        
    String[] Temp = new String[1];
    Temp = ScriptContent.get(y).split(":");

    if(Temp.length>0){
    ScriptTimer.add(Temp[0]);
    ScriptSoundWave.add(Temp[1]);
    }
    
    }//Separator
    
    WordsFileSeq = readTXTFileWithOutJavaUtilities(WordsFile);
    
       // System.out.println("-------------------------------------------");
       // System.out.println("ScriptContent Size: "+ScriptContent.size());
       // System.out.println("WordsFileSeq Size: "+WordsFileSeq.size());
        //System.out.println("ScriptSoundWave Size: "+ScriptSoundWave.size());
       // System.out.println("");
    
    for(int y = 0;y<ScriptSoundWave.size();y++){
    for(int u=0;u<WordsFileSeq.size();u++){
        
     System.out.println("WordsFileSeq "+WordsFileSeq.get(u));
     System.out.println("ScriptSoundWave "+ScriptSoundWave.get(y));
        
    if(ScriptSoundWave.get(y) == WordsFileSeq.get(u)){
    EqualsIndex += 1;
    SeqIn = ScriptTimer.get(y);
    System.out.println("---------------------------------------------------------------------------------------------------------------------------");
    //break;
    }else{
    if(EqualsIndex < SymmetricIn){
    EqualsIndex = 0;
    SeqIn = "";
    SeqOut ="";
    }else{
    SeqOut = ScriptTimer.get(y);
    Sequence.add(SeqIn);
    Sequence.add(SeqOut);
    SeqIn = "";
    SeqOut ="";
    }
    }
    }
    }
    
    if(Sequence.size() > 0){
    File ResultFile = new File(ResultPath+"0\\"+Words[i]+".txt");
    TXTUtilities.CreateTXT.CreateTXTFile(ResultFile);
    
    String[] SequenceARRAY = new String[Sequence.size()];
    for(int y=0;y<SequenceARRAY.length;y++){
    SequenceARRAY[y] = Sequence.get(y);
    }
    
    TXTUtilities.WriteTXT.Write_Array_TXT_File(ResultFile, SequenceARRAY);
    }
        System.out.println("Letter: "+Words[i]);
    }//Main For
    
    
    
    }//Method
    
    public static void passComparatorWithTolerancePlus(File file,int Tolerance,int PassTimes) throws FileNotFoundException, IOException{
        
     File TempWords =  new File(WordsPath);
     File[] Temps = TempWords.listFiles();
     
     Words = new String[Temps.length];
     for(int i=0;i<Temps.length;i++){
         String Name = "";
         String Extencion = "";
         boolean SaveName = true;
         for(int t = 0;t<Temps[i].getName().length();t++){
             if(Temps[i].getName().charAt(t) == '.'){
             SaveName = false;
             }
             if(!SaveName){
             Extencion = Extencion+Temps[i].getName().charAt(t);
             }
             if(SaveName){
             Name = Name+Temps[i].getName().charAt(t);
             }
         }
         if(Extencion.equals(".txt")){
            // System.out.println("Name: d "+Name);
         Words[i] = Name;
         }else{
         Words[i] = "a";
         }
         //System.out.println("Name: "+Name);
         //System.out.println("Extencion: "+Extencion);
         //ArrayList<String> WordsList = new ArrayList<>();
        // for(int g=0;g<Words.length;g++){
          //WordsList.add(Words[g]);
         //}
         //System.out.println("Words "+WordsList);
     }
        
    for(int l =0;l<PassTimes;l++){
    ResultPath = ResultPathBackup;   
    ResultPath = ResultPath+""+l;
    File tempFolder = new File(ResultPath);
    if(!tempFolder.exists()){
    tempFolder.mkdir();
    }
    ResultPath = ResultPath +"\\";
     
    for(int i =0;i<Words.length;i++){
        
    int EqualsIndex = 0;
    String SeqIn = "";
    String SeqOut = "";
    
    ArrayList<String> Sequence = new ArrayList<String>();
        if(Words[i] == "" || Words == null){
        break;
        }
    File WordsFile = new File(WordsPath+Words[i]+".txt");
        
    ArrayList<String> WordsFileSeq = new ArrayList<String>();

    ArrayList<String> ScriptContent = new ArrayList<String>();
    ScriptContent = readTXTFileWithOutJavaUtilities(file);

    ArrayList<String> ScriptSoundWave = new ArrayList<String>();
    ArrayList<String> ScriptTimer = new ArrayList<String>();
    
    for(int y = 0;y<ScriptContent.size();y++){
        
    String[] Temp = new String[1];
    Temp = ScriptContent.get(y).split(":");

    if(Temp.length>0){
    ScriptTimer.add(Temp[0]);
    ScriptSoundWave.add(Temp[1]);
    }
    
    }//Separator
    
    WordsFileSeq = readTXTFileWithOutJavaUtilities(WordsFile);
    
       // System.out.println("-------------------------------------------");
       // System.out.println("ScriptContent Size: "+ScriptContent.size());
       // System.out.println("WordsFileSeq Size: "+WordsFileSeq.size());
        //System.out.println("ScriptSoundWave Size: "+ScriptSoundWave.size());
       // System.out.println("");
    int SymmetricIn = 0;
    for(int y = 0;y<ScriptSoundWave.size();y++){
    for(int u=0;u<WordsFileSeq.size();u++){
        
     //System.out.println("WordsFileSeq "+WordsFileSeq.get(u));
     //System.out.println("ScriptSoundWave "+ScriptSoundWave.get(y));
        
     int Prop = (Integer.parseInt(WordsFileSeq.get(u))*Tolerance)/100;
     int tempNumber = Integer.parseInt(WordsFileSeq.get(u)) - Prop;
     
     SymmetricIn = (WordsFileSeq.size() * Tolerance)/100;
     
     boolean AddEquals = false;
     
    for(int h = 0;h< Prop;h++){
     
    if(Integer.parseInt(ScriptSoundWave.get(y)) == (tempNumber+h)){
        
    AddEquals = true;
    SeqIn = ScriptTimer.get(y);
    //System.out.println("Very Good: "+(tempNumber+h));
    break;
    
    }
    }//FOR
        
    if(AddEquals){
    EqualsIndex += 1;
    }
    
    }
    
    if(EqualsIndex < SymmetricIn){
    EqualsIndex = 0;
    SeqIn = "";
    SeqOut ="";
    
    }else{
        
    //System.out.println("EqualsIndex "+EqualsIndex);
    //System.out.println("SymmetricIn "+SymmetricIn);
    //System.out.println("");
        
    SeqOut = ScriptTimer.get(y);
    
    if(SeqOut==SeqIn){
    Sequence.add("Start:"+SeqIn);
    //Sequence.add("Close:"+(SeqOut));   
    }
    
    SeqIn = "";
    SeqOut ="";
    EqualsIndex = 0;
    }
    }
    
    if(Sequence.size() > 0){
    File ResultFile = new File(ResultPath+Words[i]+".txt");
    
    ArrayList<String> DataTemp = new ArrayList<>();
    
    if(ResultFile.exists()){
    DataTemp = readTXTFileWithOutJavaUtilities(ResultFile);
    for(int y=0;y<Sequence.size();y++){
    DataTemp.add(Sequence.get(y));
    }
    }else{
    DataTemp = Sequence;
    }
    
    int Control = 0;
    
    for(int r =0;r<DataTemp.size();r++){
        for(int k=0;k<DataTemp.size();k++){
        
            if(DataTemp.get(r) == DataTemp.get(k)){
             Control += 1;   
            if(Control > 1){
            DataTemp.remove(k);
            }
            }
            
        }
    Control = 0;
    }
    
    TXTUtilities.CreateTXT.CreateTXTFile(ResultFile);

    String[] SequenceARRAY = new String[DataTemp.size()];  

    for(int y =0;y<SequenceARRAY.length;y++){
    SequenceARRAY[y] = DataTemp.get(y);
    }
    
    TXTUtilities.WriteTXT.Write_Array_TXT_File(ResultFile, SequenceARRAY);
    Sequence.clear();
    }
    //System.out.println("Letter: "+Words[i]);
    }//Main For
        
    }//PassTimes
    
    new DataTreatment();
    }
    
    public static ArrayList<String> readTXTFileWithOutJavaUtilities(File file) throws FileNotFoundException{
    ArrayList<String> FileContent = new ArrayList<String>();
    
    Scanner Reader = new Scanner(file);
    
        while (Reader.hasNext()) {            
            
        String LineContent = Reader.nextLine();
        FileContent.add(LineContent); 
            
        }
    
    return FileContent;
    }
    
}
