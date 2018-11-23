package src.service.textGenerator;

public class TextParsingException extends Exception {

    public TextParsingException(String problem){
        super(problem);
    }

    public TextParsingException(String problem, Throwable cause) { super(problem, cause); }
}
