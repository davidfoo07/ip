package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
   protected LocalDate by;

   /**
    * 
    * @param var1
    * @param var2
    */
   public Deadline(String var1, String var2) {
      super(var1);
      this.by = LocalDate.parse(var2.trim());
   }

   /**
    * 
    * @param var1
    * @param var2
    * @param var3
    */
   public Deadline(String var1, String var2, boolean var3) {
      super(var1, var3);
      this.by = LocalDate.parse(var2.trim());
   }

   public String toString() {
      String var10000 = this.isDone ? "X" : " ";
      return "[D][" + var10000 + "] " + this.description + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
   }

   public String toFileString() {
      String var10000 = this.isDone ? "1" : "0";
      return "D | " + var10000 + " | " + this.description + " | " + this.by;
   }
}
