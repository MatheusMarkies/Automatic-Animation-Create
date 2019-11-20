/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnimationCreate;

import TXTUtilities.CreateTXT;
import TXTUtilities.ReadTXT;
import TXTUtilities.WriteTXT;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Matheus Markies
 */
public class CreateKeyFrameData {
    
    public static String[] AEI = {"a","e"};
    public static String[] O = {"o"};
    //public static String[] I = {"i"};
    public static String[] CDGKNSXZ = {"c","d","g","k","n","s","x","z"};
    public static String[] JCHSH = {"j","ch","sh"};
    public static String[] FTTHV = {"f","t","th","v"};
    public static String[] EE = {"ee"};
    public static String[] BMP = {"b","m","p"};
    public static String[] QW = {"q","w"};
    public static String[] R = {"r"};
    public static String[] U = {"u"};
    public static String[] IY = {"i","y"};
    
    public static int VideoTime;
    
    public static String[] MainData = { "Adobe After Effects 8.0 Keyframe Data\n" +
"\n" +
"	Units Per Second	60\n" +
"	Source Width	497\n" +
"	Source Height	114\n" +
"	Source Pixel Aspect Ratio	1\n" +
"	Comp Pixel Aspect Ratio	1\n" +
"\n" +
"Transform	Opacity\n" +
"	Frame	percent	"};
    
    public static String[] EndData = { "\n"
            + "\n"
            + "End of Keyframe Data" };
    
    public static ArrayList<String> KeyFrames = new ArrayList<String>();
    
    public static void CreateKeyFrameDataFile(String Mouth,String Text, int VideoTime_) throws IOException{
        
        String[] MouthWords = null;
        VideoTime = VideoTime_;
        
        if(Mouth == "AEI"){
         MouthWords = AEI;   
        }
        if(Mouth == "O"){
           MouthWords = O;   
        }
        //if(Mouth == "I"){
            
        //}
        if(Mouth == "CDGKNSXZ"){
           MouthWords = CDGKNSXZ;   
        }
        if(Mouth == "JCHSH"){
           MouthWords = JCHSH;   
        }
        if(Mouth == "FTTHV"){
           MouthWords = FTTHV;   
        }
        if(Mouth == "QW"){
            MouthWords = QW;  
        }
        if(Mouth == "R"){
          MouthWords = R;    
        }
        if(Mouth == "U"){
           MouthWords = U;   
        }
        if(Mouth == "IY"){
           MouthWords = IY;   
        }
        
    ArrayList<String> Frame = new ArrayList<String>();
    ArrayList<String> Percent = new ArrayList<String>();
    
    ArrayList<String> Data = new ArrayList<String>();
        
        for(int y=0;y<Text.length();y++){
        for(int u=0;u<MouthWords.length;u++){
            if(MouthWords[u].length() > 1){
                
            }else{
            if(Text.charAt(y) == MouthWords[u].charAt(0)){
            
            int Cframe = (y +1) * (VideoTime_/Text.length()) * 60;  
            
            //Before Frame
            Frame.add((Cframe - 1)+"");
            Percent.add("0");
            //In Frame
            Frame.add(Cframe+"");
            Percent.add("100");
            }
            }
        }
        }
        
        File Folder = new File("C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\"+Mouth+".txt");
        
        if(!Folder.exists()){
        CreateTXT.CreateTXTFile(Folder);
        }else{
        Folder.delete();
        CreateTXT.CreateTXTFile(Folder);
        }
        
        for(int i = 0;i<Frame.size();i++){
           
        KeyFrames.add("        "+Frame.get(i)+"      "+Percent.get(i)+"     ");
            
        }
        
       for(int i =0;i<MainData.length;i++){
       Data.add(MainData[i]);
       }
       
       for(int i =0;i<KeyFrames.size();i++){
       Data.add(KeyFrames.get(i));
       }
       
       for(int i =0;i<EndData.length;i++){
       Data.add(EndData[i]);
       }
       
       String[] DataArray = new String[Data.size()];
       
       for(int r=0;r<Data.size();r++){
       DataArray[r] = Data.get(r);
       }
       
       WriteTXT.Write_Array_TXT_File(Folder, DataArray);
        
    }
    
}
