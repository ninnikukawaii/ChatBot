package service;

import service.alice.Query;
import service.alice.Reply;
import service.enums.UserState;

import java.util.HashMap;

import static service.Constants.QUESTIONS_PATH;

public class PreProcessor {
    private HashMap<String, AnswerProcessor> users = new HashMap<>();

    public String HandleRequest(String gsonFromUser) throws service.exceptions.QuizParsingException {
        Query queryFromUser = new Query();
        queryFromUser.ConvertFromGson(gsonFromUser);
        String userId = queryFromUser.GetUserID();
        String commandFromUser = queryFromUser.GetCommand();
        String commandForUser;
        if (! users.containsKey(userId)){
            AnswerProcessor answerProcessor = new AnswerProcessor(UserState.Chat, QUESTIONS_PATH);
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
