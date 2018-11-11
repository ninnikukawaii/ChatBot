package service.alice;

import service.alice.protocol.Button;
import service.alice.protocol.Query;
import service.alice.protocol.Reply;
import service.userAnswerProcessing.StandardResponse;
import service.quiz.QuizParsingException;
import service.userAnswerProcessing.AnswerProcessor;
import service.userAnswerProcessing.UserStateType;

import java.util.HashMap;

import static service.Constants.QUESTIONS_PATH;

public class RequestHandler {
    private HashMap<String, AnswerProcessor> users = new HashMap<>();

    String handleRequest(String userRequest) throws QuizParsingException {
        Query query = new Query(userRequest);

        String userId = query.getUserID();
        String request = query.GetCommand();
        String payload = query.getPayload();

        String response = getResponse(userId, request, payload);
        Reply reply = new Reply(response, false, query.GetSession(), query.GetVersion());

        UserStateType userState = users.get(userId).getUserState();
        if (userState == UserStateType.Exit) {
            reply.setEndSession();
            users.remove(userId);
        }
        else if (Button.defaultButtons.containsKey(userState)) {
            reply.setButtons(Button.defaultButtons.get(userState));
        }

        return reply.convertToGson();
    }

    private String getResponse(String userId, String request, String payload)
            throws QuizParsingException {

        if (! users.containsKey(userId)){
            AnswerProcessor answerProcessor = new AnswerProcessor(UserStateType.Chat, QUESTIONS_PATH);
            users.put(userId, answerProcessor);
            return String.join("\n", StandardResponse.CHAT_GREETING);
        }

        if (payload != null){
            request = payload;
        }
        return String.join("\n", users.get(userId).processAnswer(request));
    }
}
