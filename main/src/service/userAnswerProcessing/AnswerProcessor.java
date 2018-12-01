package src.service.userAnswerProcessing;

import src.service.quiz.QuizParsingException;
import src.service.textGenerator.TextGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AnswerProcessor {

    private UserState userState;
    private TextGenerator textGenerator;

    public AnswerProcessor(UserStateType initialState, String questionsFileName,
                           TextGenerator textGenerator){
        userState = new UserState(initialState, questionsFileName);
        this.textGenerator = textGenerator;
    }

    public UserStateType getUserState() {
        return userState.getState();
    }

    List<String> createText() {
        if (userState.getState() == UserStateType.CHAT) {
            return Collections.singletonList(textGenerator.createText());
        }
        else return null;
    }

    List<String> getHelp() { return userState.getHelp(); }

    List<String> getScore() { return  userState.getScore(); }

    List<String> startQuiz() throws QuizParsingException { return userState.startQuiz(); }

    List<String> exit() { return userState.exit(); }

    public List<String> processAnswer(String answer) throws QuizParsingException {
        answer = answer.toLowerCase();
        Command command = Command.parse(answer);
        Optional<List<String>> response = Optional.empty();

        if (command != null) {
            response = Optional.ofNullable(command.processCommand(this));

        }

        String finalAnswer = answer;
        return response.orElseGet(() -> Optional.ofNullable(userState.checkAnswer(finalAnswer))
                .orElseGet(() -> Collections.singletonList(StandardResponse.MISUNDERSTOOD)));
    }
}
