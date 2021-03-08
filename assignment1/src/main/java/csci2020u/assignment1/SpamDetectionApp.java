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
		SpamDisplayUI secondScreen = new SpamDisplayUI("./Data");

		//Update sizes
		pane.getChildren().add(secondScreen.GetBackgroundGrid());
		secondScreen.GetBackgroundGrid().prefWidthProperty().bind(pane.widthProperty());
		secondScreen.GetBackgroundGrid().prefHeightProperty().bind(pane.heightProperty());

		//Root child is the pane so we can resize everything
        Scene scene = new Scene(pane, 640, 480);
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