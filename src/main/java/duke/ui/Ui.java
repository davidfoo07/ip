package duke.ui;
import java.util.Scanner;

public class Ui {
    private Scanner scn;

    public Ui() {
        this.scn = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("""
                ____________________________________________________________
                Hello! I'm G
                What can I do for you?
                ____________________________________________________________
                """);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scn.nextLine().trim();
    }

    public void showLoadingError() {
        System.out.println("Loading Error.");
    }

    public void showExit() {
        System.out.print("Bye. Hope to see you again soon!\n");
    }

    public void showError(String error) {
        System.out.println("Error: " + error);
    }

    public void closeScanner() {
        scn.close();
    }
}
