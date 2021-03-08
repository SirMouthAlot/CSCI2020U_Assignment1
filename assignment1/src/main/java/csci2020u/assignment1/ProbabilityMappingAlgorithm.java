package csci2020u.assignment1;

import java.util.*;

public class ProbabilityMappingAlgorithm 
{
    private FileLoader _trainHamLoader;
    private FileLoader _trainSpamLoader;
    private FileLoader _testHamLoader;
    private FileLoader _testSpamLoader;

    private Map<String, Double> _probMap = new TreeMap<>();

    private List<TestFile> _testFiles;

    private int _numTruePositives;
    private int _numTrueNegatives;
    private int _numFalsePositives;
    private int _numFilesGuessed;

    public ProbabilityMappingAlgorithm(String folderPath)
    {
        Initialize(folderPath);
    }

    public void Initialize(String folderPath) 
    {
        _testFiles = new ArrayList<>();

        _trainHamLoader = new FileLoader();
        _trainSpamLoader = new FileLoader();

        _testHamLoader = new FileLoader();
        _testSpamLoader = new FileLoader();

        Map<String, Double> probWordIsSpam;
        Map<String, Double> probWordIsHam;

        _trainHamLoader.Load(folderPath + "/train/ham", "ham");
        _trainSpamLoader.Load(folderPath + "/train/spam", "spam");

        _testHamLoader.Load(folderPath + "/test/ham", "ham");
        _testSpamLoader.Load(folderPath + "/test/spam", "spam");

        probWordIsHam = GetProbabilities(_trainHamLoader);
        probWordIsSpam = GetProbabilities(_trainSpamLoader);

        CreateProbMap(probWordIsSpam, probWordIsHam);

        FilesSpamProb(_testHamLoader, false);
        FilesSpamProb(_testSpamLoader, true);
    }

    private Map<String, Double> GetProbabilities(FileLoader fileLoader) 
    {
        Set<String> keys = fileLoader.GetWordFileCounter().keySet();
        Iterator<String> keyIterator = keys.iterator();

        Map<String, Double> tempProbMap = new TreeMap<>();

        while (keyIterator.hasNext()) 
        {
            String key = keyIterator.next();

            double probability;

            int numOfFilesContainWord = fileLoader.GetWordFileCounter().get(key);
            int numOfFiles = fileLoader.GetFiles().size();

            probability = (double)numOfFilesContainWord / (double) numOfFiles;

            tempProbMap.put(key, probability);
        }
        return tempProbMap;
    }

    private void CreateProbMap(Map<String, Double> spamMap, Map<String, Double> hamMap) 
    {
        //create a list off all of the words(ham and spam)
        List<String> allWords = new ArrayList<>();

        _probMap.clear();

        Set<String> keys = spamMap.keySet();
        Iterator<String> keyIterator = keys.iterator();

        while (keyIterator.hasNext()) 
        {
            String key = keyIterator.next();

            if(!allWords.contains(key))
            {
                allWords.add(key);
            }
        }
        keys = hamMap.keySet();
        keyIterator = keys.iterator();

        while (keyIterator.hasNext()) 
        {
            String key = keyIterator.next();

            if(!allWords.contains(key))
            {
                allWords.add(key);
            }
        }

        for(String key : allWords)
        {
            if (spamMap.containsKey(key) && hamMap.containsKey(key))
            {
                _probMap.put(key, (double)spamMap.get(key) / ((double) spamMap.get(key) + (double)hamMap.get(key)));
            }
            else if(spamMap.containsKey(key))
            {
                _probMap.put(key, 0.9999d);
            }
            else 
            {
                _probMap.put(key, 0.00001d);
            }
        }
    }

    private void FilesSpamProb(FileLoader fileLoader, boolean spam) 
    {
        int truePositives = 0;
        int trueNegatives = 0;
        int falsePositives = 0;

        for(EmailFile emailFile : fileLoader.GetFiles()) 
        {
            double nValue = 0d;
            for(String key : emailFile.GetWords())
            {
                if(_probMap.containsKey(key)) 
                {
                    nValue += (Math.log(1 - _probMap.get(key)) - Math.log(_probMap.get(key)));
                }
            }

            double probability = 1 / (1 + Math.pow(Math.E, nValue));
            //Rounds to 3 decimal places
            double roundedProb = Math.round(probability * 1000.0) / 1000.0;

            //If spam and probability greater than 50%
                //This is a true positive
            if ((spam && probability >= 0.5d))
            {
                truePositives++;
            }
            //If not spam and probability less than 50%
                //This is a true negative
            else if((!spam && probability <= 0.5d))
            {
                trueNegatives++;
            }
            //If not spam and probability is greater than 50%
                //this is a false positive
            else if ((!spam && probability >= 0.5d))
            {
                falsePositives++;
            }

            TestFile testFile = new TestFile(emailFile.GetFileName(), roundedProb, emailFile.GetActClass());
            _testFiles.add(testFile);
        }
        //Adds to number of true positives
        _numTruePositives += truePositives;
        //Adds to number of true negatives
        _numTrueNegatives += trueNegatives;
        //Adds to number of false positives
        _numFalsePositives += falsePositives;
        //Adds to total number of files guessed
        _numFilesGuessed += fileLoader.GetFiles().size();
    }

    public List<TestFile> GetTestFiles()
    {
        return _testFiles;
    }

    public double GetAccuracy()
    {
        return (((double)_numTruePositives + (double)_numTrueNegatives) / (double)_numFilesGuessed);
    }

    public double GetPrecision()
    {
        return ((double)_numTruePositives / ((double)_numFalsePositives + (double)_numTruePositives));
    }

}
