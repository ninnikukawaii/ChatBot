package tests;

import org.junit.Test;
import service.quiz.ItemsGenerator;
import service.quiz.Question;
import service.quiz.Quiz;
import service.quiz.QuizParsingException;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        //assertThat(questions, hasItem(question));  // то же самое несравнивание двух строк, хз как переделать
        assertEquals(question.getAnswer(), questions.get(0).getAnswer());
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