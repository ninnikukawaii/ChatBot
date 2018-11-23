package src.service.userAnswerProcessing;

import src.service.quiz.QuizParsingException;
import src.service.textGenerator.TextGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AnswerProcessor {

    private UserState userState;

    public AnswerProcessor(UserStateType initialState, String questionsFileName,
                           TextGenerator textGenerator){
        userState = new UserState(initialState, questionsFileName, textGenerator);
    }

    public UserStateType getUserState() {
        return userState.getState();
    }

    public List<String> processAnswer(String answer) throws QuizParsingException {
        answer = answer.toLowerCase();
        Command command = Command.parse(answer);
        Optional<List<String>> response = Optional.empty();

        if (command != null) {
            response = Optional.ofNullable(command.processCommand(userState));

        }

        String finalAnswer = answer;
        return response.orElseGet(() -> Optional.ofNullable(userState.checkAnswer(finalAnswer))
                .orElseGet(() -> Collections.singletonList(StandardResponse.MISUNDERSTOOD)));
    }
}
