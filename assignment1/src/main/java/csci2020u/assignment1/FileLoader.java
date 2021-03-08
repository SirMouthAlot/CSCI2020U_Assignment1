package csci2020u.assignment1;
import java.io.*;
import java.util.*;

public class FileLoader 
{
    private List<EmailFile> _files;
    private Map<String, Integer> _wordFileCounter;

    public FileLoader() 
    {
        _files = new ArrayList<>();

        _wordFileCounter = new TreeMap<>();
    }

    public void Load(String filePath, String actClass) 
    {
        if (filePath == null)
        {
            System.err.println("Usage: java WordCounter <inputDir> <outfile>");
            System.exit(0);
        }

        File dataDir = new File(filePath);

        try 
        {
            ParseFile(dataDir, actClass);
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        FileCounter();
    }

    private void ParseFile(File file, String actClass) throws IOException
    {
        //Is the file we've called a directory?
        if (file.isDirectory()) 
        {
            //Recursively parse all files in folder
            File[] fileList = file.listFiles();
            for (File current : fileList) 
            {
                ParseFile(current, actClass);
            }
        }
        else
        {
            //Create new EmailFile object using the filename
            EmailFile emailFile = new EmailFile(file.getName(), actClass);

            //Scan through the file
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext())
            {
                //Gets next string to whitespace
                String token = scanner.next();
                
                //Check if the next token is a word & if the emailFile doesn't already contain it
                if (IsValidWord(token) && !(emailFile.GetWords().contains(token)))
                {
                    emailFile.GetWords().add(token);
                }
            }
            //Close the scanner
            scanner.close();

            //Add the email file to the list
            _files.add(emailFile);
        }

    }

    private boolean IsValidWord(String word)
    {
        String allLetters = "^[a-zA-Z]+$";

        // returns true if the word is composed by only upper or lowercase letters otherwise returns false;
        return word.matches(allLetters);
    }

    private void FileCounter() 
    {
        for (EmailFile mail : _files) 
        {
            for (String word : mail.GetWords())
            {
                if (_wordFileCounter.containsKey(word)) 
                {
                    int prev = _wordFileCounter.get(word);
                    prev += 1;
                    _wordFileCounter.put(word, prev);
                }
                else 
                {
                    _wordFileCounter.put(word, 1);
                }
            }
        }
    }

    public List<EmailFile> GetFiles()
    {
        return _files;
    }

    public Map<String, Integer> GetWordFileCounter()
    {
        return _wordFileCounter;
    }
}
