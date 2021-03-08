CSCI2020U_Assignment1

Kevin Lounsbury 100654226
Nick Juniper 100

Project Information:
This project is a spam email detector. It uses one directory that contains a train folder, for the program to learn the difference between spam and ham, and a test folder that has both spam and ham folders to test if the program is accurate at finding spam and ham. With the current train and test email files, we are able to achieve 90% accuracy and 99.38% percision.

It was counted as "accurate" if the program decided above 50% that a spam file was spam, or if it decided that a not spam file wasn't spam with a <50% probability. For example, if it gave a spam file >50% probability, it was a true positive, if it gave a not spam file >50% probability it was a false positive, if it gave a non spam file a <50% probability it was a true negative.
ScreenShot of program running here: https://cdn.discordapp.com/attachments/798225909739421697/818524068012883978/unknown.png

Improvements Made:
Our improvements to the model include…

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


