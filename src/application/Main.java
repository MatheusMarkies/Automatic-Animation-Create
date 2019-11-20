package application;

import AnimationCreate.TestRunnable;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main extends Application{

      public static PlayerExample playerExample = new PlayerExample();

      public static String AudioName;
      public static boolean Reconizer;
      public static boolean Training;
      
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
                        
                        new MainFrame().setVisible(true);
                        
                        if(Training){
                            
                        }
                        
                        if(Reconizer){

                        }

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
	launch(args);
	}
    
}
