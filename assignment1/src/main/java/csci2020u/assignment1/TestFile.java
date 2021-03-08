package csci2020u.assignment1;

import java.text.DecimalFormat;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TestFile 
{
    private SimpleStringProperty fileName;
    private SimpleStringProperty actualClass;
    
    private SimpleDoubleProperty spamProbability;

    public TestFile(String fName, double sProb, String aClass)
    {
        fileName = new SimpleStringProperty(fName);
        actualClass = new SimpleStringProperty(aClass);

        spamProbability = new SimpleDoubleProperty(sProb);
    }

    public String getFileName() 
    {
        return fileName.get();
    }
    public void setFileName(String value)
    {
        fileName.set(value);
    }

    public Double getSpamProbability() 
    {
        return spamProbability.get();
    }
    public void setSpamProbability(double value)
    {
        spamProbability.set(value);
    }

    public String getActualClass() 
    {
        return actualClass.get();
    }
    public void setActualClass(String value) 
    {
        actualClass.set(value);
    }

    public String getSpamProbRounded() 
    {
        DecimalFormat df = new DecimalFormat("0.00000");
        return df.format(spamProbability.get());
    }
}