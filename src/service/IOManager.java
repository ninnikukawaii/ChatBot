package service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class IOManager {

    public static String getFilePath(String fileName) {
        Path path = Paths.get("resources", fileName);
        return path.toString();
    }

    private BufferedReader inputReader;
    private BufferedWriter outputWriter;

    public IOManager(InputStream inputStream, OutputStream outputStream) {
        inputReader = new BufferedReader(new InputStreamReader(inputStream));
        outputWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public String readLine() throws IOException {
        return inputReader.readLine();
    }

    private void writeLine(String line) throws IOException {
        outputWriter.write(line);
        outputWriter.newLine();
        outputWriter.flush();
    }

    public void writeLines(List<String> lines) throws IOException {
        String line = String.join("\n", lines);
        writeLine(line);
    }
}
