package service;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileProcessor {
    public static String getFilePath(String fileName) {
        Path path = Paths.get("resources", fileName);
        return path.toString();
    }
}
