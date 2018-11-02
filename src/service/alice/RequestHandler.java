package service.alice;

import service.userAnswerProcessing.StandardResponse;
import service.quiz.QuizParsingException;
import service.userAnswerProcessing.AnswerProcessor;
import service.userAnswerProcessing.UserStateType;

import java.util.HashMap;

import static service.Constants.QUESTIONS_PATH;

public class RequestHandler {
    private HashMap<String, AnswerProcessor> users = new HashMap<>();

    public String handleRequest(String userRequest) throws QuizParsingException {
        Query query = new Query();
        query.Query(userRequest);

        String response = getResponse(query);
        Reply reply = new Reply(response, false, query.GetSession(), query.GetVersion());

        if (response.equals(StandardResponse.CHAT_FAREWELL)){
            reply.SetEndSession();
        }

        /*if (users.get(userId).getUserState() == UserStateType.Chat){
            replyForUser.addButtonOnChat();
        }

        if (users.get(userId).getUserState() == UserStateType.Quiz){
            replyForUser.addButtonOnQuiz();
        }

        if (commandForUser.equals(StandardResponse.CHAT_FAREWELL)){
            replyForUser.SetEndSession();
        }*/

        return reply.ConvertToGson();
    }

    private String getResponse(Query query) throws QuizParsingException {
        String userId = query.GetUserID();
        String request = query.GetCommand();

        if (! users.containsKey(userId)){
            AnswerProcessor answerProcessor = new AnswerProcessor(UserStateType.Chat, QUESTIONS_PATH);
            users.put(userId, answerProcessor);
            return String.join("\n", StandardResponse.CHAT_GREETING);
        }

        if (query.havePayload()){
            request = query.getPayload();
        }
        return String.join("\n", users.get(userId).processAnswer(request));
    }
}
