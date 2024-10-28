import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter the file name (with full path if not in program's folder):");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        File file = new File(path);
        while (!file.exists()) {
            System.out.println("Invalid path or file name, enter correctly:");
            path = scanner.nextLine();
            file = new File(path);
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

        System.out.println("Enter output file, into which program should write the result:");
        path = scanner.nextLine();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);
            for (HashMap.Entry<Character, Long> entry : frequencyDictionary.entrySet()) {
                fileWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            System.out.println("Successfully wrote the result into file!");
        } catch (IOException e) {
            System.err.println("Error: file can not be opened or created for writing. Message: " + e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error closing output file, message: " + e.getMessage());
                }
            }
        }
    }
}
