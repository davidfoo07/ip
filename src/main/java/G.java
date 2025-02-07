import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class G {
    private static final String FILE_PATH = System.getProperty("user.home") + "/Documents/CS2103T/tasks.txt";

    public static void main(String[] args) {
        String message = """
                ____________________________________________________________
                Hello! I'm G
                What can I do for you?
                ____________________________________________________________
                """;

        System.out.println(message);

        ArrayList<Task> tasks = loadTasksFromFile(); // Load tasks from file
        Scanner scn = new Scanner(System.in);
        String str1 = scn.nextLine().trim();

        while (!str1.equals("bye")) {
            if (str1.equals("list")) {
                printTaskList(tasks);

            } else {
                String[] inputArr = str1.split(" ", 2);
                if (inputArr.length < 2) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Invalid command. Please provide enough arguments.");
                    System.out.println("____________________________________________________________");
                } else {
                    String command = inputArr[0];
                    String details = inputArr[1].trim();

                    if (command.equals("todo")) {
                        tasks.add(new Todo(details));
                        System.out.println("Added task: " + details);
                    } else if (command.equals("deadline")) {
                        String[] parts = details.split("/by", 2);
                        if (parts.length == 2) {
                            try {
                                tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
                                System.out.println("Added deadline: " + parts[0].trim());
                            } catch (Exception e) {
                                System.out.println("Invalid date format. Use yyyy-mm-dd.");
                            }
                        } else {
                            System.out.println("Invalid format. Use: deadline <task> /by yyyy-mm-dd.");
                        }
                    } else if (command.equals("event")) {
                        String[] parts = details.split("/from", 2);
                        if (parts.length == 2) {
                            String[] timeParts = parts[1].split("/to", 2);
                            if (timeParts.length == 2) {
                                try {
                                    tasks.add(new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
                                    System.out.println("Added event: " + parts[0].trim());
                                } catch (Exception e) {
                                    System.out.println("Invalid date format. Use yyyy-mm-dd.");
                                }
                            } else {
                                System.out.println("Invalid format. Use: event <task> /from yyyy-mm-dd /to yyyy-mm-dd.");
                            }
                        } else {
                            System.out.println("Invalid format. Use: event <task> /from yyyy-mm-dd /to yyyy-mm-dd.");
                        }
                    } else if (command.equals("delete")) {
                        try {
                            int index = Integer.parseInt(details) - 1;
                            if (index >= 0 && index < tasks.size()) {
                                Task removedTask = tasks.remove(index);
                                System.out.println("Deleted task: " + removedTask);
                            } else {
                                System.out.println("Task number " + (index + 1) + " does not exist.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid format. Use: delete <task_number>.");
                        }
                    } else if (command.equals("mark")) {
                        try {
                            int index = Integer.parseInt(details) - 1;
                            if (index >= 0 && index < tasks.size()) {
                                Task t = tasks.get(index);
                                if (t.getStatus()) {
                                    System.out.println("____________________________________________________________");
                                    System.out.println("This task has already been marked as done.");
                                    System.out.println("____________________________________________________________");
                                } else {
                                    t.setStatus(true); // Mark as done
                                    System.out.println("____________________________________________________________");
                                    System.out.println("Nice! I've marked this task as done:");
                                    System.out.println("  " + t);
                                    System.out.println("____________________________________________________________");
                                }
                            } else {
                                System.out.println("Task number " + (index + 1) + " does not exist.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid format. Use: mark <task_number>.");
                        }
                    } else if (command.equals("unmark")) {
                        try {
                            int index = Integer.parseInt(details) - 1;
                            if (index >= 0 && index < tasks.size()) {
                                Task t = tasks.get(index);
                                if (!t.getStatus()) {
                                    System.out.println("____________________________________________________________");
                                    System.out.println("This task is already marked as not done.");
                                    System.out.println("____________________________________________________________");
                                } else {
                                    t.setStatus(false); // Mark as not done
                                    System.out.println("____________________________________________________________");
                                    System.out.println("OK, I've marked this task as not done yet:");
                                    System.out.println("  " + t);
                                    System.out.println("____________________________________________________________");
                                }
                            } else {
                                System.out.println("Task number " + (index + 1) + " does not exist.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid format. Use: unmark <task_number>.");
                        }
                    }

                }
            }
            str1 = scn.nextLine().trim();
        }

        scn.close();
        saveTasksToFile(tasks); // Save tasks before exiting

        System.out.print("""
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """);
    }

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        File directory = new File(file.getParent()); // Get the directory

        try {
            // Create the directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
                return tasks; // Return empty task list
            }

            // Read file content if it exists
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");

                if (parts.length < 3) {  // Ensure at least task type, status, and description exist
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                switch (parts[0]) {
                    case "T":
                        tasks.add(new Todo(parts[2], parts[1].equals("1")));
                        break;
                    case "D":
                        if (parts.length < 4) {
                            System.out.println("Skipping malformed deadline entry: " + line);
                            continue;
                        }
                        tasks.add(new Deadline(parts[2], parts[3], parts[1].equals("1")));
                        break;
                    case "E":
                        if (parts.length < 5) {
                            System.out.println("Skipping malformed event entry: " + line);
                            continue;
                        }
                        tasks.add(new Event(parts[2], parts[3], parts[4], parts[1].equals("1")));
                        break;
                    default:
                        System.out.println("Skipping unknown task type: " + line);
                }

            }

            br.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file.");
        }

        return tasks;
    }


    private static void saveTasksToFile(ArrayList<Task> tasks) {
        File file = new File(FILE_PATH);
        File directory = new File(file.getParent()); // Get the directory

        try {
            // Ensure directory exists
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Ensure file exists
            if (!file.exists()) {
                file.createNewFile();
            }

            // Write tasks to file
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                bw.write(task.toFileString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }


    // ✅ Print task list
    private static void printTaskList(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }
}
