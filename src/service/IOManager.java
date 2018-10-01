package service;

import java.io.*;

public class IOManager {

    private BufferedReader inputReader;
    private BufferedWriter outputWriter;

    public IOManager(InputStream inputStream, OutputStream outputStream) {
        inputReader = new BufferedReader(new InputStreamReader(inputStream));
        outputWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    public String readLine() throws IOException {
        return inputReader.readLine();
    }

    public void writeLine(String line) throws IOException {
        outputWriter.write(line);
        outputWriter.newLine();
        outputWriter.flush();
    }

    public void writeLines(String[] lines) throws IOException {
        String line = String.join("\n\n", lines);
        writeLine(line);
    }
}
