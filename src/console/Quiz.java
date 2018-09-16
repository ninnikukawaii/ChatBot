package console;

import java.util.Scanner;

public class Quiz
{
    public static void Quiz()
    {
        //Вывести вступление
        Scanner input = new Scanner(System.in);
        String filename = System.getProperty("user.dir") + "\\questions.txt";
        QuestionGenerator questionGenerator = new QuestionGenerator(filename);
        loop: while (questionGenerator.CountOfQuestion() != 0)
        {
            Question quest = questionGenerator.generateQuestion();
            System.out.println(quest.getQuestion());
            String answer = input.nextLine();
            if (answer.equals(quest.getAnswer()))
            {
                System.out.println("Правильно");
            }
            else
            {
                System.out.println("Неправильно");
            }
        }
        System.out.println("К сожалению, у меня закончились вопросы. Приятно было поиграть с тобой");
    }
}
