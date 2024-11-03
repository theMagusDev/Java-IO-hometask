import java.io.*;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FrequencyAnalyzer {

    /**
     * Method to get input file path from user.
     * @param scanner {@code Scanner} object, so new {@code Scanner} instance is not created inside method.
     * @return A {@code File} object, representing user's input file.
     * @throws InputStreamClosedException if scanner's input stream is closed
     */
    static File getInputFileFromUser(Scanner scanner) throws InputStreamClosedException {
        System.out.println(
                "Enter the file name (leave empty to use default). \n"
                        + "Note: if the file is not in program's root folder, enter a full path."
        );
        String path;
        try {
            path = scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new InputStreamClosedException("I/O stream was unexpectedly closed.");
        }
        if (path.isEmpty()) {
            path = "src/main/resources/java_description.txt";
        }
        File file = new File(path);
        while (!file.exists()) {
            System.out.println("Invalid path or file name, enter correctly:");
            path = scanner.nextLine();
            if (path.isEmpty()) {
                path = "src/main/resources/java_description.txt";
            }
            file = new File(path);
        }

        return file;
    }

    /**
     * Method to get destination file path from user and export frequency analysis results into this file.
     * @param frequencyDictionary Frequency analysis resulting dictionary of type {@code HashMap<Character, Long>}
     * @param scanner {@code Scanner} object, so new {@code Scanner} instance is not created inside method.
     * @throws InputStreamClosedException if scanner's input stream is closed
     */
    static void exportAnalysisResults(HashMap<Character, Long> frequencyDictionary, Scanner scanner) throws InputStreamClosedException {
        System.out.println("Enter output file, into which program should write the result (e.g. output.txt):");
        String path = "";
        try {
            path = scanner.nextLine();
            while (path.isEmpty()) {
                System.out.println("Output file path should not be empty!");
                System.out.println("Enter output file, into which program should write the result (e.g. output.txt):");
                path = scanner.nextLine();
            }
        } catch (NoSuchElementException e) {
            throw new InputStreamClosedException("I/O stream was unexpectedly closed.");
        }
        try (FileWriter fileWriter = new FileWriter(path)) {
            for (HashMap.Entry<Character, Long> entry : frequencyDictionary.entrySet()) {
                fileWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            System.out.println("Successfully wrote the result into file!");
        } catch (IOException e) {
            System.err.println("Error: file can not be opened or created for writing. Message: " + e.getMessage());
        }
    }

    /**
     * @param file file path
     * @return frequency dictionary with type {@code HashMap<Character, Long>}
     * @throws FileNotFoundException if file path is invalid.
     * @throws IOException if some unknown error occurred while reading a file.
     */
    static HashMap<Character, Long> calculateLettersFrequency(File file) throws FileNotFoundException, IOException {
        HashMap<Character, Long> frequencyDictionary = new HashMap<Character, Long>();
        for (char character = 'a'; character <= 'z'; character++) {
            frequencyDictionary.put(character, 0L);
            frequencyDictionary.put(Character.toUpperCase(character), 0L);
        }

        if (file == null) {
            return frequencyDictionary; // return empty dict
        }
        if (!file.exists()) {
            throw new FileNotFoundException("File " + file.getName() + " does not exist.");
        }

        try (FileReader fileReader = new FileReader(file)) {
            boolean isLowercaseLetter;
            boolean isUppercaseLetter;
            int character;
            try {
                while ((character = fileReader.read()) != -1) {
                    isLowercaseLetter = (character >= 97 && character <= 122);
                    isUppercaseLetter = (character >= 65 && character <= 90);
                    if (!isLowercaseLetter && !isUppercaseLetter) {
                        continue;
                    }
                    frequencyDictionary.put((char) character, frequencyDictionary.get((char) character) + 1L);
                }
            } catch (IOException e) { // modify the exception message to write, where the error has occurred
                throw new IOException("Exception while reading a file. " + e.getMessage());
            }
        }

        return frequencyDictionary;
    }
}
