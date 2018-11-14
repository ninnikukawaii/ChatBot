package main;

import service.userAnswerProcessing.AnswerProcessor;
import service.IOManager;
import service.userAnswerProcessing.StandardResponse;
import service.userAnswerProcessing.UserStateType;
import service.quiz.QuizParsingException;

import java.io.IOException;
import java.util.List;

import static service.Constants.QUESTIONS_FILE;

public class ConsoleMain {

    public static void main(String[] args) throws IOException, QuizParsingException {
        IOManager ioManager = new IOManager(System.in, System.out);
        AnswerProcessor answerProcessor = new AnswerProcessor(UserStateType.CHAT, QUESTIONS_FILE);
        ioManager.writeLines(StandardResponse.CHAT_GREETING);

        while (answerProcessor.getUserState() != UserStateType.EXIT) {
            String answer = ioManager.readLine();
            List<String> response = answerProcessor.processAnswer(answer);
            ioManager.writeLines(response);
        }
    }
}
