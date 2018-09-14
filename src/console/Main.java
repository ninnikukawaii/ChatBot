package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello from Java! Enter your name:");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String name = in.readLine();
        System.out.println("Hello, " + name + "!");
    }
}
