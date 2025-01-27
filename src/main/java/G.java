import java.util.ArrayList;
import java.util.Scanner;

public class G {
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        String message = """
                ____________________________________________________________
                Hello! I'm G
                What can I do for you?
                ____________________________________________________________
                """;

        System.out.println(message);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scn = new Scanner(System.in);
        String str1 = scn.nextLine();
        while (!str1.equals("bye")) {
            if (str1.equals("list")) {
                System.out.println("_________________________________\n");
                
                if (tasks.isEmpty()) {
                    System.out.println("The list is empty.");
                }

                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + "[" + tasks.get(i).getStatusIcon() + "]" + " " + tasks.get(i).getDescription());
                };

                System.out.println("_________________________________\n");


            } else {
                String[] inputArr = str1.split("[\\s]");
                if (inputArr.length == 2 && inputArr[0].equals("mark")) {
                    try {
                        // Attempt to parse the string as an integer
                        int index = Integer.parseInt(inputArr[1]);
                        if (index > 0 && index <= tasks.size()) {
                            Task t = tasks.get(index - 1);
                            if (t.getStatus()) {
                                System.out.println("_________________________________\n");
                                System.out.println("This task has already been marked.");
                                System.out.println("_________________________________\n");
                            } else {
                                t.setStatus();
                                System.out.println("_________________________________\n" +
                                "Nice, I've marked this task as done:\n" + "[X]" + " " + tasks.get(index - 1).getDescription());
                            }
                        } else {
                            System.out.println("Index " + index +" out of bounds.");
                        }

                    } catch (NumberFormatException e) {
                        // If parsing fails, print "String"
                        tasks.add(new Task(str1));
                        String echo = 
                                "_________________________________\n" +
                                "added: " + str1 + "\n" +
                                "_________________________________\n";
            
                        System.out.println(echo);
                    }
                    
                } else {
                    tasks.add(new Task(str1));
                    String echo = 
                            "_________________________________\n" +
                            "added: " + str1 + "\n" +
                            "_________________________________\n";
        
                    System.out.println(echo);
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
