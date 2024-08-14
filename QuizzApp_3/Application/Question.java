/**
 * The Question class represents a quiz question with various attributes.
 * It includes the question text, options, correct answer, category, and difficulty level.
 * This class also provides methods for retrieving question details and generating formatted output.
 *
  @author MeetKumar
 * @since 2023-09-27
 */
package QuizzApp_3.Application;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Question {
    private String question;
    private LinkedList<String> options;
    private String correctAnswer;
    private String category;
    private String difficultyLevel;

    // Just for formatting purpose
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
    String QuestionTag = "\n" + underLine + bold + blueColor;
    String QuestionPrint = "\n\n \t ' " + indigoColor + bold;
    String optionTag = "\n" + underLine + bold + yellowColor;
    String answerTag = "\n" + underLine + bold + greenColor;
    String answer = "\n\n\t" + brightBlack + bold;
    String category_ = " " + purpleColor;
    String categoryTag = "\n" + underLine + bold + purpleColor;
    String diffLevelTag = "\n" + underLine + bold + redColor;
    String diffLevel = " " + brightRed;

    // Constructor
    public Question(String question, LinkedList<String> options, String correctAnswer, String category,
            String difficultyLevel) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficultyLevel = difficultyLevel;
    }

    // Getters
    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    /**
     * Retrieves one randomly selected incorrect option from the list of options.
     * This method shuffles the list of options, removes the correct answer,
     * and returns a randomly chosen incorrect option. If no incorrect options are
     * available,
     * it returns null.
     *
     * @return A randomly selected incorrect option, or null if none are available.
     */

    public String getOneRandomIncorrectOption() {
        List<String> incorrectOptions = new LinkedList<>(options);

        incorrectOptions.remove(correctAnswer);
        Collections.shuffle(incorrectOptions);

        return incorrectOptions.isEmpty() ? null : incorrectOptions.get(0);
    }

    // Setters
    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions(LinkedList<String> options) {
        this.options = options;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * Generates a formatted string representation of the question.
     *
     * @return A formatted string containing question details.
     */
    @Override
    public String toString() {
        return " " + QuestionTag + "Question" + reset + " :" +
                QuestionPrint + question + reset + " ' \n" +
                optionTag + "Options" + reset + " :" + "\n" +
                brightWhite + bold + "\n\t " + options.get(0) + "\n\t " + options.get(1) +
                "\n\t " + options.get(2) + "\n\t " + options.get(3) + reset + "\n"
                + answerTag + "Correct Answer" + reset + " :" +
                answer + correctAnswer + reset + "\n" +
                categoryTag + "Category" + reset + " :" +
                category_ + category + reset + "\n" +
                diffLevelTag + "Difficulty Level" + reset + " :" +
                diffLevel + difficultyLevel + reset + "\n" +
                " ";
    }

}
