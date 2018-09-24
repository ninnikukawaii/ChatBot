package console;

import service.AnswerProcessor;
import service.FileProcessor;
import java.io.*;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) throws IOException {
        AnswerProcessor answerProcessor = new AnswerProcessor(in, out);
        FileProcessor.printDocument("greeting.txt", new OutputStreamWriter(out));
        if (answerProcessor.processAnswer(null))
            answerProcessor.writeLine("Приятно было пообщаться с тобой!");
    }
}
