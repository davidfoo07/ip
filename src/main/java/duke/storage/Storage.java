package duke.storage;
import java.io.*;
import java.util.ArrayList;

import duke.tasks.*;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        File directory = new File(file.getParent());

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            if (!directory.exists()) {
                directory.mkdirs();
            }
        } catch (IOException e) {
            System.out.println("Error intializing storage file.");
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T" :
                        tasks.add(new Todo(parts[2], parts[1].equals("1")));
                        break;
                    case "D":
                        tasks.add(new Deadline(parts[2], parts[3], parts[1].equals("1")));
                        break;
                    case "E":
                        tasks.add(new Event(parts[2], parts[3], parts[4]));
                        break;
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        } 
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath))){
            for (Task task: tasks) {
                bw.write(task.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }




}
