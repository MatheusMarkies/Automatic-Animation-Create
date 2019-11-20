/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnimationCreate;

import application.PlayerExample;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AutomaticAnimationCreate extends Application{

      public static String[] GetFrames;
      
      PlayerExample playerExample = new PlayerExample();

	@Override
	public void start(Stage primaryStage) {
		try {

			// Scene
			Scene scene = new Scene(playerExample, 600, 600);
			scene.setCursor(Cursor.HAND);
			primaryStage.setScene(scene);

			// Show
			primaryStage.setOnCloseRequest(c -> System.exit(0));
			primaryStage.show();

			// Selection of song to play
			JFileChooser jFileChooser = new JFileChooser();
			jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			jFileChooser.setFileFilter(new FileNameExtensionFilter("audio","mp3","wav"));
			jFileChooser.setAcceptAllFileFilterUsed(false);

			while(true){
				if(jFileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
					playerExample.playSong(jFileChooser.getSelectedFile());
					break;
				} else{
					JOptionPane.showMessageDialog(null,"Please choose audio file","Select audio",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
	launch(args);
	}
	
    
            
}