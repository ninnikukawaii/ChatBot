package service;

import java.io.*;

public class FileProcessor {
    public static String getFilePath(String fileName) {
        return System.getProperty("user.dir") + "\\" + fileName;
    }

    public static void printDocument(String filename, OutputStreamWriter out) throws IOException {
        BufferedWriter output = new BufferedWriter(out);

        filename = FileProcessor.getFilePath(filename);
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            output.write(line);
            output.newLine();
        }

        output.flush();
    }

    public static void printHelp(OutputStreamWriter out) throws IOException {
        printDocument("help.txt", out);
    }
}
