package logger.model;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileOperation implements LoggerFileOperation {
    private String fileName;

    public FileOperation(String fileName) {
        this.fileName = fileName;
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean saveLine(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
