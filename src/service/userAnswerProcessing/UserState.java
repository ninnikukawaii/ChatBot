package service.userAnswerProcessing;

import service.quiz.Question;
import service.quiz.Quiz;
import service.quiz.QuizParsingException;

import java.util.ArrayList;
import java.util.List;

class UserState {
    private UserStateType state;
    private Quiz quiz;
    private Question lastQuestion;

    private final String questionsFileName;

    UserState(UserStateType initialState, String questionsFileName) {
        state = initialState;
        this.questionsFileName = questionsFileName;
    }

    UserStateType getState(){ return state; }

    List<String> getHelp() {
        return state.getHelp();
    }

    List<String> getScore() {
        if (state == UserStateType.Quiz) {
            return new ArrayList<String>() {{add(quiz.getScore());}};
        }
        else return null;
    }

    List<String> exit() {
        if (state == UserStateType.Quiz) {
            state = UserStateType.Chat;
            return new ArrayList<String>() {{add(StandardResponse.QUIZ_FAREWELL);}};
        }
        else {
            state = UserStateType.Exit;
            return new ArrayList<String>() {{add(StandardResponse.CHAT_FAREWELL);}};
        }
    }

    List<String> checkAnswer(String answer) {
        if (state == UserStateType.Quiz){
            if (answer.equals(lastQuestion.getAnswer().toLowerCase())) {
                quiz.incrementCorrectAnswersCount();
                return new ArrayList<String>() {{
                    add(StandardResponse.CORRECT_ANSWER);
                    addAll(getNextQuestion());
                }};
            }
            else {
                return new ArrayList<String>() {{
                    add(StandardResponse.INCORRECT_ANSWER);
                    add(lastQuestion.getAnswer());
                    addAll(getNextQuestion());
                }};
            }
        }
        else return null;
    }

    List<String> startQuiz() throws QuizParsingException {
        quiz = Quiz.createQuiz(questionsFileName);
        updateState(UserStateType.Quiz);
        return new ArrayList<String>() {{
            addAll(StandardResponse.QUIZ_GREETING);
            addAll(getNextQuestion());
        }};
    }

    private void updateState(UserStateType state) {
        this.state = state;
    }

    private List<String> getNextQuestion() {
        if (quiz.hasNextQuestion()) {
            lastQuestion = quiz.getNextQuestion();
            return new ArrayList<String>() {{
                add(lastQuestion.getQuestion());
            }};
        }
        else {
            updateState(UserStateType.Chat);
            return new ArrayList<String>() {{
                add(StandardResponse.NO_MORE_QUESTIONS);
                add(StandardResponse.QUIZ_FAREWELL);
            }};
        }
    }
}
