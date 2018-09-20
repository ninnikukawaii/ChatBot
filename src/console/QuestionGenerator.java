package console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuestionGenerator {
    private ArrayList<Question> questions;
    private Random random;
    private int countOfQuestion;

    public QuestionGenerator(String filename){
        questions = new ArrayList<>();
        random = new Random();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineParts = line.split("~");
                questions.add(new Question(lineParts[0], lineParts[1]));
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }
        this.countOfQuestion = questions.size();
    }

    public Question generateQuestion() {
        int index = random.nextInt(this.countOfQuestion--);
        Question question = questions.get(index);
        questions.set(index, questions.get(countOfQuestion));
        return question;
    }

    public int CountOfQuestion(){
        return this.countOfQuestion;
    }

    public ArrayList<Question> getAllQuestions(){
        return new ArrayList<>(questions);
    }
}
