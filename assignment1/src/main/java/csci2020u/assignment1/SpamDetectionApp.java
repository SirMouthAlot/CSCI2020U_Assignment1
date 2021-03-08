package csci2020u.assignment1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class SpamDetectionApp extends Application
{	
	@Override
	public void start(Stage stage) throws Exception 
	{
		Pane pane = new Pane();

		//Auto adds itself to pane and uses stage to set up directory selector
		new DirectorySelectionUI(stage, pane);

		//Root child is the pane so we can resize everything
        Scene scene = new Scene(pane, 640, 180);
		stage.setResizable(false);
		pane.prefWidthProperty().bind(scene.widthProperty());
		pane.prefHeightProperty().bind(scene.heightProperty());

		//Window name
        stage.setTitle("Spam Detection Application");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
	
	public static void main(String[] args)
	{
		launch();
	}
}