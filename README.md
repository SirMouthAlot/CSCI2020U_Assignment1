CSCI2020U_Assignment1

Kevin Lounsbury 100654226
Nicholas Juniper 100659791

Project Information:
This project is a spam email detector. It uses one directory that contains a train folder, for the program to learn the difference between spam and ham, and a test folder that has both spam and ham folders to test if the program is accurate at finding spam and ham. With the current train and test email files, we are able to achieve 90% accuracy and 99.38% percision.

It was counted as "accurate" if the program decided above 50% that a spam file was spam, or if it decided that a not spam file wasn't spam with a <50% probability. For example, if it gave a spam file >50% probability, it was a true positive, if it gave a not spam file >50% probability it was a false positive, if it gave a non spam file a <50% probability it was a true negative.
ScreenShot of program running here: https://cdn.discordapp.com/attachments/798225909739421697/818524068012883978/unknown.png

Improvements Made:
Our improvements to the model include making it so the numbers aren't whole. The way the logarithm at the end works is such that you shouldn't give it a whole number. When you give it a whole number you end up with a log of 0. log(0) is an undefined number. 

In the event that a word is not included in the HamMap, but is included in the SpamMap, this would make the equation evaluate to: (spamMap% / spamMap% + 0) which equals 1.0, this is an issue because it means we're getting log(0) now when trying to calculate the nValue. So what we did instead was have edge cases, if the word doesn't exist in HamMap but does exist in SpamMap, the probability of that word being spam is 0.99, as it is nearly 100% that that word is spam, and, if the word doesn't exist in SpamMap but does exist in HamMap, the probability of that word being spam is 0.000001, as it is nearly 0% that that word is spam.

These approximations ended up making our system produce far less:
Undefined Numbers (NaN)
Infinite Numbers (POSITIVE_INFINITY or NEGATIVE_INFINITY)
False Positives (Where the system assigned a probability of >50% to a ham file)

And led to a much more accurate and precise rating for our algorithm.

As for improvements made to the UI, we simply changed the UI to use a borderpane so that the accuracy and precision information could be presented as a colored footer. We also added the ability to resize the window and have it properly resize the items within allowing you to see more items on screen at once! The initial file selection screen does not allow you to resize as we saw no point to having that screen resizable as it would only increase the amount of blank space.

How to Run (step-by-step):
Step 1: Make sure you are using java11 or later
Step 2: Have Gradle properly installed and configured
Step 3: Open into ./assignment1 
Step 4: Run command 'gradle run'
Step 5: Wait for program to open, then click 'Choose Directory' button
Step 6: Choose the /data folder that contains the train and test folders and wait for the program to parse the files.
Step 7: Scroll through the table to see all information.

Referenced Resources:
Referenced Java documentation: 
https://docs.oracle.com/en/java/javase/11/
Referenced JavaFX documentation: 
https://docs.oracle.com/javase/8/javafx/api/toc.htm

All code submitted is original.


