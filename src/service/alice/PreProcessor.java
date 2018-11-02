package service.alice;

import service.userAnswerProcessing.StandardResponse;
import service.quiz.QuizParsingException;
import service.userAnswerProcessing.AnswerProcessor;
import service.userAnswerProcessing.UserStateType;

import java.util.HashMap;

import static service.Constants.QUESTIONS_PATH;

public class PreProcessor {
    private HashMap<String, AnswerProcessor> users = new HashMap<>();

    public String HandleRequest(String gsonFromUser) throws QuizParsingException {
        Query queryFromUser = new Query();
        queryFromUser.Query(gsonFromUser);
        String userId = queryFromUser.GetUserID();
        String commandFromUser = queryFromUser.GetCommand();
        String commandForUser;
        if (! users.containsKey(userId)){
            AnswerProcessor answerProcessor = new AnswerProcessor(UserStateType.Chat, QUESTIONS_PATH);
            users.put(userId, answerProcessor);
            commandForUser = String.join("\n", StandardResponse.CHAT_GREETING);
        }
        else {
            commandForUser = String.join("\n",
                    users.get(userId).processAnswer(commandFromUser));
        }

        Reply replyForUser = new Reply(commandForUser, false,
                queryFromUser.GetSession(), queryFromUser.GetVersion());

        if (commandForUser.equals(StandardResponse.CHAT_FAREWELL)){
            replyForUser.SetEndSession();
        }
        return replyForUser.ConvertToGson();
    }
}
