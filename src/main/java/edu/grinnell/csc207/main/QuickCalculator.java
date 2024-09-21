package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;
import java.io.PrintWriter;

/** QuickCalculator class. */
public class QuickCalculator {
  /** fail boolean. */
  private static boolean fail;

  /**
   * Main method.
   *
   * @param args an array of Strings
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    // register object
    BFRegisterSet register = new BFRegisterSet();
    // calculator object
    BFCalculator calculator = new BFCalculator();
    // if command line empty return the program;
    if (args.length == 0) {
      return;
    } // end of if
    for (int i = 0; i < args.length; i++) {
      String arg = args[i];
      String[] values = arg.split(" ");
      // work horse if statement, cheks if the command is to store or to perform
      // operations
      if (InteractiveCalculator.isStore(values)) {
        // if there is no previous value to store we return an error, otherwiese we
        // store the value
        if (!calculator.get().toString().equals("0")) {
          register.store(values[1].charAt(0), calculator.get());
          pen.println(arg + " -> STORED");
        } else {
          System.err.println("*** Error [No value to store] ***");
        } // end of if
      } else if (InteractiveCalculator.isValidEquation(values)) {
        BigFraction current = InteractiveCalculator.assign(register, values[0]);
        // checks if the user was trying to access the value from the register and no
        // value was stored there
        if (current == null) {
          System.err.println("*** Error [No value is store in the register " + values[0] + "] ***");
          continue;
        } // end of if
        // setting last computed value to the first value in the array
        calculator.setLastValue(current);
        // calculating the equation
        for (int index = 1; index < values.length - 1; index += 2) {
          // checks if the user was trying to access the value from the register and no
          // value was stored there
          BigFraction next = InteractiveCalculator.assign(register, values[index + 1]);
          if (next == null) {
            calculator.clear();
            fail = true;
            System.err.println(
                "*** Error [No value is store in the register " + values[index + 1] + "] ***");
            break;
          } // end of if
          InteractiveCalculator.operations(calculator, values[index], next);
        } // end of loop
        // if user was tryin acces the value from register but no value was in it
        // we dont print
        if (!fail) {
          pen.println(arg + " -> " + calculator.get().toString());
        } // end of if
      } else {
        System.err.println(arg + " FAILED [Invalid expression]");
      } // end of if
    } // end of while
    pen.close();
  } // main(String[] args)
} // QuickCalculator
