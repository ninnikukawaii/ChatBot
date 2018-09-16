package console;

import java.util.Scanner;

public class Quiz
{
    public static void startQuiz(){
        System.out.println();
        Scanner input = new Scanner(System.in);
        String filename = System.getProperty("user.dir") + "\\questionsLong.txt";
        QuestionGenerator questionGenerator = new QuestionGenerator(filename);
        int totalQuestionsCount = 0;
        int correctAnswersCount = 0;

        while (questionGenerator.CountOfQuestion() != 0) {
            Question question = questionGenerator.generateQuestion();
            totalQuestionsCount += 1;
            System.out.println(question.getQuestion());
            String answer = input.nextLine().toLowerCase();
            if (answer.equals("счет")) {
                System.out.println("Твой счет: " + correctAnswersCount + " из " + (totalQuestionsCount - 1));
                answer = input.nextLine().toLowerCase();
            }
            if (answer.equals(question.getAnswer().toLowerCase())) {
                correctAnswersCount += 1;
                System.out.println("Правильно");
            }
            else if (answer.equals("выход")) {
                break;
            }
            else {
                System.out.println("Неправильно, правильный ответ: " + question.getAnswer());
            }
        }
        System.out.println("Приятно было поиграть с тобой!");
    }
}
