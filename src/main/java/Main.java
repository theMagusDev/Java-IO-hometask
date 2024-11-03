import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file;
        try {
            file = FrequencyAnalyzer.getInputFileFromUser(scanner);
        } catch (InputStreamClosedException e) {
            System.out.println(e.getMessage());
            return;
        }

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

        try {
            FrequencyAnalyzer.exportAnalysisResults(frequencyDictionary, scanner);
        } catch (InputStreamClosedException e) {
            System.out.println(e.getMessage());
        }
    }
}
