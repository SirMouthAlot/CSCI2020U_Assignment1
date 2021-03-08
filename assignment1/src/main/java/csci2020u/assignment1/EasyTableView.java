package csci2020u.assignment1;

import java.util.*;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/////****Format Information****\\\\\
// %f      Float
// %i      Int
// %d      Double
// %s      String

public class EasyTableView<T>
{
    private TableView<T> _tableView = new TableView<>();

    public EasyTableView(List<String> columnNames, List<TypeFormats> columnFormats, List<T> items, 
                            List<String> dataPropertyNames, boolean resizeWithTable)
    {
        //Creates the columns
        for (int i = 0; i < columnNames.size(); i++)
        {
            switch(columnFormats.get(i))
            {
                // Adds Float column
                case FLOAT:
                    TableColumn<T, Float> temp1 = new TableColumn<>(columnNames.get(i));
                    _tableView.getColumns().add(temp1);
                    break;
                // Adds Int column
                case INT:
                    TableColumn<T, Integer> temp2 = new TableColumn<>(columnNames.get(i));
                    _tableView.getColumns().add(temp2);
                    break;
                // Adds Double column
                case DOUBLE:
                    TableColumn<T, Double> temp3 = new TableColumn<>(columnNames.get(i));
                    _tableView.getColumns().add(temp3);
                    break;
                // Adds String column
                case STRING:
                    TableColumn<T, String> temp4 = new TableColumn<>(columnNames.get(i));
                    _tableView.getColumns().add(temp4);
                    break;
                default:
                    //Do nothing
                    break;
            }
        }

        //Sets columns to resize equally with table
        if (resizeWithTable)
        {
            for (int i = 0; i < _tableView.getColumns().size(); i++)
            {
                _tableView.getColumns().get(i).prefWidthProperty().bind(_tableView.widthProperty().multiply(1.0 
                                                                            / _tableView.getColumns().size()));
            }
        }

        //Converts the data to an observable list
        ObservableList<T> tableData = FXCollections.observableArrayList(items);

        //Sets up the cell value factories
        for (int i = 0; i < _tableView.getColumns().size(); i++)
        {
            _tableView.getColumns().get(i).setCellValueFactory(
                new PropertyValueFactory<>(dataPropertyNames.get(i))
            );
        }

        //Set the table data
        _tableView.setItems(tableData);
    }

    public void BindPrefWidthProperty(ReadOnlyDoubleProperty widthProperty)
    {
        _tableView.prefWidthProperty().bind(widthProperty);
    }

    public void BindPrefHeightProperty(ReadOnlyDoubleProperty heightProperty)
    {
        _tableView.prefHeightProperty().bind(heightProperty);
    }
    
    public TableView<T> GetTable()
    {
        return _tableView;
    }
}
