package console;

public class Main {

    public static void main(String[] args){
        String filename = System.getProperty("user.dir") + "\\questions.txt";
        QuestionGenerator questionGenerator = new QuestionGenerator(filename);
        Question question = questionGenerator.generateQuestion();
        System.out.println(question.getQuestion());
        System.out.println(question.getAnswer());
    }
}
