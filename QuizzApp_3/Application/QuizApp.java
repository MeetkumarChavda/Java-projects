/**
 * The QuizApp class serves as the entry point for the Quiz Application.
 * It extends the `QA_Executor` class and provides the `main` method to start the quiz.
 * 
 * To run the quiz, it calls the executeQuiz method inherited from QA_Executor.
 * 
 * Note: The main method is declared without any arguments.
 * To execute the application, you should use a method signature like `public static void main(String[] args)`.
 * 
 * @author Meetkumar
 * @since 2023-09-24
 */

package QuizzApp_3.Application;

public class QuizApp extends QA_Executor {
    /**
     * The main method to start the Quiz Application.
     * It calls the `executeQuiz` method inherited from `QA_Executor` to begin the
     * quiz.
     * 
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        executeQuiz();
    }
}