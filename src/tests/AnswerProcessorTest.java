package tests;

import org.junit.Test;
import service.AnswerProcessor;
import service.Response;
import service.enums.UserState;

import static org.junit.Assert.assertEquals;

public class AnswerProcessorTest {
    private AnswerProcessor answerProcessor = new AnswerProcessor(UserState.Chat, "questionsForTesting.txt");

    @Test
    public void TestState() throws service.exceptions.QuizParsingException {
        assertEquals(answerProcessor.getUserState(), UserState.Chat);
        answerProcessor.processAnswer("Викторина".toLowerCase());
        assertEquals(answerProcessor.getUserState(), UserState.Quiz);
    }

    @Test
    public void TestReturnHelp() throws service.exceptions.QuizParsingException{
        assertEquals(answerProcessor.processAnswer("Справка".toLowerCase())[0], Response.help);
    }

    @Test
    public void TestExit() throws service.exceptions.QuizParsingException {
        answerProcessor.processAnswer("Викторина".toLowerCase());
        assertEquals(answerProcessor.processAnswer("Выход".toLowerCase())[0], Response.quizFarewell);
        assertEquals(answerProcessor.processAnswer("Выход".toLowerCase())[0], Response.chatFarewell);
    }

    @Test
    public void TestOnFalse() throws service.exceptions.QuizParsingException {
        answerProcessor.processAnswer("Викторина".toLowerCase());
        assertEquals(answerProcessor.processAnswer("Пять".toLowerCase())[0], Response.incorrectAnswer);
    }

    @Test
    public void  TestOnTrue() throws service.exceptions.QuizParsingException {
        answerProcessor.processAnswer("Викторина".toLowerCase());
        assertEquals(answerProcessor.processAnswer("Четыре".toLowerCase())[0], Response.correctAnswer); ///БЛЯЯЯЯЯЯЯЯЯ
    }
}