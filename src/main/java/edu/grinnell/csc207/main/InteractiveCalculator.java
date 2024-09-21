package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;
import edu.grinnell.csc207.util.BigFraction;
import java.io.PrintWriter;
import java.util.Scanner;

/** InteractiveCalculator class. */
public class InteractiveCalculator {
  /** size of the operation. */
  public static final int SIZE_OF_REGISTER = 1;

  /** fail boolean. */
  private static boolean fail;

  /** size of the store command. */
  public static final int SIZE_OF_STORE_FRACTION = 2;

  /** Oprators array. */
  public static final String[] OPERATORS = {"+", "-", "*", "/"};

  /**
   * Checks if the string is a fraction.
   *
   * @param str string
   * @return true if the string is a fraction
   */
  public static boolean isFraction(String str) {
    String[] partOfFraction = str.split("/");
    if (partOfFraction.length != SIZE_OF_STORE_FRACTION) {
      return false;
    } // end of if
    try {
      int num = Integer.parseInt(partOfFraction[0]);
      int denom = Integer.parseInt(partOfFraction[1]);
      if (denom == 0) {
        return false;
      } // end of if
    } catch (Exception e) {
      return false;
    } // end if try
    return true;
  } // isFraction(String str)

  /**
   * Checks if the string is an integer.
   *
   * @param str string
   * @return true if the string was an intger value
   */
  public static boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (Exception e) {
      return false;
    } // end of try
  } // isInteger(String str)

  /**
   * Checks if the charachter is lowercase.
   *
   * @param c charachter
   * @return true if charachter is lowercase
   */
  public static boolean isLowercase(char c) {
    return c >= 'a' && c <= 'z';
  } // isLowercase(cahr c)

  /**
   * Checks if the string is single a charachter.
   *
   * @param str string
   * @return true if string is one charachter string
   */
  public static boolean isCharacter(String str) {
    return str.length() == SIZE_OF_REGISTER && isLowercase(str.charAt(0));
  } // isCharacter(String str)

  /**
   * Checks if the operation is valid.
   *
   * @param str string
   * @return true if the string is one of the operation in the OPERATIONS array
   */
  public static boolean isValidOperator(String str) {
    // got through each value in OPERATORS array
    for (String opr : OPERATORS) {
      if (str.equals(opr)) {
        return true;
      } // end of
    } // end of loop
    return false;
  } // isValidOperator(String str)

  /**
   * Chaeks if the string is valid Operand.
   *
   * @param str string
   * @return true if string is either an integer, or charachter or fraction
   */
  public static boolean isValidOperand(String str) {
    return isInteger(str) || isCharacter(str) || isFraction(str);
  } // isValidOperand(String str)

  /**
   * Checks if the array of the string a valid equation.
   *
   * @param str array of strings
   * @return true if equation is valid
   */
  public static boolean isValidEquation(String[] str) {
    if (str.length == 0 || str.length % 2 == 0) {
      return false;
    } // end if
    // for loop that goes through the array
    for (int i = 0; i < str.length; i++) {
      if (i % 2 == 0) {
        // if valid operand increase the count by 1
        if (!isValidOperand(str[i])) {
          return false;
        } // end of if
      } else {
        // if valid operator increase the count by 1
        if (!isValidOperator(str[i])) {
          return false;
        } // end of if
      } // end of if
    } // end of loop
    // if count equals to the length of the string array
    return true;
  } // isValidEquation(String[] str)

  /**
   * Add, or Subtracts, or Multiplyes, or Divide or if passed wrong operator send an error.
   *
   * @param obj BFCalculator object
   * @param operator string that represents one of the operations
   * @param next BigFraction
   */
  public static void operations(BFCalculator obj, String operator, BigFraction next) {
    switch (operator) {
      case "+":
        obj.add(next);
        break;
      case "-":
        obj.subtract(next);
        break;
      case "*":
        obj.multiply(next);
        break;
      case "/":
        obj.divide(next);
        break;
      default:
        break;
    } // end of switch
  } // Operations(BFCalculator obj, String operator, BigFraction next)

  /**
   * Checks if an array of strings represents a STORE command.
   *
   * @param str array of strings
   * @return true if the length of the meassage is two and first string is equal to STORE and second
   *     string is a single lowercase character string
   */
  public static boolean isStore(String[] str) {
    return str.length == SIZE_OF_STORE_FRACTION
        && str[0].equals("STORE")
        && str[1].charAt(0) >= 'a'
        && str[1].charAt(0) <= 'z'
        && isCharacter(str[1]);
  } // isStore(String[] str)

  /**
   * Calculates BigFraction based on the string(fraction or integer or charachter).
   *
   * @param reg BFRegisterSet object
   * @param str string
   * @return a BigFraction based on the stirng input
   */
  public static BigFraction assign(BFRegisterSet reg, String str) {
    if (isInteger(str)) {
      int val = Integer.parseInt(str);
      return BigFraction.int2fraction(val);
    } else if (isCharacter(str)) {
      BigFraction res = reg.get(str.charAt(0));
      // if charachter in the register not null
      if (res != null) {
        return res;
      } else {
        return null;
      } // end of if
    } else {
      return new BigFraction(str);
    } // end of if
  } // Assign(BFRegister reg, String str)

  /**
   * Main method.
   *
   * @param args is an array of strings
   */
  public static void main(String[] args) {
    // input object
    Scanner eyes = new Scanner(System.in);
    // output object
    PrintWriter pen = new PrintWriter(System.out, true);
    // register object
    BFRegisterSet register = new BFRegisterSet();
    // calculator object
    BFCalculator calculator = new BFCalculator();
    // loop until user QUIT
    while (true) {
      fail = false;
      String arg = eyes.nextLine();
      if (arg.equals("QUIT")) {
        break;
      } // exits the program
      String[] values = arg.split(" ");
      // work horse if statement, cheks if the command is to store or to perform
      // operations
      if (isStore(values)) {
        // if there is no previous value to store we return an error, otherwiese we
        // store the value
        if (!calculator.get().toString().equals("0")) {
          register.store(values[1].charAt(0), calculator.get());
          pen.println("STORED");
        } else {
          System.err.println("*** Error [No value to store] ***");
        } // end of if
      } else if (isValidEquation(values)) {
        BigFraction current = assign(register, values[0]);
        // checks if the user was trying to access the value from the register and no
        // value was stored there
        if (current == null) {
          System.err.println("*** Error [No value is store in the register " + values[0] + "] ***");
          continue;
        } // end of if
        // setting last computed value to the first value in the array
        calculator.setLastValue(current);
        // calculating the equation
        for (int i = 1; i < values.length - 1; i += 2) {
          // checks if the user was trying to access the value from the register and no
          // value was stored there
          BigFraction next = assign(register, values[i + 1]);
          if (next == null) {
            calculator.clear();
            fail = true;
            System.err.println(
                "*** Error [No value is store in the register " + values[i + 1] + "] ***");
            break;
          } // end of if
          operations(calculator, values[i], next);
        } // end of loop
        // if user was tryin acces the value from register but no value was in it
        // we dont print
        if (!fail) {
          pen.println(calculator.get().toString());
        } // end of if
      } else {
        System.err.println("*** Error [Invalid expression] ***");
      } // end of if
    } // end of while
    pen.close();
    eyes.close();
  } // main(String[] args)
} // InteractiveCalculator
