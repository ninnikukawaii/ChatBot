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

        String response = getResponse(query);
        Reply reply = new Reply(response, false, query.GetSession(), query.GetVersion());

        UserStateType userState = users.get(query.GetUserID()).getUserState();
        if (userState == UserStateType.Chat) {
            reply.setButtons(new Button[]{Button.showHelp, Button.startQuiz, Button.exit});
        }
        else if (userState == UserStateType.Quiz) {
            reply.setButtons(new Button[]{Button.showHelp, Button.showScore, Button.exitQuiz});
        }
        else {
            reply.SetEndSession();
        }

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
