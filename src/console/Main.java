package console;

import service.AnswerProcessor;
import service.IOManager;
import service.StandardResponse;
import service.enums.UserState;
import service.exceptions.QuizParsingException;

import java.io.IOException;

import static service.Constants.QUESTIONS_PATH;

public class Main {

    public static void main(String[] args) throws IOException, QuizParsingException {
        IOManager ioManager = new IOManager(System.in, System.out);
        AnswerProcessor answerProcessor = new AnswerProcessor(UserState.Chat, QUESTIONS_PATH);
        ioManager.writeLines(StandardResponse.CHAT_GREETING);

        while (answerProcessor.getUserState() != UserState.Exit) {
            String answer = ioManager.readLine().toLowerCase();
            String[] response = answerProcessor.processAnswer(answer);
            ioManager.writeLines(response);
        }
    }
}
