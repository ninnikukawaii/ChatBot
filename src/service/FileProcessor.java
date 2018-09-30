package service;

public class FileProcessor {
    public static String getFilePath(String fileName) {
        return System.getProperty("user.dir") + "\\" + fileName;
    }
}
