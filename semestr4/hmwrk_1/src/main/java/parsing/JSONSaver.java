package parsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Tree;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JSONSaver {
    static JSONSaver jsonSaver;
    ObjectMapper mapper = new ObjectMapper();

    private JSONSaver() {
    }

    public static JSONSaver getInstance() {
        return jsonSaver == null ? jsonSaver = new JSONSaver() : jsonSaver;
    }

    public void writeInFile(Tree tree) {
        try {
            File file = new File("Tree.json");
            if (!file.exists())
                file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            String str = write(tree);
            System.out.println(str);
            fileWriter.write(str);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Tree readFromFile() {
        try {
            File file = new File("Tree.json");
            Scanner sc = new Scanner(file);
            StringBuilder stringBuilder = new StringBuilder();
            while (sc.hasNextLine()) {
                stringBuilder.append(sc.nextLine());
            }
            return read(stringBuilder.toString());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Tree read(String str) {
        try {
            return mapper.readValue(str, Tree.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String write(Tree tree) {
        try {
            return mapper.writeValueAsString(tree);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
