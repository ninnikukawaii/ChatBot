package tests;

import service.quiz.Quiz;
import service.quiz.ItemsGenerator;
import service.quiz.Question;
import service.quiz.QuizParsingException;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;
import static service.Constants.TEST_QUESTIONS_PATH;

public class QuestionGeneratorTest {

    private ItemsGenerator<Question> createGenerator() throws QuizParsingException {
        return Quiz.createQuiz(TEST_QUESTIONS_PATH).getQuestionGenerator();
    }

    @Test
    public void testGenerator() throws QuizParsingException {
        ItemsGenerator<Question> generator = createGenerator();
        ArrayList<Question> questions = (ArrayList<Question>) generator.getAllItems();
        Question question = new Question("Сколько будет дважды два?", "четыре");
        assertThat(questions, hasItem(question));
    }

    @Test
    public void testCountDecreases() throws QuizParsingException {
        ItemsGenerator<Question> generator = createGenerator();
        int firstCount = generator.getCountOfItems();
        generator.iterator().next();
        int secondCount = generator.getCountOfItems();
        assertTrue(firstCount > secondCount);
    }
}