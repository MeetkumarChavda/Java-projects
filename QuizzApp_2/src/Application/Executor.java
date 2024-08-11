package Application;

import java.util.Scanner;
import DataStrucures.LinkedList;

/**
 * Manages the quiz execution, including question handling, user input, and
 * display.
 */
public class Executor {
    private static int questionCount;
    static int trueCount = 0;
    static int falseCount = 0;
    private final static String welcomeMsg = "Welcome to the MyQuizz express =^_^=, where innovation and smiles meet at every stop!";
    private final static String welcomeMsg2 = "Heyyy.... !" + "\nReady to experience the uniqueness of MyQuizz!";
    private final static String massage = "Let's, Start Synking ";
    private final static String[] helpline = { "reduceOptions", "showCorrectAnswer", "callAFriend", "Hint" };
    private static int[] hLUseTime = { 0, 0, 0, 0 };// 0-reduceOptions,1-showCorrectAnswer,2-callAFriend,3-hint;
    private static boolean reduceOptionsMagic = false;
    private static boolean correctAnsMagic = false;
    private static boolean hintMagic = false;
    private static String[] userAnswer = new String[10];

    private final static String[] questions = {

            " \nWho opened the first school for girls in India?",
            " \nHow many years did it take to build the Taj Mahal?",
            " \nHow many bones does a human adult have?",
            " \nWho designed the periodic table?",
            " \nWho is the current Governor of the Reserve Bank of India?",
            " \nWhat does the ozone layer protect us from?",
            " \nAt what temperature is the density of water at its maximum?",
            " \nWhich gas is also known as laughing gas?",
            " \nWho wrote 'The Jungle Book'?",
            " \nWhen is National Farmer's Day celebrated in India?"

    };
    private static LinkedList<String> questionList = new LinkedList<>();
    private final static String[] options = {
            "(A). Savitribai Phule(b). Captain Prem Mathu (c). Vijaya Lakshmi Pandit(d). Tessy Thomas ",
            "(A). 20 Years        (b). 10 Years           (c). 25 Years             (d). 30 Years  ",
            "(A). 195             (b). 206                (c). 300                  (d). 208 ",
            "(A). Adverd jener    (b). Robort Hoock       (c). Dmitri Mendeleev     (d). Robort kecin ",
            "(A). Y. V. Reddy     (b). Raghuram Rajan     (c). Urjit Patel          (d). Shaktikanta Das ",
            "(A). X-Rays          (b). I-R Radiation      (c). UV rays              (d). Nothig ",
            "(A). 6-degree celsius(b). 0-degree celsius   (c). 100-degree celsius   (d). 4-degree celsius ",
            "(A). Nitrous DiOxide (b). Nitrous Oxide      (c). Nitrous TriOxide     (d). Nitrous Sulphate ",
            "(A). Rudyard Vipling (b). Audyard Siplong    (c). Yard Kong            (d). Rudyard Kipling ",
            "(A). 23 December     (b). 23 Octomber        (c). 22 November          (d). 22 December "
    };
    private static LinkedList<String> optionList = new LinkedList<>();
    private final static String[] redusceOptions = {
            "A). Savitribai Phule  (C). Vijaya Lakshmi Pandit  ",
            "A). 20 Years          (D). 30 Years  ",
            "A). 195               (B). 206  ",
            "C). Dmitri Mendeleev  (D). Robort kecin  ",
            "A). Y. V. Reddy       (D). Shaktikanta Das  ",
            "B). I-R Radiation     (C). UV rays  ",
            "A). 6-degree celsius  (D). 4-degree celsius  ",
            "B). Nitrous Oxide     (D). Nitrous Sulphate  ",
            "C). Yard Kong         (D). Rudyard Kipling  ",
            "A). 23 December       (B). 23 Octomber  "
    };
    private static LinkedList<String> redusceOptionList = new LinkedList<>();

    private final static String[] answers = { "A", "A", "B", "C", "D", "C", "D", "B", "D", "A" };
    private static LinkedList<String> answerList = new LinkedList<>();

    private final static String[] hints = {
            "Belonging from Maharashtra",
            "The artisans and laborers involved in the building of the iconic mausoleum, Taj Mahal, dedicated a significant portion of their lives to the project, which lasted for two score fortnights.",
            "The human skeletal system is a marvel of nature, comprising of a precise number of bones that is just over two hundred",
            "The individual who is credited with the design of the periodic table was a Russian chemist who lived during the 19th century.",
            "The current Governor of the Reserve Bank of India has previously held the position of Deputy Governor in the same institution and has also worked for the International Monetary Fund (IMF).",
            "The ozone layer serves as a shield against a type of electromagnetic radiation that has a wavelength shorter than that of visible light and is harmful to living organisms on Earth.",
            "The temperature at which water is most dense is not the same as its freezing or boiling point.",
            "This colorless and non-flammable gas, which has a slightly sweet odor and taste, is also used as a propellant in aerosol cans and whipped cream dispensers.",
            "The author who wrote the collection of stories known as 'The Jungle Book' was a British author who was born in India during the 19th century.",
            "National Farmer's Day is an annual observance in India that celebrates the contributions of farmers and agriculture to the country's economy and society, and it is typically celebrated during a harvest season.",
    };
    private static LinkedList<String> hintList = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static LinkedList<String> menu = new LinkedList<>();
    static {

        menu.add("Add Questions ");
        menu.add("Remove Questions ");
        menu.add("Attempt Quizz ");
        menu.add("Print Questions");

    }
    // For formating
    final static String reset = "\u001b[0m";
    final static String bold = "\u001B[1m";
    final static String underLine = "\u001B[4m";
    final static String redColor = "\u001b[31m";
    final static String greenColor = "\u001b[32m";
    final static String yellowColor = "\u001b[33m";
    final static String blueColor = "\u001b[34m";
    final static String purpleColor = "\u001B[35m";
    final static String indigoColor = "\u001B[36m";
    final static String QuestionPrint = indigoColor + bold;
    final static String QuestionTag = "\n" + underLine + bold + blueColor;
    final static String answerTag = "\n" + underLine + bold + greenColor;
    // final static String brightBlack = "\u001B[90m";
    // final static String brightRed = "\u001B[91m ";
    // final static String brightBlue = "\u001B[94m";
    // final static String brightWhite = "\u001B[97m";

    /**
     * Executes the quiz by displaying the welcome message, adding questions, and
     * starting the application.
     */
    protected static void executeQuiz() {

        Executor.welcomeDisplay();
        Executor.addQuestions();
        Executor.startApplication();

    }

    private static void welcomeDisplay() {
        final Displayer display = new Displayer();
        display.ColorfulTypewriterBlinkingDisplay("Hello ! ");
        display.colorFullDisplayer(welcomeMsg);
        display.typerdDisplay(welcomeMsg2);
        display.blinkingDisplay(massage);
        display.clearConsole_();
    }

    /**
     * Adds question details to respective lists.
     * 
     * @param question       The quiz question.
     * @param option         The options for the question.
     * @param reducedOptions The reduced options.
     * @param answer         The correct answer.
     * @param hint           The hint for the question.
     */
    // Note
    /*
     * // answerList.add(bold + greenColor + answer);
     * we cannot do answerList.add(bold + greenColor + answer); becouse at the
     * answer comparing time the answer A or B or .... come with asci like this
     * and comparison get false so do not do anything
     * 
     */
    private static void listAdder(String question, String option, String reducedOptions, String answer, String hint) {
        questionList.add(QuestionPrint + question + reset);
        optionList.add(yellowColor + option + reset);
        redusceOptionList.add(bold + greenColor + reducedOptions + reset);
        answerList.add(answer);
        hintList.add(bold + purpleColor + hint + reset);
    }

    private static void addQuestions() {

        // in this Method we add Questions from Array to into LinkedList
        for (int i = 0; i < questions.length; i++) {

            listAdder(questions[i], options[i], redusceOptions[i], answers[i], hints[i]);

        }

    }

    private static void startApplication() {
        String choice;
        do {
            Executor.menuPrinter();
            System.out.print("Enter Choice : ");
            choice = scanner.nextLine();
            Executor.operationHandalar(choice);
        } while (true);
    }

    /**
     * Handles menu operations based on user input.
     * 
     * @param input User choice from the menu.
     */
    private static void operationHandalar(String input) {
        Executor executor = new Executor();
        switch (input) {
            case "1": {
                System.out.print("Enter Question : ");
                String question = "\n" + scanner.nextLine();
                System.out.println();
                System.out.print("Enter Options : ");
                String options = "\n" + scanner.nextLine();
                System.out.println();
                System.out.print("Enter Reduced Options : ");
                String reducedOptions = "\n" + scanner.nextLine();
                System.out.println();
                System.out.print("Enter Answer : ");
                String answer = scanner.nextLine();
                System.out.print("Enter Hint : ");
                String hint = "\n" + scanner.nextLine();

                listAdder(question, options, reducedOptions, answer, hint);
                break;
            }
            case "2": {
                System.out.println("Enter index : ");
                executor.removeQuestion(Integer.parseInt(scanner.nextLine()) - 1);
                System.out.println(questionList);
                break;
            }
            case "3": {
                executor.startQuiz();
                break;
            }
            case "4": {
                executor.printQuestions();
                break;
            }
            case "0": {
                new Displayer().byeMassage();
                break;
            }
            default: {
                System.out.println("Enter value between  0 to 4 !");
                break;
            }
        }
    }

    /**
     * Removes a question and its associated data from the Customlists by index.
     * 
     * @param index The index of the question to remove.
     */
    public void removeQuestion(int index) {

        if (index < 0 || questionList.size() < index) {
            System.out.println("Wrong index !");
            return;
        }
        questionList.removeAtIndex(index);
        optionList.removeAtIndex(index);
        redusceOptionList.removeAtIndex(index);
        answerList.removeAtIndex(index);
        hintList.removeAtIndex(index);
    }

    private void printQuestions() {
        for (int i = 0; i < questionList.size(); i++) {
            System.out.println(questionList.get(i));
            System.out.println(optionList.get(i));
            // System.out.println(redusceOptionList.get(i));
            // System.out.println(answerList.get(i));
            // System.out.println(hintList.get(i));
        }
        System.out.println(
                redColor + "\nWant to see(also print) the options , answers , hint chekOut printQuestions() class Executor--->218");
    }

    /**
     * Starts the quiz, presenting questions and collecting user answers.
     */
    private void startQuiz() {
        String line = new Displayer().line;
        while (true) {
            System.out.println(line + "\nWelcome to the Quiz !\n");
            System.out.println(line
                    + "\nRules : \n1]. 10 questions all questions are mendatory\n2]. Give answers in apropriate options \n3]. Use H for using helpline \n4]. You have only 4 helplines \n5]. One helpline can use only one time\n"
                    + line);
            System.out.println("Agree ? : (y/n)");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("Y")) {
                attendingQuix();
            } else if (choice.equalsIgnoreCase("N")) {
                System.out.println("Thank you visit again!");
                System.exit(0);
            } else {
                System.out.println("Invalid choice!");
            }
            System.exit(0);
        }

    }

    /**
     * Conducts the quiz, processes answers, and applies helplines if needed.
     */
    private void attendingQuix() {

        String temp;
        for (questionCount = 0; questionCount < questionList.size() && questionCount < 10; questionCount++) {

            questionAndOptionPrint(questionCount);
            do {
                System.out.print("Answer :");
                userAnswer[questionCount] = new String();
                userAnswer[questionCount] = scanner.next();
                temp = userAnswer[questionCount];
                // System.out.println(answerList.get(questionCount));
            } while (!userAnswerCheck(temp));
            if (userAnswer[questionCount].equalsIgnoreCase(answerList.get(questionCount))) {
                ++trueCount;
                System.out.println("Points: " + trueCount);
            } else if (userAnswer[questionCount].equalsIgnoreCase("H")) {
                Executor.helplines(questionCount);
                questionCount--;
            } else {
                System.out.println("Points: " + trueCount);
            }
        }
        Executor.resultPrint();
        trueCount = 0;

    }

    /**
     * Prints the result of the quiz based on the user's score.
     */
    private static void resultPrint() {
        System.out.println(new Displayer().line);
        if (trueCount > 7) {
            System.out.println(
                    "Your score : " + trueCount + "\n Excelent performance !\n You should check your tini mistakes.");
        } else if (trueCount <= 7 && trueCount >= 4) {
            System.out.println(
                    "Your score : " + trueCount + "\n Average performance !\n You should go throungh the solutions.");
        } else {
            System.out.println(
                    "Your score : " + trueCount + "\n Very poor performance !\n You have to lern all the solutions.");
        }

    }

    /**
     * Prints the question and options based on the question count.
     * Provides additional features like hints, reduced options, or correct answers
     * based on flags.
     * 
     * @param questionCount Index of the question to print.
     */
    private void questionAndOptionPrint(int questionCount) {
        String line = new Displayer().line;
        // To print Question
        try {
            System.out.println(QuestionTag + "Question:" + reset);
            System.out.println(questionList.get(questionCount));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(redColor + "We are out of questions! bye!");
            System.exit(0);
        }
        System.out.println(line);

        // To print options
        System.out.println(yellowColor + underLine + bold + "Options :" + reset);
        if (reduceOptionsMagic) {
            // to print reduceOptions
            System.out.println(optionList.get(questionCount));
            System.out.print(line + bold + underLine + greenColor + "\nreduced Options :" + reset);
            System.out.println(redusceOptionList.get(questionCount) + "\n" + line);
            reduceOptionsMagic = false;
        } else if (correctAnsMagic) {
            // to print correct answers
            System.out.println(optionList.get(questionCount));
            System.out.print(line + bold + underLine + greenColor + "\n correct option :" + reset);
            System.out.println(answerList.get(questionCount) + "\n" + line);
            correctAnsMagic = false;
        } else if (hintMagic) {
            // to give a hint
            System.out.println(optionList.get(questionCount));
            System.out.println(bold + underLine + purpleColor + "hint " + reset);
            System.out.println(hintList.get(questionCount) +
                    "\n" + reset + line);
            hintMagic = false;
        } else { // normal
            System.out.println(optionList.get(questionCount));
        }
    }

    /**
     * Validates if the user input is a valid answer or helpline choice.
     * 
     * @param temp User input.
     * @return True if the input is valid; otherwise, false.
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
     * Displays available helplines and processes the user's choice.
     * 
     * @param questionCount The index of the question for which the helpline is
     *                      requested.
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
        Executor.helplineSelection(questionCount);
    }

    static String helplineChoice;

    /**
     * Prompts the user to select a helpline and executes it.
     * 
     * @param questionCount The index of the question for which the helpline is
     *                      requested.
     */
    private static void helplineSelection(int questionCount) {
        do {
            do {
                System.out.print("helpline choice : ");
                helplineChoice = scanner.next();
                if (helplineChoice.equalsIgnoreCase("Back")) {
                    return;
                }
            } while (helplineChoiceValidation());
            if (hLUseTime[Integer.parseInt(helplineChoice) - 1] == 0) {
                helplineExicution(questionCount);
                break;
            } else {
                System.out.println("This helpline is Already Used sir!");
                System.out.println("Enter other Helpline ! or Write back to return !");
            }
        } while (true);
    }

    /**
     * Validates if the helpline choice is valid.
     * 
     * @return True if the choice is invalid; otherwise, false.
     */
    private static boolean helplineChoiceValidation() {
        boolean isValidHelpline = helplineChoice.equals("1") || helplineChoice.equals("2") ||
                helplineChoice.equals("3") || helplineChoice.equals("4");

        return !isValidHelpline;
    }

    /**
     * Executes the selected helpline based on user choice.
     * 
     * @param questionCount The index of the question for which the helpline is
     *                      used.
     */
    private static void helplineExicution(int questionCount) {
        // int temp = Integer.parseInt(helplineChoice);
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

    /**
     * Prints the menu to the console with borders and options.
     * The menu items are fetched from the `menu` list, and various formatting
     * elements are used to create a visually appealing menu layout.
     */
    private static void menuPrinter() {

        Displayer displayer = new Displayer();
        String miniLine = displayer.miniLine;
        String redVerticalSplash = displayer.redVerticalSplash;
        String line = displayer.line;
        String border = redVerticalSplash + "     " + spaceAdder(miniLine, "") + "" + redVerticalSplash;
        String Exit = redVerticalSplash + " " + 0 + "). " + spaceAdder(miniLine, "Exit") + "" + redVerticalSplash;
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
        System.out.println(Exit);
        System.out.println(border);
        System.out.println(" " + miniLine + "");
    }

    /**
     * Adds appropriate spaces to a word to maintain the borders of the menu.
     * 
     * @param givenLine The line used for determining the space length.
     * @param word      The word to be padded with spaces.
     * @return The word with spaces added to fit the menu's border layout.
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
