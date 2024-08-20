package Security;

import Formating.Displayer;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * The OperationExecutor class is responsible for executing banking operations
 * within the Prime Financial Group application. It serves as the entry point
 * for user interactions and authentication.
 *
 * @author MeetKumar
 * @since 18-09-23
 */

public class OperationExecutor extends LoginController {

    private final static Displayer displayer = new Displayer();
    // private final static Scanner scanner = new Scanner(System.in);

    protected final static LinkedList<String> menu;
    static {
        menu = new LinkedList<>(Arrays.asList("Home", "About Us", "Services",
                "Feedback", "Support", "Logout", "Exit"));
    }
    // private static boolean isLoged = false;
    // private static String userAuthentication;
    // private static int userId;

    public static void executor() {
        while (true) {
            System.out.println(new Displayer().line);
            displayer.menuPrinter(menu);
            new ResponseController().menuExecutor();
            if (!LoginController.isLoged) {
                LoginController.authenticateUser(userlogInResponse());
            }
        }
    }

}
//////////////////////////////////////////////////////////////////////////////////////////////////////
// Logics
// You can check if a given string input is a digit (numeric) or not in Java
// using
// various
// approaches.
// Here
// are
// two
// common
// methods:

// 1. **Using `Character.isDigit()` method:**
// You can use the `Character.isDigit(char ch)` method to check if each
// character in the string is a digit.

// ```java
// public static boolean isNumeric(String str) {
// for (char c : str.toCharArray()) {
// if (!Character.isDigit(c)) {
// return false;
// }
// }
// return true;
// }
// ```

// This method iterates through each character in the string and checks if it's
// a digit.

// 2. **Using Regular Expressions:**
// You can also use regular expressions to check if the entire string is a
// numeric value.

// ```java
// public static boolean isNumeric(String str) {
// return str.matches("\\d+"); // Matches one or more digits
// }
// ```

// This method checks if the string contains one or more digits from start to
// end.

// Here's how you can use these methods:

// ```java
// String input = "12345";
// if (isNumeric(input)) {
// System.out.println("Input is numeric.");
// } else {
// System.out.println("Input is not numeric.");
// }
// ```

// This code will output "Input is numeric." for the input "12345" and "Input is
// not numeric." for non-numeric inputs.