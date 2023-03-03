package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class DefaultFileOperation implements FileOperation {
    private String fileName;

    public DefaultFileOperation(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String> readAllLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (line != null) {
                     lines.add(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

    @Override
    public void saveAllLines(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
            for (String line : lines) {
                writer.write(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
