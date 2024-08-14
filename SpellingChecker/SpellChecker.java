/**
 * The SpellChecker class is responsible for checking the spelling of words in an input string
 * by utilizing a Trie-based dictionary. It checks each word in the input against the dictionary
 * and highlights misspelled words in red, along with suggesting the closest correct word in blue.
 * Additionally, it provides ANSI escape codes for color formatting.
 * 
 * @author Meetkumar
 * @since 2023-09-20
 */

package SpellingChecker;

public class SpellChecker {
    private static Trie dictionary;

    /**
     * Initializes the SpellChecker with a Trie-based dictionary.
     *
     * @param trie The Trie containing the dictionary of correctly spelled words.
     */
    public SpellChecker(Trie trie) {
        SpellChecker.dictionary = trie;
    }

    // ANSI escape codes for setting colors while printing words
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_Pink = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * Checks the spelling of words in the input string and prints the result.
     *
     * @param input The input string to be spell-checked.
     */
    public void check(String input) {
        // Splitting the input string into words
        String[] words = input.split("\\s+");
        // Printing the spell checked words
        printCheckedTrie(words);
    }

    /**
     * Prints the spell-checked words along with the closest correct word.
     *
     * @param words An array of words to be spell-checked.
     */
    public static void printCheckedTrie(String[] words) {
        int m = 0;
        for (String word : words) {
            // Checking if the word exists in the dictionary
            if (!dictionary.search(word)) {
                // If the word doesn't exist, print the word in red
                System.out.print(ANSI_Pink + word + " " + ANSI_RESET);
                m++;
            } else {
                // If the word exists, print the word in blue
                System.out.print(ANSI_BLUE + word + " " + ANSI_RESET);
            }
        }
        System.out.println();
        if (m == 0)
            System.out.println("No mistakes, you're good.");
        else {
            // Printing the header for the possible corrections for misspelled words
            System.out.println(ANSI_YELLOW + "**Possible Corrections for misspelled words**" + ANSI_RESET);
            // Iterating through each word in the input
            for (String word : words) {
                // Checking if the word exists in the dictionary
                if (!dictionary.search(word)) {
                    // If the word doesn't exist, print the misspelled word in red along with the
                    // closest correct word in blue
                    System.out.println(ANSI_Pink + word + ANSI_RESET + " --> " + ANSI_BLUE + dictionary.getClosest(word)
                            + ANSI_RESET);
                }
            }
        }
    }
}
