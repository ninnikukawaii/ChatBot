package service.userAnswerProcessing;

import service.quiz.QuizParsingException;

import java.util.ArrayList;
import java.util.List;

public class AnswerProcessor {

    private UserState userState;

    public AnswerProcessor(UserStateType initialState, String questionsFileName){
        this.userState = new UserState(initialState, questionsFileName);
    }

    public UserStateType getUserState() {
        return userState.getState();
    }

    public List<String> processAnswer(String answer) throws QuizParsingException {
        Command command = Command.parse(answer);

        if (command != null) {
            return command.processCommand(userState);
        }

        List<String> response = userState.checkAnswer(answer);
        if (response != null) {
            return response;
        }
        else {
            return new ArrayList<String>() {{add(StandardResponse.MISUNDERSTOOD);}};
        }
    }
}
