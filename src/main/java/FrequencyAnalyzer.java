import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FrequencyAnalyzer {

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

        boolean successfullyRead = false;
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
