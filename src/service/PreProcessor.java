package service;

import com.google.gson.Gson;
import service.alice.Query;
import service.alice.Reply;
import service.enums.UserState;

import java.util.HashMap;

public class PreProcessor {
    private HashMap<String, AnswerProcessor> DictionOfUser = new HashMap<String, AnswerProcessor>();

    public String HandleRequest(Gson gsonFromUser) throws service.exceptions.QuizParsingException {
        Query queryFromUser = new Query();
        queryFromUser.ConvertFromGson(gsonFromUser);
        String userId = queryFromUser.GetUserID();
        if (! DictionOfUser.containsKey(userId)){
            AnswerProcessor answerProcessor = new AnswerProcessor(UserState.Chat,
                    "questionsLong.txt");
            DictionOfUser.put(userId, answerProcessor);
        }
        String comandFromUser = queryFromUser.GetCommand();
        String comandForUser = DictionOfUser.get(userId).processAnswer(comandFromUser)[0];

        Reply replyForUser = new Reply(comandForUser, false, queryFromUser.GetSession(), queryFromUser.GetVersion());

        if (comandForUser.equals(Response.chatFarewell)){
            replyForUser.SetEndSession();
        }

        return replyForUser.ConvertToGson();

    }

    public void Main() {
        System.out.println("Hello");

    }
}
