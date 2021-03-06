package src.service.userAnswerProcessing;

import src.service.quiz.Question;
import src.service.quiz.Quiz;
import src.service.quiz.QuizParsingException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    List<String> getHelp() { return state.getHelp(); }

    List<String> getScore() {
        if (state == UserStateType.QUIZ) {
            return Collections.singletonList(quiz.getScore());
        }
        else return null;
    }

    List<String> exit() {
        if (state == UserStateType.QUIZ) {
            state = UserStateType.CHAT;
            return Collections.singletonList(StandardResponse.QUIZ_FAREWELL);
        }
        else {
            state = UserStateType.EXIT;
            return Collections.singletonList(StandardResponse.CHAT_FAREWELL);
        }
    }

    List<String> checkAnswer(String answer) {
        if (state == UserStateType.QUIZ){
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
        updateState(UserStateType.QUIZ);
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
            return Collections.singletonList(lastQuestion.getQuestion());
        }
        else {
            updateState(UserStateType.CHAT);
            return Arrays.asList(
                    StandardResponse.NO_MORE_QUESTIONS,
                    StandardResponse.QUIZ_FAREWELL
            );
        }
    }
}
