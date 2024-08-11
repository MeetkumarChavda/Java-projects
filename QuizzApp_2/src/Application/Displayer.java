package Application;

/*
 * The Displayer class provides various methods to display messages to the console
 * with colorful and animated effects. It includes features like typewriter effects,
 * blinking messages, and loading animations. The class supports color customization
 * and provides utility functions for clearing the console and waiting between actions.
 * 
 * Author: Meetkumar Chavda
 * Date: August 2024
 * Version: 1.0
 * 
 * Key Methods:
 * - ColorfulTypewriterBlinkingDisplay: Displays a message with a typewriter effect and blinking.
 * - colorFullDisplay: Shows a message with a colorful loading animation.
 * - typerdDisplay: Displays a message with a typewriter effect.
 * - blinkingDisplay: Displays a message with a blinking effect.
 * - byeMassage: Displays a farewell message before exiting the application.
 * - clearConsole: Clears the console screen.
 * - wait: Pauses execution for a specified duration.
 */

public class Displayer {

    public final String line = "\u001B[32m"
            + "_________________________________________________________________________________________________________________________________________________"
            + "\u001B[0m";
    public final String miniLine = "\u001B[31m" + "_________________________________________" + "\u001B[0m";
    public final String redVerticalSplash = "\u001B[31m" + "|" + "\u001B[0m";

    public void ColorfulTypewriterBlinkingDisplay(String iString) {
        ColorfulTypewriterBlinkingMessage(iString);
    }

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

    public void typerdDisplay(String iString) {
        typerdPrint(iString);
    }

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

    public void blinkingDisplay(String iString) {
        blinkingPrint(iString);
    }

    private static void printMessage(String message) {
        System.out.print("\u001B[32m"); // Set color to green
        System.out.println(message);
        System.out.print("\u001B[0m"); // Reset color
    }

    // Clears the console screen
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void clearConsole_() {
        clearConsole();
    }

    // Pause execution for a specified duration
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

}
