package service.userAnswerProcessing;

import service.quiz.QuizParsingException;

import java.util.Collections;
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
        answer = answer.toLowerCase();
        Command command = Command.parse(answer);

        if (command != null) {
            return command.processCommand(userState);
        }

        List<String> response = userState.checkAnswer(answer);
        if (response != null) {
            return response;
        }
        else {
            return Collections.singletonList(StandardResponse.MISUNDERSTOOD);
        }
    }
}
