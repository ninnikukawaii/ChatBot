package console;

import service.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quiz
{
    private Iterator<Question> questionIterator;
    private int totalQuestionsCount = 0;
    private int correctAnswersCount = 0;

    public Quiz(String questionsFileName) throws IOException {
        String questionsFilePath = FileProcessor.getFilePath(questionsFileName);
        questionIterator = new ItemsGenerator<>(parseQuestions(questionsFilePath)).iterator();
    }

    public static List<Question> parseQuestions(String filePath) throws IOException {
        ArrayList<Question> questions = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = br.readLine()) != null) {
            String[] lineParts = line.split("~");
            if (lineParts.length != 2)
                throw new IllegalArgumentException();
            questions.add(new Question(lineParts[0], lineParts[1]));
        }

        return questions;
    }

    public boolean hasNextQuestion() {
        return questionIterator.hasNext();
    }

    public Question getNextQuestion() {
        totalQuestionsCount += 1;
        return questionIterator.next();
    }

    public void incrementCorrectAnswersCount() {
        correctAnswersCount += 1;
    }

    public String getScore() {
        return "Твой счет: " + correctAnswersCount + " из " + (totalQuestionsCount - 1);
    }
}
