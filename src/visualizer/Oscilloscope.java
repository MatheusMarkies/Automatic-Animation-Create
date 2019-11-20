/*
 *  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

   Also(warning!):
 
  1)You are not allowed to sell this product to third party.
  2)You can't change license and made it like you are the owner,author etc.
  3)All redistributions of source code files must contain all copyright
     notices that are currently in this file, and this list of conditions without
     modification.
 */
package visualizer;

import Recognizer.FrameClock;
import AnimationCreate.TestRunnable;
import static Training.MainTraining.*;
import application.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * The Class Oscilloscope.
 * 
 * -----------------------------------------------------------------------
 * 
 * -----------------------------------------------------------------------
 * 
 * 
 * Oscilloscope
 * 
 * -----------------------------------------------------------------------
 * 
 * -----------------------------------------------------------------------
 *
 * 
 * @author GOXR3PLUS
 */
public class Oscilloscope {

	// ---------------Oscilloscope-------------------

	/** The color size. */
	private final int colorSize = 360;

	/** The color index. */
	private int colorIndex = 0;

	/** The band width. */
	private static float bandWidth;

	/** The x. */
	private static int x = 0;

	/** The y. */
	private static  int y = 0;

	/** The x old. */
	private static int xOld = 0;

	/** The y old. */
	@SuppressWarnings("unused")
	private static int yOld = 0;
        
        ArrayList<String> Ylist = new ArrayList<String>();
        static File TXT_File;

	/** VisualizerDrawer instance. */
	private VisualizerDrawer visualizerDrawer;

	// ---------------------------------------------------------------------------------------------------

	/**
	 * Constructor.
	 *
	 * @param visualizerDrawer
	 *            the visualizer drawer
	 */
	public Oscilloscope(VisualizerDrawer visualizerDrawer) {
		this.visualizerDrawer = visualizerDrawer;
	}

	/*-----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 * 
	 * 
	 * 			        Oscilloscope
	 * 
	 * -----------------------------------------------------------------------
	 * 
	 * -----------------------------------------------------------------------
	 */

	/**
	 * Draws an Oscilloscope.
	 *
	 * @param stereo
	 *            The Oscilloscope with have 2 lines->stereo or 1 line->merge
	 *            left and right audio
	 */
	public void drawOscilloscope(boolean stereo) throws FileNotFoundException, IOException {
		float[] pSample1;

                if(!Main.Training){
                TXT_File = new File("C:\\Users\\Matheus Markies\\Desktop\\AnimationData\\SpeechDataBase\\ScriptData\\"+Main.AudioName+".txt");
                }else{
                    try{
                TXT_File = new File(getTrainingResultsFolder()+"\\"+allAudioFolders[IndexFolders].getName()+"\\"+AudioFilesFromFolder[NextAudio].getName()+".txt");
                    }catch(Exception e){
                        System.err.println("TXT_File = null");
                        System.err.println(e);
                    }
                File TXT_Folder = new File(getTrainingResultsFolder()+"\\"+allAudioFolders[IndexFolders].getName());
                if(!TXT_Folder.exists()){
                TXT_Folder.mkdir();
                }
                }
                
                 FrameClock rc = new FrameClock();
                 Thread t = new Thread(rc);
                 t.start(); 
                
                 TXTUtilities.CreateTXT.CreateTXTFile(TXT_File);
                 TXTUtilities.WriteTXT.Write_TXT_File(TXT_File, "----------------------Music INFO----------------------");
                 
		// It will be stereo?
		if (stereo)
			pSample1 = visualizerDrawer.pLeftChannel;
		else // not?Then merge the array
			pSample1 = visualizerDrawer.stereoMerge(visualizerDrawer.pLeftChannel, visualizerDrawer.pRightChannel);

		visualizerDrawer.gc.setStroke(visualizerDrawer.oscilloscopeColor);
		// System.out.println(pSample1.length)

		int yLast1 = (int) (pSample1[0] * (float) visualizerDrawer.halfCanvasHeight)
				+ visualizerDrawer.halfCanvasHeight;
		int samIncrement1 = 1;
		for (int a = samIncrement1, c = 0; c < visualizerDrawer.canvasWidth; a += samIncrement1, c++) {
			int yNow = (int) (pSample1[a] * (float) visualizerDrawer.halfCanvasHeight)
					+ visualizerDrawer.halfCanvasHeight;
			visualizerDrawer.gc.strokeLine(c, yLast1, c + 1.00, yNow);
                        
                        if(yNow != 300){
                            int Y = yNow-300;
                           if(!Main.Training){
                           Ylist.add(FrameClock.getTimer()+":"+yNow);
                           }else{
                           Ylist.add(yNow+"");
                           }
                        }
                        
                        String[] List = new String[Ylist.size()];
                        
                        for(int i = 0;i<List.length;i++){
                        List[i] = Ylist.get(i);
                        }
                        
                        TXTUtilities.WriteTXT.Write_Array_TXT_File(TXT_File, List);
                        

                        yLast1 = yNow;
		}

		// Oscilloscope will be stereo
		if (stereo) {
			colorIndex = (colorIndex == colorSize - 1) ? 0 : colorIndex + 1;
			visualizerDrawer.gc.setStroke(Color.hsb(colorIndex, 1.0f, 1.0f));

			float[] pSample2 = visualizerDrawer.pRightChannel;

			int yLast2 = (int) (pSample2[0] * (float) visualizerDrawer.halfCanvasHeight)
					+ visualizerDrawer.halfCanvasHeight;
			int samIncrement2 = 1;
			for (int a = samIncrement2, c = 0; c < visualizerDrawer.canvasWidth; a += samIncrement2, c++) {
				int yNow = (int) (pSample2[a] * (float) visualizerDrawer.halfCanvasHeight)
						+ visualizerDrawer.halfCanvasHeight;
				visualizerDrawer.gc.strokeLine(c, yLast2, c + 1.00, yNow);
				yLast2 = yNow;
			}

		}

	}

        public static int getX (){
            return x;
        }
        public static int getY (){
            return y;
        }
        public static int getXold (){
            return xOld;
        }
        public static int getYold (){
            return yOld;
        }
        public static float getBand (){
            return bandWidth;
        }
        public static File getFile(){
            return TXT_File;
        }
        
}
