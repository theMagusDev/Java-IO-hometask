import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class FrequencyAnalyzer {

    /**
     * Method to get input file path from user.
     * @return A {@code File} object, representing user's input file.
     */
    static File getInputFileFromUser() {
        System.out.println(
                "Enter the file name (leave empty to use default). \n"
                        + "Note: if the file is not in program's root folder, enter a full path."
        );
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        if (path.isEmpty()) {
            path = "src/main/resources/java_description.txt";
        }
        File file = new File(path);
        while (!file.exists()) {
            System.out.println("Invalid path or file name, enter correctly:");
            path = scanner.nextLine();
            file = new File(path);
        }

        scanner.close();

        return file;
    }

    /**
     * Method to get destination file path from user and export frequency analysis results into this file.
     * @param frequencyDictionary Frequency analysis resulting dictionary of type {@code HashMap<Character, Long>}
     */
    static void exportAnalysisResults(HashMap<Character, Long> frequencyDictionary) {
        System.out.println("Enter output file, into which program should write the result:");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        try (FileWriter fileWriter = new FileWriter(path)) {
            for (HashMap.Entry<Character, Long> entry : frequencyDictionary.entrySet()) {
                fileWriter.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            System.out.println("Successfully wrote the result into file!");
        } catch (IOException e) {
            System.err.println("Error: file can not be opened or created for writing. Message: " + e.getMessage());
        }

        scanner.close();
    }

    /**
     * @param file file path
     * @return frequency dictionary with type {@code HashMap<Character, Long>}
     * @throws FileNotFoundException if file path is invalid.
     * @throws IOException if some unknown error occurred while reading a file.
     */
    static HashMap<Character, Long> calculateLettersFrequency(File file) throws FileNotFoundException, IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File " + file.getName() + " does not exist.");
        }

        HashMap<Character, Long> frequencyDictionary = new HashMap<Character, Long>();
        try (FileReader fileReader = new FileReader(file);) {
            for (char character = 'a'; character <= 'z'; character++) {
                frequencyDictionary.put(character, 0L);
                frequencyDictionary.put(Character.toUpperCase(character), 0L);
            }
            int character;
            try {
                while ((character = fileReader.read()) != -1) {
                    if (!Character.isLetter((char) character)) {
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
