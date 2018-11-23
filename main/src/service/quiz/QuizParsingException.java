package src.service.quiz;

public class QuizParsingException extends Exception {
    public QuizParsingException(String problem){
        super(problem);
    }

    public QuizParsingException(String problem, Throwable cause) { super(problem, cause); }
}
