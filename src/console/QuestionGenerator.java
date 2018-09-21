package console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionGenerator {
    private ArrayList<Question> questions;
    public IterableOfQuestion<Question> collectionOfQuestion;

    public QuestionGenerator(String filename){
        questions = new ArrayList<>();
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
        doIterable();
    }

    public void doIterable(){
        collectionOfQuestion = new IterableOfQuestion<Question>(this.questions);
    }

    public ArrayList<Question> getAllQuestions(){
        return new ArrayList<>(questions);
    }
}
