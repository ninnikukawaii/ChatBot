package console;

import service.AnswerProcessor;
import service.IOManager;
import service.Response;
import service.enums.UserState;

import java.io.*;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) throws IOException {
        IOManager ioManager = new IOManager(in, out);
        AnswerProcessor answerProcessor = new AnswerProcessor(UserState.Chat,
                                               "questionsLong.txt");
        ioManager.writeLine(Response.chatGreeting);

        while (answerProcessor.getUserState() != UserState.Exit) {
            String answer = ioManager.readLine().toLowerCase();
            String response = answerProcessor.processAnswer(answer);
            ioManager.writeLine(response);
        }
    }
}
