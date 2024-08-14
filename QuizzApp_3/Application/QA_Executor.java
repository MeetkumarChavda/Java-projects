/**
 * The QA_Executor class serves as the entry point for the Quiz Application.
 * It is responsible for executing the quiz and initializing the application.
 * This class loads questions, displays a menu for user interaction, and starts the quiz.
 * 
 * @author MeetKumar
 * @since 2023-09-27
 */

package QuizzApp_3.Application;

import java.util.Arrays;
import java.util.LinkedList;

import QuizzApp_3.Formating.Displayer;

public class QA_Executor {

    // private final QuizManager quizManager = new QuizManager();
    private final static Displayer displayer = new Displayer();

    /**
     * Initiates the execution of the quiz application.
     */
    protected static void executeQuiz() {
        QA_Executor.addQuestions();
        QA_Executor.startApplication();

    }

    /**
     * Adds questions to the quiz application using a QuestionFileParser.
     */
    private static void addQuestions() {
        QuestionFileParser qFileParser = new QuestionFileParser();
        qFileParser.questionAdder();
    }

    /**
     * Starts the quiz application by displaying the menu and calling the response
     * controller.
     */
    private static void startApplication() {
        LinkedList<String> menu = new LinkedList<>(
                Arrays.asList("Question by Category ", "Question by Difficulty ", "Random Question", "Exit"));
        displayer.menuPrinter(menu);
        QuizManager.responseController();
    }

}
