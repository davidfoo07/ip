// import java.util.ArrayList;
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
        Task[] tasks = new Task[100];
        int taskCount = 0;
        Scanner scn = new Scanner(System.in);
        String str1 = scn.nextLine();
        while (!str1.equals("bye")) {
            if (str1.equals("list")) {
                System.out.println("____________________________________________________________\n");
                
                if (tasks.length == 0) {
                    System.out.println("The list is empty.");
                }

                for (int i = 0; i < taskCount; i++) {
                    System.out.println(i + 1 + ". " + tasks[i].toString());
                };

                System.out.println("____________________________________________________________\n");

            } else {
                String[] inputArr = str1.split(" ", 2);
                if (inputArr.length == 2 && inputArr[0].equals("mark")) {
                    try {
                        // Attempt to parse the string as an integer
                        int index = Integer.parseInt(inputArr[1]);
                        if (index > 0 && index <= taskCount) {
                            Task t = tasks[index - 1];
                            if (t.getStatus()) {
                                System.out.println("____________________________________________________________\n");
                                System.out.println("This task has already been marked.");
                                System.out.println("____________________________________________________________\n");
                            } else {
                                t.setStatus();
                                System.out.println("____________________________________________________________\n" +
                                "Nice, I've marked this task as done:\n" + tasks[index - 1].toString() + "\n" +
                                "____________________________________________________________\n");
                            }
                        } else {
                            System.out.println("Index " + index +" out of bounds.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Please give me a clear instructions such as todo, deadline, event or mark. Thank you.\n");
                    }
                    
                } else if (inputArr.length == 2 && inputArr[0].equals("todo")) {
                    String description = inputArr[1].trim();
                    if (description.isEmpty()) {
                        System.out.println("____________________________________________________________\n" +
                        "The description of a todo cannot be empty or whitespace only.\n" +
                        "____________________________________________________________\n");
                    } else {
                        tasks[taskCount] = new Todo(description);
                        String echo = 
                        "____________________________________________________________\n" +
                        "Got it, I've added this task: \n" 
                        + tasks[taskCount].toString() + "\n" +
                        "Now you already have " + (++taskCount) + " tasks.\n" +
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
                        String description = tempArr[0].trim(); // Trim description to check if it's empty
                        String by = tempArr[1];

                        // Validate the description (it must not be empty or just whitespace)
                        if (description.isEmpty()) {
                            System.out.println("____________________________________________________________\n" +
                                    "The description for the deadline cannot be empty or whitespace only.\n" +
                                    "Format: deadline <description> /by <date/time>\n" +
                                    "____________________________________________________________\n");
                        }
                        // Validate the /by segment (must start with a space and have valid content)
                        else if (!by.matches("^\\s+\\S.*$")) {
                            System.out.println("____________________________________________________________\n" +
                                    "Invalid format for /by. It must be followed by a space and a valid date/time.\n" +
                                    "Format: deadline <description> /by <date/time>\n" +
                                    "____________________________________________________________\n");
                        } else {
                            by = by.trim();
                            tasks[taskCount] = new Deadline(description, by);
                            System.out.println("____________________________________________________________\n" +
                                    "Got it, I've added this task: \n"
                                    + tasks[taskCount].toString() + "\n" +
                                    "Now you already have " + (++taskCount) + " tasks.\n" +
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
                        String description = tempArr[0].trim(); // Trim the description to remove spaces
                        String fromToSegment = tempArr[1]; // Extract the part after /from

                        // Check if the description is empty after trimming
                        if (description.isEmpty()) {
                            System.out.println(
                                    "____________________________________________________________\n" +
                                    "The description of an event cannot be empty or whitespace only.\n" +
                                    "____________________________________________________________\n");
                        } else {
                            String[] fromToArr = fromToSegment.split("/to", 2);
                            if (fromToArr.length < 2 || fromToArr[0].isBlank() || fromToArr[1].isBlank()) {
                                System.out.println("____________________________________________________________\n" +
                                        "The /from and /to segments must be valid and cannot be empty.\n" +
                                        "Format: event <description> /from <start> /to <end>\n" +
                                        "____________________________________________________________\n");
                            } else {
                                String from = fromToArr[0]; // Extract /from segment
                                String to = fromToArr[1]; // Extract /to segment

                                // Validate /from and /to segments
                                if (!from.matches("^\\s+\\S.*$") || !to.matches("^\\s+\\S.*$")) {
                                    System.out.println("____________________________________________________________\n" +
                                            "Invalid format for /from or /to. They must be followed by a space and valid times.\n" +
                                            "Format: event <description> /from <start> /to <end>\n" +
                                            "____________________________________________________________\n");
                                } else {
                                    from = from.trim();
                                    to = to.trim();
                                    tasks[taskCount] = new Event(description, from, to);
                                    System.out.println("____________________________________________________________\n" +
                                            "Got it, I've added this task: \n"
                                            + tasks[taskCount].toString() + "\n" +
                                            "Now you already have " + (++taskCount) + " tasks.\n" +
                                            "____________________________________________________________\n");
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("____________________________________________________________\n" +
                                        "Please give me a clear instructions such as todo, deadline, event or mark.\n" 
                                        + "Thank you.\n" 
                                        + "____________________________________________________________\n");

                }
                // else {
                //     taskCount++;
                //     tasks[taskCount] = new Task(str1);
                //     String echo = 
                //             "_________________________________\n" +
                //             "added: " + str1 + "\n" +
                //             "_________________________________\n";
        
                //     System.out.println(echo);
                // }

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
