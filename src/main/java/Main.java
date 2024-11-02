import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = FrequencyAnalyzer.getInputFileFromUser();

        HashMap<Character, Long> frequencyDictionary;
        try {
            frequencyDictionary = FrequencyAnalyzer.calculateLettersFrequency(file);
        } catch (FileNotFoundException e) {
            System.err.println("File has not been found, message: " + e.getMessage());
            return; // no use in following writing, source file was not found
        } catch (IOException e) {
            System.err.println("Error occurred while reading a file. Details: " + e.getMessage());
            return; // no use in following writing, frequency calculation failed
        }

        FrequencyAnalyzer.exportAnalysisResults(frequencyDictionary);
    }
}
