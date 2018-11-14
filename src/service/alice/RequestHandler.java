package service.alice;

import service.alice.protocol.Button;
import service.alice.protocol.Query;
import service.alice.protocol.Reply;
import service.userAnswerProcessing.StandardResponse;
import service.quiz.QuizParsingException;
import service.userAnswerProcessing.AnswerProcessor;
import service.userAnswerProcessing.UserStateType;

import java.util.HashMap;

import static service.Constants.QUESTIONS_FILE;

class RequestHandler {
    private HashMap<String, AnswerProcessor> users = new HashMap<>();

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
            AnswerProcessor answerProcessor = new AnswerProcessor(UserStateType.CHAT, QUESTIONS_FILE);
            users.put(userId, answerProcessor);
            return String.join("\n", StandardResponse.CHAT_GREETING);
        }

        return String.join("\n", users.get(userId).processAnswer(request));
    }
}
