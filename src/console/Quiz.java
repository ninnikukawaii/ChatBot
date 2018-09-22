package console;

import service.Commands;
import service.FileProcessor;
import service.ItemsGenerator;
import service.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Quiz
{
    private ItemsGenerator<Question> questionsGenerator;
    private InputStreamReader inputStreamReader;
    private OutputStreamWriter outputStreamWriter;
    private int totalQuestionsCount = 0;
    private int correctAnswersCount = 0;

    public Quiz(String questionsFileName,
                InputStream inputStream,
                OutputStream outputStream) throws IOException {

        String questionsFilePath = FileProcessor.getFilePath(questionsFileName);
        questionsGenerator = new ItemsGenerator<>(parseQuestions(questionsFilePath));
        inputStreamReader = new InputStreamReader(inputStream);
        outputStreamWriter = new OutputStreamWriter(outputStream);
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
            totalQuestionsCount += 1;
            writeLine(question.getQuestion());
            if (processAnswer(question))
                break;
        }

        writeLine("Приятно было поиграть с тобой!");
    }

    private boolean processAnswer(Question question) throws IOException {
        while (true){
            String answer = readLine().toLowerCase();
            if (answer.equals(question.getAnswer().toLowerCase())) {
                correctAnswersCount += 1;
                writeLine("Правильно");
                break;
            }
            else if (answer.equals(Commands.Score)) {
                writeLine(("Твой счет: " + correctAnswersCount + " из " + (totalQuestionsCount - 1)));
            }
            else if (answer.equals(Commands.Exit)) {
                return true;
            }
            else if (answer.contains(Commands.Help)) {
                FileProcessor.printHelp(outputStreamWriter);
            }
            else {
                writeLine(("Неправильно, правильный ответ: " + question.getAnswer()));
                break;
            }
        }
        return false;
    }

    private String readLine() throws IOException {
        BufferedReader input = new BufferedReader(inputStreamReader);
        return input.readLine();
    }

    private void writeLine(String line) throws IOException {
        BufferedWriter output = new BufferedWriter(outputStreamWriter);
        output.write(line);
        output.newLine();
        output.flush();
    }
}
