package src.service.alice;

import src.service.alice.protocol.Button;
import src.service.alice.protocol.Query;
import src.service.alice.protocol.Reply;
import src.service.textGenerator.TextGenerator;
import src.service.textGenerator.TextParsingException;
import src.service.userAnswerProcessing.StandardResponse;
import src.service.quiz.QuizParsingException;
import src.service.userAnswerProcessing.AnswerProcessor;
import src.service.userAnswerProcessing.UserStateType;

import java.util.HashMap;

import static src.service.Constants.QUESTIONS_FILE;

class RequestHandler {
    private HashMap<String, AnswerProcessor> users = new HashMap<>();
    private TextGenerator textGenerator;

    RequestHandler() throws TextParsingException {
        textGenerator = TextGenerator.createTextGenerator();
    }

    Reply handleRequest(Query query) throws QuizParsingException {
        String userId = query.getUserID();
        String request = query.getCommand();

        String response = getResponse(userId, request);
        Reply reply = new Reply(response, false, query.getSession(), query.getVersion());

        UserStateType userState = users.get(userId).getUserState();
        if (userState == UserStateType.EXIT) {
            reply.setEndSession();
            users.remove(userId);
        }
        else {
            reply.setButtons(Button.getDefaultButtons(userState));
        }

        return reply;
    }

    private String getResponse(String userId, String request) throws QuizParsingException {
        if (! users.containsKey(userId)){
            AnswerProcessor answerProcessor = new AnswerProcessor(UserStateType.CHAT,
                    QUESTIONS_FILE, textGenerator);
            users.put(userId, answerProcessor);
            return String.join("\n", StandardResponse.CHAT_GREETING);
        }

        return String.join("\n", users.get(userId).processAnswer(request));
    }
}
