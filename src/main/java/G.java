import java.util.ArrayList;
import java.util.Scanner;

public class G {
    public static void main(String[] args) {
        String message = """
                ____________________________________________________________
                Hello! I'm G
                What can I do for you?
                ____________________________________________________________
                """;

        System.out.println(message);
        ArrayList<Task> tasks = new ArrayList<>(); // Replaced Task[] with ArrayList<Task>
        Scanner scn = new Scanner(System.in);
        String str1 = scn.nextLine();
        while (!str1.equals("bye")) {
            if (str1.equals("list")) {
                System.out.println("____________________________________________________________\n");

                if (tasks.isEmpty()) {
                    System.out.println("The list is empty.");
                }

                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i).toString());
                }

                System.out.println("____________________________________________________________\n");

            } else {
                String[] inputArr = str1.split(" ", 2);
                if (inputArr.length == 2 && inputArr[0].equals("mark")) {
                    try {
                        int index = Integer.parseInt(inputArr[1]) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            Task t = tasks.get(index);
                            if (t.getStatus()) {
                                System.out.println("____________________________________________________________\n");
                                System.out.println("This task has already been marked.");
                                System.out.println("____________________________________________________________\n");
                            } else {
                                t.setStatus();
                                System.out.println("____________________________________________________________\n" +
                                        "Nice, I've marked this task as done:\n" + tasks.get(index).toString() + "\n" +
                                        "____________________________________________________________\n");
                            }
                        } else {
                            System.out.println("Index " + (index + 1) + " out of bounds.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Please give me clear instructions such as todo, deadline, event, or mark. Thank you.\n");
                    }

                } else if (inputArr.length == 2 && inputArr[0].equals("todo")) {
                    String description = inputArr[1].trim();
                    if (description.isEmpty()) {
                        System.out.println("____________________________________________________________\n" +
                                "The description of a todo cannot be empty or whitespace only.\n" +
                                "____________________________________________________________\n");
                    } else {
                        tasks.add(new Todo(description));
                        String echo =
                                "____________________________________________________________\n" +
                                        "Got it, I've added this task: \n"
                                        + tasks.get(tasks.size() - 1).toString() + "\n" +
                                        "Now you already have " + tasks.size() + " tasks.\n" +
                                        "____________________________________________________________\n";
                        System.out.println(echo);
                    }

                } else if (inputArr.length == 2 && inputArr[0].equals("deadline")) {
                    String[] tempArr = inputArr[1].split("/by", 2);
                    if (tempArr.length < 2 || tempArr[1].isBlank()) {
                        System.out.println("____________________________________________________________\n" +
                                "The deadline must have a valid description and /by followed by a valid date/time.\n" +
                                "Format: deadline <description> /by <date/time>\n" +
                                "____________________________________________________________\n");
                    } else {
                        String description = tempArr[0].trim();
                        String by = tempArr[1].trim();

                        if (description.isEmpty() || by.isEmpty()) {
                            System.out.println("____________________________________________________________\n" +
                                    "The description for the deadline cannot be empty or whitespace only.\n" +
                                    "Format: deadline <description> /by <date/time>\n" +
                                    "____________________________________________________________\n");
                        } else {
                            tasks.add(new Deadline(description, by));
                            System.out.println("____________________________________________________________\n" +
                                    "Got it, I've added this task: \n"
                                    + tasks.get(tasks.size() - 1).toString() + "\n" +
                                    "Now you already have " + tasks.size() + " tasks.\n" +
                                    "____________________________________________________________\n");
                        }
                    }
                } else if (inputArr.length == 2 && inputArr[0].equals("event")) {
                    String[] tempArr = inputArr[1].split("/from", 2);
                    if (tempArr.length < 2 || tempArr[1].isBlank()) {
                        System.out.println("____________________________________________________________\n" +
                                "The event must have a valid description, /from, and /to segments.\n" +
                                "Format: event <description> /from <start> /to <end>\n" +
                                "____________________________________________________________\n");
                    } else {
                        String description = tempArr[0].trim();
                        String[] fromToArr = tempArr[1].split("/to", 2);
                        if (fromToArr.length < 2 || fromToArr[0].isBlank() || fromToArr[1].isBlank()) {
                            System.out.println("____________________________________________________________\n" +
                                    "The /from and /to segments must be valid and cannot be empty.\n" +
                                    "Format: event <description> /from <start> /to <end>\n" +
                                    "____________________________________________________________\n");
                        } else {
                            String from = fromToArr[0].trim();
                            String to = fromToArr[1].trim();

                            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                                System.out.println("____________________________________________________________\n" +
                                        "The description of an event cannot be empty or whitespace only.\n" +
                                        "____________________________________________________________\n");
                            } else {
                                tasks.add(new Event(description, from, to));
                                System.out.println("____________________________________________________________\n" +
                                        "Got it, I've added this task: \n"
                                        + tasks.get(tasks.size() - 1).toString() + "\n" +
                                        "Now you already have " + tasks.size() + " tasks.\n" +
                                        "____________________________________________________________\n");
                            }
                        }
                    }
                } else if (inputArr.length == 2 && inputArr[0].equals("delete")) {
                    try {
                        int index = Integer.parseInt(inputArr[1]) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            Task removedTask = tasks.remove(index);
                            System.out.println("____________________________________________________________\n" +
                                    "Noted. I've removed this task:\n"
                                    + "  " + removedTask.toString() + "\n"
                                    + "Now you have " + tasks.size() + " tasks in the list.\n"
                                    + "____________________________________________________________\n");
                        } else {
                            System.out.println("____________________________________________________________\n" +
                                    "Task number " + (index + 1) + " does not exist.\n" +
                                    "____________________________________________________________\n");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________\n" +
                                "Invalid format. Use: delete <task_number>.\n" +
                                "____________________________________________________________\n");
                    }
                } else {
                    System.out.println("____________________________________________________________\n" +
                            "Please give me a clear instruction such as todo, deadline, event, mark, or delete.\n"
                            + "Thank you.\n"
                            + "____________________________________________________________\n");
                }
            }

            str1 = scn.nextLine();
        }

        scn.close();

        String byeMessage = """
                ____________________________________________________________

                Bye. Hope to see you again soon!
                ____________________________________________________________

                """;
        System.out.print(byeMessage);
    }
}
