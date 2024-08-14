
/**
 * The SpellCheckerMain class serves as the main entry point for the SpellCheckerUsingTrie program.
 * It reads words from a dictionary file, inserts them into a Trie data structure, and then allows
 * the user to input strings to check for spelling errors.
 * 
 * @author Meetkumar
 * @version 1.2
 * @since 2023-09-20
 */
package SpellingChecker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// import SpellingChecker.Formating.Displayer;

// This is the Main class of the SpellCheckerUsingTrie program

public class SpellCheckerMain {

    /**
     * Reads words from a file and inserts them into a Trie data structure.
     *
     * @param file The file containing the dictionary of words.
     * @return A Trie containing the words from the dictionary.
     * @throws FileNotFoundException If the dictionary file is not found.
     */
    public static Trie filetotrie(File file) throws FileNotFoundException {
        Trie trie = new Trie();
        Scanner s = new Scanner(file);
        // Read the file line by line and insert each word into the Trie
        while (s.hasNextLine()) {
            String line = s.nextLine();
            trie.insert(line);
        }
        s.close();
        // Print a message indicating that the reading of the file is done
        System.out.println("Done reading dictionary");
        return trie;
    }

    /**
     * Main method to run the Spelling Checker application.
     *
     * @throws FileNotFoundException If the dictionary file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {

        String indigoColor = "\u001B[36m";
        String bold = "\u001B[1m";
        String reset = "\u001b[0m";
        File file = new File(
                "D:\\Java Projects\\Java-projects\\SpellingChecker\\Dictionary.txt");

        // Create a Trie and insert words from the dictionary file into it
        Trie trie = filetotrie(file);
        SpellChecker spellChecker = new SpellChecker(trie);
        System.out.println(indigoColor + bold);
        System.out.print("Enter the string to be checked (Characters only): ");
        System.out.print(reset);

        // Create a scanner to read user input
        try (Scanner scanner = new Scanner(System.in)) {
            String line;
            // Keep looping until the user enters 'q'
            while ((line = scanner.nextLine()) != null) {

                if (line.equals("q")) {
                    break;
                }
                // Call the check method of the SpellChecker to check the entered string
                spellChecker.check(line);
                System.out.print("Enter another string or ('q' for exit): ");
            }
        }
    }
}
