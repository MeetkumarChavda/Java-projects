/**
 * The QuestionFileParser class is responsible for parsing quiz questions from a text file.
 * It reads the questions, options, correct answers, category, and difficulty level
 * from a specified file and constructs Question objects.
 * 
 * @author MeetKumar
 * @version 2.10
 * @since 2023-09-29
 */

package QuizzApp_3.Application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class QuestionFileParser {

    /**
     * Parses questions from a text file, constructs Question objects,
     * and adds them to the quiz manager.
     */
    private void questionAddAndPrinter() {

        QuizManager quizManager = new QuizManager();
        LinkedList<Question> questions = parseQuestionsFromFile();
        for (Question question : questions) {
            System.out.println(question);
            // --> if we want to check question added or not
            quizManager.addQuestions(question);
        }
    }

    /**
     * Initiates the question parsing process and adds questions to the quiz
     * manager.
     */
    public void questionAdder() {
        new QuestionFileParser().questionAddAndPrinter();
    }

    /**
     * Parses questions from a text file and returns a list of Question objects.
     *
     * @return A list of parsed Question objects.
     */
    private static LinkedList<Question> parseQuestionsFromFile() {
        LinkedList<Question> questionList = new LinkedList<>();

        // Specify the file path for the questions file
        String fileName = "D:\\Java Projects\\Java-projects\\QuizzApp_3\\Content\\Questions.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String correctAnswer = null;
            String category = null;
            String difficultyLevel = null;
            StringBuilder questionText = new StringBuilder();
            LinkedList<String> options = new LinkedList<>();
            boolean isQuestionBlock = false;
            boolean isOptionBlock = false;

            while ((line = reader.readLine()) != null) {

                line = line.trim();
                if (line.startsWith("**Question:**")) {
                    isQuestionBlock = true;
                    questionText = new StringBuilder();
                    questionText.append(extractContent(line, "**Question:**").trim());
                } else if (line.startsWith("**Options:**")) {
                    isOptionBlock = true;
                    options = new LinkedList<>();
                } else if (line.startsWith("**Correct Answer:**")) {
                    isQuestionBlock = false;
                    correctAnswer = extractContent(line, "**Correct Answer:**").trim();
                } else if (line.startsWith("**Category:**")) {
                    category = extractContent(line, "**Category:**").trim();
                } else if (line.startsWith("**Difficulty Level:**")) {
                    difficultyLevel = extractContent(line, "**Difficulty Level:**").trim();
                    isQuestionBlock = false;
                    Question question = new Question(
                            questionText.toString(),
                            options,
                            correctAnswer,
                            category,
                            difficultyLevel);
                    questionList.add(question);
                } else {
                    if (!isQuestionBlock) {
                        questionText.append(" ").append(line.trim());
                    } else if (isOptionBlock && !line.trim().isEmpty()) {
                        options.add(line.trim());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return questionList;
    }

    /**
     * Extracts content from a line by removing a specified marker.
     *
     * @param line   The line containing the content.
     * @param marker The marker to remove from the line.
     * @return The extracted content.
     */
    private static String extractContent(String line, String maker) {
        return line.substring(maker.length()).trim();
    }

}
