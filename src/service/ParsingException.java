package service;

import java.io.IOException;

public class ParsingException extends IOException {
    public ParsingException(String problem){
        super(problem);
    }
}
