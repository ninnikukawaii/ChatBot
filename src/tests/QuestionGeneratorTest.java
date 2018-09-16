package tests;

import console.Question;
import console.QuestionGenerator;
import org.junit.Assert;
import org.junit.Test;

public class QuestionGeneratorTest {

    @Test
    public void testGenerator() {
        String filename = System.getProperty("user.dir") + "\\questions.txt";
        QuestionGenerator generator = new QuestionGenerator(filename);

        Assert.assertTrue(generator.getAllQuestions().contains(
                new Question("Сколько будет 2х2?", "4")));
        Assert.assertFalse(generator.getAllQuestions().contains(
                new Question("Сколько будет 2х2?", "5")
        ));
    }
}