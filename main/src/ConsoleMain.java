package src;

import src.service.textGenerator.TextGenerator;
import src.service.textGenerator.TextParsingException;
import src.service.userAnswerProcessing.AnswerProcessor;
import src.service.IOManager;
import src.service.userAnswerProcessing.StandardResponse;
import src.service.userAnswerProcessing.UserStateType;
import src.service.quiz.QuizParsingException;

import java.io.IOException;
import java.util.List;

import static src.service.Constants.QUESTIONS_FILE;
import static src.service.Constants.TEXT_FILE;

public class ConsoleMain {

    public static void main(String[] args) throws IOException,
            QuizParsingException, TextParsingException {
        IOManager ioManager = new IOManager(System.in, System.out);
        TextGenerator textGenerator = TextGenerator.createTextGenerator(
                IOManager.getFilePath(TEXT_FILE));
        AnswerProcessor answerProcessor = new AnswerProcessor(UserStateType.CHAT,
                QUESTIONS_FILE, textGenerator);
        ioManager.writeLines(StandardResponse.CHAT_GREETING);

        while (answerProcessor.getUserState() != UserStateType.EXIT) {
            String answer = ioManager.readLine();
            List<String> response = answerProcessor.processAnswer(answer);
            ioManager.writeLines(response);
        }
    }
}
