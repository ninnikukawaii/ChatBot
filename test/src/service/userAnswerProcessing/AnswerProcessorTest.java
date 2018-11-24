package src.service.userAnswerProcessing;

import src.service.quiz.QuizParsingException;

import org.junit.Test;

import static src.service.Constants.TEST_QUESTIONS_FILE;
import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnswerProcessorTest {
    private AnswerProcessor answerProcessor = new AnswerProcessor(UserStateType.CHAT,
            TEST_QUESTIONS_FILE, null);

    @Test
    public void testState() throws QuizParsingException {
        assertEquals(UserStateType.CHAT, answerProcessor.getUserState());
        answerProcessor.processAnswer(Command.QUIZ.getName());
        assertEquals(UserStateType.QUIZ, answerProcessor.getUserState());
    }

    @Test
    public void testHelp() throws QuizParsingException {
        assertTrue(isEqualCollection(StandardResponse.CHAT_HELP,
                answerProcessor.processAnswer(Command.HELP.getName())));
        answerProcessor.processAnswer(Command.QUIZ.getName());
        assertTrue(isEqualCollection(StandardResponse.QUIZ_HELP,
                answerProcessor.processAnswer(Command.HELP.getName())));
    }

    @Test
    public void testExit() throws QuizParsingException {
        answerProcessor.processAnswer(Command.QUIZ.toString());
        assertEquals(StandardResponse.CHAT_FAREWELL,
                answerProcessor.processAnswer(Command.EXIT.getName()).get(0));
        assertEquals(StandardResponse.CHAT_FAREWELL,
                answerProcessor.processAnswer(Command.EXIT.getName()).get(0));
    }

    @Test
    public void testMisunderstood() throws QuizParsingException {
        assertEquals(StandardResponse.MISUNDERSTOOD,
                answerProcessor.processAnswer("не команда").get(0));
    }

    @Test
    public void testIncorrectAnswer() throws QuizParsingException {
        answerProcessor.processAnswer(Command.QUIZ.getName());
        assertEquals(StandardResponse.INCORRECT_ANSWER,
                answerProcessor.processAnswer("пять").get(0));
    }

    @Test
    public void testCorrectAnswer() throws QuizParsingException {
        answerProcessor.processAnswer(Command.QUIZ.getName());
        assertEquals(StandardResponse.CORRECT_ANSWER,
                answerProcessor.processAnswer("четыре").get(0));
    }
}
