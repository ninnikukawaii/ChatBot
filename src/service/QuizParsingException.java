package service;

import java.io.IOException;

public class QuizParsingException extends Exception {
    public QuizParsingException(String problem){
        super(problem);
    }
}
