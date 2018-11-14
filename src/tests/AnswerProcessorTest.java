package tests;

import service.userAnswerProcessing.AnswerProcessor;
import service.userAnswerProcessing.StandardResponse;
import service.userAnswerProcessing.Command;
import service.userAnswerProcessing.UserStateType;
import service.quiz.QuizParsingException;

import org.junit.Test;

import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static service.Constants.TEST_QUESTIONS_FILE;

public class AnswerProcessorTest {
    private AnswerProcessor answerProcessor = new AnswerProcessor(UserStateType.Chat,
            TEST_QUESTIONS_FILE);

    @Test
    public void testState() throws QuizParsingException {
        assertEquals(UserStateType.Chat, answerProcessor.getUserState());
        answerProcessor.processAnswer(Command.Quiz.getName());
        assertEquals(UserStateType.Quiz, answerProcessor.getUserState());
    }

    @Test
    public void testHelp() throws QuizParsingException {
        assertTrue(isEqualCollection(StandardResponse.CHAT_HELP,
                answerProcessor.processAnswer(Command.Help.getName())));
        answerProcessor.processAnswer(Command.Quiz.getName());
        assertTrue(isEqualCollection(StandardResponse.QUIZ_HELP,
                answerProcessor.processAnswer(Command.Help.getName())));
    }

    @Test
    public void testExit() throws QuizParsingException {
        answerProcessor.processAnswer(Command.Quiz.toString());
        assertEquals(StandardResponse.CHAT_FAREWELL,
                answerProcessor.processAnswer(Command.Exit.getName()).get(0));
        assertEquals(StandardResponse.CHAT_FAREWELL,
                answerProcessor.processAnswer(Command.Exit.getName()).get(0));
    }

    @Test
    public void testIncorrectAnswer() throws QuizParsingException {
        answerProcessor.processAnswer(Command.Quiz.getName());
        assertEquals(StandardResponse.INCORRECT_ANSWER,
                answerProcessor.processAnswer("пять").get(0));
    }

    @Test
    public void testCorrectAnswer() throws QuizParsingException {
        answerProcessor.processAnswer(Command.Quiz.getName());
        assertEquals(StandardResponse.CORRECT_ANSWER,
                answerProcessor.processAnswer("четыре").get(0));
    }
}
