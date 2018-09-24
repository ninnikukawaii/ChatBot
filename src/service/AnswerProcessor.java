package service;

import console.Quiz;

import java.io.*;

public class AnswerProcessor {

    private InputStream inputStream;
    private OutputStream outputStream;
    private int totalQuestionsCount = 0;
    private int correctAnswersCount = 0;

    public AnswerProcessor(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void incrementTotalQuestionsCount() {
        totalQuestionsCount += 1;
    }

    public boolean processAnswer(Question question) throws IOException {
        boolean hasQuestion = question != null;

        while (true){
            String answer = readLine().toLowerCase();
            if (hasQuestion && answer.equals(question.getAnswer().toLowerCase())) {
                correctAnswersCount += 1;
                writeLine("Правильно");
                break;
            }
            else if (hasQuestion && answer.equals(Commands.Score)) {
                writeLine(("Твой счет: " + correctAnswersCount + " из " + (totalQuestionsCount - 1)));
            }
            else if (answer.equals(Commands.Exit)) {
                return true;
            }
            else if (answer.contains(Commands.Help)) {
                FileProcessor.printHelp(new OutputStreamWriter(outputStream));
            }
            else if (!hasQuestion && answer.contains(Commands.Quiz)) {
                FileProcessor.printDocument("quizGreeting.txt", new OutputStreamWriter(outputStream));
                Quiz quiz = new Quiz("questionsLong.txt", inputStream, outputStream);
                quiz.startQuiz();
            }
            else if (hasQuestion) {
                writeLine(("Неправильно, правильный ответ: " + question.getAnswer()));
                break;
            }
            else {
                writeLine("Кажется, я не понял тебя. Может попросишь справку?");
            }
        }
        return false;
    }

    public String readLine() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
        return input.readLine();
    }

    public void writeLine(String line) throws IOException {
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(outputStream));
        output.write(line);
        output.newLine();
        output.flush();
    }
}
