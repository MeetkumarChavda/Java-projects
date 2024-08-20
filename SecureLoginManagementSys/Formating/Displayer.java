
/**
 * A utility class for text formatting and display.
 */
package Formating;

import java.util.LinkedList;

public class Displayer {

    // ANSI escape codes for setting text colors and styles
    // (These codes are used for text color formatting)
    public String redColor = "\u001b[31m";
    public String brightRed = "\u001B[91m ";
    public String greenColor = "\u001b[32m";
    public String blueColor = "\u001b[34m";
    public String indigoColor = "\u001B[36m";
    public String purpleColor = "\u001B[35m";
    public String bold = "\u001B[1m";
    public String underLine = "\u001B[4m";
    public String brightBlue = "\u001B[94m";
    public String brightWhite = "\u001B[97m";
    public String brightBlack = "\u001B[90m";
    public String reset = "\u001b[0m";
    public String yellowColor = "\u001b[33m";

    public final String line = "\u001B[32m"
            + "_________________________________________________________________________________________________________________________________________________"
            + "\u001B[0m";
    public final String miniLine = "\u001B[31m" + "_________________________________________" + "\u001B[0m";
    public final String redVerticalSplash = "\u001B[31m" + "|" + "\u001B[0m";

    /**
     * Displays a welcome message with colorful formatting.
     */
    public void WelcomeDisplay() {

        System.out.println(line);
        wait(2000);
        ColorfulTypewriterBlinkingMessage("Hello !");
        String welcomeMsg = "Welcome to the AppSynk express =^_^=, where innovation and smiles meet at every stop!";
        colorFullDisplay(welcomeMsg);
        String welcomeMsg2 = "Heyyy..Synkers !" + "\nReady to experience the uniqueness of AppSynk!";
        typerdDisplay(welcomeMsg2);
        String massage = "Let's, Start Synking ";
        blinkingDisplay(massage);
        clearConsole();

    }

    /**
     * Displays a colorful typewriter-style blinking message to the console.
     *
     * @param message The message to display.
     */
    public void ColorfulTypewriterBlinkingDisplay(String iString) {
        ColorfulTypewriterBlinkingMessage(iString);
    }

    /**
     * Displays a colorful typewriter-style blinking message with a specified typing
     * speed and blink duration.
     *
     * @param input         The message to display.
     * @param typingSpeed   The typing speed in milliseconds per character.
     * @param blinkDuration The duration in milliseconds for the blinking effect.
     */
    private void ColorfulTypewriterBlinkingMessage(String input) {
        final String message = input;
        final int typingSpeed = 200; // Milliseconds per character
        final int blinkDuration = 500; // Milliseconds for blinking effect
        clearConsole();
        printTypewriterBlinkingMessage(message, typingSpeed, blinkDuration);

    }

    private static void printTypewriterBlinkingMessage(String message, int typingSpeed, int blinkDuration) {

        final String[] colors = {
                "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m"
        };

        for (int i = 0; i < message.length(); i++) {
            clearConsole();
            String color = colors[i % colors.length];
            System.out.print(color);
            System.out.print(message.substring(0, i + 1));
            System.out.print("\u001B[0m"); // Reset color
            wait(typingSpeed);

            clearConsole();
            wait(typingSpeed);
        }

        for (int i = 0; i < 2; i++) { // Blink for 2 cycles
            clearConsole();
            System.out.print("\u001B[31m"); // Red color
            System.out.print(message);
            System.out.print("\u001B[0m"); // Reset color
            wait(blinkDuration);
            clearConsole();
            wait(blinkDuration);
        }
    }

    /**
     * Prints a colorful typewriter-style blinking message to the console.
     *
     * @param message       The message to display.
     * @param typingSpeed   The typing speed in milliseconds per character.
     * @param blinkDuration The duration in milliseconds for the blinking effect.
     */
    private void colorFullDisplay(String input) {

        final String message = input;
        int loadingTime = 500; // Milliseconds for each animation frame

        final String[] loadingFrames = {
                "   ", ".  ", ".. ", "...", " ..", "  .", "   ", "   ", ".  ", ".. ", "...", " ..", "  ."
        };

        final String[] colors = {
                "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m"
        };

        System.out.println("\u001B[0m"); // Reset color
        System.out.println("Loading....");
        try {
            for (int i = 0; i < loadingFrames.length; i++) {
                System.out.print(colors[i % colors.length]);
                System.out.print("\r" + loadingFrames[i] + " " + message);
                Thread.sleep(loadingTime);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\u001B[0m"); // Reset color

    }

    public void colorFullDisplayer(String iString) {
        colorFullDisplay(iString);
    }

    /**
     * Prints a message character by character with a typing effect.
     *
     * @param input The message to be displayed with the typing effect.
     */
    private void typerdPrint(String input) {

        final String message = input;
        final int typingSpeed = 100; // Milliseconds for each character typing
        System.out.println("\u001B[0m");
        clearConsole();

        for (int i = 0; i < message.length(); i++) {

            System.out.print(message.charAt(i));
            wait(typingSpeed);
        }
        System.out.println();

    }

    /**
     * Displays a message with a typing effect.
     *
     * @param iString The message to be displayed with the typing effect.
     */
    public void typerdDisplay(String iString) {
        typerdPrint(iString);
    }

    /**
     * Displays a message with a blinking effect by alternating between displaying
     * and hiding the message.
     *
     * @param input The message to be displayed with the blinking effect.
     */
    private void blinkingPrint(String input) {
        final String message = input;
        int blinkingInterval = 100; // Milliseconds for each animation frame

        clearConsole();

        for (int i = 0; i < 6; i++) {
            clearConsole();
            wait(blinkingInterval);
            printMessage(message);
            wait(blinkingInterval);
        }
    }

    /**
     * Displays a message with a blinking effect.
     *
     * @param iString The message to be displayed with the blinking effect.
     */
    public void blinkingDisplay(String iString) {
        blinkingPrint(iString);
    }

    /**
     * Prints a message with a green color.
     *
     * @param message The message to be printed in green.
     */
    private static void printMessage(String message) {
        System.out.print("\u001B[32m"); // Set color to green
        System.out.println(message);
        System.out.print("\u001B[0m"); // Reset color
    }

    /**
     * Clears the console screen.
     */
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void clearConsole_() {
        clearConsole();
    }

    /**
     * Pauses execution for a specified duration in milliseconds.
     *
     * @param milliseconds The duration to pause execution in milliseconds.
     */
    private static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void byeMassage() {
        final String msg = "Have a colorful and exciting day" + " See you Soon..";
        typerdDisplay(msg);
        System.out.println(line);
        System.exit(0);

    }

    public void waitt() {
        wait(2000);
    }

    public void menuPrinter(LinkedList<String> menu) {
        menu_Printer(menu);
    }

    /**
     * Prints a menu to the console with numbered options.
     *
     * @param menu A linked list containing the menu options to be displayed.
     */
    private static void menu_Printer(LinkedList<String> menu) {

        Displayer displayer = new Displayer();
        String miniLine = displayer.miniLine;
        String redVerticalSplash = displayer.redVerticalSplash;
        String line = displayer.line;
        String border = redVerticalSplash + "     " + spaceAdder(miniLine, "") + "" + redVerticalSplash;
        // String Exit = redVerticalSplash + " " + 0 + "). " + spaceAdder(miniLine,
        // "Exit") + "" + redVerticalSplash;
        System.out.println(line + "\u001B[0m");
        System.out.println(" " + miniLine + "");
        System.out.println(border);
        int tempCounter = 1;
        for (int i = 0; i < menu.size(); i++) {
            String menuString = menu.get(i);
            System.out.println(redVerticalSplash + " " + tempCounter + "). " + spaceAdder(miniLine, menuString)
                    + "" + redVerticalSplash);
            tempCounter++;
        }
        // System.out.println(Exit);
        System.out.println(border);
        System.out.println(" " + miniLine + "");
    }

    /**
     * Adds spaces to a given word to ensure it maintains borders in a menu.
     *
     * @param givenLine The reference line to determine the total length.
     * @param word      The word to be adjusted with spaces.
     * @return A formatted string with added spaces.
     */
    private static String spaceAdder(String givenLine, String word) {

        int lineLength = givenLine.length();
        String space = "";
        String printString = "";
        int index = 0;
        try {
            for (index = 0; index <= lineLength - word.length() - 15; index++) {
                space += " ";
            }
            printString = word + space;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return printString;
    }

}
