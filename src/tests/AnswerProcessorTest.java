package tests;

import org.junit.Test;
import service.AnswerProcessor;
import service.Quiz;
import service.Response;
import service.enums.UserState;

import static org.junit.Assert.assertEquals;

public class AnswerProcessorTest {
    private AnswerProcessor answerProcessor = new AnswerProcessor(UserState.Quiz, "questionsForTesting");
    private Quiz quiz;

    @Test
    public void TestState(){
        assertEquals(answerProcessor.getUserState(), UserState.Quiz);
    }

    @Test
    public void TestReturnHelp(){
        assertEquals(answerProcessor.processAnswer("Справка"), Response.help);
    }

    @Test
    public void TestExit(){
        assertEquals(answerProcessor.processAnswer("Выход"), UserState.Exit);
    }

    @Test
    public void TestOnScore(){
        assertEquals(answerProcessor.processAnswer("Счет"), quiz.getScore());
    }

    @Test
    public void TestOnFalse(){
        assertEquals(answerProcessor.processAnswer("5"));
    }

    @Test
    public void  TestOnTrue(){
        assertEquals(answerProcessor.processAnswer("4"));
    }



}
