package console;

import service.Commands;
import service.FileProcessor;

import java.io.*;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(in);
        FileProcessor.printDocument("greeting.txt", new OutputStreamWriter(out));
        while (true) {
            String str = input.nextLine().toLowerCase();
            if (str.contains(Commands.Help)) {
                FileProcessor.printHelp(new OutputStreamWriter(out));
            }
            else if (str.contains(Commands.Quiz)) {
                FileProcessor.printDocument("quizGreeting.txt", new OutputStreamWriter(out));
                Quiz quiz = new Quiz("questionsLong.txt", in, out);
                quiz.startQuiz();
            }
            else if (str.equals(Commands.Exit)) {
                break;
            }
            else {
                out.println("Кажется, я не понял тебя. Может попросишь справку?");
            }
        }
        out.println("Приятно было пообщаться с тобой!");
    }
}
