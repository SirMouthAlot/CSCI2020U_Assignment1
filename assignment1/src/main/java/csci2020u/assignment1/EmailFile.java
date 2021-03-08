package csci2020u.assignment1;

import java.util.ArrayList;
import java.util.List;


public class EmailFile 
{

    private String _fileName;
    private String _actClass;

    private List<String> _words;

    public String GetFileName()
    { 
        return _fileName; 
    }
    public String GetActClass()
    { 
        return _actClass; 
    }

    public List<String> GetWords() 
    { 
        return _words; 
    }

    public EmailFile(String fileName, String actClass)
    {
        //Filename
        _fileName = fileName;
        //Actual class
        _actClass = actClass;

        //Words in the email
        _words = new ArrayList<>();
    }

}