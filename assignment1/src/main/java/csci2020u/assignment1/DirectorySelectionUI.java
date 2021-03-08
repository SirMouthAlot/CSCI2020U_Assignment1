package csci2020u.assignment1;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class DirectorySelectionUI 
{
    //The main panel for the spam display
    private Stage _mainStage;
    private Pane _rootPane;

    private BorderPane _backgroundPane;
    private Button _chooseDirectory;
    private DirectoryChooser _directoryChooser;
    
    //The second widnow where our spam or ham gets displayed
    private SpamDisplayUI _secondScreen;

    public DirectorySelectionUI(Stage stage, Pane pane)
    {
        _mainStage = stage;
        _rootPane = pane;

        _backgroundPane = new BorderPane();
        _directoryChooser = new DirectoryChooser();
        _directoryChooser.setInitialDirectory(new File("./"));

        _rootPane.getChildren().add(_backgroundPane);
		_backgroundPane.prefWidthProperty().bind(pane.widthProperty());
		_backgroundPane.prefHeightProperty().bind(pane.heightProperty());

        _chooseDirectory = new Button("Choose Directory");
        _chooseDirectory.setOnAction(e -> {
            File selectedDirectory = _directoryChooser.showDialog(_mainStage);

            _secondScreen = new SpamDisplayUI(selectedDirectory.getAbsolutePath());
            _mainStage.setWidth(640);
            _mainStage.setHeight(480);
            _mainStage.setResizable(true);

            _rootPane.getChildren().clear();

            _rootPane.getChildren().add(_secondScreen.GetBackgroundPane());
            _secondScreen.GetBackgroundPane().prefWidthProperty().bind(pane.widthProperty());
		    _secondScreen.GetBackgroundPane().prefHeightProperty().bind(pane.heightProperty());
        });
        
        Label titleLabel = new Label("IS IT SPAM OR HAM?");
        titleLabel.setFont(new Font(50.0d));
        
        _backgroundPane.setTop(titleLabel);
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        _backgroundPane.setCenter(new Label("Please choose a data directory."));
        _backgroundPane.setCenter(_chooseDirectory);
    }

    public BorderPane GetBackgroundPane()
    {
        return _backgroundPane;
    }
}
