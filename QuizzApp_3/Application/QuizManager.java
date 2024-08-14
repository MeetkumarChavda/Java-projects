/**
 * The `QuizManager` class manages the core functionality of the Quiz Application.
 * It handles questions, user interactions, and various game mechanics.
 *
 * The class includes attributes for managing game state, user responses, and formatting.
 * It also initializes collections for questions by category and difficulty levels.
 *
 * Note: This class might benefit from additional methods to organize its functionality.
 *
 * @author Meetkumar
 * @since 2023-09-031
 */

package QuizzApp_3.Application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import QuizzApp_3.Formating.Displayer;

public class QuizManager {
    private static int questionCount;
    static int trueCount = 0;
    static int falseCount = 0;
    private final static String[] helpline = { "reduceOptions", "showCorrectAnswer", "callAFriend", "Hint" };
    private static int[] hLUseTime = { 0, 0, 0, 0 };// 0-reduceOptions,1-showCorrectAnswer,2-callAFriend,3-hint;
    private static boolean reduceOptionsMagic = false;
    private static boolean correctAnsMagic = false;
    private static boolean hintMagic = false;
    private static String[] userAnswer = new String[10];
    String redColor = "\u001b[31m";
    String brightRed = "\u001B[91m ";
    String greenColor = "\u001b[32m";
    String blueColor = "\u001b[34m";
    String indigoColor = "\u001B[36m";
    String purpleColor = "\u001B[35m";
    String bold = "\u001B[1m";
    String underLine = "\u001B[4m";
    String brightBlue = "\u001B[94m";
    String brightWhite = "\u001B[97m";
    String brightBlack = "\u001B[90m";
    String reset = "\u001b[0m";
    String yellowColor = "\u001b[33m";

    private static final Scanner scanner = new Scanner(System.in);
    private static LinkedList<Question> allQuestions = new LinkedList<>();
    private static HashMap<String, LinkedList<Question>> questionsByCategory = new HashMap<>();
    private static HashMap<String, LinkedList<Question>> questionsByDifficulty = new HashMap<>();
    private static LinkedList<Question> geography = new LinkedList<>();
    private static LinkedList<Question> literature = new LinkedList<>();
    private static LinkedList<Question> science = new LinkedList<>();
    private static LinkedList<Question> history = new LinkedList<>();
    private static LinkedList<Question> art = new LinkedList<>();
    private static LinkedList<Question> philosophy = new LinkedList<>();
    private static LinkedList<Question> mathematics = new LinkedList<>();
    private static LinkedList<Question> dataStructures = new LinkedList<>();
    private static LinkedList<Question> java = new LinkedList<>();
    private static LinkedList<Question> multithreading = new LinkedList<>();
    private static LinkedList<Question> easy = new LinkedList<>();
    private static LinkedList<Question> medium = new LinkedList<>();
    private static LinkedList<Question> hard = new LinkedList<>();
    private static LinkedList<Question> veryHard = new LinkedList<>();
    private final String[] categoryArray = {
            "Geography",
            "Java",
            "Art",
            "Literature",
            "Multithreading",
            "Data Structures",
            "Science",
            "Mathematics",
            "History",
            "Philosophy"
    };
    private final String[] diffLevelArray = {
            "Easy",
            "Medium",
            "Hard",
            "Very Hard"
    };
    static int i = 0;

    /**
     * Adds a single question to the quiz using the public-facing method.
     * This method is a public interface for adding questions and delegates
     * to the private `addQuestion` method.
     *
     * @param question The question to be added to the quiz.
     */

    public void addQuestions(Question question) {
        addQuestion(question);
    }

    /**
     * Adds a single question to the quiz.
     * This method adds the question to the general question pool, as well as
     * categorizes it based on its category and difficulty level.
     *
     * @param question The question to be added to the quiz.
     */
    private void addQuestion(Question question) {

        allQuestions.add(question);
        addByCategory(question);
        addByDifficultyLevel(question);
        System.out.println("Question Added !" + i++);
    }

    private void addByCategory(Question question) {
        switch (question.getCategory()) {
            case "Geography": {
                geography.add(question);
                questionsByCategory.put("Geography", geography);
                break;
            }
            case "Literature": {
                literature.add(question);
                questionsByCategory.put("Literature", literature);
                break;
            }
            case "Science": {
                science.add(question);
                questionsByCategory.put("Science", science);
                break;
            }
            case "History": {
                history.add(question);
                questionsByCategory.put("History", history);
                break;
            }
            case "Art": {
                art.add(question);
                questionsByCategory.put("Art", art);
                break;
            }
            case "Philosophy": {
                philosophy.add(question);
                questionsByCategory.put("Philosophy", philosophy);
                break;
            }
            case "Mathematics": {
                mathematics.add(question);
                questionsByCategory.put("Mathematics", mathematics);
                break;
            }
            case "Data Structures": {
                dataStructures.add(question);
                questionsByCategory.put("Data Structures", dataStructures);
                break;
            }
            case "Java": {
                java.add(question);
                questionsByCategory.put("Java", java);
                break;
            }
            case "Multithreading": {
                multithreading.add(question);
                questionsByCategory.put("Multithreading", multithreading);
                break;
            }
            default: {
                // NOTHING HAPPEN HERE
            }

        }
    }

    /**
     * Categorizes a question by its category and adds it to the respective category
     * list.
     * This method categorizes the question based on its category and stores it in a
     * specific
     * category list. It also updates the questionsByCategory map with the
     * categorized questions.
     *
     * @param question The question to be categorized and added.
     */

    private void addByDifficultyLevel(Question question) {
        switch (question.getDifficultyLevel()) {
            case "Easy":
                easy.add(question);
                questionsByDifficulty.put("Easy", easy);
                break;
            case "Medium":
                medium.add(question);
                questionsByDifficulty.put("Medium", medium);
                break;
            case "Hard":
                hard.add(question);
                questionsByDifficulty.put("Hard", hard);
                break;
            case "Very Hard":
                veryHard.add(question);
                questionsByDifficulty.put("Very Hard", veryHard);
                break;
            default:
                // Nothing to do here
        }
    }

    /**
     * Helper method for user selection from a list of options.
     * This method displays a list of options to the user, accepts their choice,
     * and validates that the choice is within the valid range of options.
     *
     * @param options An array of options from which the user can choose.
     * @return The selected option as a string.
     */

    private String selectionHelper(String[] options) {

        System.out.println("Here are your Choices :");
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + 1 + "). " + options[i]);
        }
        int choice = 0;
        boolean isValid = false;

        do {
            System.out.print("Enter choice : ");
            choice = Integer.parseInt(scanner.next());
            isValid = choice <= options.length && choice > 0;
        } while (!isValid);

        return options[choice - 1];
    }

    /**
     * Retrieves a list of questions based on the specified category.
     * This method takes a category as input and returns a linked list of questions
     * that belong to the specified category from the internal storage.
     *
     * @param category The category for which questions are to be retrieved.
     * @return A linked list of questions in the specified category, or null if the
     *         category is not found.
     */
    private LinkedList<Question> getQuestionsByCategory(String category) {
        return questionsByCategory.get(category);
    }

    /**
     * Retrieves a list of questions based on the specified difficulty level.
     * This method takes a difficulty level as input and returns a linked list of
     * questions
     * that match the specified difficulty level from the internal storage.
     *
     * @param difficulty The difficulty level for which questions are to be
     *                   retrieved.
     * @return A linked list of questions with the specified difficulty level, or
     *         null if the level is not found.
     */

    private LinkedList<Question> getQuestionsByDifficulty(String difficulty) {
        return questionsByDifficulty.get(difficulty);
    }

    /**
     * Retrieves a list of questions in a random order.
     * This method takes a linked list of questions as input and returns a new
     * linked list
     * containing the same questions but arranged in a random order.
     *
     * @param linkedList The original list of questions to be shuffled.
     * @return A new linked list of questions in a random order.
     */

    private LinkedList<Question> getRandomQuestions(LinkedList<Question> linkedList) {

        // Create a copy of the original list to avoid modifying it directly.
        LinkedList<Question> result = new LinkedList<>(linkedList);
        Random random = new Random();
        int size = result.size();

        // Shuffle the list by randomly swapping elements.
        for (int swap = 0; swap < 1000; swap++) {
            int i = random.nextInt(size);
            int j = random.nextInt(size);

            // Swap elements at indices i and j
            Question temp = result.get(i);
            result.set(i, result.get(j));
            result.set(j, temp);
        }
        return result;
    }

    /**
     * Handles user input and controls the response to user choices.
     * This method repeatedly prompts the user to enter a choice and then delegates
     * the choice to the appropriate action based on the input.
     */
    protected static void responseController() {
        String choice;
        do {
            System.out.print("Enter Choice : ");
            choice = scanner.next();

        } while (choice == null);
        // scanner.nextLine();
        new QuizManager().responseController(choice);
    }

    /**
     * Controls the application's response based on user input.
     * This method takes the user's input and performs actions accordingly,
     * such as starting the quiz, displaying a farewell message, or returning
     * to the main quiz menu.
     *
     * @param inpute The user's choice input.
     */
    private void responseController(String inpute) {

        switch (inpute) {

            case "1": {
                // Start the quiz with questions based on selected categories.
                startQuiz(getQuestionsByCategory(selectionHelper(categoryArray)));
                break;
            }
            case "2": {
                // Start the quiz with questions based on selected difficulty levels.
                startQuiz(getQuestionsByDifficulty(selectionHelper(diffLevelArray)));
                break;
            }
            case "3": {
                // Start a random quiz.
                startQuiz(getRandomQuestions(allQuestions));
                break;
            }
            case "4": {
                // Display a farewell message and exit the application.
                new Displayer().byeMassage();
                System.exit(0);
                break;
            }
            default: {
                // Return to the main quiz menu for any other input.
                QA_Executor.executeQuiz();
                break;
            }
        }
    }

    /**
     * Initiates and manages the quiz session.
     * This method sets up the quiz session, displays rules to the user,
     * and prompts the user to agree or disagree to start the quiz.
     *
     * @param questions The list of questions for the quiz session.
     */
    private void startQuiz(LinkedList<Question> questions) {
        try (Scanner myScanner = new Scanner(System.in)) {
            while (true) {
                System.out.println(new Displayer().line + "\nWelcome to the Quiz !\n");
                System.out.println(new Displayer().line
                        + "\nRules : \n1]. 10 questions all questions are mendatory\n2]. Give answers in apropriate options \n3]. Use H for using helpline \n4]. You have only 4 helplines \n5]. One helpline can use only one time\n"
                        + new Displayer().line);
                System.out.print("Agree ? : (y/n)");
                String choice = myScanner.next();
                if (choice.equalsIgnoreCase("Y")) {
                    attendingQuix(questions);
                } else if (choice.equalsIgnoreCase("N")) {
                    System.out.println("Thank you visit again!");
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice!");
                }
                System.exit(0);
            }
        }
    }

    /**
     * Manages the user's participation in the quiz.
     * This method handles the quiz session, including displaying questions,
     * accepting user answers, validating answers, and tracking scores.
     *
     * @param questions The list of questions for the quiz session.
     */
    private void attendingQuix(LinkedList<Question> questions) {
        try (Scanner scanner_ = new Scanner(System.in)) {
            String temp;
            for (questionCount = 0; questionCount < questions.size() && questionCount < 10; questionCount++) {

                questionAndOptionPrint(questionCount, questions);
                do {
                    System.out.print("Answer :");
                    userAnswer[questionCount] = new String();
                    userAnswer[questionCount] = scanner_.next();
                    temp = userAnswer[questionCount];
                } while (!userAnswerCheck(temp));

                if (userAnswer[questionCount]
                        .equalsIgnoreCase(questions.get(questionCount).getCorrectAnswer().charAt(0) + "")) {
                    // Increment the score if the user's answer is correct.
                    ++trueCount;

                } else if (userAnswer[questionCount].equalsIgnoreCase("H")) {
                    // Handle the case when the user uses a helpline.
                    QuizManager.helplines(questionCount);
                    questionCount--;
                } else {
                    // Handle the case when the user's answer is incorrect.
                }
                System.out.println("Answer:" + questions.get(questionCount).getCorrectAnswer());
                System.out.println("Points:" + trueCount);
            }
            scanner_.close();
        }
        QuizManager.resultPrint();
        // Reset the trueCount for the next quiz session.
        trueCount = 0;
    }

    /**
     * Prints the user's quiz results and performance feedback.
     * This method displays the user's score and provides feedback on their
     * performance
     * based on the score achieved in the quiz.
     */
    private static void resultPrint() {
        System.out.println(new Displayer().line);
        if (trueCount > 7) {
            System.out.println(
                    "Your score : " + trueCount + "\n Excelent performance !\n You should chcek your tini mistakes.");
        } else if (trueCount <= 7 && trueCount >= 4) {
            System.out.println(
                    "Your score : " + trueCount + "\n Average performance !\n You should go throungh the solutions.");
        } else {
            System.out.println(
                    "Your score : " + trueCount + "\n Very poor performance !\n You have to lern all the solutions.");
        }

    }

    /**
     * Prints the question and options to the user.
     * This method displays the current question and its options to the user.
     * It also handles special cases like displaying reduced options, correct
     * answers,
     * and providing hints based on certain conditions.
     *
     * @param questionCount The count of the current question being displayed.
     * @param questions     The list of questions for the quiz session.
     */
    private void questionAndOptionPrint(int questionCount, LinkedList<Question> questions) {
        String line = new Displayer().line;
        // To print Question
        System.out.println(new Displayer().line);
        System.out.println(bold + underLine + blueColor + "Question: " + reset);
        System.out.println(questions.get(questionCount).getQuestion());
        // To print options
        System.out.println(bold + underLine + "Options : " + reset);
        if (reduceOptionsMagic) {
            // Display reduced options if applicable.
            System.out.print(line + bold + underLine + greenColor + "\nreduced Options : " + reset);
            System.out.println(greenColor + questions.get(questionCount).getOneRandomIncorrectOption()
                    + "  " + questions.get(questionCount).getCorrectAnswer() + reset + "\n" + line);
            reduceOptionsMagic = false;
        } else if (correctAnsMagic) {
            // Display correct answer if applicable.
            System.out.print(line + bold + underLine + greenColor + "\n correct option :" + reset);
            System.out.println(greenColor + questions.get(questionCount).getCorrectAnswer() + "\n" + line);
            correctAnsMagic = false;
        } else if (hintMagic) {
            // Provide a hint if applicable.
            System.out.println(bold + underLine + purpleColor + "hint " + reset);
            System.out.println("Think critically and analyze the details provided in the question and options.\n "
                    + "The clues you need to find the right answer are right in front of you" + "\n" + line);
            hintMagic = false;
        } else {
            // Display normal options if none of the above conditions apply.
            System.out.println(questions.get(questionCount).getOptions());
        }
    }

    /**
     * Checks the validity of the user's answer input.
     *
     * @param temp The user's input representing an answer option or action.
     * @return True if the input is valid (A, B, C, D, or H), false otherwise.
     */
    private static boolean userAnswerCheck(String temp) {
        boolean isValidInput = false;
        if (temp.equalsIgnoreCase("A") ||
                temp.equalsIgnoreCase("B") || temp.equalsIgnoreCase("C") ||
                temp.equalsIgnoreCase("D") || temp.equalsIgnoreCase("H")) {
            return !isValidInput;
        }
        return isValidInput;
    }

    /**
     * Displays available helplines to the user and checks their usage status.
     * This method lists the available helplines and indicates whether each one
     * has already been used or is still available based on the usage status.
     *
     * @param questionCount The count of the current question being displayed.
     */
    private static void helplines(int questionCount) {
        System.out.println("Available Helplines are :- ");
        for (int i = 0; i < helpline.length; i++) {
            if (hLUseTime[i] == 0) {
                System.out.println("-->[" + (i + 1) + "]. " + helpline[i]);
            } else {
                if (hLUseTime[0] != 0 && hLUseTime[1] != 0 && hLUseTime[2] != 0 && hLUseTime[3] != 0) {
                    System.out.println("All Helplines Were already used!");
                    break;
                } else {
                    System.out.println("   [x]. " + helpline[i] + " Used.");
                }
            }
        }
        QuizManager.helplineSelection(questionCount);
    }

    private static String helplineChoice;

    /**
     * Allows the user to select a helpline for the current question.
     * This method prompts the user to choose a helpline and validates their choice.
     * It also checks if the selected helpline has already been used and handles
     * the execution of the chosen helpline's action.
     *
     * @param questionCount The count of the current question being displayed.
     */

    private static void helplineSelection(int questionCount) {
        do {
            do {
                System.out.print("helpline choice : ");
                helplineChoice = scanner.next();

                // Allow the user to go back to the previous menu.
                if (helplineChoice.equalsIgnoreCase("Back")) {
                    return;
                }
            } while (helplineChoiceValidation());
            if (hLUseTime[Integer.parseInt(helplineChoice) - 1] == 0) {

                // Execute the selected helpline if it has not been used.
                helplineExicution(questionCount);
                break;
            } else {
                System.out.println("This helpline is Already Used sir!");
                System.out.println("Enter other Helpline ! or Write back to return !");
            }
        } while (true);
    }

    /**
     * Validates the user's helpline choice.
     *
     * @return True if the user's choice is valid (1, 2, 3, or 4), false otherwise.
     */
    private static boolean helplineChoiceValidation() {
        boolean isValidHelpline = helplineChoice.equals("1") || helplineChoice.equals("2") ||
                helplineChoice.equals("3") || helplineChoice.equals("4");

        return !isValidHelpline;
    }

    /**
     * Executes the selected helpline action and updates its usage status.
     *
     * @param questionCount The count of the current question being displayed.
     */
    private static void helplineExicution(int questionCount) {

        switch (helplineChoice) {
            case "1": {
                hLUseTime[0]++;
                reduceOptionsMagic = true;
                break;
            }
            case "2": {
                hLUseTime[1]++;
                correctAnsMagic = true;
                break;
            }
            case "3": {
                Displayer displayer = new Displayer();
                hLUseTime[2]++;
                System.out.println(displayer.line);
                displayer.colorFullDisplayer("\nCalling the friend................................");
                System.out.println("");
                System.out
                        .println("Friend : Aatm Nirbhar bano \n khatam TaTa Bye..Bye..... !\n" + new Displayer().line);
                break;
            }
            case "4": {
                System.out.println("Ask the Audience");
                hLUseTime[3]++;
                hintMagic = true;
                break;
            }
        }
    }

    // Test case to check Whether the info get int the HashMapes or not
    public void printHashedMap() {
        // Case 1
        for (Map.Entry<String, LinkedList<Question>> entry : questionsByDifficulty.entrySet()) {
            System.out.println(entry.getKey());

        }
        // Case 2
        for (Map.Entry<String, LinkedList<Question>> entry : questionsByCategory.entrySet()) {
            System.out.println(entry.getKey());
        }
        // Case 3
        // System.out.println(questionsByCategory.get("Java"));
    }
}

/*
 * Swapping logics
 * for (int i = size - 1; i >= 1; i--) {
 * // Generate a random index between 0 and i (inclusive)
 * int j = random.nextInt(i + 1);
 * // Swap elements at indices i and j
 * Question temp = result.get(i);
 * result.set(i, result.get(j));
 * result.set(j, temp);
 * System.out.println(i + " SWaped with " + j);
 * }
 * for (int i = 1; i < size - 1; i++) {
 * // Generate a random index between 0 and i (inclusive)
 * int j = random.nextInt(i + 1);
 * // Swap elements at indices i and j
 * Question temp = result.get(i);
 * result.set(i, result.get(j));
 * result.set(j, temp);
 * System.out.println(i + " SWaped with " + j);
 * }
 */