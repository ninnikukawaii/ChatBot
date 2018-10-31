package tests;

import service.AnswerProcessor;
import service.StandardResponse;
import service.enums.Command;
import service.enums.UserState;
import service.exceptions.QuizParsingException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static service.Constants.TEST_QUESTIONS_PATH;

public class AnswerProcessorTest {
    private AnswerProcessor answerProcessor = new AnswerProcessor(UserState.Chat,
            TEST_QUESTIONS_PATH);

    @Test
    public void TestState() throws QuizParsingException {
        assertEquals(UserState.Chat, answerProcessor.getUserState());
        answerProcessor.processAnswer(Command.Quiz.getString());
        assertEquals(UserState.Quiz, answerProcessor.getUserState());
    }

    @Test
    public void TestHelp() throws QuizParsingException {
        assertArrayEquals(StandardResponse.CHAT_HELP,
                answerProcessor.processAnswer(Command.Help.getString()));
        answerProcessor.processAnswer(Command.Quiz.getString());
        assertArrayEquals(StandardResponse.QUIZ_HELP,
                answerProcessor.processAnswer(Command.Help.getString()));
    }

    @Test
    public void TestExit() throws QuizParsingException {
        answerProcessor.processAnswer(Command.Quiz.toString());
        assertEquals(StandardResponse.QUIZ_FAREWELL,
                answerProcessor.processAnswer(Command.Exit.getString())[0]);
        assertEquals(StandardResponse.CHAT_FAREWELL,
                answerProcessor.processAnswer(Command.Exit.getString())[0]);
    }

    @Test
    public void TestOnFalse() throws QuizParsingException {
        answerProcessor.processAnswer(Command.Quiz.getString());
        assertEquals(StandardResponse.INCORRECT_ANSWER,
                answerProcessor.processAnswer("пять")[0]);
    }

    @Test
    public void  TestOnTrue() throws QuizParsingException {
        answerProcessor.processAnswer(Command.Quiz.getString());
        assertEquals(StandardResponse.CORRECT_ANSWER,
                answerProcessor.processAnswer("четыре")[0]);
    }
}