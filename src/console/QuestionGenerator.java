package console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuestionGenerator {
    private ArrayList<Question> questions;
    private Random random;

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
    }

    public Question generateQuestion() {
        int index = random.nextInt(questions.size());
        questions.remove(index);
        return questions.get(index);
    }

    public int CountOfQuestion(){
        return questions.size();
    }

    public ArrayList<Question> getAllQuestions(){
        return new ArrayList<>(questions);
    }
}
