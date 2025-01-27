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
        Scanner scn = new Scanner(System.in);
        String str1 = scn.nextLine();

        while (!str1.equals("bye")) {
            String echo = 
                    "_________________________________\n" +
                    str1 + "\n" +
                    "_________________________________\n";

            System.out.println(echo);
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
