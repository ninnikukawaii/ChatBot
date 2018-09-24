package console;

import service.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Quiz
{
    private ItemsGenerator<Question> questionsGenerator;
    private AnswerProcessor answerProcessor;

    public Quiz(String questionsFileName,
                InputStream inputStream,
                OutputStream outputStream) throws IOException {

        String questionsFilePath = FileProcessor.getFilePath(questionsFileName);
        questionsGenerator = new ItemsGenerator<>(parseQuestions(questionsFilePath));
        answerProcessor = new AnswerProcessor(inputStream, outputStream);
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

    public void startQuiz() throws IOException {

        for (Question question: questionsGenerator) {
            answerProcessor.incrementTotalQuestionsCount();
            answerProcessor.writeLine(question.getQuestion());
            if (answerProcessor.processAnswer(question))
                break;
        }

        answerProcessor.writeLine("Приятно было поиграть с тобой!");
    }
}
