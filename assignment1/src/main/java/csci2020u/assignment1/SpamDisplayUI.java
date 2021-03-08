package csci2020u.assignment1;

import java.util.*;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public class SpamDisplayUI 
{
    //The main panel for the spam display
    private BorderPane _backgroundPane;

    //Spam filter scrolling
    private Pane _spamFilterPane;

    //Grid for the labels
	private GridPane _labelGrid;
    //Accuracy label
    private Label _accuracy;
    //Accuracy textfield
    private Label _accuracyText;
    //Precision label
    private Label _precision;
    //Precision text
    private Label _precisionText;

    public SpamDisplayUI(String folderPath)
    {
        //Set up the background Grid object
        _backgroundPane = new BorderPane();

        //Set up the spam filter scroll pane
        _spamFilterPane = new Pane();
        
        ProbabilityMappingAlgorithm probMapFiles = new ProbabilityMappingAlgorithm(folderPath);

        //Easy Table View Setup
		List<String> columnNames = List.of("File", "Actual Class", "Spam Probability");
		List<TypeFormats> columnFormats = List.of(TypeFormats.STRING, TypeFormats.STRING, TypeFormats.DOUBLE);
		List<String> dataPropertyNames = List.of("fileName", "actualClass", "spamProbability");

		//Creates an easy table view, with columns named as above with the types as above that resizes by itself
		EasyTableView<TestFile> spamOrHamFileView = new EasyTableView<>(columnNames, columnFormats, probMapFiles.GetTestFiles(),
																				dataPropertyNames, true);

		//Makes it so the table resizes with the background pane
		spamOrHamFileView.BindPrefWidthProperty(_spamFilterPane.widthProperty());
		spamOrHamFileView.BindPrefHeightProperty(_spamFilterPane.heightProperty());

        //Sets the table scroll to the content of our table
		_spamFilterPane.getChildren().add(spamOrHamFileView.GetTable());

        //Add to background grid on top row
		_backgroundPane.setCenter(_spamFilterPane);

        //Set up precision grid
        _labelGrid = new GridPane();
		//Set up row constraints
		RowConstraints precisionRow1 = new RowConstraints();
		precisionRow1.setPercentHeight(50);
		RowConstraints precisionRow2 = new RowConstraints();
		precisionRow2.setPercentHeight(50);
		//Set up label grid
		_labelGrid.getRowConstraints().addAll(precisionRow1, precisionRow2);

		//Set up label grid
		_labelGrid.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

		Background labelBackground = new Background(new BackgroundFill(Color.GREENYELLOW, null, null));

		//Add labels
		_accuracy = new Label("Accuracy:   ");
		_labelGrid.add(_accuracy, 0, 0);
		_accuracy.setBackground(labelBackground);
	    _accuracyText = new Label(String.valueOf(Math.round(probMapFiles.GetAccuracy() * 10000.0d)/100.0d) + "%  ");        //Set this value to the accuracy percent
		_accuracyText.setBackground(labelBackground);
		_labelGrid.add(_accuracyText, 1, 0);
		_precision = new Label("Precision:   ");
		_precision.setBackground(labelBackground);
		_labelGrid.add(_precision, 0, 1);
		_precisionText = new Label(String.valueOf(Math.round(probMapFiles.GetPrecision() * 10000.0d)/100.0d) + "%  ");      //Set this value to the precision percent
		_precisionText.setBackground(labelBackground);
		_labelGrid.add(_precisionText, 1, 1);
        
        //Add to background grid on bottom row
        _backgroundPane.setBottom(_labelGrid);
    }

	public BorderPane GetBackgroundPane()
    {
        //Get background grid
        return _backgroundPane;
    }
}
