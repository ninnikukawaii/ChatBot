package service.quiz;

import service.IOManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quiz
{
    private ItemsGenerator<Question> questionGenerator;
    private Iterator<Question> questionIterator;
    private int totalQuestionsCount = 0;
    private int correctAnswersCount = 0;

    private Quiz(List<Question> questions) {
        questionGenerator = new ItemsGenerator<>(questions);
        questionIterator = questionGenerator.iterator();
    }

    public static Quiz createQuiz(String questionsFileName) throws QuizParsingException {
        String filePath = IOManager.getFilePath(questionsFileName);
        ArrayList<Question> questions = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split("~");
                if (lineParts.length != 2)
                    throw new QuizParsingException("Ошибка в файле вопросов!");
                questions.add(new Question(lineParts[0], lineParts[1]));
            }
        }
        catch (IOException ex) {
            throw new QuizParsingException("Файл вопросов не найден!", ex);
        }

        return new Quiz(questions);
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

    public ItemsGenerator<Question> getQuestionGenerator() { return questionGenerator; }
}
